package InformatikProjekt;

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
    public String spielabsichtFragen(SpielArt hoechstesSpiel) {
        System.out.println("Das aktuell höchste Spiel ist:" + hoechstesSpiel + "Gib bitte deine Spielabsicht ein, also entweder 'weiter', 'Sau', 'Wenz' oder 'Solo'");
        return benutzerEingabe.nextLine();
        //Handkarten müssen währenddessen angezeigt werden + die bisher höchste Spielart und von wem ausgerufen;
    }

    public void ungueltigeEingabe() {
        System.out.println("Das war leider eine ungültige Eingabe");
    }

    public void zeigeHandkarten(ArrayList<Spielkarte> handkarten) {
        System.out.println("Das sind deine Handkarten:");
        for (Spielkarte karte : handkarten) {
            System.out.println(karte);
        }
    }

    public void zeigeWelcherSpieler(int wieVielterSpieler) {
        System.out.println("Du bist der " + wieVielterSpieler + ". Spieler.");
    }

    public void spielArtEntschieden(int spieler, String ausgabe) {
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

    public void rundeGewonnen(WelcherSpieler gewinner1, WelcherSpieler gewinner2, int[] punkte) {
        int gewinnerPunkte = punkte[0] + punkte[1];
        System.out.println(gewinner1.gebeName() + " und " + gewinner2.gebeName() + " haben die Runde mit " + gewinnerPunkte + " Punkten gewonnen.");
        //TODO: wie soll das der GUI übergeben werden?
    }


    //Methode wird aufgerufen, wenn Spieler auf irgendeinen Button klickt
    public void zeigeLetztenStich() {
        System.out.println(spieler.gebeLetztenStich());
    }
}