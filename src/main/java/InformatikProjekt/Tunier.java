package InformatikProjekt;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

// Programmierer: Adrian

public class Tunier {
    private final Speicherung speicherung;
    private final TunierModel tunierModel;
    private final ArrayList<Mitspieler> spieler;

    Tunier(int anzahlRunden, Spieler echterSpieler) {
        spieler = new ArrayList<>(4);
        speicherung = Speicherung.speicherungErstellen();

        tunierModel = new TunierModel(anzahlRunden, echterSpieler);
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
                spieler.add(tunierModel.gebeEchterSpieler());
            }
        }

        // Spielkarten vorbereiten
        ArrayList<Spielkarte> spielKarten = new ArrayList<>();
        for (Farbe farbe : Farbe.values()) {
            for (Werte wert : Werte.values()) {
                spielKarten.add(new Spielkarte(farbe, wert));
            }
        }
        Collections.shuffle(spielKarten);

        // Runden spielen
        for (int i = 0; i < tunierModel.gebeAnzahlRunden(); i++) {
            Runde runde = new Runde(spieler, tunierModel.gebeEchterSpieler(), spielKarten, positionSpieler, speicherung); // i: Vorhand (wird als erstes gefragt, legt erste Karte)
            int[] sieger = runde.starteRunde(vorhand);

            vorhand = (vorhand == 3) ? 0 : vorhand + 1;

            tunierModel.erhoehePunkteTunierUmEins(sieger[0]);
            tunierModel.erhoehePunkteTunierUmEins(sieger[1]);
        }

        // Tunier auswerten
        int hoechsteTunierPunkte = -1;
        int tunierSieger = -1;
        for (int i = 0; i < 4; i++) {
            if (tunierModel.gebePunkteTunier(i) > hoechsteTunierPunkte) {
                hoechsteTunierPunkte = tunierModel.gebePunkteTunier(i);
                tunierSieger = i;
            }
            else if (tunierModel.gebePunkteTunier(i) == hoechsteTunierPunkte) {
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
