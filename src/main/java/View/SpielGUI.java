package View;

import Controler.Runde;
import Controler.Spieler;
import Model.Farbe;
import Model.Spielkarte;
import Model.SpielArt;
import Model.WelcherSpieler;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;


public class SpielGUI {

    //Variablen für Knöpfe(Buttons) und Texte(Labels) werden erstellt
    final private JButton weiterButton; //neu
    final private JButton sauButton;

    final private JButton okButton;
    final private JButton neueRundeButton;

    private JButton schellenSauButton;
    private JButton eichelSauButton;
    private JButton grasSauButton;

    final private JLabel mitteTextLabel;
    final private JLabel kleinMitteTextLabel;

    //JTextPane ist einfach nur, weil ich einen Mehrzeiler gebraucht habe
    final private JTextPane endTextArea;

    //Arraylists, die Objekte von JButtons und JLabels speichern können, werden deklariert
    final private ArrayList<JButton> spieler1KartenArray;
    final private ArrayList<JButton> weiterSauButtons;

    private JLabel[] mitteKarten;

    //Die Variablen für Fenster werden erstellt
    private final JFrame mainFrame;

    //Konstruktor
    public SpielGUI() {
        //Hauptfenster wird erzeugt und angepasst
        mainFrame = new JFrame();
        mainFrame.setResizable(false);
        mainFrame.setSize(1400, 700);

        // Hintergrundbild laden, skalieren und setzt
        // möglichen Hintergrund für den MainFrame -Abgelehnt von Tom wegen zu dominanter Farben
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/HintergrundSpielGUI2.png")));
        Image img = imageIcon.getImage();
        Image scaledImg = img.getScaledInstance(mainFrame.getWidth(), mainFrame.getHeight(), Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(scaledImg));

        mainFrame.setContentPane(backgroundLabel);
        mainFrame.setLayout(null);

        mainFrame.setVisible(true);

        //Buttons werden initialisiert und jeweils zum Hauptfenster hinzugefügt
        okButton = erstelleSchoenenButton("Ok", 1000, 400, 100, 50);
        mainFrame.add(okButton);

        neueRundeButton = erstelleSchoenenButton("Tunier-Punkte Übersicht", 900, 400, 250, 50);
        mainFrame.add(neueRundeButton);

        //Array wird schon vor den anderen benötigt
        weiterSauButtons = new ArrayList<>();

        weiterButton = erstelleSchoenenButton("Weiter", 970,600,100,50);
        mainFrame.add(weiterButton);
        weiterSauButtons.add(weiterButton);

        sauButton = erstelleSchoenenButton("SAU", 1100, 600, 100, 50);
        mainFrame.add(sauButton);
        weiterSauButtons.add(sauButton);

        //Textfelder aus der Mitte werden erstellt und gezeichnet
        mitteTextLabel = new JLabel();
        mitteTextLabel.setFont(new Font("Arial", Font.BOLD, 30));
        mitteTextLabel.setHorizontalAlignment(JLabel.CENTER);
        mitteTextLabel.setVerticalAlignment(JLabel.CENTER);
        mitteTextLabel.setBounds(170, 150, 1000, 200);
        mitteTextLabel.setVisible(true);

        kleinMitteTextLabel = new JLabel();
        kleinMitteTextLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        kleinMitteTextLabel.setHorizontalAlignment(JLabel.CENTER);
        kleinMitteTextLabel.setVerticalAlignment(JLabel.CENTER);
        kleinMitteTextLabel.setBounds(170, 200, 1000, 200);
        kleinMitteTextLabel.setVisible(true);

        mainFrame.add(mitteTextLabel);
        mainFrame.add(kleinMitteTextLabel);

        //Die Arraylists werden initialisiert
        spieler1KartenArray = new ArrayList<>();

        ArrayList<JLabel> spieler2KartenLabels = new ArrayList<>();
        ArrayList<JLabel> spieler3KartenLabels = new ArrayList<>();
        ArrayList<JLabel> spieler4KartenLabels = new ArrayList<>();

        //Ein Bild wird erstellt(ImageIcon) und einer Variable zugewiesen
        ImageIcon playerIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/spieler.png")));
        ImageIcon botIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/bot.png")));
        ImageIcon kartenRuekseite = new ImageIcon(Objects.requireNonNull(getClass().getResource("/rueckseiteKarte.png")));

        //Bildchen für Spieler1 wird erstellt
        JLabel Spieler1 = new JLabel();
        Spieler1.setText("Spieler 1");
        Spieler1.setIcon(playerIcon);
        Spieler1.setVerticalTextPosition(JLabel.TOP);
        Spieler1.setHorizontalTextPosition(JLabel.CENTER);
        Spieler1.setBounds(650, 400, 160, 160);
        //Bildchen für Spieler2 wird erstellt
        JLabel Spieler2 = new JLabel();
        Spieler2.setText("Spieler 2");
        Spieler2.setIcon(botIcon);
        Spieler2.setVerticalTextPosition(JLabel.TOP);
        Spieler2.setHorizontalTextPosition(JLabel.CENTER);
        Spieler2.setBounds(200, 300, 60, 80);
        //Bildchen für Spieler3 wird erstellt
        JLabel Spieler3 = new JLabel();
        Spieler3.setText("Spieler 3");
        Spieler3.setIcon(botIcon);
        Spieler3.setVerticalTextPosition(JLabel.TOP);
        Spieler3.setHorizontalTextPosition(JLabel.CENTER);
        Spieler3.setBounds(650, 120, 60, 80);
        //Bildchen für Spieler4 wird erstellt
        JLabel Spieler4 = new JLabel();
        Spieler4.setText("Spieler 4");
        Spieler4.setIcon(botIcon);
        Spieler4.setVerticalTextPosition(JLabel.TOP);
        Spieler4.setHorizontalTextPosition(JLabel.CENTER);
        Spieler4.setBounds(1100, 300, 60, 80);

        //Die Bildchen werden auf das (oder "in das" - deutsche Sprache schwere Sprache) Hauptfenster gezeichnet
        mainFrame.add(Spieler1);
        mainFrame.add(Spieler2);
        mainFrame.add(Spieler3);
        mainFrame.add(Spieler4);

        //Umgedrehte Startkarten für Spieler 2 werden erstellt:
        for (int i = 0; i < 8; i++) {
            JLabel karte = new JLabel();
            if (i % 2 == 0) {
                karte.setBounds(60,140 + (i/2 * 100), 80, 100);
            }
            else {
                karte.setBounds(0,40 + (((i+1)*100)/2), 80, 100);
            }
            karte.setIcon(kartenRuekseite);
            spieler2KartenLabels.add(karte);
        }

        //Umgedrehte Startkarten für Spieler 3 werden erstellt:
        for (int i = 0; i < 8; i++) {
            JLabel karte = new JLabel();
            karte.setBounds(400+(i*70), 40, 80, 95);
            karte.setIcon(kartenRuekseite);
            spieler3KartenLabels.add(karte);
        }

        //Umgedrehte Startkarten für Spieler 4 werden erstellt:
        for (int i = 0; i < 8; i++) {
            JLabel karte = new JLabel();
            if (i % 2 == 0) {
                karte.setBounds(1250,140 + (i/2 * 100), 80, 100);
            }
            else {
                karte.setBounds(1310,40 + (((i+1)*100)/2), 80, 100);
            }
            karte.setIcon(kartenRuekseite);
            spieler4KartenLabels.add(karte);
        }

        //Die ganzen umgedrehten Karten werden gezeichnet
        for(int i = 0; i < 8; i++){
            mainFrame.add(spieler2KartenLabels.get(i));
            mainFrame.add(spieler3KartenLabels.get(i));
            mainFrame.add(spieler4KartenLabels.get(i));
        }

        mitteKartenInitialisieren();
        endTextArea = new JTextPane();
    }

    //Der ok Button lässt sich unsichtbar setzen
    public void okButtonSichtbarkeit(boolean sichtbarkeit) {
        okButton.setVisible(sichtbarkeit);
    }

    //selbsterklärend
    public void okButtonActionListenerLoeschen() {
        actionListenerLoeschen(okButton);
    }

    //selbsterklärend
    public void neueRundeButtonActionListenerLoeschen() {
        actionListenerLoeschen(neueRundeButton);
    }

    //selbsterklärend
    private void actionListenerLoeschen(JButton button) {
        for (ActionListener al : button.getActionListeners()) {
            button.removeActionListener(al);
        }
    }

    /*
    Hier werden die Handkarten für Spieler1 also für einen selbst erstellt
    und in eine ArrayList gegeben, die dann zurückgegeben wird
     */
    public ArrayList<JButton> spieler1ButtonsErstellen() {
        for (int i = 0; i < 8; i++) {
            JButton button = new JButton();
            button.setBounds((300 + i * 60) + 120, 540, 70, 110);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            spieler1KartenArray.add(button);
        }
        return spieler1KartenArray;
    }

    public void handkartenAusteilen() {
        for (int i = 0; i < 8; i++) {
            mainFrame.add(spieler1KartenArray.get(i));
            spieler1KartenArray.get(i).setVisible(true);
        }
    }

    private void mitteKartenInitialisieren() {
        mitteKarten = new JLabel[4];
        mitteKarten[0] = MitteKartePlatzErstellen(520, 350);
        mitteKarten[1] = MitteKartePlatzErstellen(320, 250);
        mitteKarten[2] = MitteKartePlatzErstellen(520, 80);
        mitteKarten[3] = MitteKartePlatzErstellen(920, 250);
    }

    private JLabel MitteKartePlatzErstellen(int x, int y) {
        JLabel jl = new JLabel();
        jl.setBounds(x, y, 100, 210);
        jl.setVisible(false);
        mainFrame.add(jl);
        return jl;
    }

    public void karteInDieMitte(Spielkarte karte, WelcherSpieler adressat) {
        ImageIcon bild = gibBild(karte);
        mitteKarten[adressat.ordinal()].setIcon(bild);
        mitteKarten[adressat.ordinal()].setVisible(true);
    }

    //Erstellt 3 Buttons für jeweils jede Sau, die man zur Auswahl hat
    public void farbeFuerSpielabsichtAnzeigen() {
        schellenSauButton = erstelleSchoenenButton("Schellen",1000, 450, 120, 50);
        mainFrame.add(schellenSauButton);
        schellenSauButton.setVisible(true);

        grasSauButton = erstelleSchoenenButton("Gras",1000, 520, 120, 50);
        mainFrame.add(grasSauButton);
        grasSauButton.setVisible(true);

        eichelSauButton = erstelleSchoenenButton("Eichel",1000, 590, 120, 50);
        mainFrame.add(eichelSauButton);
        eichelSauButton.setVisible(true);
    }

    //Blendet die 3-Säue-Buttons wieder aus
    public void farbeFuerSpielabsichtAusblenden() {
        schellenSauButton.setVisible(false);
        eichelSauButton.setVisible(false);
        grasSauButton.setVisible(false);
    }

    //Setzt Weiten und Sau unsichtbar
    public void setzeSpielabsichtUnsichtbar() {
        sauButton.setVisible(false);
        weiterButton.setVisible(false);
    }

    //selbsterklärend
    public void textAusgeben(String text) {
        mitteTextLabel.setText(text);
    }

    //löscht alle Karten, die die verschiedenen Spieler gelegt haben
    public void mitteAufrauemen() {
        for (JLabel label : mitteKarten) {
            label.setIcon(null);
        }
    }

    //Wenn der Weiterbutton gedrückt wird, wird eine Methode in einem Spieler aufgerufen
    public void weiterButtonActionListener(Spieler spieler){
        weiterSauButtons.getFirst().addActionListener(_ -> spieler.spielabsichtGesagt(SpielArt.KEINSPIEL));
    }

    //Wenn der Saubutton gedrückt wird, wird eine Methode in Spieler aufgerufen
    public void sauButtonActionListener(Spieler spieler){
        weiterSauButtons.get(1).addActionListener(_ -> spieler.spielabsichtGesagt(SpielArt.SAUSPIEL));
    }

    // Der Weiter-Button und/oder der Sau-Button werden unsichtbar gemacht
    public void spielAbsichtButtonsSichtbarkeitSetzen(boolean sichtbarkeit, int i){
        weiterSauButtons.get(i).setVisible(sichtbarkeit);
    }

    //Action Listener werden den einzelnen Sau-Auswahlmöglichkeiten zugewiesen
    public void farbeFuerSpielabsichtButtonsActionListener(Spieler spieler){
        schellenSauButton.addActionListener(_ -> spieler.farbeFeurSpielAbsichtGesagt(Farbe.SCHELLEN));
        grasSauButton.addActionListener(_ -> spieler.farbeFeurSpielAbsichtGesagt(Farbe.GRAS));
        eichelSauButton.addActionListener(_ -> spieler.farbeFeurSpielAbsichtGesagt(Farbe.EICHEL));
    }


    public void buttonKartenZuornden(Spieler spieler, ArrayList<Spielkarte> handkarten) {
        ArrayList<JButton> handButtons = spieler1ButtonsErstellen();
        //Zuweisung von den passenden Bildern zu den Buttons
        for (int i = 0; i < handkarten.size(); i++) {
            handButtons.get(i).setIcon(gibBild(handkarten.get(i)));
            int finalI = i; //für Lambda Expression
            actionListenerLoeschen(handButtons.get(i));
            handButtons.get(i).addActionListener(_ -> spieler.karteGelegt(handkarten.get(finalI), finalI)); //gibt Spielkarte weiter und Index für handButtons
        }
    }


    public void buttonKartenZuorndenKeineReaktion(ArrayList<Spielkarte> handkarten) {
        ArrayList<JButton> handButtons = spieler1ButtonsErstellen(); //spieler1
        //Zuweisung von den passenden Bildern zu den Buttons
        for (int i = 0; i < handkarten.size(); i++) {
            handButtons.get(i).setIcon(gibBild(handkarten.get(i)));
            actionListenerLoeschen(handButtons.get(i));
        }
    }


    /*gibt zu einer Karte das ImageIcon mit passendem Bild (Co-Programmierer: Tim)*/
    private ImageIcon gibBild(Spielkarte karte) {
        String dateiname = "/Karten/";
        switch (karte.gebeFarbe()) {
            case SCHELLEN:
                dateiname += "Schelle";
                break;
            case HERZ:
                dateiname += "Herz";
                break;
            case GRAS:
                dateiname += "Grass";
                break;
            case EICHEL:
                dateiname += "Eichel";
                break;
        }
        dateiname += "_";
        switch (karte.gebeWert()) {
            case SAU:
                dateiname += "Ass";
                break;
            case ZEHNER:
                dateiname += "10";
                break;
            case KOENIG:
                dateiname += "Koenig";
                break;
            case OBER:
                dateiname += "Ober";
                break;
            case UNTER:
                dateiname += "Unter";
                break;
            case NEUNER:
                dateiname += "9";
                break;
            case ACHTER:
                dateiname += "8";
                break;
            case SIEBENER:
                dateiname += "7";
                break;
        }
        dateiname += ".png";
        return new ImageIcon(Objects.requireNonNull(getClass().getResource(dateiname)));
    }

    //setzt eine eigene Karte unsichtbar
    public void handkartenSichtbarkeitSetzen(boolean sichtbarkeit, int i){
        spieler1KartenArray.get(i).setVisible(sichtbarkeit);
    }

    //entfernt eine eigene Karte aus dem Array
    public void entferneIndexVonHandButtons(int i) {
        spieler1KartenArray.remove(i);
    }

    //Bu="Button, ActLi="ActionListener", Eigentlich Selbsterklärend
    public void okBuActLiSetzenSpielabsicht(Runde runde, int wiederholung, int vorhand) {
        okButton.addActionListener(_ -> runde.spielAbsichtFragenRunde(wiederholung, vorhand));
    }

    public void okBuActLiSetzenFarbeSpielabsicht(Runde runde) {
        okButton.addActionListener(_ -> runde.farbeFuerSpielAbsicht());
    }

    public void okBuActLiSetzenStichSpielen(Runde runde) {
        okButton.addActionListener(_ -> runde.stichSpielen());
    }

    public void okBuActLiSetzenFrageStichVorbei(Runde runde) {
        okButton.addActionListener(_ -> runde.frageStichVorbei());
    }

    public void neueRundeBuActLiSetzenNeueRundeStarten(Spieler spieler, SpielArt spielArt) {
        neueRundeButton.addActionListener(_ -> spieler.neueRundeStarten(spielArt));
    }

    //selbsterklärend
    public void neueRundeButtonSichtbarkeit(boolean sichtbarkeit) {
        neueRundeButton.setVisible(sichtbarkeit);
    }

    //zeigt einen kleinen Text in der Mitte an
    public void hinweisAnNutzer(String text) {
        kleinMitteTextLabel.setText(text);
    }

    //Zeigt am Ende eine Übersicht an, welches Team mit wie viel Punkten verloren oder gewonnen hat
    public void endtextAnzeigen(WelcherSpieler[] gewinner, int[] punkte){
        String gewinner1 = gewinner[0].gebeName();
        String gewinner2 = gewinner[1].gebeName();
        String verlierer1 = gewinner[2].gebeName();
        String verlierer2 = gewinner[3].gebeName();

        int gewinnerpunkte = punkte[0];
        int verliererpunkte = punkte[1];


        endTextArea.setBounds(435, 200, 500, 250);

        endTextArea.setText("Gewinner Team:\n" +gewinner1+ " & " + gewinner2+ " ("+gewinnerpunkte+")\n \n Verlierer Team:\n" +verlierer1+ " & " +verlierer2+ " ("+verliererpunkte+")");

        StyledDocument doc = endTextArea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        endTextArea.setEditable(false);
        endTextArea.setFont(new Font("Arial", Font.PLAIN, 30));
        endTextArea.setOpaque(false);
        endTextArea.setBackground(new Color(0,0,0,0));

        endTextArea.setVisible(true);
        mainFrame.add(endTextArea);
    }

    //Endübersicht wird ausgeblendet
    public void endtextAusblenden(){
        endTextArea.setVisible(false);
    }

    //Setzt das ganze Hauptfenster unsichtbar
    public void spielGUIUnsichtbar(){
        mainFrame.setVisible(false);
    }

    //Setzt das ganze Hauptfenster sichtbar
    public void spielGUISichtbar(){
        mainFrame.setVisible(true);
    }

    /*
    In dieser Methode wird grundsätzlich ein neuer Button erstellt und am Ende zurückgegeben.
    Die Methode fügt den mit ihr erstellten Buttons einen Effekt hinzu, bei dem sich die Farbe durch das
    über den Button Fahren ändert.
    Falls jemand die Farben ändern will: https://rgbcolorpicker.com/
     */
    public JButton erstelleSchoenenButton(String text, int x, int y, int width, int height) {
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

                // Wichtig, damit der Text des Buttons gezeichnet wird
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

        button.setBounds(x, y, width, height);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false); // Wichtig für Custom Painting
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));


        if ("SAU".equals(text) || "Weiter".equals(text)){
            button.setVisible(false);
        }

        return button;
    }

    //Zeichnet einfach das Fenster neu, um den Hover-Bug zu beheben
    public void aktualisieren(){
        mainFrame.repaint();
    }
}
