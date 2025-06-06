package InformatikProjekt;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


class SpielGUI {
    private Spieler spieler;


    JButton weiterButton; //neu
    JButton sauButton;

    JRadioButton schellenButton;
    JRadioButton grasButton;
    JRadioButton eichelButton;
    private  JButton okButton;

    private ArrayList<JButton> spieler1KartenArray;
    private ArrayList<JButton> weiterSauButtons;
    private ArrayList<JButton> farbauswahlButtons;

    private ArrayList<JLabel> spieler1KartenLabels;
    private ArrayList<JLabel> spieler2KartenLabels;
    private ArrayList<JLabel> spieler3KartenLabels;
    private ArrayList<JLabel> spieler4KartenLabels;

    private JLabel spieler1MitteKarte;
    private JLabel spieler2MitteKarte;
    private JLabel spieler3MitteKarte;
    private JLabel spieler4MitteKarte;
    private JLabel mitteTextLabel;

    ImageIcon kartenRuekseite;

    private JFrame spielabsichtFrame;
    private final JFrame mainFrame;

    public SpielGUI(Spieler spieler) {
        this.spieler = spieler;
        mainFrame = new JFrame();

        mainFrame.setSize(1400, 700); //ersetzt duch frame.pack() !frame.pack() muss am ende stehen!
        mainFrame.setVisible(true);
        mainFrame.setLayout(null);

        okButton = new JButton("Ok");
        okButton.setBounds(1000, 400, 100, 50);
        okButton.setVisible(false);
        mainFrame.add(okButton);



        weiterButton = new JButton("Weiter");
        weiterButton.setBounds(1000, 600, 100, 50);
        weiterButton.setVisible(false);

        sauButton = new JButton("Sau");
        sauButton.setBounds(1100, 600, 100, 50);
        sauButton.setVisible(false);

        spieler1KartenArray = new ArrayList<JButton>();
        weiterSauButtons = new ArrayList<JButton>();
        farbauswahlButtons = new ArrayList<JButton>();

        spieler1KartenLabels = new ArrayList<JLabel>();
        spieler2KartenLabels = new ArrayList<JLabel>();
        spieler3KartenLabels = new ArrayList<JLabel>();
        spieler4KartenLabels = new ArrayList<JLabel>();

        mainFrame.add(weiterButton);
        //weiterButton.setVisible(false);
        mainFrame.add(sauButton);
        //sauButton.setVisible(false);

        //Bilder als Variablen festlegen
        ImageIcon playerIcon = new ImageIcon("src\\main\\resources\\spieler.png");
        ImageIcon botIcon = new ImageIcon("src\\main\\resources\\bot.png");
        kartenRuekseite = new ImageIcon("src\\main\\resources\\rueckseiteKarte.png");
        //Spieler1
        JLabel Spieler1 = new JLabel();
        Spieler1.setText("Spieler 1");
        Spieler1.setIcon(playerIcon);
        Spieler1.setVerticalTextPosition(JLabel.TOP);
        Spieler1.setHorizontalTextPosition(JLabel.CENTER);
        Spieler1.setBounds(650,400,160,160);
        //Spieler2
        JLabel Spieler2 = new JLabel();
        Spieler2.setText("Spieler 2");
        Spieler2.setIcon(botIcon);
        Spieler2.setVerticalTextPosition(JLabel.TOP);
        Spieler2.setHorizontalTextPosition(JLabel.CENTER);
        Spieler2.setBounds(200,300,60,80);
        //Spieler3
        JLabel Spieler3 = new JLabel();
        Spieler3.setText("Spieler 3");
        Spieler3.setIcon(botIcon);
        Spieler3.setVerticalTextPosition(JLabel.TOP);
        Spieler3.setHorizontalTextPosition(JLabel.CENTER);
        Spieler3.setBounds(650,120,60,80);
        //Spieler4
        JLabel Spieler4 = new JLabel();
        Spieler4.setText("Spieler 4");
        Spieler4.setIcon(botIcon);
        Spieler4.setVerticalTextPosition(JLabel.TOP);
        Spieler4.setHorizontalTextPosition(JLabel.CENTER);
        Spieler4.setBounds(1100,300,60,80);


        //Umgedrehte Startkarten für Spieler 2 werden erstellt:
        /*
        for (int i = 0; i < 8; i++) {
            int u = 0;
            JLabel karte = new JLabel();
            if (i % 2 == 0) {
                karte.setBounds(60,140 + (i/2 * 100), 80, 100);
            }
            else {
                karte.setBounds(0,40 + (i+1)/2, 80, 100);
            }
            karte.setIcon(kartenRuekseite);
            spieler1KartenLabels.add(karte);
        }

        for (int i = 0; i < 8; i++) {
            int u = 0;
            JLabel karte = new JLabel();
            if (i % 2 == 0) {
                karte.setBounds(60,140 + (i/2 * 100), 80, 100);
            }
            else {
                karte.setBounds(0,40 + (i+1)/2, 80, 100);
            }
            karte.setIcon(kartenRuekseite);
            spieler3KartenLabels.add(karte);
        }

        for (int i = 0; i < 8; i++) {
            int u = 0;
            JLabel karte = new JLabel();
            if (i % 2 == 0) {
                karte.setBounds(60,140 + (i/2 * 100), 80, 100);
            }
            else {
                karte.setBounds(0,40 + (i+1)/2, 80, 100);
            }
            karte.setIcon(kartenRuekseite);
            spieler4KartenLabels.add(karte);
        }

        */
        JLabel ruekseitenKarte21 = new JLabel();
        ruekseitenKarte21.setIcon(kartenRuekseite);
        ruekseitenKarte21.setBounds(0,140,80,100);
        JLabel ruekseitenKarte22 = new JLabel();
        ruekseitenKarte22.setIcon(kartenRuekseite);
        ruekseitenKarte22.setBounds(60,140,80,100);
        JLabel ruekseitenKarte23 = new JLabel();
        ruekseitenKarte23.setIcon(kartenRuekseite);
        ruekseitenKarte23.setBounds(0,240,80,100);
        JLabel ruekseitenKarte24 = new JLabel();
        ruekseitenKarte24.setIcon(kartenRuekseite);
        ruekseitenKarte24.setBounds(60,240,80,100);
        JLabel ruekseitenKarte25 = new JLabel();
        ruekseitenKarte25.setIcon(kartenRuekseite);
        ruekseitenKarte25.setBounds(0,340,80,100);
        JLabel ruekseitenKarte26 = new JLabel();
        ruekseitenKarte26.setIcon(kartenRuekseite);
        ruekseitenKarte26.setBounds(60,340,80,100);
        JLabel ruekseitenKarte27 = new JLabel();
        ruekseitenKarte27.setIcon(kartenRuekseite);
        ruekseitenKarte27.setBounds(0,440,80,100);
        JLabel ruekseitenKarte28 = new JLabel();
        ruekseitenKarte28.setIcon(kartenRuekseite);
        ruekseitenKarte28.setBounds(60,440,80,100);

        //Umgedrehte Startkarten für Spieler 4 werden erstellt:
        JLabel ruekseitenKarte41 = new JLabel();
        ruekseitenKarte41.setIcon(kartenRuekseite);
        ruekseitenKarte41.setBounds(1250,140,80,100);
        JLabel ruekseitenKarte42 = new JLabel();
        ruekseitenKarte42.setIcon(kartenRuekseite);
        ruekseitenKarte42.setBounds(1310,140,80,100);
        JLabel ruekseitenKarte43 = new JLabel();
        ruekseitenKarte43.setIcon(kartenRuekseite);
        ruekseitenKarte43.setBounds(1250,240,80,100);
        JLabel ruekseitenKarte44 = new JLabel();
        ruekseitenKarte44.setIcon(kartenRuekseite);
        ruekseitenKarte44.setBounds(1310,240,80,100);
        JLabel ruekseitenKarte45 = new JLabel();
        ruekseitenKarte45.setIcon(kartenRuekseite);
        ruekseitenKarte45.setBounds(1250,340,80,100);
        JLabel ruekseitenKarte46 = new JLabel();
        ruekseitenKarte46.setIcon(kartenRuekseite);
        ruekseitenKarte46.setBounds(1310,340,80,100);
        JLabel ruekseitenKarte47 = new JLabel();
        ruekseitenKarte47.setIcon(kartenRuekseite);
        ruekseitenKarte47.setBounds(1250,440,80,100);
        JLabel ruekseitenKarte48 = new JLabel();
        ruekseitenKarte48.setIcon(kartenRuekseite);
        ruekseitenKarte48.setBounds(1310,440,80,100);

        //Umgedrehte Startkarten für Spieler 3 werden erstellt:
        JLabel ruekseitenKarte31 = new JLabel();
        ruekseitenKarte31.setIcon(kartenRuekseite);
        ruekseitenKarte31.setBounds(400,20,80,95);
        JLabel ruekseitenKarte32 = new JLabel();
        ruekseitenKarte32.setIcon(kartenRuekseite);
        ruekseitenKarte32.setBounds(470,20,80,95);
        JLabel ruekseitenKarte33 = new JLabel();
        ruekseitenKarte33.setIcon(kartenRuekseite);
        ruekseitenKarte33.setBounds(540,20,80,95);
        JLabel ruekseitenKarte34 = new JLabel();
        ruekseitenKarte34.setIcon(kartenRuekseite);
        ruekseitenKarte34.setBounds(610,20,80,95);
        JLabel ruekseitenKarte35 = new JLabel();
        ruekseitenKarte35.setIcon(kartenRuekseite);
        ruekseitenKarte35.setBounds(680,20,80,95);
        JLabel ruekseitenKarte36 = new JLabel();
        ruekseitenKarte36.setIcon(kartenRuekseite);
        ruekseitenKarte36.setBounds(750,20,80,95);
        JLabel ruekseitenKarte37 = new JLabel();
        ruekseitenKarte37.setIcon(kartenRuekseite);
        ruekseitenKarte37.setBounds(820,20,80,95);
        JLabel ruekseitenKarte38 = new JLabel();
        ruekseitenKarte38.setIcon(kartenRuekseite);
        ruekseitenKarte38.setBounds(890,20,80,95);

        //Sachen zum Frame hinzufügen
        mainFrame.add(Spieler1);
        mainFrame.add(Spieler2);
        mainFrame.add(Spieler3);
        mainFrame.add(Spieler4);

        mainFrame.add(ruekseitenKarte21);
        mainFrame.add(ruekseitenKarte22);
        mainFrame.add(ruekseitenKarte23);
        mainFrame.add(ruekseitenKarte24);
        mainFrame.add(ruekseitenKarte25);
        mainFrame.add(ruekseitenKarte26);
        mainFrame.add(ruekseitenKarte27);
        mainFrame.add(ruekseitenKarte28);

        mainFrame.add(ruekseitenKarte41);
        mainFrame.add(ruekseitenKarte42);
        mainFrame.add(ruekseitenKarte43);
        mainFrame.add(ruekseitenKarte44);
        mainFrame.add(ruekseitenKarte45);
        mainFrame.add(ruekseitenKarte46);
        mainFrame.add(ruekseitenKarte47);
        mainFrame.add(ruekseitenKarte48);

        mainFrame.add(ruekseitenKarte31);
        mainFrame.add(ruekseitenKarte32);
        mainFrame.add(ruekseitenKarte33);
        mainFrame.add(ruekseitenKarte34);
        mainFrame.add(ruekseitenKarte35);
        mainFrame.add(ruekseitenKarte36);
        mainFrame.add(ruekseitenKarte37);
        mainFrame.add(ruekseitenKarte38);

        mainFrame.add(weiterButton);
        mainFrame.add(sauButton);
        mainFrame.show();
        spielabsichtFrame = new JFrame();

        mitteTextLabel = new JLabel();
        mitteTextLabel.setFont(new Font("Arial", Font.BOLD, 50));
        mitteTextLabel.setBounds(400, 150, 800, 200);
        mitteTextLabel.setVisible(true);
        mainFrame.add(mitteTextLabel);
    }

    public ArrayList<JButton> spieler1ButtonsErstellen(){
        for (int i = 0; i < 8; i++){
            JButton button = new JButton();
            button.setBounds((300 + i*60) + 120, 540, 70, 110);
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            spieler1KartenArray.add(button);
        }
        return spieler1KartenArray;
    }


    public ArrayList<JButton> spielabsichtFragen() {
//      weiterButton = new JButton("Weiter");
        weiterSauButtons.add(weiterButton);
        weiterButton.setVisible(true);
  //    sauButton = new JButton("Sau");
        weiterSauButtons.add(sauButton);
        sauButton.setVisible(true);
        return weiterSauButtons;
    }

    public ArrayList<JButton> farbeFuerSpielabsicht(){
        spielabsichtFrame = new JFrame("Auswahl: Spielabsicht");
        spielabsichtFrame.setSize(1000, 600);
        spielabsichtFrame.setLayout(null);



        //Schellen, Gras und Eichel
        JButton schellenButton = new JButton("Schellen");
        schellenButton.setBounds(100, 100, 100, 50);
        schellenButton.setVisible(true);
        farbauswahlButtons.add(schellenButton);

        JButton grasButton = new JButton("Gras");
        grasButton.setBounds(100, 200, 100, 50);
        grasButton.setVisible(true);
        farbauswahlButtons.add(grasButton);
        JButton eichelButton = new JButton("Eichel");
        eichelButton.setBounds(100, 300, 100, 50);
        eichelButton.setVisible(true);
        farbauswahlButtons.add(eichelButton);

        spielabsichtFrame.add(schellenButton);
        spielabsichtFrame.add(grasButton);
        spielabsichtFrame.add(eichelButton);

        return farbauswahlButtons;
    }

    public void setzeSpielabsichtUnsichtbar(){
        sauButton.setVisible(false);
        weiterButton.setVisible(false);
    }

    public void handkartenAusteilen(){
        for (int i = 0; i < 8; i++){
            mainFrame.add(spieler1KartenArray.get(i));
            spieler1KartenArray.get(i).setVisible(true);
        }
    }


    public void  karteInDieMitte(JLabel karteFuerDieMitte, WelcherSpieler adressat){
        switch (adressat){
            case NUTZER:
                spieler1MitteKarte = karteFuerDieMitte;
                spieler1MitteKarte.setBounds(220, 380, 100, 210);
                spieler1MitteKarte.setVisible(true);
                break;
            case LINKER:
                spieler2MitteKarte = karteFuerDieMitte;
                spieler2MitteKarte.setBounds(320, 380, 100, 210);
                spieler2MitteKarte.setVisible(true);
                break;
            case OBERER:
                spieler3MitteKarte = karteFuerDieMitte;
                spieler3MitteKarte.setBounds(420, 380, 100, 210);
                spieler3MitteKarte.setVisible(true);
                break;
            case RECHTER:
                spieler4MitteKarte = karteFuerDieMitte;
                spieler4MitteKarte.setBounds(520, 380, 100, 210);
                spieler4MitteKarte.setVisible(true);
                break;
        }
        mainFrame.add(karteFuerDieMitte);
        karteFuerDieMitte.setVisible(true);

    }
    public void spielerHatAusgerufenHinzufuegen(String text){
        mitteTextLabel.setText(text);
    }

    public void spielerHatAusgerufenEntfernen(JLabel mitteText){
        mainFrame.remove(mitteText);
    }

    public JButton gebeOkButton() {
        return okButton;
    }




    public void mitteAufrauemen(){


    }

    public void kartenZuweisen(WelcherSpieler spieler){
        if (spieler == WelcherSpieler.NUTZER) {
            JLabel stapelLabel1 = new JLabel();
            stapelLabel1.setIcon(kartenRuekseite);
            mainFrame.add(stapelLabel1);
            stapelLabel1.setVisible(true);
            stapelLabel1.setBounds(600, 300, 100, 100);
        }
        else if (spieler == WelcherSpieler.LINKER){
            JLabel stapelLabel2 = new JLabel();
            stapelLabel2.setIcon(kartenRuekseite);
            stapelLabel2.setBounds(400, 300, 100, 100);
            mainFrame.add(stapelLabel2);
            stapelLabel2.setVisible(true);
        } else if (spieler == WelcherSpieler.OBERER) {
            JLabel stapelLabel3 = new JLabel();
            stapelLabel3.setIcon(kartenRuekseite);
            stapelLabel3.setBounds(400, 300, 100, 100);
            mainFrame.add(stapelLabel3);
            stapelLabel3.setVisible(true);
        } else if (spieler == WelcherSpieler.RECHTER) {
            JLabel stapelLabel4 = new JLabel();
            stapelLabel4.setIcon(kartenRuekseite);
            stapelLabel4.setBounds(600, 300, 100, 100);
            mainFrame.add(stapelLabel4);
            stapelLabel4.setVisible(true);
        }
    }
    public void handkartenAktualisieren(int t){
        mainFrame.remove(spieler1KartenArray.get(t));
        mainFrame.revalidate();

    }

    //jjjjjjjjjjjj
    public void zeigeHandkarten(ArrayList<Spielkarte> handKarten){
    }

    public void spielerHatSpielerabsichtGesagt(SpielArt spielAbsicht, WelcherSpieler welcherSpieler){}

    public void ungueltigeEingabe(String konkretisierung){}
    public Spielkarte legeKarte() {
        return null;
    }
    public void zeigeGelegteKarte(Spielkarte karte, WelcherSpieler spielerHatGelegt) {}

    public void zeigeLetztenStich() {
        System.out.println(spieler.gebeLetztenStich());
    }


}