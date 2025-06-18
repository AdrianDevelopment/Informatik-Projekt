package Controler;

// Programmierer: Adrian

import View.MenueGUI;
import View.StatistikGUI;

public class Menue {
    //private final Spieler echterSpieler;
    MenueGUI menueGUI;
    public Menue(MenueGUI menueGUI) {

        this.menueGUI = menueGUI;
        //echterSpieler = new Spieler();
    }
    public void uiIntialisieren(){
        menueGUI.gibStatistikButton().addActionListener(e -> new StatistikGUI());
        menueGUI.gibPlayButton().addActionListener(e -> starteGame(1));
    }

    public void starteGame(int menueAuswahl) {
        switch (menueAuswahl) {
            case 0:
                break;
            case 1:
                Turnier tunier = new Turnier(4); // Anzahl der Runden hier hardcoded, kann in Zukunft erweitert werden
                tunier.rundeStarten(0, new int[]{-1, -1});

                break;
            case 2:
                // Mehrspieler
        }
    }
}
