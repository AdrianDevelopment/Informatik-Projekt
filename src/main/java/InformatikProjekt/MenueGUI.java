package InformatikProjekt;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenüGUI{

    //globale Variablen erstellen
    JButton playButton;

    public int guiStart() {
        JFrame frame = new JFrame();
        frame.setTitle("Startmenue");
        frame.setSize(500, 500); //ersetzt duch frame.pack() !frame.pack() muss am ende stehen!
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.green);

        Border border = BorderFactory.createLineBorder(Color.black);


        //Hier werden Labels erstellt:
        JLabel label1 = new JLabel();
        label1.setText("Willkommen!");
        label1.setVerticalAlignment(JLabel.TOP);
        label1.setHorizontalAlignment(JLabel.CENTER);
        label1.setFont(new Font("My Boli",Font.PLAIN, 40));
        label1.setBounds(75,0,350,40);

        //Hier werden Buttons erstellt:
        playButton = new JButton();
        playButton.addActionListener( e -> System.out.println("hi"));
        playButton.addActionListener(e -> Menue.startMainGame());
        playButton.setBounds(150,50,200,40);
        playButton.setText("SPIELEN");
        playButton.setFocusable(false);

        //Hier wird der Frame bearbeitet
        frame.setLayout(null);
        frame.add(label1);
        frame.add(playButton);


        return 0;
    }

}
