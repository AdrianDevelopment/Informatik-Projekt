package View;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MenueGUI {

    // UI-Komponenten
    private final JFrame frame;
    private final JButton playButton;
    private final JButton statistikButton;
    private final JButton beendenButton;

    public MenueGUI() {
        // Frame-Initialisierung
        frame = new JFrame("Schafkopf Startmenü");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Hintergrundbild setzen
        try {
            ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/hintergrundMenueGUI.png")));
            JLabel backgroundLabel = new JLabel(imageIcon);
            frame.setContentPane(backgroundLabel);
            frame.setLayout(new GridBagLayout());
        } catch (NullPointerException e) {
            System.err.println("Hintergrundbild nicht gefunden!");
            frame.setLayout(new GridBagLayout());
            frame.getContentPane().setBackground(new Color(0, 50, 0)); // Fallback-Farbe
        }

        GridBagConstraints gbc = new GridBagConstraints();

        // Titel-Label
        JLabel titleLabel = new JLabel("Schafkopf");
        titleLabel.setFont(new Font("Garamond", Font.BOLD, 70));
        titleLabel.setForeground(new Color(25, 25, 112)); // Dunkelblau
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 10, 30, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(titleLabel, gbc);

        // Buttons erstellen
        playButton = erstelleSchoenenButton("SPIELEN");
        statistikButton = erstelleSchoenenButton("STATISTIK");
        beendenButton = erstelleSchoenenButton("BEENDEN");

        // Buttons zum Frame hinzufügen
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        frame.add(playButton, gbc);

        gbc.gridy = 2;
        frame.add(statistikButton, gbc);

        gbc.gridy = 3;
        frame.add(beendenButton, gbc);

        // Frame-Konfiguration
        frame.pack();
        frame.setLocationRelativeTo(null); // Zentriert das Fenster
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public JButton gibPlayButton() {
        return playButton;
    }

    public JButton gibStatistikButton() {
        return statistikButton;
    }

    public JButton gibBeendenButton() {
        return beendenButton;
    }

    private JButton erstelleSchoenenButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.setBackground(new Color(25, 25, 112));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 18));

        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 0, 139), 2, true),
                BorderFactory.createEmptyBorder(10, 25, 10, 25)
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
