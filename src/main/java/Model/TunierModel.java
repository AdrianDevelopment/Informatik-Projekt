package Model;

import Controler.Spieler;

public class TunierModel {
    private int anzahlRunden;
    private Spieler echterSpieler;
    private int[] punkteTunier;
    private int positionSpieler;

    public TunierModel(int anzahlRunden, Spieler echterSpieler) {
        this.anzahlRunden = anzahlRunden;
        this.echterSpieler = echterSpieler;
        this.punkteTunier = new int[4];
    }


    // Geber
    public int gebeAnzahlRunden() {
        return anzahlRunden;
    }

    public Spieler gebeEchterSpieler() {
        return echterSpieler;
    }

    public int gebePunkteTunier(int index) {
        return punkteTunier[index];
    }

    public int gebePositionSpieler() {
        return positionSpieler;
    }


    // Setzer
    public void setzeAnzahlRunden(int anzahlRunden) {
        this.anzahlRunden = anzahlRunden;
    }

    public void setzeEchterSpieler(Spieler echterSpieler) {
        this.echterSpieler = echterSpieler;
    }

    public void setzePunkteTunier(int index, int punkte) {
        punkteTunier[index] = punkte;
    }

    public void erhoehePunkteTunierUmEins(int index) {
        punkteTunier[index]++;
    }

    public void setzePositionSpieler(int positionSpieler) {
        this.positionSpieler = positionSpieler;
    }
}
