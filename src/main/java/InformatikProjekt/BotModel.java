package InformatikProjekt;

import java.util.ArrayList;

public class BotModel {
    ArrayList<Spielkarte> hand;
    Spielkarte sau;
    SpielArt spielArt;
    Farbe soloFarbe;


    ArrayList<Spielkarte> gibHand(){
        return this.hand;
    }
    Spielkarte gibSau(){
        return this.sau;
    }
    SpielArt gibSpielArt(){
        return this.spielArt;
    }
    Farbe gibsoloFarbe(){
        return this.soloFarbe;
    }
}
