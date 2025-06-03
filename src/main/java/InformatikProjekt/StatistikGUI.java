package InformatikProjekt;

import javax.swing.*;
import java.awt.*;

public class StatistikGUI {
    
    private Speicherung speicherung;
    
    public StatistikGUI() {
        speicherung = new Speicherung();
        
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

        JLabel spielerGesamtLabel = new JLabel();
        spielerGesamtLabel.setText("Gewonnene Spiele: " + speicherung.gewonneneSpieleGeben());
        spielerGesamtLabel.setBounds(75, 75, 350, 50);
        spielerGesamtLabel.setHorizontalAlignment(JLabel.CENTER);
        spielerGesamtLabel.setFont(new Font("My Boli", Font.PLAIN, 20));

        //Frame
        Statistik.add(ueberschriftLabel);
        Statistik.add(spielerGesamtLabel);

    }


}
