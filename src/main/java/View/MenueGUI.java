package View;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class MenueGUI {

    private final JButton playButton;
    private final JButton statistikButton;
    private final JButton beendenButton;

    public MenueGUI() {
        // Frame-Initialisierung
        // UI-Komponenten
        JFrame frame = new JFrame("Schafkopf Startmenü");
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

        // Titel-Label mit Umrandung
        JLabel titleLabel = getJLabel();
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

    private static JLabel getJLabel() {
        final int strokeWidth = 4;
        JLabel titleLabel = new JLabel("Schafkopf") {
            @Override
            public Dimension getPreferredSize() {
                // Erstellt einen temporären Grafikkontext, um genaue Schrift-Informationen zu erhalten
                BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = img.createGraphics();
                g2.setFont(getFont());
                FontRenderContext frc = g2.getFontRenderContext();
                g2.dispose();

                TextLayout layout = new TextLayout(getText(), getFont(), frc);
                java.awt.geom.Rectangle2D bounds = layout.getBounds();

                // Berechnet die volle Breite inklusive des Rands und eventueller negativer X-Offsets
                int width = (int) Math.ceil(bounds.getWidth()) + (int) Math.ceil(Math.abs(bounds.getX())) + strokeWidth * 2;
                int height = (int) Math.ceil(bounds.getHeight()) + strokeWidth * 2;

                return new Dimension(width, height);
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                FontRenderContext frc = g2.getFontRenderContext();
                TextLayout layout = new TextLayout(getText(), getFont(), frc);
                java.awt.geom.Rectangle2D bounds = layout.getBounds();

                // Präzise Zentrierung unter Berücksichtigung der Layout-Grenzen
                float x = (float) (getWidth() - bounds.getWidth()) / 2 - (float) bounds.getX();
                float y = (getHeight() + layout.getAscent() - layout.getDescent()) / 2;

                Shape outline = layout.getOutline(AffineTransform.getTranslateInstance(x, y));

                // Umrandung zeichnen
                g2.setColor(new Color(92, 64, 51)); // Dunkelbraun
                g2.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2.draw(outline);

                // Text füllen
                g2.setColor(getForeground());
                g2.fill(outline);

                g2.dispose();
            }
        };
        titleLabel.setFont(new Font("Bookman Old Style", Font.BOLD, 90));
        titleLabel.setForeground(new Color(255, 215, 0)); // Goldgelb
        return titleLabel;
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

                super.paintComponent(g2);
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
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        return button;
    }
}
