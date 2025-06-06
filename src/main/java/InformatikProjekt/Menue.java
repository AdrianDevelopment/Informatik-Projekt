package InformatikProjekt;

// Programmierer: Adrian

public class Menue {
    //private final Spieler echterSpieler;

    public Menue() {

        MenueGUI menueGUI = new MenueGUI(this);
        menueGUI.guiStart();
        //echterSpieler = new Spieler();
    }

    public void setzeMenueAuswahl(int menueAuswahl) {
        starteGame(menueAuswahl);
    }

    public void starteGame(int menueAuswahl) {
        switch (menueAuswahl) {
            case 0:
                break;
            case 1:
                Tunier tunier = new Tunier(4); // Anzahl der Runden hier hardcoded, kann in Zukunft erweitert werden
                break;
            case 2:
                // Mehrspieler
        }
    }
}
