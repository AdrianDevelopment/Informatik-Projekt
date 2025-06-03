package InformatikProjekt;

// Programmierer: Adrian

public class Menue {
    private final MenueGUI menueGUI;
    private Spieler spieler;

    public Menue() {
        spieler = new Spieler();
        menueGUI = new MenueGUI(spieler);
    }

    public void starteGame() {
        int menueAuswahl = menueGUI.guiStart();
        switch (menueAuswahl) {
            case 0:
                break;
            case 1:
                Tunier tunier = new Tunier(4, spieler); // Anzahl der Runden hier hardcoded, kann in Zukunft erweitert werden
                tunier.tunierStarten();
                break;
            case 2:
                // Mehrspieler
        }
    }
}
