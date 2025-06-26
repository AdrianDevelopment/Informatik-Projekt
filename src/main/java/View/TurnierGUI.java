package View;

import Controler.Menue;

import javax.swing.*;
import java.awt.*;

public class TurnierGUI {
    private Menue menue;
    private JFrame frame;
    private JLabel label;
    private JTextField eingabefeld;
    private JButton knopf;
    public TurnierGUI(Menue menue){
        this.menue = menue;
        frame = new JFrame("Turnier starten");

        frame.setLayout(null);
        frame.setBounds(0,0,800,140);
        label = new JLabel("Anzahl der Runden eingeben:");
        label.setBounds(0,0,300,50);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("My Boli", Font.PLAIN, 20));
        label.setVisible(true);
        frame.add(label);

        eingabefeld = new JTextField(20);
        eingabefeld.setBounds(300,0,500,50);
        eingabefeld.setVisible(true);
        eingabefeld.setFont(new Font("My Boli", Font.PLAIN, 20));
        label.setHorizontalAlignment(JLabel.LEFT);
        frame.add(eingabefeld);

        knopf = new JButton("Turnier starten");
        knopf.setBounds(0,50,800,50);
        knopf.setVisible(true);
        knopf.setFont(new Font("My Boli", Font.PLAIN, 20));
        knopf.setHorizontalAlignment(JLabel.CENTER);
        knopf.addActionListener(e -> kopf_gedrueckt());
        frame.add(knopf);
        frame.setVisible(true);
    }
    private void kopf_gedrueckt(){
        Integer i;
        try{
            i = Integer.valueOf(eingabefeld.getText());
        }catch (NumberFormatException e){
            return;
        }
        int anzahlRunden = i.intValue();
        if (anzahlRunden<1||anzahlRunden>16)return;
        menue.turnierStarten(anzahlRunden);
        frame.dispose();
    }
}
