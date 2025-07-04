package View;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Model.Speicherung;
import Model.TurnierStatistik;

public class TurnierHistorieGUI {
    public static void TurnierStatistikAnzeigen(){
        JFrame frame = new JFrame("Turnierhistorie");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        ArrayList<TurnierStatistik> statistiken =
                Model.Speicherung.speicherungErstellen().gebeAlteTurnierstatistiken();
        String[] turniereTexte = new String[statistiken.size()];
        for (int i=0;i<turniereTexte.length;++i){
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
            turniereTexte[turniereTexte.length - i - 1] = aktuellerText;
        }
        JList<String> liste = new JList<>(turniereTexte);
        liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane ScrollLeiste = new JScrollPane(liste);
        frame.add(ScrollLeiste);
        frame.setVisible(true);
    }
}
