package View;

// Programmierer: Adrian

import Controler.Turnier;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class TurnierPunkteGUI {
    private final JFrame frame;
    private final JPanel gridPanel;

    private final JLabel label1;
    private final JLabel label2;
    private final JLabel label3;
    private final JLabel label4;

    private final JLabel labelPunkte1;
    private final JLabel labelPunkte2;
    private final JLabel labelPunkte3;
    private final JLabel labelPunkte4;

    private final JButton neueRundeButton;
    private final JButton turnierBeendenButton;
    private final JPanel buttonPanel;


    public TurnierPunkteGUI() {
        frame = new JFrame();
        gridPanel = new JPanel(new GridLayout(2, 4, 20, 20));

        frame.setTitle("Turnier-Punkte Übersicht");
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setSize(600, 300);

        gridPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        frame.add(gridPanel, BorderLayout.CENTER);

        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();

        labelPunkte1 = new JLabel();
        labelPunkte2 = new JLabel();
        labelPunkte3 = new JLabel();
        labelPunkte4 = new JLabel();

        neueRundeButton = erstelleSchoenenButton("Neue Runde", 100, 200, 100, 50);
        turnierBeendenButton = erstelleSchoenenButton("Turnier Beenden", 250, 200, 100, 50);
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        gridPanel.add(label1);
        gridPanel.add(label2);
        gridPanel.add(label3);
        gridPanel.add(label4);

        gridPanel.add(labelPunkte1);
        gridPanel.add(labelPunkte2);
        gridPanel.add(labelPunkte3);
        gridPanel.add(labelPunkte4);

        buttonPanel.add(neueRundeButton);
        buttonPanel.add(turnierBeendenButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void turnierPunkteGUIAusfuehren(Turnier turnier, int wiederholungRunden, int[] turnierPunkte) {
        label1.setText("<html>Punke: " + turnier.wieVielterSpieler(0).gebeName() + "<html>");
        label1.setFont(new Font("Arial", Font.BOLD, 16));
        label2.setText("<html>Punkte: " + turnier.wieVielterSpieler(1).gebeName() + "<html>");
        label2.setFont(new Font("Arial", Font.BOLD, 16));
        label3.setText("<html>Punkte: " + turnier.wieVielterSpieler(2).gebeName() + "<html>");
        label3.setFont(new Font("Arial", Font.BOLD, 16));
        label4.setText("<html>Punkte: " + turnier.wieVielterSpieler(3).gebeName() + "<html>");
        label4.setFont(new Font("Arial", Font.BOLD, 16));

        labelPunkte1.setText(String.valueOf(turnierPunkte[0]));
        labelPunkte1.setFont(new Font("Arial", Font.BOLD, 16));
        labelPunkte1.setHorizontalAlignment(SwingConstants.CENTER);
        labelPunkte1.setVerticalAlignment(SwingConstants.CENTER);

        labelPunkte2.setText(String.valueOf(turnierPunkte[1]));
        labelPunkte2.setFont(new Font("Arial", Font.BOLD, 16));
        labelPunkte2.setHorizontalAlignment(SwingConstants.CENTER);
        labelPunkte2.setVerticalAlignment(SwingConstants.CENTER);

        labelPunkte3.setText(String.valueOf(turnierPunkte[2]));
        labelPunkte3.setFont(new Font("Arial", Font.BOLD, 16));
        labelPunkte3.setHorizontalAlignment(SwingConstants.CENTER);
        labelPunkte3.setVerticalAlignment(SwingConstants.CENTER);

        labelPunkte4.setText(String.valueOf(turnierPunkte[3]));
        labelPunkte4.setFont(new Font("Arial", Font.BOLD, 16));
        labelPunkte4.setHorizontalAlignment(SwingConstants.CENTER);
        labelPunkte4.setVerticalAlignment(SwingConstants.CENTER);

        neueRundeButton.setText("neue Runde");
        neueRundeButton.addActionListener(e -> turnier.rundeStarten());
        turnierBeendenButton.setText("Beenden");
        turnierBeendenButton.addActionListener(e -> turnier.turnierBeenden());

        buttonPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

        if (wiederholungRunden != turnier.gebeAnzahlRunden()) {
            neueRundeButton.setVisible(true);
            turnierBeendenButton.setVisible(false);
        }
        else {
            neueRundeButton.setVisible(false);
            turnierBeendenButton.setVisible(true);
        }
    }

    public void turnierPunkteGUISichtbarkeit(boolean sichtbarkeit) {
        frame.setVisible(sichtbarkeit);
    }

    // Kopie SpielGUI (Thiemo)
    public JButton erstelleSchoenenButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);

        button.setFocusPainted(false);
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.setBackground(new Color(25, 25, 112));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));

        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 139), 2, true),
                BorderFactory.createEmptyBorder(8, 20, 8, 20)
        ));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 0, 205));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(25, 25, 112));
            }
        });

        return button;
    }
    // Kopie Ende
}