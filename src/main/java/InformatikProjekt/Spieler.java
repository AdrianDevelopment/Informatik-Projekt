package InformatikProjekt;

import java.util.ArrayList;

public class Spieler extends Mitspieler {
    private SpielerModel model; //speichert Daten des Spielers
    private SpielerGUI gui;

    public Spieler() {
        model = new SpielerModel();
    }

    /**
     * Übergibt die wichtigen Dinge dem SpielerModel
     *
     * @param karten
     * @param wieVielterSpieler
     */
    @Override
    public void rundeStarten(ArrayList<Spielkarte> karten, int wieVielterSpieler) {
        model.setzeHandkarten(karten);
        model.setzeWelcherSpieler(wieVielterSpieler);
    }

    @Override
    public SpielArt spielabsichtFragen(SpielArt hoechstesSpiel) { //warum Spielkarte als return Wert
        return gui.spielabsichtFragen(hoechstesSpiel); //TODO: Spieler übergeben, nach Patch von Tim
    }

    @Override
    public void spielArtEntschieden(int spieler, Spielkarte sau, Farbe farbeSolo, SpielArt spielArt) {
        /*switch (spielArt) {
            case SOLO: model.spielArtEntschieden(spieler, farbeSolo);
            case WENZ: model.spielArtEntschieden(spieler, sau);
            default: System.out.println("Spielart noch nicht implementiert.");
        }*/ //TODO: Methode in GUI implementieren + abklären
    }

    /**
     * gibt den erstenSpieler, der nach einem Stich wieder anfängt
     *
     * @param ersterSpieler
     */
    @Override
    public void setzeErsterSpieler(int ersterSpieler) {
        int leger = ersterSpieler - model.gebeWelcherSpieler();
        //TODO: nochmal durchdenken, ob es wirklich passt
        /*
        positive Zahl: gehe x Spieler im Uhrzeigersinn, der fängt mit Karten legen an
        negative Zahl: gehe |x| Spieler gegen den Uhrzeigersinn, der fängt mit Karten legen an
        */
        //gui.setzeLeger(leger); //TODO: gui-Methode
    }

    @Override
    public Spielkarte duBistDran(SpielArt spielArt) {
        return null;
    }

    @Override
    public void karteWurdeGelegt(Spielkarte Karte, int spielerHatGelegt) {

    }

    @Override
    public void spielAusgerufen(SpielArt ausgerufenesSpiel, int spieler) {

    }

    @Override
    public void rundeGewonnen(int spieler) {

    }
}
