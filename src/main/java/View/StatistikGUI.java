package View;

import Model.Speicherung;

import javax.swing.*;
import java.awt.*;

public class StatistikGUI {

    public StatistikGUI() {
        Speicherung speicherung = Speicherung.speicherungErstellen();
        erstellUndZeigeGUI(speicherung);
    }

    private void erstellUndZeigeGUI(Speicherung speicherung) {
        // Frame-Initialisierung
        JFrame frame = new JFrame("Statistiken");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(240, 240, 240)); // Heller Hintergrund

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Überschrift
        JLabel ueberschriftLabel = new JLabel("Statistiken");
        ueberschriftLabel.setFont(new Font("My Boli", Font.BOLD, 36));
        ueberschriftLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 20, 10);
        frame.add(ueberschriftLabel, gbc);

        // Zurücksetzen der Grid-Constraints
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 10);

        // Statistiken anzeigen
        int yPos = 1;
        yPos = addStatistikSektion(frame, gbc, "Spiele:", new String[]{
                "Gespielte Spiele: " + speicherung.gespielteSpieleGeben(),
                "Gewonnene Spiele: " + speicherung.gewonneneSpieleGeben(),
                "Verlorene Spiele: " + speicherung.verloreneSpieleGeben(),
                "Gewonnene Spiele (Schneider): " + speicherung.gewonneneSpieleSchneiderGeben(),
                "Verlorene Spiele (Schneider): " + speicherung.verloreneSpieleSchneiderGeben()
        }, yPos);

        yPos = addStatistikSektion(frame, gbc, "Turniere:", new String[]{
                "Gespielte Turniere: " + speicherung.gespielteTurniereGeben(),
                "Gewonnene Turniere: " + speicherung.gewonneneTurniereGeben(),
                "Verlorene Turniere: " + speicherung.verloreneTurniereGeben()
        }, yPos);

        yPos = addStatistikSektion(frame, gbc, "Sonstiges:", new String[]{
                "Gespielte Karten: " + speicherung.gespielteKartenGeben(),
                "Höchste Punktzahl (Runde): " + speicherung.hoechstePunktzahlRundeGeben(),
                "Gesamte Punktzahl: " + speicherung.gesamtePunkteGeben()
        }, yPos);

        // Button für Turnierhistorie
        JButton knopf = erstelleSchoenenButton("Turnierhistorie anzeigen");
        knopf.addActionListener(e -> TurnierHistorieGUI.TurnierStatistikAnzeigen());
        gbc.gridy = yPos;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 10, 10);
        frame.add(knopf, gbc);

        // Frame-Konfiguration
        frame.pack();
        frame.setMinimumSize(new Dimension(500, frame.getHeight())); // Mindestbreite
        frame.setLocationRelativeTo(null); // Zentriert das Fenster
        frame.setResizable(true);
        frame.setVisible(true);
    }

    private int addStatistikSektion(JFrame frame, GridBagConstraints gbc, String titel, String[] statistiken, int startY) {
        JLabel sektionTitel = new JLabel(titel);
        sektionTitel.setFont(new Font("My Boli", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = startY;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 10, 5, 10);
        frame.add(sektionTitel, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(2, 10, 2, 10);
        int yPos = startY + 1;
        for (String statistik : statistiken) {
            JLabel label = new JLabel(statistik);
            label.setFont(new Font("My Boli", Font.PLAIN, 18));
            gbc.gridy = yPos++;
            frame.add(label, gbc);
        }
        return yPos;
    }

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
}
