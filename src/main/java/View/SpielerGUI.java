package View;

import Controler.Spieler;
import Model.Spielkarte;
import Model.Farbe;
import Model.SpielArt;
import Model.WelcherSpieler;

import java.util.ArrayList;
import java.util.Scanner;

//Programmierer: Tom

public class SpielerGUI {
    private Scanner benutzerEingabe;
    private Spieler spieler;

    public SpielerGUI(Spieler spieler) {
        this.spieler = spieler;
        benutzerEingabe = new Scanner(System.in);
    }

    //gui-Methode, die Benutzer nach Spielabsicht frägt und als String zurückgibt
    public void spielabsichtFragen() {
        System.out.println("Das aktuell höchste Spiel ist: Gib bitte deine Spielabsicht ein, also entweder 'weiter', 'Sau', 'Wenz' oder 'Solo'");
        //return benutzerEingabe.nextLine();
        //Handkarten müssen währenddessen angezeigt werden + die bisher höchste Spielart und von wem ausgerufen;
    }

    public void farbeFuerSpielAbsicht() {
        //rufe spieler.farbeFuerSpielAbsichtGUI(farbe) auf
    }


    public void spielerHatSpielerabsichtGesagt(SpielArt spielAbsicht, WelcherSpieler welcherSpieler) {
        System.out.println(welcherSpieler.gebeName() + " hat " + spielAbsicht + " ausgerufen.");
    }

    public void ungueltigeEingabe(String konkretisierung) {
        System.out.println("Das war leider eine ungültige Eingabe. " + konkretisierung);
    }

    public void zeigeHandkarten(ArrayList<Spielkarte> handkarten) {
        System.out.println("Das sind deine Handkarten:");
        for (Spielkarte karte : handkarten) {
            System.out.println(karte);
        }
    }

    public void spielArtEntschieden(WelcherSpieler welcherSpieler, SpielArt spielArt, Farbe farbe) {
        String ausgabe = "Ausrufer: " + welcherSpieler.gebeName() + "| ";
        switch (spielArt) {
            case KEINSPIEL:
                ausgabe += "Niemand wollte spielen";
            case SAUSPIEL:
                ausgabe = "Sauspiel auf die ";
                switch (farbe) {
                    case SCHELLEN:
                        ausgabe += "Bumbe";
                        break;
                    case GRAS:
                        ausgabe += "Blaue";
                        break;
                    case EICHEL:
                        ausgabe += "Alte";
                        break;
                }
            default:
                ausgabe += "Spielart noch nicht implementiert";
                break;
        }
        System.out.println(ausgabe);
    }


    public Spielkarte legeKarte() {
        return null;
    }

    public void zeigeGelegteKarte(Spielkarte karte, WelcherSpieler spielerHatGelegt) {
        System.out.println(spielerHatGelegt.gebeName() + " hat die Karte " + karte + " gelegt."); //TODO: kann man Karte so einfach anzeigen? -> @Thiemo zusammen setzen
    }

    public void stichGewonnen(WelcherSpieler spieler) {
        System.out.println(spieler.gebeName() + " hat den Stich gewonnen.");
    }

    public void rundeGewonnen(int[] punkte) {
        int gewinnerPunkte = punkte[0] + punkte[1];
        System.out.println(punkte[0] + " und haben die Runde mit " + gewinnerPunkte + " Punkten gewonnen.");
        //TODO: wie soll das der GUI übergeben werden?
    }


    //Methode wird aufgerufen, wenn Spieler auf irgendeinen Button klickt
    public void zeigeLetztenStich() {
        System.out.println(spieler.gebeLetztenStich());
    }

}