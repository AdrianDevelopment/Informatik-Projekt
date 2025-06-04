package InformatikProjekt;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

// Programmierer: Adrian

public class Tunier {
    private Speicherung speicherung;
    private final ArrayList<Mitspieler> spieler; // vielleicht nicht final?
    private final ArrayList<Spielkarte> spielKarten; // vielleicht nicht final?
    private final int anzahlRunden;
    private int[] punkteTunier;
    private Spieler echterSpieler;

    Tunier(int anzahlRunden, Spieler echterSpieler) {
        spieler = new ArrayList<>(4);
        spielKarten = new ArrayList<>();
        speicherung = Speicherung.speicherungErstellen();
        this.anzahlRunden = anzahlRunden;
        this.echterSpieler = echterSpieler;
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
                spieler.add(echterSpieler);
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
            Runde runde = new Runde(spieler, echterSpieler, spielKarten, positionSpieler, speicherung); // i: Vorhand (wird als erstes gefragt, legt erste Karte)
            int[] sieger = runde.starteRunde(vorhand);

            vorhand = (vorhand == 3) ? 0 : vorhand + 1;

            punkteTunier[sieger[0]]++;
            punkteTunier[sieger[1]]++;
        }

        // Tunier auswerten
        int hoechsteTunierPunkte = -1;
        int tunierSieger = -1;
        for (int i = 0; i < 4; i++) {
            if (punkteTunier[i] > hoechsteTunierPunkte) {
                hoechsteTunierPunkte = punkteTunier[i];
                tunierSieger = i;
            }
            else if (punkteTunier[i] == hoechsteTunierPunkte) {
                // Sonderfall, geht über Prototyp hinaus; Gewinner ist, wer als erstes die meisten Punkte hat
            }
        }
        if (tunierSieger == positionSpieler) {
            speicherung.TurnierGewonnen();
        }
        else {
            speicherung.TurnierVerloren();
        }
    }
}
