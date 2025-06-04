package InformatikProjekt;

import java.util.ArrayList;

public class RundeModel {
    private int[] punkte;
    private int ausrufer;
    private Mitspieler ausruferObjekt;
    private Spielkarte[] aktuellerStich;

    public RundeModel() {
        punkte = new int[4];
        aktuellerStich = new Spielkarte[4];
    }

    public int ermittleSieger(Spielkarte[] aktuellerStich) {
        // Logik wie im Originalcode
    }

    public int gebeTrumpfRang(Spielkarte karte) {
        // Logik wie im Originalcode
    }

    public int gebeFarbeRang(Spielkarte karte) {
        // Logik wie im Originalcode
    }

    public int ermittlePunkte(Spielkarte[] aktuellerStich) {
        // Logik wie im Originalcode
    }

    public int[] rundenSiegerErmitteln(int ausrufer, Mitspieler spielerObjekt) {
        // Logik aus rundenSiegerErmitteln()
    }

    // Getter und Setter für Daten
    public void setzePunkte(int spielerIndex, int punktzahl) {
        punkte[spielerIndex] = punktzahl;
    }

    public int gebePunkte(int spielerIndex) {
        return punkte[spielerIndex];
    }
    
    public void setzeAusrufer(int ausrufer, Mitspieler ausruferObjekt) {
        this.ausrufer = ausrufer;
        this.ausruferObjekt = ausruferObjekt;
    }

    public int gebeAusrufer() {
        return ausrufer;
    }
    
    public Spielkarte[] gebeAktuellerStich() {
        return aktuellerStich;
    }
}