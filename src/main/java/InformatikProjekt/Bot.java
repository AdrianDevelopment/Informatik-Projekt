package InformatikProjekt;

import java.util.ArrayList;
import java.util.Random;

public class Bot extends Mitspieler {
    private BotModel model;

    public Bot() {
        model = new BotModel();
    }

    @Override
    public SpielArt spielabsichtFragen(SpielArt hoechsteSpiel) {
        int[] besondereKarten = wieVieleBesondereKarten();
        int anzahlOU = besondereKarten[0] + besondereKarten[1]; // Ober und Unter zählen
        int indexFarbeMitMeistenKarten = findeFarbeMitMeistenKarten(besondereKarten);

        ArrayList<Farbe> farbenZumAusrufen = sauZumAusrufen(model.gibHand());

        if (!farbenZumAusrufen.isEmpty() && anzahlOU + besondereKarten[4] >= 5 &&
                hoechsteSpiel.gebeSpielArtID() < SpielArt.SAUSPIEL.gebeSpielArtID()) {
            model.setzteSau(new Spielkarte(farbenZumAusrufen.get(0), Werte.SAU));
            return SpielArt.SAUSPIEL;
        }

        return SpielArt.KEINSPIEL;
    }

    @Override
    public Farbe farbeFuerSpielAbsicht(SpielArt spielArt) {
        if (spielArt == SpielArt.SAUSPIEL) {
            return model.gibSau().gebeFarbe();
        }
        return null; // Standardwert für Nicht-Sauspiel
    }

    @Override
    public void spielerHatSpielabsichtGesagt(SpielArt spielAbsicht, int spieler) {

    }

    @Override
    public Spielkarte legeEineKarte() {
        ArrayList<Spielkarte> moeglicheKarten = model.gibStichGelegteKartenAnzahl() == 0
                ? erlaubteKartenAusspielenBeiSauspiel(model.gibHand(), model.gibSau())
                : gibErlaubteKarten(
                        new ArrayList<>(model.gibHand()),
                        model.gibSpielArt(),
                        model.gibSau(),
                        model.gibErsteKarteAufTisch(),
                        model.gibsoloFarbe(),
                        model.gibSauFarbeVorhandGespielt()
                );

        // Wählt eine Karte abhängig von der Spielart
        Spielkarte gewaelteKarte = waehleKarteAbhaengigVonSpielart(moeglicheKarten);

        // Entfernt die gelegte Karte aus der Hand
        model.entferneKarteAusHand(gewaelteKarte);

        return gewaelteKarte;
    }

    private Spielkarte waehleKarteAbhaengigVonSpielart(ArrayList<Spielkarte> erlaubteKarten) {
        if (model.gibSpielArt() == SpielArt.SAUSPIEL) {
            return sauSpielKarteWaehlen(erlaubteKarten);
        }
        return zufaelligeKarteAuswaehlen(erlaubteKarten);
    }

    private Spielkarte sauSpielKarteWaehlen(ArrayList<Spielkarte> erlaubteKarten) {
        int[] besondereKarten = wieVieleBesondereKarten();

        if (gibWertFuerBisherGelegteKarten() > 20) {
            // Beispielspielzüge, basierend auf Spielsituation
        }
        return zufaelligeKarteAuswaehlen(erlaubteKarten);
    }

    private Spielkarte zufaelligeKarteAuswaehlen(ArrayList<Spielkarte> erlaubteKarten) {
        return erlaubteKarten.get(new Random().nextInt(erlaubteKarten.size()));
    }

    @Override
    public void rundeStarten(ArrayList<Spielkarte> karten, int spielerIndex) {
        model.setzteHand(karten);
        model.setzeSpielerIndex(spielerIndex);
    }

    @Override
    public void spielArtEntschieden(int spieler, Farbe farbe, SpielArt spielArt) {
        model.setzeSpielerHatSauAusgerufen(spieler);
        model.setzteSau(new Spielkarte(farbe, Werte.SAU));
        model.setzteSpielArt(spielArt);
        model.setzteSoloFarbe(farbe);
    }

    @Override
    public void karteWurdeGelegt(Spielkarte karte, int spielerHatGelegt) {
        if (model.gibStichGelegteKartenAnzahl() == 0
                && model.gibSau().gebeFarbe() == karte.gebeFarbe()
                && !karte.istTrumpf(model.gibSpielArt(), model.gibsoloFarbe())) {
            model.setzeSauFarbeVorhandGespielt(true);
        }

        model.fuegeGelegteKarteHinzu(karte);
        model.fuegeSpielerNummerGelegteKarteHinzu(spielerHatGelegt);

        if (model.gibSau().equals(karte)) {
            model.setzteTeamSpieler(model.gibSpielerHatSauAusgerufen() == model.gibSpielerIndex()
                    ? spielerHatGelegt
                    : 6 - (spielerHatGelegt + model.gibSpielerHatSauAusgerufen() + model.gibSpielerIndex()));
        }
    }

    @Override
    public void stichGewonnen(int spieler) {
        model.spielerNummerGelegteKarteZuruecksetzen();
    }

    @Override
    public int gebeMitspieler() {
        return -1; // Standardwert
    }

    @Override
    public void rundeGewonnen(int[] gewinner, int[] punkte) {
        model = new BotModel(); // Zurücksetzen bei Rundenabschluss
    }

    // Hilfsmethoden
    private int findeFarbeMitMeistenKarten(int[] besondereKarten) {
        int index = 3; // Start bei Farben
        for (int i = 3; i <= 6; i++) {
            if (besondereKarten[index] < besondereKarten[i]) {
                index = i;
            }
        }
        return index;
    }

    public int gibWertFuerBisherGelegteKarten() {
        return model.gibAlleGelegteKarten().stream()
                .skip(model.gibAlleGelegteKarten().size() - model.gibStichGelegteKartenAnzahl())
                .mapToInt(karte -> karte.gebeWert().gebePunktzahl())
                .sum();
    }

    public int[] wieVieleBesondereKarten() {
        int anzahlOber = 0, anzahlUnter = 0, anzahlSau = 0;
        int anzahlHerz = 0, anzahlGras = 0, anzahlEichel = 0, anzahlSchellen = 0;

        for (Spielkarte karte : model.gibHand()) {
            if (karte.gebeWert() == Werte.OBER) {
                anzahlOber++;
            } else if (karte.gebeWert() == Werte.UNTER) {
                anzahlUnter++;
            } else if (karte.gebeWert() == Werte.SAU) {
                anzahlSau++;
            }

            switch (karte.gebeFarbe()) {
                case SCHELLEN -> anzahlSchellen++;
                case HERZ -> anzahlHerz++;
                case GRAS -> anzahlGras++;
                case EICHEL -> anzahlEichel++;
            }
        }

        return new int[]{anzahlOber, anzahlUnter, anzahlSau, anzahlHerz, anzahlGras, anzahlEichel, anzahlSchellen};
    }

    public int gibAnzahlKartenInHand() {
        return model.gibHand().size();
    }

    public int gibTeamSpieler() {
        return model.gibTeamSpieler();
    }

    public void keinSpiel() {
        // Keine Aktion für Bot erforderlich
    }
}