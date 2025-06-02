package InformatikProjekt;

import javax.swing.*;
import java.util.ArrayList;


class SpielGUI {
    private Spieler spieler;
    public SpielGUI(Spieler spieler) {
        this.spieler = spieler;
        JFrame mainFrame = new JFrame();
        mainFrame.setSize(1400, 700); //ersetzt duch frame.pack() !frame.pack() muss am ende stehen!
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);

        //Bilder als Variablen festlegen
        ImageIcon playerIcon = new ImageIcon("src\\main\\resources\\spieler.png");
        ImageIcon botIcon = new ImageIcon("src\\main\\resources\\bot.png");
        ImageIcon kartenRuekseite = new ImageIcon("src\\main\\resources\\rueckseiteKarte.png");


        ImageIcon eichelKarte7 = new ImageIcon("src\\main\\resources\\Karten\\Eichel_7.png");
        ImageIcon eichelKarte8 = new ImageIcon("src\\main\\resources\\Karten\\Eichel_8.png");
        ImageIcon eichelKarte9 = new ImageIcon("src\\main\\resources\\Karten\\Eichel_9.png");
        ImageIcon eichelKarte10 = new ImageIcon("src\\main\\resources\\Karten\\Eichel_10.png");
        ImageIcon eichelKarteAss = new ImageIcon("src\\main\\resources\\Karten\\Eichel_Ass.png");
        ImageIcon eichelKarteKoenig = new ImageIcon("src\\main\\resources\\Karten\\Eichel_Koenig.png");
        ImageIcon eichelKarteOber = new ImageIcon("src\\main\\resources\\Karten\\Eichel_Ober.png");
        ImageIcon eichelKarteUnter = new ImageIcon("src\\main\\resources\\Karten\\Eichel_Unter.png");

        ImageIcon GrassKarte7 = new ImageIcon("src\\main\\resources\\Karten\\Grass_7.png");
        ImageIcon GrassKarte8 = new ImageIcon("src\\main\\resources\\Karten\\Grass_8.png");
        ImageIcon GrassKarte9 = new ImageIcon("src\\main\\resources\\Karten\\Grass_9.png");
        ImageIcon GrassKarte10 = new ImageIcon("src\\main\\resources\\Karten\\Grass_10.png");
        ImageIcon GrassKarteAss = new ImageIcon("src\\main\\resources\\Karten\\Grass_Ass.png");
        ImageIcon GrassKarteKoenig = new ImageIcon("src\\main\\resources\\Karten\\Grass_Koenig.png");
        ImageIcon GrassKarteOber = new ImageIcon("src\\main\\resources\\Karten\\Grass_Ober.png");
        ImageIcon GrassKarteUnter = new ImageIcon("src\\main\\resources\\Karten\\Grass_Unter.png");

        ImageIcon HerzKarte7 = new ImageIcon("src\\main\\resources\\Karten\\Herz_7.png");
        ImageIcon HerzKarte8 = new ImageIcon("src\\main\\resources\\Karten\\Herz_8.png");
        ImageIcon HerzKarte9 = new ImageIcon("src\\main\\resources\\Karten\\Herz_9.png");
        ImageIcon HerzKarte10 = new ImageIcon("src\\main\\resources\\Karten\\Herz_10.png");
        ImageIcon HerzKarteAss = new ImageIcon("src\\main\\resources\\Karten\\Herz_Ass.png");
        ImageIcon HerzKarteKoenig = new ImageIcon("src\\main\\resources\\Karten\\Herz_Koenig.png");
        ImageIcon HerzKarteOber = new ImageIcon("src\\main\\resources\\Karten\\Herz_Ober.png");
        ImageIcon HerzKarteUnter = new ImageIcon("src\\main\\resources\\Karten\\Herz_Unter.png");

        ImageIcon SchelleKarte7 = new ImageIcon("src\\main\\resources\\Karten\\Schelle_7.png");
        ImageIcon SchelleKarte8 = new ImageIcon("src\\main\\resources\\Karten\\Schelle_8.png");
        ImageIcon SchelleKarte9 = new ImageIcon("src\\main\\resources\\Karten\\Schelle_9.png");
        ImageIcon SchelleKarte10 = new ImageIcon("src\\main\\resources\\Karten\\Schelle_10.png");
        ImageIcon SchelleKarteAss = new ImageIcon("src\\main\\resources\\Karten\\Schelle_Ass.png");
        ImageIcon SchelleKarteKoenig = new ImageIcon("src\\main\\resources\\Karten\\Schelle_Koenig.png");
        ImageIcon SchelleKarteOber = new ImageIcon("src\\main\\resources\\Karten\\Schelle_Ober.png");
        ImageIcon SchelleKarteUnter = new ImageIcon("src\\main\\resources\\Karten\\Schelle_Unter.png");

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


        //Buttuns zum Auswählen der Aktionen:
        JButton weiterButton = new JButton("Weiter");
        weiterButton.setBounds(1000, 600, 100, 50);
        weiterButton.addActionListener(e -> spieler.);

        JButton sauButton = new JButton("SAU");
        sauButton.setBounds(1100, 600, 100, 50);
        sauButton.addActionListener(e -> System.out.println("SAU"));


        //Karten von Spieler1 werden erstellt:
        JButton spieler1Karte1 = new JButton();
        spieler1Karte1.addActionListener(e -> System.out.println("hhhhh"));
        spieler1Karte1.setIcon(kartenRuekseite);
        spieler1Karte1.setBounds(300,560,80,100);
        spieler1Karte1.setBorderPainted(false);
        spieler1Karte1.setContentAreaFilled(false);
        spieler1Karte1.setFocusPainted(false);

        JButton beispielEichel = new JButton();
        beispielEichel.addActionListener(e -> System.out.println("Eichel"));
        beispielEichel.setIcon(eichelKarte);
        beispielEichel.setBounds(400,560,80,100);
        beispielEichel.setBorderPainted(false);
        beispielEichel.setContentAreaFilled(false);
        beispielEichel.setFocusPainted(false);



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

        mainFrame.add(spieler1Karte1);
    }

    public void zeigeHandkarten(ArrayList<Spielkarte> handKarten){

    }

    public void absichtAbfragen(){

    }

}