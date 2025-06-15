package InformatikProjekt;

import javax.swing.*;
import java.util.ArrayList;

//Programmierer: Tom

public class SpielerModel {
    private ArrayList<Spielkarte> handkarten; //speichert eigene Handkarten
    private ArrayList<JButton> handButtons; //speichert Handkarten als Buttons mit richtigen Bildern
    private int[] andereSpielerKarten; //speichert Anzahl der Spielerkarten von den anderen Spielern
    private int welcherSpieler;
    private JButton okButton;
    //Attribute für eine Runde nach dem Ausrufen
    private SpielArt spielArt;
    private WelcherSpieler ausrufenderSpieler;
    private int[] spieler; //speichert wer ausruft und wer Mitspieler ist
    private Farbe farbe;
    //Attribute für Lege-/Stichrunden
    private ArrayList<Spielkarte> stich; //speichert Karten eines Stichs
    private ArrayList<Spielkarte> letzterStich; //speichert Karten des vorherigen Stichs
    private int anzahlSpielerSchonGelegt; //gibt an, wie viele Spieler in der Lege-Runde dran waren
    private Spielkarte zuLegeKarte;
    private boolean dranLegen;
    private boolean dranSpielabsicht;
    private boolean dranFarbeSpielabsicht;
    //temporäre Attribute für GUI-Übergabe
    private boolean sauFarbeVorhandGespielt; //Tim: Benötigt um zu überprüfen, ob die Sau gelegt werden darf.

    //Attribute für Runde
    private int wiederholung;
    private int vorhand;

    public SpielerModel() {
        handkarten = new ArrayList<Spielkarte>();
        handButtons = new ArrayList<JButton>();

        spielArt = null;
        ausrufenderSpieler = null;
        spieler = new int[2];
        farbe = null;

        stich = new ArrayList<Spielkarte>();
        anzahlSpielerSchonGelegt = 0;

    }

    //handkarten
    public ArrayList<Spielkarte> gebeHandkarten() {
        return handkarten;
    }

    public void setzeHandkarten(ArrayList<Spielkarte> neueKarten) {
        for (int i = 0; i < neueKarten.size(); i++) {
            handkarten.add(i, neueKarten.get(i));
        }
    }

    public void setzeHandButtons(ArrayList<JButton> neueButtons) {
        for (int i = 0; i < neueButtons.size(); i++) {
            handButtons.add(i, neueButtons.get(i));
        }
    }

    public ArrayList<JButton> gebeHandButtons() {
        return handButtons;
    }

    //welcher Spieler
    public void setzeWelcherSpieler(int nummer) {
        welcherSpieler = nummer;
    }

    public int gebeWelcherSpieler() {
        return welcherSpieler;
    }

    //okButton
    public void setzeOkButton(JButton jButton) {
        okButton.add(jButton);
    }

    public JButton gebeOkButton() {
        return okButton;
    }


    /**
     * setzt alle wichtigen Attribute für eine Runde nach dem Ausrufen
     */
    public void setzeSpielArt(WelcherSpieler ausrufenderSpieler, SpielArt ausgerufeneSpielArt, Farbe ausgerufeneFarbe, int spieler) {
        spielArt = ausgerufeneSpielArt;
        this.ausrufenderSpieler = ausrufenderSpieler;
        this.spieler[0] = spieler;
        farbe = ausgerufeneFarbe;
    }

    public SpielArt gebeSpielArt() {
        return spielArt;
    }

    public Farbe gebeFarbe() {
        return farbe;
    }

    /**
     * Attribute für Lege-/Stichrunden
     */

    public void setzeGelegteKarte(Spielkarte karte) {
        stich.add(karte);
        anzahlSpielerSchonGelegt++;
    }

    public void setzeMitspieler(int spielerHatGelegt) {
        spieler[1] = spielerHatGelegt; //Mitspieler (der Sau hat) wird gesetzt
    }

    public int gebeMitspieler() {
        return spieler[1];
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

    //dranLegen
    public void setzeDranLegen(boolean dran) {
        dranLegen = dran;
    }

    public boolean gebeDranLegen() {
        return dranLegen;
    }

    //dranSpielabsicht
    public void setzeDranSpielabsicht(boolean dran) {
        dranSpielabsicht = dran;
    }

    public boolean gebeDranSpielabsicht() {
        return dranSpielabsicht;
    }

    //dranFarbeSpielabsicht
    public void setzeDranFarbeSpielabsicht(boolean dran) {
        dranFarbeSpielabsicht = dran;
    }

    public boolean gebeDranFarbeSpielabsicht() {
        return dranFarbeSpielabsicht;
    }


    /**
     * Methoden für GUI-Rückgabe
     */
    //zuLegendeFarbe
    public void setzeZuLegendeKarte(Spielkarte spielkarte) {
        zuLegeKarte = spielkarte;
    }

    public Spielkarte gebeZuLegendeKarte() {
        return zuLegeKarte;
    }

    //Tim: benötigt um zu bestimmen, ob die gesuchte Sau gespielt werden darf.
    public void setzteSauFarbeVorhandGespielt(boolean b) {
        sauFarbeVorhandGespielt = b;
    }

    public boolean gebeSauFarbeVorhandGespielt() {
        return sauFarbeVorhandGespielt;
    }

    //Attribute für Runde
    public void setzeWiederholung(int wiederholung) {
        this.wiederholung = wiederholung;
    }

    public int gebeWiederholung() {
        return wiederholung;
    }

    public void setzeVorhand(int vorhand) {
        this.vorhand = vorhand;
    }

    public int gebeVorhand() {
        return vorhand;
    }
}

