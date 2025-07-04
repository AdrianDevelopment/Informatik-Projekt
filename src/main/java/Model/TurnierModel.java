package Model;

// Programmierer: Adrian

import java.util.Arrays;
import java.util.OptionalInt;

public class TurnierModel {
    private int anzahlRunden;
    private int[] punkteTurnier;
    private int positionSpieler;
    private int vergangeneRunden;

    public TurnierModel(int anzahlRunden, int positionSpieler) {
        this.anzahlRunden = anzahlRunden;
        this.punkteTurnier = new int[4];
        this.positionSpieler = positionSpieler;
    }


    // Geber
    public int gebeAnzahlRunden() {
        return anzahlRunden;
    }

    public int[] gebePunkteTurnierArray() {
        return punkteTurnier;
    }

    public int gebePositionSpieler() {
        return positionSpieler;
    }

    public boolean istTurnierSiegerEchterSpieler() {
        int maxIndex = 0;
        for (int i = 1; i < punkteTurnier.length; i++) {
            if (punkteTurnier[i] > punkteTurnier[maxIndex]) {
                maxIndex = i;
            }
        }
        return (maxIndex == positionSpieler);
    }


    // Setzer
    public void erhoehePunkteTurnierUmEins(int index) {
        punkteTurnier[index]++;
    }

    public void setzeVergangeneRunden(int n) {
        vergangeneRunden=n;
    }


    // -------------------------------------------------------------------------------------------
    // Geber, die aktuell nicht benutzt werden
    public int gebePunkteTurnier(int index) {
        return punkteTurnier[index];
    }
    public int gebeVergangeneRunden() {
        return vergangeneRunden;
    }
    // Setzer, die aktuell nicht benutzt werden
    public void setzeAnzahlRunden(int anzahlRunden) {
        this.anzahlRunden = anzahlRunden;
    }


    public void setzePunkteTurnier(int index, int punkte) {
        punkteTurnier[index] = punkte;
    }

    public void setzePositionSpieler(int positionSpieler) {
        this.positionSpieler = positionSpieler;
    }
}
