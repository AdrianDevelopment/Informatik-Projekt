package Controler;

// Programmierer: Adrian

import Model.*;
import View.SpielGUI;
import View.TurnierPunkteGUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Turnier {
    private final Speicherung speicherung;
    private final TurnierModel turnierModel;
    private final ArrayList<Mitspieler> spieler;
    private final Spieler echterSpieler;
    private TurnierPunkteGUI turnierPunkteGUI;

    public Turnier(int anzahlRunden) {
        Random random = new Random();
        echterSpieler = new Spieler();
        speicherung = Speicherung.speicherungErstellen();
        turnierModel = new TurnierModel(anzahlRunden, random.nextInt(4));
        spieler = new ArrayList<>(4);

        // Spieler-ArrayList vorbereiten
        for (int i = 0; i < 4; i++) {
            if (i != turnierModel.gebePositionSpieler()) {
                spieler.add(new Bot());
            } else {
                spieler.add(echterSpieler);
            }
        }
        echterSpieler.spielGUIErstellen(new SpielGUI());
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

    // Werte für TurnierPunkteGUI werden gesetzt und die GUI wird aufgerufen
    public void turnierPunkteAnzeigen(SpielArt spielArt, int[] sieger) {
        turnierModel.setzeVergangeneRunden(turnierModel.gebeVergangeneRunden() + 1);
        if (sieger == null) {
            return;
        }
        if (spielArt != SpielArt.KEINSPIEL) {
            turnierModel.erhoehePunkteTurnierUmEins(sieger[0]);
            turnierModel.erhoehePunkteTurnierUmEins(sieger[1]);
        }
        turnierPunkteGUI = new TurnierPunkteGUI();
        turnierPunkteGUI.turnierPunkteGUIAusfuehren(this, turnierModel.gebeVergangeneRunden(), turnierModel.gebePunkteTurnierArray());
    }

    public void rundeStarten() {
        if (turnierPunkteGUI != null) {
            turnierPunkteGUI.turnierPunkteGUIEntferneActionListener();
            turnierPunkteGUI.turnierPunkteGUIBeenden();
        }
        if (turnierModel.gebeVergangeneRunden() < turnierModel.gebeAnzahlRunden()) {
            new Runde(spieler, spielKartenVorbereiten(), turnierModel.gebePositionSpieler(), speicherung, this, turnierModel.gebeVergangeneRunden(), echterSpieler);
        }
    }

    public void turnierBeenden() {
        turnierPunkteGUI.turnierPunkteGUIEntferneActionListener();
        turnierPunkteGUI.turnierPunkteGUIBeenden();
        if (!turnierModel.gebeTurnierNurKeinspiel()) {
            speicherung.TurnierZuEnde(turnierModel.gebePunkteTurnier(turnierModel.gebePositionSpieler()), turnierModel.istTurnierSiegerEchterSpieler());
            if (turnierModel.istTurnierSiegerEchterSpieler()) {
                speicherung.TurnierGewonnen();
            } else {
                speicherung.TurnierVerloren();
            }
            speicherung.DatenSpeichern();
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
            System.out.println("ERROR: Methode wieVielterSpieler " + spieler); //Test
        }
        return spielerImUhrzeigersinn;
    }
    // Kopie Ende

    public int gebeAnzahlRunden() {
        return turnierModel.gebeAnzahlRunden();
    }
}