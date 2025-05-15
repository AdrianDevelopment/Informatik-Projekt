package InformatikProjekt;

import java.util.ArrayList;

public class Spieler extends Mitspieler {
    private SpielerModel model; //speichert Daten des Spielers
    private SpielerGUI gui;
    private int welcherSpieler;

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
        welcherSpieler = wieVielterSpieler;

        //TODO: gui übergeben wieVielterSpieler man ist
    }

    @Override
    public void spielArtEntschieden(int spieler, Spielkarte Sau, Farbe farbeSolo, SpielArt spielArt) {

    }

    @Override
    public void setzteErsterSpieler(int ersterSpieler) {

    }

    @Override
    public SpielArt spielabsichtFragen(SpielArt höchstesSpiel) { //warum Spielkarte als return Wert
        //return gui.spielabsichtFragen(höchstesSpiel); //TODO: gui-Methode, die User nach Spielabsicht frägt und als Spielart zurück gibt
        return null;
    }

    /**
     * gibt den erstenSpieler, der nach einem Stich wieder anfängt
     *
     * @param ersterSpieler
     */
    @Override
    public void setzeErsterSpieler(int ersterSpieler) {
        int leger = ersterSpieler - welcherSpieler; /*
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
