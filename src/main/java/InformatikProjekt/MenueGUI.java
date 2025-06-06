package InformatikProjekt;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MenueGUI {

    //globale Variablen erstellen
    JButton playButton;
    JButton statistikButton;
    JLabel label1;
    JFrame frame;
    JLabel lblHintergrund;
    private Spieler spieler;
    private Menue menue;
    private ImageIcon imageIcon = new ImageIcon("src\\main\\resources\\hintergrundMenueGUI.png");




    public  MenueGUI() {
        frame = new JFrame();
        frame.setTitle("Startmenue");
        frame.setSize(889, 500); //ersetzt duch frame.pack() !frame.pack() muss am ende stehen!
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        //frame.setBackground(Color.GREEN);
        lblHintergrund = new JLabel(imageIcon);
        lblHintergrund.setOpaque(false);
        frame.setContentPane(lblHintergrund);

        Border border = BorderFactory.createLineBorder(Color.black);


        //Hier werden Labels erstellt:
        label1 = new JLabel();
        label1.setText("Willkommen!");
        label1.setVerticalAlignment(JLabel.TOP);
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setFont(new Font("My Boli", Font.PLAIN, 40));
        label1.setBounds(269, 0, 350, 50);

        //Hier werden Buttons erstellt:
        playButton = new JButton();
        // playButton.addActionListener(e -> Menue.startMainGame());
        playButton.setBounds(344, 60, 200, 40);
        playButton.setText("SPIELEN");
        playButton.setFocusable(false);

        statistikButton = new JButton();
        statistikButton.setBounds(344, 120, 200, 40);
        statistikButton.setText("STATISTIK");
        statistikButton.setFocusable(false);


        //Hier wird der Frame bearbeitet
        frame.setLayout(null);
        frame.add(label1);
        frame.add(playButton);
        frame.add(statistikButton);

    }
    JButton gibPlayButton(){
        return  playButton;
    }
    JButton gibStatistikButton(){
        return  statistikButton;
    }

}