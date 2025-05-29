package InformatikProjekt;

import java.util.ArrayList;
import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Collections;

// Programmierer: Adrian

public class Tunier {
    private final Queue<Mitspieler> spieler; // vielleicht nicht final?
    private final ArrayList<Spielkarte> spielKarten; // vielleicht nicht final?
    private final int anzahlRunden;
    private int[] punkteTunier;

    Tunier(int anzahlRunden) {
        spieler = new LinkedList<>();
        spielKarten = new ArrayList<>();
        this.anzahlRunden = anzahlRunden;
    }

    public void tunierStarten() {
        // Spieler-Warteschlange vorbereiten
        Random random = new Random();
        int positionSpieler = random.nextInt(4);
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

        // Position des Spielers aktuell halten und Runden starten
        for (int i = 0; i < anzahlRunden; i++) {
            if (positionSpieler == 3) {
                positionSpieler = 0;
            }
            else {
                positionSpieler++;
            }
            // aktuell nur 1 Runde
            Runde runde = new Runde(spieler, spielKarten, positionSpieler); // i: Vorhand (wird als erstes gefragt, legt erste Karte)
            int[] punkte = runde.starteRunde();

            for (int j = 0; j < 4; j++) {
                punkteTunier[j] += punkte[j];
            }

            spieler.offer(spieler.poll());
        }
    }
}
