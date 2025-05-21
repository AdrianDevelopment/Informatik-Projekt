package InformatikProjekt;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class SpielerGUI {
    private Scanner benutzerEingabe;

    public SpielerGUI() {
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
}