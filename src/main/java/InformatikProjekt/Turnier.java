package InformatikProjekt;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

// Programmierer: Adrian

public class Turnier {
    private final Speicherung speicherung;
    private final TunierModel tunierModel;
    private final ArrayList<Mitspieler> spieler;
    private final ArrayList<Spielkarte> spielKarten;

    Turnier(int anzahlRunden) {
        speicherung = Speicherung.speicherungErstellen();
        spieler = new ArrayList<>(4);
        spielKarten = new ArrayList<>();
        Spieler echterSpieler = new Spieler();

        tunierModel = new TunierModel(anzahlRunden, echterSpieler);

        Random random = new Random();
        tunierModel.setzePositionSpieler(random.nextInt(4));
        int vorhand = 0;

        // Spieler-ArrayList vorbereiten
        for (int i = 0; i < 4; i++) {
            if (i != tunierModel.gebePositionSpieler()) {
                spieler.add(new Bot());
            }
            else {
                spieler.add(tunierModel.gebeEchterSpieler());
            }
        }
        tunierModel.gebeEchterSpieler().spielGUIErstellen(this);
    }

        // Spielkarten vorbereiten
    public void spielKartenVorbereiten() {
        for (Farbe farbe : Farbe.values()) {
            for (Werte wert : Werte.values()) {
                spielKarten.add(new Spielkarte(farbe, wert));
            }
        }
        Collections.shuffle(spielKarten);
    }

    public void rundeStarten(int wiederholungRunden, int[] sieger) {
        if (wiederholungRunden < tunierModel.gebeAnzahlRunden()) {
            spielKartenVorbereiten();
            new Runde(spieler, spielKarten, tunierModel.gebePositionSpieler(), speicherung, 0, this, wiederholungRunden, tunierModel.gebeEchterSpieler());
        }
        else {
            if (sieger[0] == tunierModel.gebePositionSpieler() || sieger[1] == tunierModel.gebePositionSpieler()) {
                speicherung.TurnierGewonnen();
            }
            else {
                speicherung.TurnierVerloren();
            }
            speicherung.DatenSpeichern();
        }
    }

//    public void tunierStarten() {
//    Runden spielen
//        for (int i = 0; i < tunierModel.gebeAnzahlRunden(); i++) {
//            Runde runde = new Runde(spieler, tunierModel.gebeEchterSpieler(), spielKarten, positionSpieler, speicherung); // i: Vorhand (wird als erstes gefragt, legt erste Karte)
//            int[] sieger = runde.starteRunde(vorhand);
//            if (sieger != null) {
//                tunierModel.erhoehePunkteTunierUmEins(sieger[0]);
//                tunierModel.erhoehePunkteTunierUmEins(sieger[1]);
//            }
//
//            vorhand = (vorhand == 3) ? 0 : vorhand + 1;
//        }
//        Runde runde = new Runde(spieler, tunierModel.gebeEchterSpieler(), spielKarten, positionSpieler, speicherung);

        // Tunier auswerten
//        int hoechsteTunierPunkte = -1;
//        int tunierSieger = -1;
//        for (int i = 0; i < 4; i++) {
//            if (tunierModel.gebePunkteTunier(i) > hoechsteTunierPunkte) {
//                hoechsteTunierPunkte = tunierModel.gebePunkteTunier(i);
//                tunierSieger = i;
//            }
//            else if (tunierModel.gebePunkteTunier(i) == hoechsteTunierPunkte) {
//                // Sonderfall, geht über Prototyp hinaus; Gewinner ist, wer als erstes die meisten Punkte hat
//            }
//        }
//        if (tunierSieger == positionSpieler) {
//            speicherung.TurnierGewonnen();
//        }
//        else {
//            speicherung.TurnierVerloren();
//        }
    }
