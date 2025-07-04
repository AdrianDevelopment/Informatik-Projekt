package View;

// Code von Robin

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Model.Speicherung;
import Model.TurnierStatistik;

public class TurnierHistorieGUI {
    // Wird niemals erstellt -> beinhaltet nur statische "TurnierStatistikAnzeigen"-Methode
    public static void TurnierStatistikAnzeigen(){
        // Fenster erstellen
        JFrame frame = new JFrame("Turnierhistorie");
        frame.setSize(500,500);
        // Turniere aus Statistiken lesen
        ArrayList<TurnierStatistik> statistiken =
                Model.Speicherung.speicherungErstellen().gebeAlteTurnierstatistiken();
        String[] turniereTexte = new String[statistiken.size()];
        for (int i=0;i<turniereTexte.length;++i){
            // Text aus TurnierStatistik-Objekt erstellen
            String aktuellerText;
            TurnierStatistik aktuelleStatistik = statistiken.get(i);
            if (aktuelleStatistik.wurdeGewonnen()){
                aktuellerText = "Gewonnen";
            }else{
                aktuellerText = "Verloren";
            }
            aktuellerText += " mit " + aktuelleStatistik.punkteGeben();
            aktuellerText += " Punkten. ";
            aktuellerText += aktuelleStatistik.DatumGeben().format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
            aktuellerText += " ";
            aktuellerText += aktuelleStatistik.ZeitGeben().format(DateTimeFormatter.ofPattern("HH:mm"));
            // Umkehren: in statistiken-ArrayList ist das älteste Spiel am Anfang, in der Anzeige soll es das neueste sein
            turniereTexte[turniereTexte.length - i - 1] = aktuellerText;
        }
        // Liste mit Scrollleiste aus String-Array erstellen und anzeigen
        JList<String> liste = new JList<>(turniereTexte);
        liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane ScrollLeiste = new JScrollPane(liste);
        frame.add(ScrollLeiste);
        frame.setVisible(true);
    }
}
