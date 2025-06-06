package InformatikProjekt;

import javax.security.auth.Refreshable;
import java.util.ArrayList;

public class Spieler extends Mitspieler {
    private SpielerModel model;
    private CLI cli;

    public Spieler() {
        model = new SpielerModel();
    }

    public void setzeCLI(CLI cli) {
        this.cli = cli;
    }

    @Override
    public void rundeStarten(ArrayList<Spielkarte> karten, int wieVielterSpieler) {
        model.setzeHandkarten(karten);
        model.setzeWelcherSpieler(wieVielterSpieler);
        cli.rundeStarten(wieVielterSpieler);
        cli.zeigeHandkarten(karten);
    }

    @Override
    public SpielArt spielabsichtFragen(SpielArt hoechstesSpiel) {
        SpielArt spielArt = cli.spielabsichtFragen(hoechstesSpiel);
        model.setzeSpielabsicht(spielArt);
        return spielArt;
    }

    public void spielerHatSpielabsichtGesagt(SpielArt spielAbsicht, int spieler) {
        WelcherSpieler welcherSpieler = wieVielterSpieler(spieler);
        model.setzeWelcherSpieler(spieler);
        cli.spielerHatSpielerabsichtGesagt(spielAbsicht, welcherSpieler);
    }

    @Override
    public Farbe farbeFuerSpielAbsicht(SpielArt spielArt) {
        Farbe spielabsichtFarbe = cli.farbeFuerSpielAbsicht();
        ArrayList<Farbe> erlaubteFarben = sauZumAusrufen(model.gebeHandkarten());

        if (erlaubteFarben.contains(spielabsichtFarbe)) {
//            cli.spielerHatFarbeGesagt(spielabsichtFarbe, wieVielterSpieler(model.gebeWelcherSpieler()));
            return spielabsichtFarbe;
        }

        cli.ungueltigeEingabe("Du kannst auf diese Sau nicht ausrufen.");
        return farbeFuerSpielAbsicht(spielArt);
    }

    public void spielabsichtGUI(SpielArt spielabsicht) {
        model.setzeSpielabsicht(spielabsicht);
    }

    public void farbeFuerSpielAbsichtGUI(Farbe farbe) {
        model.setzeSpielabsichtFarbe(farbe);
    }

    @Override
    public void spielArtEntschieden(int spieler, Farbe farbe, SpielArt spielArt) {
        WelcherSpieler welcherSpieler = wieVielterSpieler(spieler);
        model.setzeSpielArt(welcherSpieler, spielArt, farbe, spieler);
        cli.spielerHatFarbeGesagt(farbe, welcherSpieler);
    }

    @Override
    public Spielkarte legeEineKarte() {
        Spielkarte zuLegendeKarte = cli.legeEineKarte(model.gebeHandkarten());

        ArrayList<Spielkarte> erlaubteKarten = model.gebeAnzahlSpielerSchonGelegt() == 0
                ? erlaubteKartenAusspielenBeiSauspiel(model.gebeHandkarten(), new Spielkarte(model.gebeFarbe(), Werte.SAU))
                : gibErlaubteKarten(
                        new ArrayList<>(model.gebeHandkarten()),
                        model.gebeSpielArt(),
                        new Spielkarte(model.gebeFarbe(), Werte.SAU),
                        model.gebeVorgegebeneKarte(),
                        model.gebeFarbe(),
                        model.gebeSauFarbeVorhandGespielt()
                );

        if (erlaubteKarten.contains(zuLegendeKarte)) {
            // Entfernen der gelegten Karte von der Hand
            model.gebeHandkarten().remove(zuLegendeKarte);
            return zuLegendeKarte;
        }

        cli.ungueltigeEingabe("Die Karte kann nicht gelegt werden.");
        return legeEineKarte();
    }

    public void legeEineKarteGUI(Spielkarte spielkarte) {
        model.setzeZuLegendeKarte(spielkarte);
    }

    @Override
    public void karteWurdeGelegt(Spielkarte karte, int spielerHatGelegt) {
        // Nur anzeigen, wenn die Karte nicht vom aktuellen Spieler gelegt wurde
        if (spielerHatGelegt != model.gebeWelcherSpieler()) {
            WelcherSpieler welcherSpieler = wieVielterSpieler(spielerHatGelegt);
            cli.zeigeGelegteKarte(karte, welcherSpieler);
        }

        if (model.gebeAnzahlSpielerSchonGelegt() == 0
                && karte.gebeFarbe() == model.gebeFarbe()
                && !karte.istTrumpf(model.gebeSpielArt(), null)) {
            model.setzteSauFarbeVorhandGespielt(true);
        }

        if (karte.gebeFarbe() == model.gebeFarbe() && karte.gebeWert() == Werte.SAU) {
            model.setzeMitspieler(spielerHatGelegt);
        }

        // Karte in Model speichern (ohne erneute Anzeige)
        model.setzeGelegteKarte(karte);
    }

    @Override
    public void stichGewonnen(int spieler) {
        WelcherSpieler welcherSpieler = wieVielterSpieler(spieler);
        cli.stichGewonnen(welcherSpieler);
        model.stichBeendet();
    }

    @Override
    public void rundeGewonnen(int[] sieger, int[] punkte) {
        cli.rundeGewonnen(wieVielterSpieler(sieger[0]), wieVielterSpieler(sieger[1]), punkte);
    }

    public WelcherSpieler wieVielterSpieler(int spieler) {
        int eigenerSpielerIndex = model.gebeWelcherSpieler();
        int differenz = (spieler - eigenerSpielerIndex + 4) % 4;

        return switch (differenz) {
            case 0 -> WelcherSpieler.NUTZER;
            case 1 -> WelcherSpieler.LINKER;
            case 2 -> WelcherSpieler.OBERER;
            case 3 -> WelcherSpieler.RECHTER;
            default -> {
                System.err.println("Fehler in Methode wieVielterSpieler: Ungültiger Spielerindex");
                yield null;
            }
        };
    }

    public ArrayList<Spielkarte> gebeLetztenStich() {
        return model.gebeLetzterStich();
    }

    public int gebeMitspieler() {
        return model.gebeMitspieler();
    }

    @Override
    public void keinSpiel() {
        cli.keinSpiel();
    }
}