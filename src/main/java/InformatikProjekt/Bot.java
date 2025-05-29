//Programmierer: Tim
package InformatikProjekt;

import java.util.ArrayList;
import java.util.Random;

public  class Bot extends Mitspieler {
    BotModel model;

    public Bot(){
        model = new BotModel();
    }

    @Override
    public SpielArt spielabsichtFragen(SpielArt hoechsteSpiel) {
        int[] besondereKarten = model.wieVieleBesondereKarten();
        int anzahlOU = besondereKarten[0] + besondereKarten[1];

        int indexFarbeMitMeistenKarten = besondereKarten[3];

        for(int i = 3; i <= 6; i++){
            if (besondereKarten[indexFarbeMitMeistenKarten] <besondereKarten[i]){
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
        if(!farbenZumAusrufen.isEmpty() && anzahlOU + besondereKarten[4] >= 5 && hoechsteSpiel.gebeSpielArtID() < SpielArt.SAUSPIEL.gebeSpielArtID()){
           model.setzteSau(new Spielkarte(farbenZumAusrufen.get(0),Werte.SAU));
           return  SpielArt.SAUSPIEL;
        }


        return SpielArt.KEINSPIEL;
    }


    @Override
    public Farbe farbeFuerSpielAbsicht(SpielArt spielArt) {
        switch (spielArt){
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
        if (model.gibKartenZaehler() == 0){
            moeglicheKarten = model.gibHand();
        }else{
            moeglicheKarten = gibErlaubteKarten( model.gibHand(), model.gibSpielArt(), model.gibSau(),model.gibErsteKarteAufTisch(), model.gibsoloFarbe());
        }

        Random zufall = new Random();
        int zufaelligerIndex = zufall.nextInt(moeglicheKarten.size());
        model.entferneKarteAusHand(moeglicheKarten.get(zufaelligerIndex));
        return moeglicheKarten.get(zufaelligerIndex);
    }

    @Override
    public void rundeStarten(ArrayList<Spielkarte> karten, int wievielterSpieler) {
        model.setzteHand(karten);
        model.setWievielterSpieler(wievielterSpieler);
    }



    @Override
    public void spielArtEntschieden(int spieler, Farbe farbe, SpielArt spielArt) {
        model.setzteSau(new Spielkarte(farbe, Werte.SAU));
        model.setzteSpielArt(spielArt);
        model.setzteSoloFarbe(farbe);
    }


    @Override
    public void karteWurdeGelegt(Spielkarte karte, int spielerHatGelegt) {
        model.fuegeGelegteKarteHinzu(karte);
        model.kartenZaehlerHochZaehlen();
    }


    @Override
    public void stichGewonnen(int spieler) {
        model.kartenZaehlerZuruecksetzten();
    }
    @Override
    public void rundeGewonnen(int spieler) {

    }
    @Override
    public void spielerHatSpielabsichtGesagt(SpielArt spielAbsicht, int spieler) {

    }

    //Für den Test
    public int gibAnzahlKartenInHand(){
        return model.gibHand().size();
    }
}
