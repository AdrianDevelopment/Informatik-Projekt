package InformatikProjekt;

public class Menue {
    private MenueGUI menueGUI;
    private Tunier tunier;

    public Menue() {
        menueGUI = new MenueGUI();
    }

    public void starteGame() {
        int menueAuswahl = menueGUI.guiStart();
        switch (menueAuswahl) {
            case 0:
                break;
            case 1:
                tunier = new Tunier();
                break;
            case 2:
                // Mehrspieler
        }
    }
}
