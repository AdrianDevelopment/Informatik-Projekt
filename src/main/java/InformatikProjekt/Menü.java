package InformatikProjekt;

public class Menü {
    private MenüGUI menüGUI;
    private Tunier tunier;

    public Menü() {
        menüGUI = new MenüGUI();
    }

    public void starteGame() {
        int menüAuswahl = menüGUI.guiStart();
        switch (menüAuswahl) {
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
