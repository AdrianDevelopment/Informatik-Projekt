package InformatikProjekt;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

// Programmierer: Adrian

public class Tunier {
    private final ArrayList<Mitspieler> spieler; // vielleicht nicht final?
    private int vorhand;
    private final ArrayList<Spielkarte> spielKarten; // vielleicht nicht final?
    private final int anzahlRunden;
    private int[] punkteTunier;

    Tunier(int anzahlRunden) {
        spieler = new ArrayList<>(4);
        spielKarten = new ArrayList<>();
        this.anzahlRunden = anzahlRunden;
    }

    public void tunierStarten() {
        Random random = new Random();
        int positionSpieler = random.nextInt(4);
        int vorhand = 0;

        // Spieler-ArrayList vorbereiten
        for (int i = 0; i < 4; i++) {
            if (i != positionSpieler) {
                spieler.add(new Bot());
            }
            else {
                spieler.add(new Spieler());
            }
        }

        // Spielkarten vorbereiten
        for (Farbe farbe : Farbe.values()) {
            for (Werte wert : Werte.values()) {
                spielKarten.add(new Spielkarte(farbe, wert));
            }
        }
        Collections.shuffle(spielKarten);

        // Runden spielen
        for (int i = 0; i < anzahlRunden; i++) {
            Runde runde = new Runde(spieler, spielKarten, positionSpieler); // i: Vorhand (wird als erstes gefragt, legt erste Karte)
            int[] punkte = runde.starteRunde(vorhand);

            vorhand = (vorhand == 3) ? 0 : vorhand + 1;

            rundenSiegerErmitteln(punkte);
        }
    }
}