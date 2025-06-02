package InformatikProjekt;

import javax.swing.*;
import java.awt.*;

public class StatistikGUI {

    public StatistikGUI() {
        JFrame Statistik = new JFrame("Statistik");
        Statistik.setSize(500, 500);
        Statistik.setVisible(true);
        Statistik.setDefaultCloseOperation(Statistik.EXIT_ON_CLOSE);
        Statistik.setResizable(false);
        Statistik.setLayout(null);

        //Labels
        JLabel ueberschriftLabel = new JLabel();
        ueberschriftLabel.setText("Statistik");
        ueberschriftLabel.setVerticalAlignment(JLabel.TOP);
        ueberschriftLabel.setHorizontalAlignment(JLabel.CENTER);
        ueberschriftLabel.setFont(new Font("My Boli",Font.PLAIN, 40));
        ueberschriftLabel.setBounds(75,0,350,40);

        //Frame
        Statistik.add(ueberschriftLabel);

    }


}
