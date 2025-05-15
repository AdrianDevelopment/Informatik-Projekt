package InformatikProjekt;

import java.util.ArrayList;

//Programmierer: Tim
public class Bot extends Mitspieler {
    BotModel model;

    @Override
    public SpielArt spielabsichtFragen(SpielArt höchsteSpiel) {
        return null;
    }

    @Override
    public Spielkarte legeEineKarte(SpielArt spielArt) {
        ArrayList<Spielkarte> erlaubteKarten= this.gibErlaubteKarten(model.gibHand());
    }

    @Override
    public void rundeStarten(Spielkarte[] karten, int wieVielterSpieler) {

    }

    @Override
    public void spielArtEntschieden(int spieler, Spielkarte Sau, Farbe farbeSolo, SpielArt spielArt) {

    }

    @Override
    public void setzteErsterSpieler(int ersterSpieler) {

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
}
