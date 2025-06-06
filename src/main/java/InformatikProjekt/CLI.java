package InformatikProjekt;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class CLI {
    private final Scanner benutzerEingabe;

    public CLI() {
        benutzerEingabe = new Scanner(System.in);
    }

    public void rundeStarten(int positionSpieler) {
        System.out.println("Herzlich Willkommen zu einer Runde Schafkopf!");
        System.out.println("Sie sind Spieler Nr. " + (positionSpieler + 1) + ".");
    }

    // Zeigt die Handkarten des Spielers an
    public void zeigeHandkarten(ArrayList<Spielkarte> spielkarten) {
        System.out.println("---- Ihre Handkarten ----");
        for (Spielkarte spielkarte : spielkarten) {
            System.out.println(spielkarte.gebeFarbe() + " | " + spielkarte.gebeWert());
        }
        System.out.println("-------------------------");
    }

    // Fragt die Spielabsicht des Benutzers ab und gibt die gewählte Spielart zurück.
    public SpielArt spielabsichtFragen(SpielArt hoechstesSpiel) {
        System.out.println("Momentan höchstes Spiel: " + hoechstesSpiel);
        System.out.print("Wollen Sie spielen [Weiter], [Sau]> ");

        while (true) {
            String spielart = benutzerEingabe.nextLine().toLowerCase();

            switch (spielart) {
                case "weiter":
                    return SpielArt.KEINSPIEL;
                case "sau":
                    return SpielArt.SAUSPIEL;
                default:
                    System.out.println("Bitte geben Sie 'Weiter' oder 'Sau' ein.");
            }
        }
    }

    public void spielerHatSpielerabsichtGesagt(SpielArt spielAbsicht, WelcherSpieler welcherSpieler) {
        if ((welcherSpieler.gebeName().equals("Du")))
        {
            System.out.println("Du hast " + spielAbsicht + " ausgerufen.");
        }
        else {
            System.out.println(welcherSpieler.gebeName() + " hat " + spielAbsicht + " ausgerufen.");
        }
    }

    // Fragt die Farbe ab, auf welche die Sau gespielt werden soll.
    public Farbe farbeFuerSpielAbsicht() {
        System.out.print("Wählen Sie eine Farbe für die Sau [Schellen], [Eichel], [Gras]> ");

        while (true) {
            String eingabe = benutzerEingabe.nextLine().toUpperCase();

            for (Farbe farbe : Farbe.values()) {
                if (farbe.name().equals(eingabe)) {
                    return farbe;
                }
            }

            System.out.println("Ungültige Eingabe. Bitte wählen Sie eine gültige Farbe.");
        }
    }

    public void spielerHatFarbeGesagt(Farbe farbe, WelcherSpieler welcherSpieler) {
        if (welcherSpieler.gebeName().equals("Du")) {
            System.out.println("Du spielst auf die " + farbe + "-Sau.");
        }
        else {
            System.out.println(welcherSpieler.gebeName() + " spielt auf die " + farbe + "-Sau.");
        }
    }

    // Lässt den Benutzer eine Karte aus seiner Hand auswählen.
    public Spielkarte legeEineKarte(ArrayList<Spielkarte> spielkarten) {
        System.out.println("---- Wählen Sie eine Karte ----");

        for (int i = 0; i < spielkarten.size(); i++) {
            System.out.println((i + 1) + ": " + spielkarten.get(i).gebeFarbe() + " | " + spielkarten.get(i).gebeWert());
        }

        System.out.print("Geben Sie die entsprechende Zahl ein> ");

        while (true) {
            try {
                int auswahl = benutzerEingabe.nextInt();
                benutzerEingabe.nextLine(); // Eingabe-Puffer säubern

                if (auswahl > 0 && auswahl <= spielkarten.size()) {
                    return spielkarten.get(auswahl - 1);
                } else {
                    System.out.println("Ungültige Eingabe. Bitte geben Sie eine Zahl zwischen 1 und " + spielkarten.size() + " ein.");
                }
            } catch (Exception e) {
                System.out.println("Ungültige Eingabe. Bitte geben Sie eine gültige Zahl ein.");
                benutzerEingabe.nextLine(); // Eingabe-Puffer löschen
            }
        }
    }

    public void zeigeGelegteKarte(Spielkarte karte, WelcherSpieler spielerHatGelegt) {
        if (spielerHatGelegt.gebeName().equals("Du")) {
            System.out.println("Sie haben die Karte " + karte.gebeFarbe() + " | " + karte.gebeWert() + " gelegt.");
        }
        else {
            System.out.println(spielerHatGelegt.gebeName() + " hat die Karte " + karte.gebeFarbe() + " | " + karte.gebeWert() + " gelegt.");
        }
    }

    public void stichGewonnen(WelcherSpieler spieler) {
        if (spieler.gebeName().equals("Du")) {
            System.out.println("Sie haben den Stich gewonnen!");
        }
        else {
            System.out.println(spieler.gebeName() + " hat den Stich gewonnen!");
        }
    }

    public void rundeGewonnen(WelcherSpieler sieger1, WelcherSpieler sieger2, int[] punkte) {
        if (punkte.length >= 2) {
            int gewinnerPunkte = punkte[0] + punkte[1];
            System.out.println("Spieler " + sieger1 + " und Spieler " + sieger2 + " haben die Runde mit " + gewinnerPunkte + " Punkten gewonnen.");
        } else {
            System.out.println("Fehler: Ungültige Übergabe der Punkte oder Gewinner.");
        }
        System.out.println("----------");
    }

    public void zeigeLetztenStich(ArrayList<Spielkarte> letzterStich) {
        System.out.println("---- Letzter Stich ----");
        for (Spielkarte spielkarte : letzterStich) {
            System.out.println(spielkarte.gebeFarbe() + " | " + spielkarte.gebeWert());
        }
        System.out.println("------------------------");
    }

    public void ungueltigeEingabe(String ausgabe) {
        System.out.println("Ungültige Eingabe: " + ausgabe);
    }

    public void keinSpiel() {
        System.out.println("Es wurde kein Spiel ausgerufen!");
        System.out.println("------------------------------");
    }
}