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
        frame.getContentPane().setBackground(new Color(245, 245, 220)); // Beiger Hintergrund

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

        // Button-Panel erstellen
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(frame.getContentPane().getBackground()); // Hintergrund anpassen

        JButton historieButton = erstelleSchoenenButton("Turnierhistorie anzeigen");
        historieButton.addActionListener(_ -> TurnierHistorieGUI.TurnierStatistikAnzeigen());
        buttonPanel.add(historieButton);

        JButton schliessenButton = erstelleSchoenenButton("Schließen");
        schliessenButton.addActionListener(_ -> frame.dispose());
        buttonPanel.add(schliessenButton);

        gbc.gridy = yPos;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 10, 10);
        frame.add(buttonPanel, gbc);

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
        JButton button = new JButton(text) {
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
}
