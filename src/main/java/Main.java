// Programmierer: Adrian

import Controler.Menue;
import View.MenueGUI;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Menue menue = new Menue(new MenueGUI());
                menue.uiIntialisieren();
            }
        });
    }
}