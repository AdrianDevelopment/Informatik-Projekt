package InformatikProjekt;

// Programmierer: Adrian

public class Menue {
    private final MenueGUI menueGUI;
    private Spieler echterSpieler;

    public Menue() {
        echterSpieler = new Spieler();
        menueGUI = new MenueGUI(echterSpieler);
    }

    public void starteGame() {
        int menueAuswahl = menueGUI.guiStart();
        switch (menueAuswahl) {
            case 0:
                break;
            case 1:
                Tunier tunier = new Tunier(4, echterSpieler); // Anzahl der Runden hier hardcoded, kann in Zukunft erweitert werden
                tunier.tunierStarten();
                break;
            case 2:
                // Mehrspieler
        }
    }
}
