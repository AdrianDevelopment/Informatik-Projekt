//Programmierer: Tim
package InformatikProjekt;

import java.util.ArrayList;

public class BotModel {
    ArrayList<Spielkarte> hand;
    Spielkarte sau;
    SpielArt spielArt;
    Farbe soloFarbe;
    ArrayList<Spielkarte> gelegteKarten;



    ArrayList<Spielkarte> gibHand(){
        return hand;
    }
    Spielkarte gibSau(){
        return sau;
    }
    SpielArt gibSpielArt(){
        return spielArt;
    }
    Farbe gibsoloFarbe(){
        return soloFarbe;
    }
    Spielkarte gibLetzteGelegteKarte(){return  gelegteKarten.get(this.gelegteKarten.size()-1);}

    void setzteSau(Spielkarte nSau){sau = nSau;}
    void setzteSpielArt(SpielArt nSpielArt){spielArt = nSpielArt;}
    void setzteSoloFarbe(Farbe nSoloFarbe){soloFarbe = nSoloFarbe;}
    void fuegeGelegteKarteHinzu(Spielkarte nKarte){gelegteKarten.add(nKarte);}
    void setzteHand(ArrayList<Spielkarte> nHand){hand = nHand;}
    void entferneKarteAusHand(int index){hand.remove(index);}


    int[] wieVieleBesondereKarten(){
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
