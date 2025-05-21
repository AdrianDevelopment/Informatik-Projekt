package InformatikProjekt;

import java.util.ArrayList;

public class SpielerModel {
    private ArrayList<Spielkarte> handkarten; //speichert eigene Handkarten
    private int[] andereSpielerKarten; //speichert Anzahl der Spielerkarten von den anderen Spielern
    private int welcherSpieler;
    //Attribute für eine Runde nach dem Ausrufen
    private SpielArt spielArt;
    private int ausrufenderSpieler;
    private Spielkarte sau;
    private Farbe soloFarbe;

    public SpielerModel() {
        handkarten = new ArrayList<Spielkarte>();
        spielArt = null;
        ausrufenderSpieler = -1;
        sau = null;
        soloFarbe = null;
    }

    //handkarten
    public ArrayList<Spielkarte> gebeHandkarten() {
        return handkarten;
    }

    public void setzeHandkarten(ArrayList<Spielkarte> neueKarten) {
        for (int i = 0; i < 8; i++) {
            handkarten.set(i, neueKarten.get(i));
        }
    }

    //andereSpielerKarten


    //welcher Spieler
    public void setzeWelcherSpieler(int nummer) {
        welcherSpieler = nummer;
    }

    public int gebeWelcherSpieler() {
        return welcherSpieler;
    }

    /**setzt alle wichtigen Attribute für eine Runde nach dem Ausrufen
     *
     * @param spieler
     * @param ausgerufeneSau
     * @param ausgerufeneFarbe
     * @param ausgerufeneSpielArt
     */
    public void setzeSpielArt(int spieler, SpielArt ausgerufeneSpielArt, Spielkarte ausgerufeneSau, Farbe ausgerufeneFarbe) {
        spielArt = ausgerufeneSpielArt;
        ausrufenderSpieler = spieler;
        if(ausgerufeneSau != null) {
            sau = ausgerufeneSau;
        } else if(ausgerufeneFarbe != null) {
            soloFarbe = ausgerufeneFarbe;
        }
    }
}
