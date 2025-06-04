package InformatikProjekt;

public class TunierModel {
    private int anzahlRunden;
    private Spieler echteSpieler;
    private int[] punkteTunier;

    public TunierModel(int anzahlRunden, Spieler echterSpieler) {
        this.anzahlRunden = anzahlRunden;
        this.echteSpieler = echterSpieler;
        this.punkteTunier = new int[4];
    }

    public int gebeAnzahlRunden() {
        return anzahlRunden;
    }

    public Spieler gebeEchterSpieler() {
        return echteSpieler;
    }

    public int gebePunkteTunier(int index) {
        return punkteTunier[index];
    }

    public void setzePunkteTunier(int index, int punkte) {
        punkteTunier[index] = punkte;
    }

    public void erhoehePunkteTunierUmEins(int index) {
        punkteTunier[index]++;
    }
}
