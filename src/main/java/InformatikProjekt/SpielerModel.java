package InformatikProjekt;

import java.util.ArrayList;

public class SpielerModel {
    private ArrayList<Spielkarte> handkarten; //speichert eigene Handkarten
    private int[] andereSpielerKarten; //speichert Anzahl der Spielerkarten von den anderen Spielern
    private int welcherSpieler;

    public SpielerModel() {
        handkarten = new ArrayList<Spielkarte>();
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
}
