//Programmierer: Tim
package InformatikProjekt;

import java.util.ArrayList;

public class BotModel {
    private ArrayList<Spielkarte> hand;
    private Spielkarte sau;
    private SpielArt spielArt;
    private Farbe soloFarbe;
    private ArrayList<Spielkarte> gelegteKarten;
    private  int wievielterSpieler;
    //von 0 bis 4 speichert wie viele Karten in dem Spiel gelegt wurden.
    private int kartenZaehler;


    BotModel(){
        kartenZaehler =0;
    }

    public int gibKartenZaehler(){return kartenZaehler;}
    public ArrayList<Spielkarte> gibHand(){
        return hand;
    }
    public Spielkarte gibSau(){
        return sau;
    }
    public SpielArt gibSpielArt(){
        return spielArt;
    }
    public Farbe gibsoloFarbe(){
        return soloFarbe;
    }
    public Spielkarte gibLetzteGelegteKarte(){return  gelegteKarten.get(gelegteKarten.size()-1);}
    public Spielkarte gibErsteKarteAufTisch(){return  gelegteKarten.get(gelegteKarten.size()-1-kartenZaehler);}
    public int getWievielterSpieler(){return  wievielterSpieler;}

    public void setzteSau(Spielkarte nSau){sau = nSau;}
    public void setzteSpielArt(SpielArt nSpielArt){spielArt = nSpielArt;}
    public void setzteSoloFarbe(Farbe nSoloFarbe){soloFarbe = nSoloFarbe;}
    public void fuegeGelegteKarteHinzu(Spielkarte nKarte){gelegteKarten.add(nKarte);}
    public void setzteHand(ArrayList<Spielkarte> nHand){hand = nHand;}
    public void entferneKarteAusHand(Spielkarte karte){hand.remove(karte);}
    public void setWievielterSpieler(int nSpielerNummer){wievielterSpieler = nSpielerNummer;}
    public void kartenZaehlerHochZaehlen(){kartenZaehler ++;}
    public void kartenZaehlerZuruecksetzten(){kartenZaehler = 0;}

    public int[] wieVieleBesondereKarten(){
        int anzahlOber = 0;
        int anzahlUnter = 0;
        int anzahlSau = 0;
        int anzahlHerz = 0;
        int anzahlGras = 0;
        int anzahlEichel = 0;
        int anzahlSchellen = 0;

        for (Spielkarte karte : hand){
            if (karte.gebeWert() == Werte.OBER){
                anzahlOber++;
            } else if (karte.gebeWert() == Werte.UNTER) {
                anzahlUnter++;
            }else{
                if (karte.gebeWert() == Werte.SAU){
                    anzahlSau++;
                }
                switch (karte.gebeFarbe()){
                    case SCHELLEN:
                        anzahlSchellen++;
                        break;
                    case HERZ:
                        anzahlHerz++;
                        break;
                    case GRAS:
                        anzahlGras++;
                        break;
                    case EICHEL:
                        anzahlEichel++;
                        break;
                }
            }
            

        }
        return new int[]{anzahlOber, anzahlUnter, anzahlSau, anzahlHerz,anzahlGras,anzahlEichel,anzahlSchellen};
    }
    

}
