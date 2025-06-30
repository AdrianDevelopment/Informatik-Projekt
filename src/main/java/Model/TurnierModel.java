package Model;

import Controler.Spieler;

public class TurnierModel {
    private int anzahlRunden;
    private int[] punkteTurnier;
    private int positionSpieler;

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


    // Setzer
    public void erhoehePunkteTurnierUmEins(int index) {
        punkteTurnier[index]++;
    }


    // -------------------------------------------------------------------------------------------
    // Geber, die aktuell nicht benutzt werden
    public int gebePunkteTurnier(int index) {
        return punkteTurnier[index];
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
