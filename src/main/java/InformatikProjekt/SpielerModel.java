package InformatikProjekt;

import java.util.ArrayList;

//Programmierer: Tom

public class SpielerModel {
    private ArrayList<Spielkarte> handkarten; //speichert eigene Handkarten
    private int[] andereSpielerKarten; //speichert Anzahl der Spielerkarten von den anderen Spielern
    private int welcherSpieler;
    //Attribute für eine Runde nach dem Ausrufen
    private SpielArt spielArt;
    private WelcherSpieler ausrufenderSpieler;
    private Farbe farbe;
    //Attribute für Lege-/Stichrunden
    private WelcherSpieler rundeErsterSpieler;
    private ArrayList<Spielkarte> stich; //speichert Karten eines Stichs
    private ArrayList<Spielkarte> letzterStich; //speichert Karten des vorherigen Stichs
    private int anzahlSpielerSchonGelegt; //gibt an, wie viele Spieler in der Lege-Runde dran waren
    //temporäre Attribute für GUI-Übergabe
    private SpielArt spielabsicht; //speichert Spielabsicht vom Spieler
    private Farbe spielabsichtFarbe;


    public SpielerModel() {
        handkarten = new ArrayList<Spielkarte>();

        spielArt = null;
        ausrufenderSpieler = null;
        farbe = null;

        stich = new ArrayList<Spielkarte>();
        anzahlSpielerSchonGelegt = 0;
        //temporäre Attribute für GUI-Übergabe
        spielabsicht = null;
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
     * @param ausgerufeneFarbe
     * @param ausgerufeneSpielArt
     */
    public void setzeSpielArt(WelcherSpieler spieler, SpielArt ausgerufeneSpielArt, Farbe ausgerufeneFarbe) {
        spielArt = ausgerufeneSpielArt;
        ausrufenderSpieler = spieler;
        farbe = ausgerufeneFarbe;
    }

    public SpielArt gebeSpielArt() {
        return spielArt;
    }

    public Farbe gebeFarbe() {
        return farbe;
    }

    public void setzeGelegteKarte(Spielkarte karte) {
        stich.add(karte);
        anzahlSpielerSchonGelegt++;
    }

    public Spielkarte gebeVorgegebeneKarte() {
        return stich.get(0);
    }

    public void stichBeendet() {
        letzterStich = stich;
        stich.clear();
        anzahlSpielerSchonGelegt = 0;
    }

    public ArrayList<Spielkarte> gebeLetzterStich() {
        return letzterStich;
    }


    //anzahlSpielerSchonGelegt
    public int gebeAnzahlSpielerSchonGelegt() {
        return anzahlSpielerSchonGelegt;
    }


    /**Methoden für GUI-Rückgabe*/
    //spielabsicht
    public void setzeSpielabsicht(SpielArt spielabsicht) {this.spielabsicht = spielabsicht;}
    public SpielArt gebeSpielabsicht() {return spielabsicht;}
    //spielabsichtFarbe
    public void setzeSpielabsichtFarbe(Farbe farbe) {spielabsichtFarbe = farbe;}
    public Farbe gebeSpielabsichtFarbe() {return spielabsichtFarbe;}
}
