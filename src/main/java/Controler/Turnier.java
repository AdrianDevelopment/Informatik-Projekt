package Controler;

import Model.*;
import View.SpielGUI;
import View.TurnierPunkteGUI;

import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

// Programmierer: Adrian

public class Turnier {
    private final Speicherung speicherung;
    private final TurnierModel turnierModel;
    private final ArrayList<Mitspieler> spieler;
    private final SpielGUI gui;
    private final TurnierPunkteGUI tpg;

    public Turnier(int anzahlRunden, TurnierPunkteGUI tpg) {
        this.tpg = tpg;
        speicherung = Speicherung.speicherungErstellen();
        spieler = new ArrayList<>(4);
        Spieler echterSpieler = new Spieler();
        Random random = new Random();
        turnierModel = new TurnierModel(anzahlRunden, echterSpieler, random.nextInt(4));

        // Spieler-ArrayList vorbereiten
        for (int i = 0; i < 4; i++) {
            if (i != turnierModel.gebePositionSpieler()) {
                spieler.add(new Bot());
            }
            else {
                spieler.add(turnierModel.gebeEchterSpieler());
            }
        }
        gui = new SpielGUI();
        turnierModel.gebeEchterSpieler().spielGUIErstellen(gui);
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

    public void turnierPunkteAnzeigen(int wiederholungRunden, int[] sieger) {
        turnierModel.erhoehePunkteTurnierUmEins(sieger[0]);
        turnierModel.erhoehePunkteTurnierUmEins(sieger[1]);
        if (wiederholungRunden == turnierModel.gebeAnzahlRunden()) {
            if (sieger != null) {
                if (sieger[0] == turnierModel.gebePositionSpieler() || sieger[1] == turnierModel.gebePositionSpieler()) {
                    speicherung.TurnierGewonnen();
                } else {
                    speicherung.TurnierVerloren();
                }
                speicherung.DatenSpeichern();
                gui.schliessen();
            }
            else {
                System.out.println("ERROR: sieger ist null");
            }
        }
        new TurnierPunkteGUI(this, wiederholungRunden, turnierModel.gebePunkteTurnierArray());
    }

    public void rundeStarten(TurnierPunkteGUI turnierPunkteGUI, int wiederholungRunden) {
        tpg.turnierPunkteGUIZerstoeren();
        if (turnierPunkteGUI != null) {
            turnierPunkteGUI.turnierPunkteGUIZerstoeren();
        }
        if (wiederholungRunden < turnierModel.gebeAnzahlRunden()) {
            new Runde(spieler, spielKartenVorbereiten(), turnierModel.gebePositionSpieler(), speicherung, this, wiederholungRunden, turnierModel.gebeEchterSpieler());
        }
    }

    public WelcherSpieler wieVielterSpieler(int spieler) {
        WelcherSpieler spielerImUhrzeigersinn = null;
        int rechnung = spieler - turnierModel.gebePositionSpieler(); //positive Zahlen im Uhrzeigersinn; negative gegen den Uhrzeigersinn
        if (rechnung == 0) {
            spielerImUhrzeigersinn = WelcherSpieler.NUTZER; //Nutzer
        } else if (rechnung == 1 || rechnung == -3) {
            spielerImUhrzeigersinn = WelcherSpieler.LINKER; //linker Spieler
        } else if (rechnung == 2 || rechnung == -2) {
            spielerImUhrzeigersinn = WelcherSpieler.OBERER; //oberer Spieler
        } else if (rechnung == 3 || rechnung == -1) {
            spielerImUhrzeigersinn = WelcherSpieler.RECHTER; //rechter Spieler
        } else {
            System.out.println("ERROR: Methode wieVielterSpieler" + spieler); //Test
        }
        return spielerImUhrzeigersinn;
    }
}