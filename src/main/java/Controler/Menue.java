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

    public void gUIIntialisieren(){
        menueGUI.gibStatistikButton().addActionListener(e -> new StatistikGUI());
        menueGUI.gibPlayButton().addActionListener(e -> menueGUIRueckgabe(1));
    }

    public void menueGUIRueckgabe(int menueAuswahl) {
        switch (menueAuswahl) {
            case 0:
                break;
            case 1:
                new TurnierGUI(this);
                break;
            case 2:
                // Mehrspieler
                break;
            default:
                System.out.println("ERROR: Fehler bei MenueAuswahl");
                break;
        }
    }

    public void turnierStarten(int anzahlRunden) {
        Turnier tunier = new Turnier(anzahlRunden); // Anzahl der Runden hier hardcoded, kann in Zukunft erweitert werden
        tunier.rundeStarten(null,0);
    }
}
