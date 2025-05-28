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
    private Spielkarte sau;
    private Farbe soloFarbe;
    //Attribute für Lege-/Stichrunden
    private WelcherSpieler rundeErsterSpieler;
    private ArrayList<Spielkarte> stich; //speichert Karten eines Stichs
    private ArrayList<Spielkarte> letzterStich; //speichert Karten des vorherigen Stichs
    private int anzahlSpielerSchonGelegt; //gibt an, wie viele Spieler in der Lege-Runde dran waren

    public SpielerModel() {
        handkarten = new ArrayList<Spielkarte>();

        spielArt = null;
        ausrufenderSpieler = null;
        sau = null;
        soloFarbe = null;

        stich = new ArrayList<Spielkarte>();
        anzahlSpielerSchonGelegt = 0;
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
    public void setzeSpielArt(WelcherSpieler spieler, SpielArt ausgerufeneSpielArt, Spielkarte ausgerufeneSau, Farbe ausgerufeneFarbe) {
        spielArt = ausgerufeneSpielArt;
        ausrufenderSpieler = spieler;
        if(ausgerufeneSau != null) {
            sau = ausgerufeneSau;
        } else if(ausgerufeneFarbe != null) {
            soloFarbe = ausgerufeneFarbe;
        }
    }

    public SpielArt gebeSpielArt() {
        return spielArt;
    }

    public Spielkarte gebeSau() {
        return sau;
    }

    public Farbe gebeSoloFarbe() {
        return soloFarbe;
    }

    public void setzeErsterSpieler(WelcherSpieler leger) {
        rundeErsterSpieler = leger;
        anzahlSpielerSchonGelegt = 0;
    }

    public void setzeGelegteKarte(Spielkarte karte) {
        stich.add(karte);
        anzahlSpielerSchonGelegt++;
    }

    public Spielkarte gebeVorgegebeneKarte() {
        return stich.get(0);
    }

    public int leangeStich() {
        return stich.size();
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


}
