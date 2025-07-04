// Programmierer: Adrian

import Controler.Menue;
import View.MenueGUI;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menue menue = new Menue(new MenueGUI());
            menue.menueGUIIntialisieren();
        });
    }
}