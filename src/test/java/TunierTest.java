import Model.Farbe;
import Model.Spielkarte;
import Model.Werte;

import java.util.ArrayList;
import java.util.Collections;

public class TunierTest {
    private static ArrayList<Spielkarte> spielKarten = new ArrayList<>();

    public static void main(String[] args) {
        for (Farbe farbe : Farbe.values()) {
            for (Werte wert : Werte.values()) {
                spielKarten.add(new Spielkarte(farbe, wert));
            }
        }
        Collections.shuffle(spielKarten);

        System.out.println("Spielkarten:");

        System.out.println(spielKarten.size());

        for (int i = 0; i < spielKarten.size(); i++) {
            System.out.println(spielKarten.get(i).gebeFarbe() + " " + spielKarten.get(i).gebeWert());
        }
    }
}
