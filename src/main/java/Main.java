// Programmierer: Adrian

import Controler.Menue;
import Model.Speicherung;
import View.MenueGUI;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Registriert einen Shutdown Hook, der die Daten beim Beenden der Anwendung speichert.
        // Dies ist der moderne und zuverlässige Ersatz für die veraltete finalize()-Methode.
        Runtime.getRuntime().addShutdownHook(new Thread(() -> Speicherung.speicherungErstellen().DatenSpeichern()));

        SwingUtilities.invokeLater(() -> {
            Menue menue = new Menue(new MenueGUI());
            menue.menueGUIIntialisieren();
        });
    }
}