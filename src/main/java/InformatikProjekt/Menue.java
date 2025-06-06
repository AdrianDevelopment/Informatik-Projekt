package InformatikProjekt;

import java.util.Scanner;

public class Menue {
    private final Spieler echterSpieler;

    public Menue() {
        echterSpieler = new Spieler();
        zeigeMenue();
    }

    // Zeigt das Hauptmenü an und verarbeitet die Benutzereingabe
    public void zeigeMenue() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("----- Hauptmenü -----");
            System.out.println("1. Starte Turnier");
            System.out.println("2. Mehrspieler-Modus (in Arbeit)");
            System.out.println("0. Spiel beenden");
            System.out.print("Bitte wählen Sie eine Option: ");

            try {
                int auswahl = Integer.parseInt(scanner.nextLine());

                if (auswahl == 0) {
                    System.out.println("Spiel wird beendet. Auf Wiedersehen!");
                    break;
                }

                starteGame(auswahl);
            } catch (NumberFormatException e) {
                System.out.println("Ungültige Eingabe! Bitte geben Sie eine Zahl ein (0, 1 oder 2).");
            }
        }

        scanner.close();
    }

    // Startet das Spiel basierend auf der Menüauswahl
    public void starteGame(int menueAuswahl) {
        switch (menueAuswahl) {
            case 1:
                starteTunier();
                break;
            case 2:
                starteMehrspielerModus();
                break;
            default:
                System.out.println("Ungültige Auswahl! Bitte wählen Sie eine der angezeigten Optionen.");
                break;
        }
    }

    // Startet ein Turnier
    private void starteTunier() {
        System.out.println("Ein Turnier mit 4 Runden wird gestartet.");
        Tunier tunier = new Tunier(4, echterSpieler); // Anzahl der Runden bleibt aktuell fixiert
        tunier.tunierStarten();
    }

    // Platzhalter für zukünftige Mehrspieler-Funktionalität
    private void starteMehrspielerModus() {
        System.out.println("Der Mehrspieler-Modus ist derzeit in Arbeit. Bitte später erneut versuchen.");
    }
}