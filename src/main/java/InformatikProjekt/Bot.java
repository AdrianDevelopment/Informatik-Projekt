package InformatikProjekt;

import java.util.ArrayList;

//Programmierer: Tim
public class Bot extends Mitspieler {
    BotModel model;

    @Override
    public SpielArt spielabsichtFragen(SpielArt hoechsteSpiel) {
        return null;
    }

    @Override
    public void spielerHatSpielabsichtGesagt(SpielArt spielAbsicht, int spieler) {}

    @Override
    public Spielkarte legeEineKarte() { return null; }

    @Override
    public void rundeStarten(
            ArrayList<Spielkarte> karten, int wieVielterSpieler
    ) {}

    @Override
    public void spielArtEntschieden(
            int spieler, Spielkarte sau, Farbe farbeSolo, SpielArt spielArt
    ) {}

    @Override
    public void setzeErsterSpieler(int ersterSpieler) {

    }

    @Override
    public void rundeGewonnen(int spieler) {

    }

    @Override
    public void karteWurdeGelegt(Spielkarte karte, int spielerHatGelegt) {

    }

    @Override
    public void spielAusgerufen(SpielArt ausgerufenesSpiel, int spieler) {

    }

    @Override
    public void stichGewonnen(int spieler, int[] punkte) {

    }

    @Override
    public ArrayList<Spielkarte> gibErlaubteKarten(
            ArrayList<Spielkarte> hand, SpielArt spielArt, Spielkarte sau, Spielkarte vorgegebeneKarte, Farbe soloFarbe
    ) { return null; }

    // @Override
    public Spielkarte legeEineKarte(SpielArt spielArt) {
        // ArrayList<Spielkarte> erlaubteKarten= this.gibErlaubteKarten(model.gibHand());
        return null;
    }

    // @Override
    private ArrayList<Spielkarte> soloErlaubteKarten(
            ArrayList<Spielkarte> hand, Farbe farbeSolo, Spielkarte vorgegebeneKarte
    ) { return null; }
}
