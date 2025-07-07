package View;

// Code von Robin

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Model.Speicherung;
import Model.TurnierStatistik;

public class TurnierHistorieGUI {
    // Wird niemals erstellt → beinhaltet nur statische "TurnierStatistikAnzeigen"-Methode
    public static void TurnierStatistikAnzeigen(){
        // Fenster erstellen
        JFrame frame = new JFrame("Turnierhistorie");
        frame.setSize(500,500);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(245, 245, 220));

        // Turniere aus Statistiken lesen
        ArrayList<TurnierStatistik> statistiken =
                Speicherung.speicherungErstellen().gebeAlteTurnierstatistiken();
        String[] turniereTexte = new String[statistiken.size()];
        for (int i=0;i<turniereTexte.length;++i){
            // Text aus TurnierStatistik-Objekt erstellen
            String aktuellerText;
            TurnierStatistik aktuelleStatistik = statistiken.get(i);
            if (aktuelleStatistik.wurdeGewonnen()){
                aktuellerText = "Gewonnen";
            }else{
                aktuellerText = "Verloren";
            }
            aktuellerText += " mit " + aktuelleStatistik.punkteGeben();
            aktuellerText += " Punkten. ";
            aktuellerText += aktuelleStatistik.DatumGeben().format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
            aktuellerText += " ";
            aktuellerText += aktuelleStatistik.ZeitGeben().format(DateTimeFormatter.ofPattern("HH:mm"));
            // Umkehren: in Statistiken-ArrayList ist das älteste Spiel am Anfang, in der Anzeige soll es das Neueste sein
            turniereTexte[turniereTexte.length - i - 1] = aktuellerText;
        }
        // Liste mit Scrollleiste aus String-Array erstellen und anzeigen
        JList<String> liste = new JList<>(turniereTexte);
        liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        liste.setBackground(new Color(245, 245, 220));
        JScrollPane scrollLeiste = new JScrollPane(liste);
        frame.add(scrollLeiste, BorderLayout.CENTER);

        // Button-Panel erstellen
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        buttonPanel.setOpaque(false);
        JButton schliessenButton = erstelleSchoenenButton();
        schliessenButton.addActionListener(_ -> frame.dispose());
        buttonPanel.add(schliessenButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private static JButton erstelleSchoenenButton() {
        JButton button = new JButton("Schließen") {
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
