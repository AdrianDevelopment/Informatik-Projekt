package InformatikProjekt;

public class Menü {
    private MenüGUI menüGUI = new MenüGUI();
    private Tunier tunier;

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
