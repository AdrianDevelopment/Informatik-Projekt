//Programmierer: Tim
package InformatikProjekt;

import java.util.ArrayList;
import java.util.Random;

public class Bot extends Mitspieler {
    BotModel model;

    public Bot(){}

    @Override
    public SpielArt spielabsichtFragen(SpielArt höchsteSpiel) {
        int[] besondereKarten = model.wieVieleBesondereKarten();
        int anzahlOU = besondereKarten[0] + besondereKarten[1];

        int indexFarbeMitMeistenKarten = besondereKarten[3];
        for(int i = 3; i <= 6; i++){
            if (besondereKarten[indexFarbeMitMeistenKarten] <besondereKarten[i]){
                indexFarbeMitMeistenKarten = i;
            }
        }
        if (anzahlOU + besondereKarten[indexFarbeMitMeistenKarten] >= 6){
            return SpielArt.SOLO;
        }


        

        return SpielArt.KEINSPIEL;
    }


    @Override
    public void spielerHatSpielabsichtGesagt(SpielArt spielAbsicht, int spieler) {

    }

    @Override
    public Spielkarte legeEineKarte() {
        ArrayList<Spielkarte> moeglicheKarten = this.gibErlaubteKarten( model.gibHand(), model.gibSpielArt(), model.gibSau(),model.gibLetzteGelegteKarte(), model.gibsoloFarbe());
        Random zufall = new Random();
        int zufaelligerIndex = zufall.nextInt(moeglicheKarten.size());
        model.entferneKarteAusHand(zufaelligerIndex);
        return moeglicheKarten.get(zufaelligerIndex);
    }

    @Override
    public void rundeStarten(ArrayList<Spielkarte> karten, int wieVielterSpieler) {
        model.setzteHand(karten);
    }



    @Override
    public void spielArtEntschieden(int spieler, Spielkarte Sau, Farbe farbeSolo, SpielArt spielArt) {
        model.setzteSau(Sau);
        model.setzteSpielArt(spielArt);
        model.setzteSoloFarbe(farbeSolo);
    }

    @Override
    public void setzeErsterSpieler(int ersterSpieler) {

    }



    @Override
    public void rundeGewonnen(int spieler) {

    }

    @Override
    public void karteWurdeGelegt(Spielkarte karte, int spielerHatGelegt) {
        model.fuegeGelegteKarteHinzu(karte);
    }


    @Override
    public void stichGewonnen(int spieler) {

    }
}
