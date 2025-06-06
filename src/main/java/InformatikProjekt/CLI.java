package InformatikProjekt;

import java.util.ArrayList;
import java.util.Scanner;

//Programmierer: Tom, Robin und Adrian

public class CLI {
    private final Scanner benutzerEingabe;

    public CLI() {
        benutzerEingabe = new Scanner(System.in);
    }

    public void rundeStarten(int positionSpieler) {
        System.out.println("Herzlich Willkommen einer Runde Schafkopfen");
        System.out.println("Sie sind der " + (positionSpieler + 1) + "te Spieler");
    }

    // Handkarten anzeigen
    public void zeigeHandkarten(ArrayList<Spielkarte> spielkarten) {
        System.out.println("------");
        for (Spielkarte spielkarte : spielkarten) {
            System.out.println(spielkarte.gebeFarbe() + "|" + spielkarte.gebeWert());
        }
        System.out.println("------");
    }

    //gui-Methode, die Benutzer nach Spielabsicht frägt und als String zurückgibt
    public SpielArt spielabsichtFragen(SpielArt hoechstesSpiel) {
        System.out.println("Das aktuell höchste Spiel ist: " + hoechstesSpiel);
        System.out.print("Wollen Sie spielen [Weiter], [Sau]> ");
        String spielart = benutzerEingabe.nextLine().toLowerCase();

        if (spielart.equals("weiter")) {
            return SpielArt.KEINSPIEL;
        }
        else if (spielart.equals("sau")) {
            return SpielArt.SAUSPIEL;
        }
        else {
            System.out.println("Gib bitte [Weiter] oder [Sau] als Antwort ein.");
            return spielabsichtFragen(hoechstesSpiel);
        }

        //Handkarten müssen währenddessen angezeigt werden + die bisher höchste Spielart und von wem ausgerufen;
    }

    public void spielerHatSpielerabsichtGesagt(SpielArt spielAbsicht, WelcherSpieler welcherSpieler) {
        System.out.println(welcherSpieler.gebeName() + " hat " + spielAbsicht + " ausgerufen.");
    }

    public Farbe farbeFuerSpielAbsicht() {
        System.out.print("Auf welche Sau wollen Sie spielen [Schellen], [Eichel], [Gras]> ");
        String spielabsicht = benutzerEingabe.nextLine().toUpperCase();

        for (Farbe farbe : Farbe.values()){
            if (farbe.name().toUpperCase().equals(spielabsicht)) return  farbe;
        }
        System.out.println("Geben Sie bitte eine gültige Farbe ein");
        return farbeFuerSpielAbsicht();
    }

    public void spielerHatFarbeGesagt(Farbe farbe, WelcherSpieler welcherSpieler) {
        System.out.println(welcherSpieler.gebeName() + " spielt auf die " + farbe + "-Sau.");
    }

    public Spielkarte legeEineKarte(ArrayList<Spielkarte> spielkarten) {
        for (int i = 0; i < spielkarten.size(); i++){
            System.out.println((i + 1) + ": " + spielkarten.get(i).gebeFarbe() + "|" + spielkarten.get(i).gebeWert());
        }
        System.out.print("Bitte wähle aus den obigen Karten eine aus und gebe die entsprechende Zahl ein>");
        while (true){
            try {
                int index = benutzerEingabe.nextInt();
                if (index <= spielkarten.size() && index > 1 && spielkarten.get(index - 1) != null)
                    return spielkarten.get(index - 1);
            } catch (Exception e) {
                System.out.println("Bitte gebe eine Zahl zwischen 1 und " + spielkarten.size() + " ein.");
            }
        }
    }

    public void zeigeGelegteKarte(Spielkarte karte, WelcherSpieler spielerHatGelegt) {
        System.out.println(spielerHatGelegt.gebeName() + " hat die Karte " + karte + " gelegt.");
    }

    public void stichGewonnen(WelcherSpieler spieler) {
        System.out.println(spieler.gebeName() + " hat den Stich gewonnen.");
    }

    public void rundeGewonnen(int[] punkte, int[] sieger) {
        int gewinnerPunkte = punkte[0] + punkte[1];
        System.out.println(sieger[0] + " und " + sieger[1] + " haben die Runde mit " + gewinnerPunkte + " Punkten gewonnen.");
        //TODO: wie soll das der GUI übergeben werden?
    }


    //Methode wird aufgerufen, wenn Spieler auf irgendeinen Button klickt
    public void zeigeLetztenStich(ArrayList<Spielkarte> letzerStich) {
        for (Spielkarte spielkarte : letzerStich){
            System.out.println(spielkarte.gebeFarbe() + "|" + spielkarte.gebeWert());
        }
    }

    public void ungueltigeEingabe(String ausgabe) {
        System.out.println(ausgabe);
    }

    public void keinSpiel() {
        System.out.println("Es kam kein Spiel zu stande!");
        System.out.println("----------");
    }
}
