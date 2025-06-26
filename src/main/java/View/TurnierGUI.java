package View;

import Controler.Menue;

import javax.swing.*;
import java.awt.*;

public class TurnierGUI {
    Menue menue;
    JFrame frame;
    JLabel label;
    JTextField eingabefeld;
    JButton knopf;
    public TurnierGUI(Menue menue){
        this.menue = menue;
        frame = new JFrame("Turnier starten");
        frame.setBounds(0,0,200,100);
        label = new JLabel("Anzahl der Runden eingeben:");
        label.setBounds(0,0,100,50);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("My Boli", Font.PLAIN, 20));
        label.setVisible(true);
        frame.add(label);

        eingabefeld = new JTextField(20);
        eingabefeld.setBounds(100,0,100,50);
        eingabefeld.setVisible(true);
        eingabefeld.setFont(new Font("My Boli", Font.PLAIN, 20));
        label.setHorizontalAlignment(JLabel.LEFT);
        frame.add(eingabefeld);

        knopf = new JButton("OK");
        knopf.setBounds(0,50,200,50);
        knopf.setVisible(true);
        knopf.setFont(new Font("My Boli", Font.PLAIN, 20));
        knopf.setHorizontalAlignment(JLabel.CENTER);
        knopf.addActionListener(e -> kopf_gedrueckt());
        frame.add(knopf);
    }
    void kopf_gedrueckt(){
        Integer i;
        try{
            i = Integer.valueOf(eingabefeld.getText());
        }catch (NumberFormatException e){
            return;
        }
        int anzahlRunden =
    }
}
