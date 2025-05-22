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
}
