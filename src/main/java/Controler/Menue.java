package Controler;

// Programmierer: Adrian

import View.MenueGUI;
import View.StatistikGUI;
import View.TurnierGUI;

public class Menue {
    private final MenueGUI menueGUI;

    public Menue(MenueGUI menueGUI) {
        this.menueGUI = menueGUI;
    }

    public void menueGUIIntialisieren() {
        menueGUI.gibStatistikButton().addActionListener(_ -> new StatistikGUI());
        menueGUI.gibPlayButton().addActionListener(_ -> menueGUIRueckgabe(1));
        menueGUI.gibBeendenButton().addActionListener(_ -> menueGUIRueckgabe(0));
    }

    public void menueGUIRueckgabe(int menueAuswahl) {
        switch (menueAuswahl) {
            case 0:
                System.exit(0);
                break;
            case 1:
                new TurnierGUI(this);
                break;
            case 2:
                // Mehrspieler
                break;
            default:
                System.out.println("ERROR: Fehler bei der Menü-Auswahl");
                break;
        }
    }

    public void turnierStarten(int anzahlRunden) {
        Turnier tunier = new Turnier(anzahlRunden);
        tunier.rundeStarten();
    }
}
