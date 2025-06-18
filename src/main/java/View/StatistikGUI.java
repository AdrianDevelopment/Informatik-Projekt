package View;

import Controler.Speicherung;

import javax.swing.*;
import java.awt.*;

public class StatistikGUI {

    private Speicherung speicherung;

    private void TextAnzeigen(JLabel[] labels, int index, String text){
        JLabel neuesLabel=new JLabel();
        neuesLabel.setText(text);
        neuesLabel.setBounds(75,50+(index*29),350,29);
        neuesLabel.setHorizontalAlignment(JLabel.CENTER);
        neuesLabel.setFont(new Font("My Boli", Font.PLAIN, 20));
        labels[index]=neuesLabel;
    }

    public StatistikGUI() {
        speicherung = Speicherung.speicherungErstellen();

        JFrame Statistik = new JFrame("Statistiken");
        Statistik.setSize(500, 500);
        Statistik.setVisible(true);
        //Statistik.setDefaultCloseOperation(Statistik.EXIT_ON_CLOSE);
        Statistik.setResizable(false);
        Statistik.setLayout(null);

        //Labels
        JLabel ueberschriftLabel = new JLabel();
        ueberschriftLabel.setText("Statistiken");
        ueberschriftLabel.setVerticalAlignment(JLabel.TOP);
        ueberschriftLabel.setHorizontalAlignment(JLabel.CENTER);
        ueberschriftLabel.setFont(new Font("My Boli",Font.PLAIN, 40));
        ueberschriftLabel.setBounds(75,2,350,50);

        JLabel[] alleLabels = new JLabel[14];

        TextAnzeigen(alleLabels,0,"Spiele:");
        TextAnzeigen(alleLabels,1,"Gespielte Spiele: " + speicherung.gespielteSpieleGeben());
        TextAnzeigen(alleLabels,2,"Gewonnene Spiele: " + speicherung.gewonneneSpieleGeben());
        TextAnzeigen(alleLabels,3,"Verlorene Spiele: " + speicherung.verloreneSpieleGeben());

        TextAnzeigen(alleLabels,5,"Turniere:");
        TextAnzeigen(alleLabels,6,"Gespielte Turniere: " + speicherung.gespielteTurniereGeben());
        TextAnzeigen(alleLabels,7,"Gewonnene Turniere: " + speicherung.gewonneneTurniereGeben());
        TextAnzeigen(alleLabels,8,"Verlorene Turniere: " + speicherung.verloreneTurniereGeben());

        TextAnzeigen(alleLabels,10,"Sonstige:");
        TextAnzeigen(alleLabels,11,"Gespielte Karten: " + speicherung.gespielteKartenGeben());
        TextAnzeigen(alleLabels,12,"Höchste Punktzahl (Runde): " + speicherung.hoechstePunktzahlRundeGeben());
        TextAnzeigen(alleLabels,13,"Gesamte Punktzahl: " + speicherung.gesamtePunkteGeben());

        /*JLabel spielerGesamtLabel = new JLabel();
        spielerGesamtLabel.setText("Gewonnene Spiele: " + speicherung.gewonneneSpieleGeben());
        spielerGesamtLabel.setBounds(75, 75, 350, 50);
        spielerGesamtLabel.setHorizontalAlignment(JLabel.CENTER);
        spielerGesamtLabel.setFont(new Font("My Boli", Font.PLAIN, 20));*/

        //Frame
        Statistik.add(ueberschriftLabel);
        //Statistik.add(spielerGesamtLabel);
        for (int i=0;i<alleLabels.length;++i){
            if (alleLabels[i]!=null)
                Statistik.add(alleLabels[i]);
        }

    }


}
