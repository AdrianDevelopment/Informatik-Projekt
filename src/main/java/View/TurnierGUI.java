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
        GridBagConstraints gbc = new GridBagConstraints();

        // UI-Komponenten erstellen
        JLabel label = new JLabel("Anzahl der Runden eingeben (1-16):");
        label.setFont(new Font("My Boli", Font.PLAIN, 18));

        eingabefeld = new JTextField(10);
        eingabefeld.setFont(new Font("My Boli", Font.PLAIN, 18));
        eingabefeld.setHorizontalAlignment(JTextField.CENTER);

        JButton knopf = erstelleSchoenenButton("Turnier starten");
        knopf.addActionListener(e -> kopfGedrueckt());

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
    private JButton erstelleSchoenenButton(String text) {
        JButton button = new JButton(text);
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
