package InformatikProjekt;

import java.util.ArrayList;

public class RundeModel {
    private int[] punkte;
    private int ausrufer;
    private int positionSpieler;
    private Spielkarte[] aktuellerStich;

    public RundeModel(int positionSpieler) {
        punkte = new int[4];
        aktuellerStich = new Spielkarte[4];
        this.positionSpieler = positionSpieler;
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
}