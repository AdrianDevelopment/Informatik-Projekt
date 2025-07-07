package View;

import Controler.Menue;

import javax.swing.*;
import java.awt.*;

public class TurnierGUI {

    private final Menue menue;
    private JFrame frame;
    private JTextField eingabefeld;

    public TurnierGUI(Menue menue) {
        this.menue = menue;
        erstellUndZeigeGUI();
    }

    private void erstellUndZeigeGUI() {
        // Frame-Initialisierung
        frame = new JFrame("Turnier starten");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(245, 245, 220));
        GridBagConstraints gbc = new GridBagConstraints();

        // UI-Komponenten erstellen
        JLabel label = new JLabel("Anzahl der Runden eingeben (1-16):");
        label.setFont(new Font("My Boli", Font.PLAIN, 18));

        eingabefeld = new JTextField(10);
        eingabefeld.setFont(new Font("My Boli", Font.PLAIN, 18));
        eingabefeld.setHorizontalAlignment(JTextField.CENTER);

        JButton knopf = erstelleSchoenenButton();
        knopf.addActionListener(_ -> kopfGedrueckt());

        // Komponenten zum Frame hinzufügen
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(label, gbc);

        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        frame.add(eingabefeld, gbc);

        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5, 10, 10, 10);
        frame.add(knopf, gbc);

        // Frame-Konfiguration
        frame.pack();
        frame.setLocationRelativeTo(null); // Zentriert das Fenster
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void kopfGedrueckt() {
        String inputText = eingabefeld.getText();
        int anzahlRunden;

        try {
            anzahlRunden = Integer.parseInt(inputText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Bitte eine gültige Zahl eingeben.", "Ungültige Eingabe", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (anzahlRunden < 1 || anzahlRunden > 16) {
            JOptionPane.showMessageDialog(frame, "Die Rundenanzahl muss zwischen 1 und 16 liegen.", "Ungültige Eingabe", JOptionPane.ERROR_MESSAGE);
            return;
        }

        menue.turnierStarten(anzahlRunden);
        frame.dispose();
    }

    // Kopie SpielGUI (Thiemo)
    private JButton erstelleSchoenenButton() {
        JButton button = new JButton("Turnier starten") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color bgColor;
                if (getModel().isPressed()) {
                    bgColor = new Color(105, 106, 108).darker();
                } else if (getModel().isRollover()) {
                    bgColor = new Color(105, 106, 108);
                } else {
                    bgColor = new Color(57, 57, 59);
                }

                g2.setColor(bgColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(43, 43, 44));
                g2.setStroke(new java.awt.BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                g2.dispose();
            }
        };

        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

        return button;
    }
    // Kopie Ende
}
