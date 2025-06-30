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
    private final Spieler echterSpieler;
    private final TurnierPunkteGUI turnierPunkteGUI;

    public Turnier(int anzahlRunden) {
        Random random = new Random();
        echterSpieler = new Spieler();
        speicherung = Speicherung.speicherungErstellen();
        turnierModel = new TurnierModel(anzahlRunden, random.nextInt(4));
        spieler = new ArrayList<>(4);
        turnierPunkteGUI = new TurnierPunkteGUI();

        // Spieler-ArrayList vorbereiten
        for (int i = 0; i < 4; i++) {
            if (i != turnierModel.gebePositionSpieler()) {
                spieler.add(new Bot());
            }
            else {
                spieler.add(echterSpieler);
            }
        }
        gui = new SpielGUI();
        echterSpieler.spielGUIErstellen(gui);
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
        if (sieger == null) {
            return;
        }
//        if (echterSpieler.gebeKeinSpiel()) {
//            echterSpieler.setzeKeinSpiel(false);
//            //rundeStarten(wiederholungRunden);
//            //echterSpieler.okButtonActionListenerLoeschen();
//            return;
//        }
        if (wiederholungRunden == turnierModel.gebeAnzahlRunden() - 1) {
            if (sieger[0] == turnierModel.gebePositionSpieler() || sieger[1] == turnierModel.gebePositionSpieler()) {
                speicherung.TurnierGewonnen();
            } else {
                speicherung.TurnierVerloren();
            }
            speicherung.DatenSpeichern();
            gui.schliessen();
//            new turnierGewonnenGUI();
        }
        turnierModel.erhoehePunkteTurnierUmEins(sieger[0]);
        turnierModel.erhoehePunkteTurnierUmEins(sieger[1]);
        turnierPunkteGUI.turnierPunkteGUISichtbarkeit(true);
        turnierPunkteGUI.ausfuehren(this, wiederholungRunden, turnierModel.gebePunkteTurnierArray());
    }

    public void rundeStarten(int wiederholungRunden) {
        turnierPunkteGUI.turnierPunkteGUISichtbarkeit(false);
        if (wiederholungRunden < turnierModel.gebeAnzahlRunden()) {
            new Runde(spieler, spielKartenVorbereiten(), turnierModel.gebePositionSpieler(), speicherung, this, wiederholungRunden, echterSpieler);
        }
    }

    // Kopie aus Spieler (Tom)
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