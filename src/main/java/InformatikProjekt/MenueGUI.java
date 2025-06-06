package InformatikProjekt;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MenueGUI {

    //globale Variablen erstellen
    JButton playButton;
    private Spieler spieler;
    private Menue menue;
    private ImageIcon imageIcon = new ImageIcon("src\\main\\resources\\hintergrundMenueGUI.png");

    public MenueGUI(Menue menue) {
        this.menue = menue;
    }

    //im Fenster wird dadurch alles angezeigt, ohne Vergrößerung
    public void guiStart() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                init();
            }
        });
    }

    public void init() {
        JFrame frame = new JFrame();
        frame.setTitle("Startmenue");
        frame.setSize(889, 500); //ersetzt duch frame.pack() !frame.pack() muss am ende stehen!
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        //frame.setBackground(Color.GREEN);
        JLabel lblHintergrund = new JLabel(imageIcon);
        lblHintergrund.setOpaque(false);
        frame.setContentPane(lblHintergrund);

        Border border = BorderFactory.createLineBorder(Color.black);


        //Hier werden Labels erstellt:
        JLabel label1 = new JLabel();
        label1.setText("Willkommen!");
        label1.setVerticalAlignment(JLabel.TOP);
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setFont(new Font("My Boli", Font.PLAIN, 40));
        label1.setBounds(269, 0, 350, 50);

        //Hier werden Buttons erstellt:
        JButton playButton = new JButton();
        playButton.addActionListener(e -> menue.setzeMenueAuswahl(1));
        // playButton.addActionListener(e -> Menue.startMainGame());
        playButton.setBounds(344, 60, 200, 40);
        playButton.setText("SPIELEN");
        playButton.setFocusable(false);

        JButton statistikButton = new JButton();
        statistikButton.addActionListener(e -> new StatistikGUI());
        statistikButton.setBounds(344, 120, 200, 40);
        statistikButton.setText("STATISTIK");
        statistikButton.setFocusable(false);


        //Hier wird der Frame bearbeitet
        frame.setLayout(null);
        frame.add(label1);
        frame.add(playButton);
        frame.add(statistikButton);

    }

}