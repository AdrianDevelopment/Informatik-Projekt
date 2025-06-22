package Controler;

import Model.Farbe;
import Model.Spielkarte;
import Model.TunierModel;
import Model.Werte;
import View.SpielGUI;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

// Programmierer: Adrian

public class Turnier {
    private final Speicherung speicherung;
    private final TunierModel tunierModel;
    private final ArrayList<Mitspieler> spieler;
    private final SpielGUI gui;

    public Turnier(int anzahlRunden) {
        speicherung = Speicherung.speicherungErstellen();
        spieler = new ArrayList<>(4);
        Spieler echterSpieler = new Spieler();

        tunierModel = new TunierModel(anzahlRunden, echterSpieler);

        Random random = new Random();
        tunierModel.setzePositionSpieler(random.nextInt(4));
        int vorhand = 0;

        // Spieler-ArrayList vorbereiten
        for (int i = 0; i < 4; i++) {
            if (i != tunierModel.gebePositionSpieler()) {
                spieler.add(new Bot());
            } else {
                spieler.add(tunierModel.gebeEchterSpieler());
            }
        }
        gui = new SpielGUI();
        tunierModel.gebeEchterSpieler().spielGUIErstellen(gui);
    }

    // Spielkarten vorbereiten
    public ArrayList<Spielkarte> spielKartenVorbereiten() {
        ArrayList<Spielkarte> spielKarten = new ArrayList<>();
        for (Farbe farbe : Farbe.values()) {
            for (Werte wert : Werte.values()) {
                spielKarten.add(new Spielkarte(farbe, wert));
            }
        }
        Collections.shuffle(spielKarten);
        return spielKarten;
    }

    public void rundeStarten(int wiederholungRunden, int[] sieger) {
        if (wiederholungRunden < tunierModel.gebeAnzahlRunden()) {
            new Runde(spieler, spielKartenVorbereiten(), tunierModel.gebePositionSpieler(), speicherung, 0, this, wiederholungRunden, tunierModel.gebeEchterSpieler());
        } else {
            if (sieger[0] == tunierModel.gebePositionSpieler() || sieger[1] == tunierModel.gebePositionSpieler()) {
                speicherung.TurnierGewonnen();
            } else {
                speicherung.TurnierVerloren();
            }
            speicherung.DatenSpeichern();
            gui.schliessen();
        }
    }
}