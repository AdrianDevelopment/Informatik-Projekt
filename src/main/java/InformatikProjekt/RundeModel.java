package InformatikProjekt;

import java.util.ArrayList;

public class RundeModel {
    private int[] punkte;
    private int ausrufer;
    private int positionSpieler;
    private Spielkarte[] aktuellerStich;
    private Spielkarte[] letzerStich;
    private SpielArt hoechsteSpielart;
    private Mitspieler ausruferReferenz;
    private int vorhand;

    public RundeModel(int positionSpieler, int vorhand) {
        punkte = new int[4];
        aktuellerStich = new Spielkarte[4];
        this.positionSpieler = positionSpieler;
        this.vorhand = vorhand;
    }

    // Geber
    public int gebePunkte(int index) {
        return punkte[index];
    }

    public int[] gebePunkteArray() {
        return punkte;
    }

    public int gebeAusrufer() {
        return ausrufer;
    }

    public int gebePositionSpieler() {
        return positionSpieler;
    }

    public Spielkarte gebeAktuellerStich(int index) {
        return aktuellerStich[index];
    }

    public Spielkarte[] gebeAktuellerStichArray() {
        return aktuellerStich;
    }

    public SpielArt gebeHoechsteSpielart() {
        return hoechsteSpielart;
    }

    public Mitspieler gebeAusruferReferenz() {
        return ausruferReferenz;
    }

    public int gebeVorhand() {
        return vorhand;
    }

    public Spielkarte[] gebeLetzerStichArray() {
        return letzerStich;
    }

    // Setzer
    public void setzePunkte(int index, int punktzahl) {
        punkte[index] = punktzahl;
    }

    public void addierePunkte(int index, int punktzahl) {
        punkte[index] += punktzahl;
    }

    public void setzeAusrufer(int ausrufer) {
        this.ausrufer = ausrufer;
    }

    public void setzeAktuellenStich(int index, Spielkarte spielkarte) {
        aktuellerStich[index] = spielkarte;
    }

    public void setzeHoechsteSpielart(SpielArt hoechsteSpielart) {
        this.hoechsteSpielart = hoechsteSpielart;
    }

    public void setzeAusruferReferenz(Mitspieler ausruferReferenz) {
        this.ausruferReferenz = ausruferReferenz;
    }

    public void setzeVorhand(int vorhand) {
        this.vorhand = vorhand;
    }

    public void kopiereInLetzerStich(Spielkarte[] aktuellerStich) {
        letzerStich = aktuellerStich.clone();
    }
}