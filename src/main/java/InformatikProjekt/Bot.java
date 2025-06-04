//Programmierer: Tim
package InformatikProjekt;

import java.util.ArrayList;
import java.util.Random;

public class Bot extends Mitspieler {
    BotModel model;

    public Bot() {
        model = new BotModel();
    }

    @Override
    public SpielArt spielabsichtFragen(SpielArt hoechsteSpiel) {
        int[] besondereKarten = model.wieVieleBesondereKarten();
        int anzahlOU = besondereKarten[0] + besondereKarten[1];

        int indexFarbeMitMeistenKarten = besondereKarten[3];

        for (int i = 3; i <= 6; i++) {
            if (besondereKarten[indexFarbeMitMeistenKarten] < besondereKarten[i]) {
                indexFarbeMitMeistenKarten = i;
            }

        }
        //Außerhalb des Zieles des Prototypes
        /*
        //Solospiel wenn mindestens mehr als 4 Truempfe und mindestendes 2 Karten einer Farbe(keine Truempfe).
        if (anzahlOU >= 4 &&besondereKarten[indexFarbeMitMeistenKarten] >= 2 && hoechsteSpiel.gebeSpielArtID() < SpielArt.SOLO.gebeSpielArtID()){
            Farbe[] farben = new Farbe[4];
            farben[0] = Farbe.HERZ;
            farben[1] = Farbe.GRAS;
            farben[2] = Farbe.EICHEL;
            farben[3] = Farbe.SCHELLEN;
            model.setzteSoloFarbe(farben[indexFarbeMitMeistenKarten -3]);
            return SpielArt.SOLO;
        }

        //Wenz wenn mindestens 3 Unter und 2 Asse
        if (besondereKarten[1] >=3 && besondereKarten[2] >=2 && hoechsteSpiel.gebeSpielArtID() < SpielArt.WENZ.gebeSpielArtID()){
            return SpielArt.WENZ;
        }
        */
        ArrayList<Farbe> farbenZumAusrufen = sauZumAusrufen(model.gibHand());
        //Sauspiel, wenn eine Sau ausgerufen werden kann und mindestens 5 Truempfe auf der Hand.
        if (!farbenZumAusrufen.isEmpty() && anzahlOU + besondereKarten[4] >= 5 && hoechsteSpiel.gebeSpielArtID() < SpielArt.SAUSPIEL.gebeSpielArtID()) {
            model.setzteSau(new Spielkarte(farbenZumAusrufen.get(0), Werte.SAU));
            return SpielArt.SAUSPIEL;
        }


        return SpielArt.KEINSPIEL;
    }


    @Override
    public Farbe farbeFuerSpielAbsicht(SpielArt spielArt) {
        switch (spielArt) {
            case KEINSPIEL:
                break;
            case SAUSPIEL:
                return model.gibSau().gebeFarbe();

            case WENZ:
                break;
            case SOLO:
                return model.gibsoloFarbe();

        }
        return null;
    }


    @Override
    public Spielkarte legeEineKarte() {
        ArrayList<Spielkarte> moeglicheKarten = new ArrayList<>();
        //Wenn keine Karten auf dem Tisch liegen können alle Karten gespielt werden.
        if (model.gibGelegteKartenAnzahl() == 0) {
            moeglicheKarten = erlaubteKartenAusspielenBeiSauspiel(model.gibHand(), model.gibSau());
        } else {
            moeglicheKarten = gibErlaubteKarten((ArrayList<Spielkarte>) model.gibHand().clone(), model.gibSpielArt(), model.gibSau(), model.gibErsteKarteAufTisch(), model.gibsoloFarbe(), model.gebeSauFarbeVorhandGespielt());
        }
        Spielkarte gewaelteKarte = null;
        switch (model.gibSpielArt()) {
            case KEINSPIEL:
                break;
            case SAUSPIEL:
                gewaelteKarte = sauSpielKarteWaehlen(moeglicheKarten);
            case WENZ:
                break;
            case SOLO:
                break;
        }

        model.entferneKarteAusHand(gewaelteKarte);
        return gewaelteKarte;

    }


    public Spielkarte sauSpielKarteWaehlen(ArrayList<Spielkarte> erlaubteKarten) {
        Random zufall = new Random();
        int zufaelligerIndex = zufall.nextInt(erlaubteKarten.size());
        return erlaubteKarten.get(zufaelligerIndex);

        //if (model.gibWertFuerBisherGelegteKarten() > 20) {
        //todo lege hohen Trumpf
        //}
        //todo Spieltips nochmal anschauen
        //todo wenn Teammitglied hohen Trump hat dann Schmieren
        //todo wenn gegner keine Trümpe und andere Farben mehr haben dann diese Farben legen
        //todo Wenn stich nicht gewinnen kann dan niedrige Werte

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
        //Nachdem die Farbe der gesuchten Sau gespielt wird, darf die gesuchte,  wie jede andere Karte einer Farbe frei gespielt werden.
        if (model.gibGelegteKartenAnzahl() == 0 && model.gibSau().gebeFarbe() == karte.gebeFarbe() && !karte.istTrumpf(model.gibSpielArt(), model.gibsoloFarbe())) {
            model.setzeSauFarbeVorhandGespielt(true);
        }
        model.fuegeGelegteKarteHinzu(karte);
        model.fuegeSpielerNummerGelegteKarteHinzu(spielerHatGelegt);
        //Legt Mitspieler fest, wenn die gesuchte Sau ausgerufen wird.
        if (model.gibSau() == karte) {
            if (model.gibSpielerHatSauAusgerufen() == model.gibSpielerIndex()) {
                model.setzteTeamSpieler(spielerHatGelegt);
            } else {
                model.setzteTeamSpieler(6 - (spielerHatGelegt + model.gibSpielerHatSauAusgerufen() + model.gibSpielerIndex()));
            }
        }

        //Todo abspeichern welche Karten die Mitspieler noch haben
        //Todo bei Spielart Unterscheiden --> in mehrere Methoden aufteilen
        /*
        if (model.gibErsteKarteAufTisch().gebeWert() != Werte.OBER) {
            model.setzteMitspielerDaten(null, Werte.OBER, false, spielerHatGelegt);
        }
        */


    }


    @Override
    public void stichGewonnen(int spieler) {
        model.spielerNummerGelegteKarteZuruecksetzen();
    }

    @Override
    public int gebeMitspieler() {
        return -1;
    }

    @Override
    public void rundeGewonnen(int[] gewinner, int[] punkte) {
        //Werte vom Model zurücksetzen bei einer neuen Runde
        model = new BotModel();
    }

    @Override
    public void spielerHatSpielabsichtGesagt(SpielArt spielAbsicht, int spieler) {

    }

    //Für Tests
    public int gibAnzahlKartenInHand() {
        return model.gibHand().size();
    }

    public int gibWertFuerBisherGelegteKarten() {
        return model.gibWertFuerBisherGelegteKarten();
    }
}
