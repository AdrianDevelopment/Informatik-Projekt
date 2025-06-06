package InformatikProjekt;

import java.util.ArrayList;
import java.util.Scanner;

//Programmierer: Tom und Robin

public class SpielerGUI {
    private Scanner benutzerEingabe;
    private Spieler spieler;
    private Spielkarte[] karten;

    public SpielerGUI(Spieler spieler) {
        this.spieler = spieler;
        benutzerEingabe = new Scanner(System.in);
    }

    //gui-Methode, die Benutzer nach Spielabsicht frägt und als String zurückgibt
    public String spielabsichtFragen() {
        System.out.println("Das aktuell höchste Spiel ist: Gib bitte deine Spielabsicht ein, also entweder 'weiter', 'Sau', 'Wenz' oder 'Solo'");
        return benutzerEingabe.nextLine();
        //Handkarten müssen währenddessen angezeigt werden + die bisher höchste Spielart und von wem ausgerufen;
    }

    public Farbe farbeFuerSpielAbsicht() {
        System.out.println("Spielabsicht-Farbe eingeben(Schellen,Eichel,Herz,Gras):");
        String spielabsicht = benutzerEingabe.nextLine().toUpperCase();
        for (Farbe farbe : Farbe.values()){
            if (farbe.name().toUpperCase().equals(spielabsicht))return  farbe;
        }
        System.out.println("Ungültige Farbe");
        return  farbeFuerSpielAbsicht();
    }


    public void spielerHatSpielerabsichtGesagt(SpielArt spielAbsicht, WelcherSpieler welcherSpieler) {
        System.out.println(welcherSpieler.gebeName() + " hat " + spielAbsicht + " ausgerufen.");
    }

    public void ungueltigeEingabe(String konkretisierung) {
        System.out.println("Das war leider eine ungültige Eingabe. " + konkretisierung);
    }

    public void zeigeHandkarten(ArrayList<Spielkarte> handkarten) {
        karten = new Spielkarte[handkarten.size()];
        System.out.println("Das sind deine Handkarten:");
        for (int i=0;i<handkarten.size();++i) {
            Spielkarte karte = handkarten.get(i);
            System.out.println(karte);
            karten[i]=karte;
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
        System.out.println("Wähle aus den folgenden Karten und gib die Zahl ein:");
        for (int i=0;i<karten.length;++i){
            if (karten[i]!=null)System.out.println(i + " : " + karten[i]);
        }
        while (true){
            int index = benutzerEingabe.nextInt(10);
            if (index < karten.length && index>(-1) && karten[index]!=null)return karten[index];
        }
    }

    public void zeigeGelegteKarte(Spielkarte karte, WelcherSpieler spielerHatGelegt) {
        System.out.println(spielerHatGelegt.gebeName() + " hat die Karte " + karte + " gelegt.");
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
        ArrayList<Spielkarte> karten = spieler.gebeLetztenStich();
        for (Spielkarte i : karten){
            System.out.println(i);
        }
    }

}
