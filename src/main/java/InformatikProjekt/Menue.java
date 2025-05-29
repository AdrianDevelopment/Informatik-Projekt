package InformatikProjekt;

// Programmierer: Adrian

public class Menue {
    private final MenueGUI menueGUI;

    public Menue() {
        menueGUI = new MenueGUI();
    }

    public void starteGame() {
        int menueAuswahl = menueGUI.guiStart();
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
