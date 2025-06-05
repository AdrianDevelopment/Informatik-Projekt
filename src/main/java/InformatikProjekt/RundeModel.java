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
    private int[] sieger;
    private int wiederholungenRunden;

    public RundeModel(int positionSpieler, int vorhand, int wiederholungenRunden) {
        punkte = new int[4];
        ausrufer = -1;
        this.positionSpieler = positionSpieler;
        aktuellerStich = new Spielkarte[4];
        this.letzerStich = new Spielkarte[4];
        this.hoechsteSpielart = SpielArt.KEINSPIEL;
        this.vorhand = vorhand;
        this.sieger = new int[2];
        this.wiederholungenRunden = wiederholungenRunden;
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

    public int gebeSieger(int index) {
        return sieger[index];
    }

    public int[] gebeSiegerArray() {
        return sieger;
    }

    public int gebeWiederholungenRunden() {
        return wiederholungenRunden;
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

    public void setzeSieger(int index, int sieger) {
        this.sieger[index] = sieger;
    }

    public void setzeSiegerArray(int[] sieger) {
        this.sieger = sieger;
    }

    public void setzeWiederholungenRunden(int wiederholungenRunden) {
        this.wiederholungenRunden = wiederholungenRunden;
    }
}