package InformatikProjekt;

import javax.swing.*;
import java.util.ArrayList;


class SpielGUI {
    private Spieler spieler;

    private ArrayList<JButton> spieler1handkarten;
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

    JFrame mainFrame;

    public SpielGUI(Spieler spieler) {
        this.spieler = spieler;
        spieler1handkarten = new ArrayList<JButton>();
        mainFrame = new JFrame();
        mainFrame.setSize(1400, 700); //ersetzt duch frame.pack() !frame.pack() muss am ende stehen!
        mainFrame.setVisible(true);
        //mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);

        //Bilder als Variablen festlegen
        ImageIcon playerIcon = new ImageIcon("src\\main\\resources\\spieler.png");
        ImageIcon botIcon = new ImageIcon("src\\main\\resources\\bot.png");
        ImageIcon kartenRuekseite = new ImageIcon("src\\main\\resources\\rueckseiteKarte.png");

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
        weiterButton.addActionListener(e -> spieler.spielabsichtGUI(SpielArt.KEINSPIEL));

        JButton sauButton = new JButton("SAU");
        sauButton.setBounds(1100, 600, 100, 50);
        sauButton.addActionListener(e -> spieler.spielabsichtGUI(SpielArt.SAUSPIEL));


        //Karten von Spieler1 werden erstellt:
        JButton spieler1Karte1 = new JButton();
        spieler1Karte1.addActionListener(e -> System.out.println("hhhhh"));
        spieler1Karte1.setIcon(kartenRuekseite);
        spieler1Karte1.setBounds(300,560,80,100);
        spieler1Karte1.setBorderPainted(false);
        spieler1Karte1.setContentAreaFilled(false);
        spieler1Karte1.setFocusPainted(false);



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
        mainFrame.add(spieler1Karte1);
    }

    public void zeigeHandkarten(ArrayList<Spielkarte> handKarten){


        for(int i = 0; i < 8; i++){

            if (handKarten.get(i).gebeFarbe() == Farbe.SCHELLEN){
                if (handKarten.get(i).gebeWert() == Werte.SIEBENER){
                    JButton schellenSiebener = new JButton();
                    schellenSiebener.addActionListener(e -> System.out.println("hhhhh"));
                    schellenSiebener.setIcon(SchelleKarte7);
                    schellenSiebener.setBorderPainted(false);
                    schellenSiebener.setContentAreaFilled(false);
                    schellenSiebener.setFocusPainted(false);
                    spieler1handkarten.add(schellenSiebener);
                }
                else if (handKarten.get(i).gebeWert() == Werte.ACHTER){
                    JButton schellenAchtner = new JButton();
                    schellenAchtner.addActionListener(e -> System.out.println("hhhhh"));
                    schellenAchtner.setIcon(SchelleKarte8);
                    schellenAchtner.setBorderPainted(false);
                    schellenAchtner.setContentAreaFilled(false);
                    schellenAchtner.setFocusPainted(false);
                    spieler1handkarten.add(schellenAchtner);
                }
                else if (handKarten.get(i).gebeWert() == Werte.NEUNER){
                    JButton schellenNeuner = new JButton();
                    schellenNeuner.addActionListener(e -> System.out.println("hhhhh"));
                    schellenNeuner.setIcon(SchelleKarte9);
                    schellenNeuner.setBorderPainted(false);
                    schellenNeuner.setContentAreaFilled(false);
                    schellenNeuner.setFocusPainted(false);
                    spieler1handkarten.add(schellenNeuner);
                }
                else if (handKarten.get(i).gebeWert() == Werte.ZEHNER){
                    JButton schellenZehner = new JButton();
                    schellenZehner.addActionListener(e -> System.out.println("hhhhh"));
                    schellenZehner.setIcon(SchelleKarte10);
                    schellenZehner.setBorderPainted(false);
                    schellenZehner.setContentAreaFilled(false);
                    schellenZehner.setFocusPainted(false);
                    spieler1handkarten.add(schellenZehner);
                }
                else if (handKarten.get(i).gebeWert() == Werte.SAU){
                    JButton schellenSau = new JButton();
                    schellenSau.addActionListener(e -> System.out.println("hhhhh"));
                    schellenSau.setIcon(SchelleKarteAss);
                    schellenSau.setBorderPainted(false);
                    schellenSau.setContentAreaFilled(false);
                    schellenSau.setFocusPainted(false);
                    spieler1handkarten.add(schellenSau);
                }
                else if (handKarten.get(i).gebeWert() == Werte.UNTER){
                    JButton schellenUnter = new JButton();
                    schellenUnter.addActionListener(e -> System.out.println("hhhhh"));
                    schellenUnter.setIcon(SchelleKarteUnter);
                    schellenUnter.setBorderPainted(false);
                    schellenUnter.setContentAreaFilled(false);
                    schellenUnter.setFocusPainted(false);
                    spieler1handkarten.add(schellenUnter);
                }
                else if (handKarten.get(i).gebeWert() == Werte.OBER){
                    JButton schellenOber = new JButton();
                    schellenOber.addActionListener(e -> System.out.println("hhhhh"));
                    schellenOber.setIcon(SchelleKarteOber);
                    schellenOber.setBorderPainted(false);
                    schellenOber.setContentAreaFilled(false);
                    schellenOber.setFocusPainted(false);
                    spieler1handkarten.add(schellenOber);
                }
                else if (handKarten.get(i).gebeWert() == Werte.KOENIG){
                    JButton schellenKoenig = new JButton();
                    schellenKoenig.addActionListener(e -> System.out.println("hhhhh"));
                    schellenKoenig.setIcon(SchelleKarteKoenig);
                    schellenKoenig.setBorderPainted(false);
                    schellenKoenig.setContentAreaFilled(false);
                    schellenKoenig.setFocusPainted(false);
                    spieler1handkarten.add(schellenKoenig);
                }
            }
            else if (handKarten.get(i).gebeFarbe() == Farbe.GRAS){
                if (handKarten.get(i).gebeWert() == Werte.SIEBENER){
                    JButton grasSiebener = new JButton();
                    grasSiebener.addActionListener(e -> System.out.println("hhhhh"));
                    grasSiebener.setIcon(GrassKarte7);
                    grasSiebener.setBorderPainted(false);
                    grasSiebener.setContentAreaFilled(false);
                    grasSiebener.setFocusPainted(false);
                    spieler1handkarten.add(grasSiebener);
                }
                else if (handKarten.get(i).gebeWert() == Werte.ACHTER){
                    JButton grasAchtner = new JButton();
                    grasAchtner.addActionListener(e -> System.out.println("hhhhh"));
                    grasAchtner.setIcon(GrassKarte8);
                    grasAchtner.setBorderPainted(false);
                    grasAchtner.setContentAreaFilled(false);
                    grasAchtner.setFocusPainted(false);
                    spieler1handkarten.add(grasAchtner);
                }
                else if (handKarten.get(i).gebeWert() == Werte.NEUNER){
                    JButton grasNeuner = new JButton();
                    grasNeuner.addActionListener(e -> System.out.println("hhhhh"));
                    grasNeuner.setIcon(GrassKarte9);
                    grasNeuner.setBorderPainted(false);
                    grasNeuner.setContentAreaFilled(false);
                    grasNeuner.setFocusPainted(false);
                    spieler1handkarten.add(grasNeuner);
                }
                else if (handKarten.get(i).gebeWert() == Werte.ZEHNER){
                    JButton grasZehner = new JButton();
                    grasZehner.addActionListener(e -> System.out.println("hhhhh"));
                    grasZehner.setIcon(GrassKarte10);
                    grasZehner.setBorderPainted(false);
                    grasZehner.setContentAreaFilled(false);
                    grasZehner.setFocusPainted(false);
                    spieler1handkarten.add(grasZehner);
                }
                else if (handKarten.get(i).gebeWert() == Werte.SAU){
                    JButton grasSau = new JButton();
                    grasSau.addActionListener(e -> System.out.println("hhhhh"));
                    grasSau.setIcon(GrassKarteAss);
                    grasSau.setBorderPainted(false);
                    grasSau.setContentAreaFilled(false);
                    grasSau.setFocusPainted(false);
                    spieler1handkarten.add(grasSau);
                }
                else if (handKarten.get(i).gebeWert() == Werte.UNTER){
                    JButton grasUnter = new JButton();
                    grasUnter.addActionListener(e -> System.out.println("hhhhh"));
                    grasUnter.setIcon(GrassKarteUnter);
                    grasUnter.setBorderPainted(false);
                    grasUnter.setContentAreaFilled(false);
                    grasUnter.setFocusPainted(false);
                    spieler1handkarten.add(grasUnter);
                }
                else if (handKarten.get(i).gebeWert() == Werte.OBER){
                    JButton grasOber = new JButton();
                    grasOber.addActionListener(e -> System.out.println("hhhhh"));
                    grasOber.setIcon(GrassKarteOber);
                    grasOber.setBorderPainted(false);
                    grasOber.setContentAreaFilled(false);
                    grasOber.setFocusPainted(false);
                    spieler1handkarten.add(grasOber);
                }
                else if (handKarten.get(i).gebeWert() == Werte.KOENIG){
                    JButton grasKoenig = new JButton();
                    grasKoenig.addActionListener(e -> System.out.println("hhhhh"));
                    grasKoenig.setIcon(GrassKarteKoenig);
                    grasKoenig.setBorderPainted(false);
                    grasKoenig.setContentAreaFilled(false);
                    grasKoenig.setFocusPainted(false);
                    spieler1handkarten.add(grasKoenig);
                }
            }
            else if (handKarten.get(i).gebeFarbe() == Farbe.EICHEL){
                if (handKarten.get(i).gebeWert() == Werte.SIEBENER){
                    JButton eichelSiebener = new JButton();
                    eichelSiebener.addActionListener(e -> System.out.println("hhhhh"));
                    eichelSiebener.setIcon(eichelKarte7);
                    eichelSiebener.setBorderPainted(false);
                    eichelSiebener.setContentAreaFilled(false);
                    eichelSiebener.setFocusPainted(false);
                    spieler1handkarten.add(eichelSiebener);
                }
                else if (handKarten.get(i).gebeWert() == Werte.ACHTER){
                    JButton eichelAchtner = new JButton();
                    eichelAchtner.addActionListener(e -> System.out.println("hhhhh"));
                    eichelAchtner.setIcon(eichelKarte8);
                    eichelAchtner.setBorderPainted(false);
                    eichelAchtner.setContentAreaFilled(false);
                    eichelAchtner.setFocusPainted(false);
                    spieler1handkarten.add(eichelAchtner);
                }
                else if (handKarten.get(i).gebeWert() == Werte.NEUNER){
                    JButton eichelNeuner = new JButton();
                    eichelNeuner.addActionListener(e -> System.out.println("hhhhh"));
                    eichelNeuner.setIcon(eichelKarte9);
                    eichelNeuner.setBorderPainted(false);
                    eichelNeuner.setContentAreaFilled(false);
                    eichelNeuner.setFocusPainted(false);
                    spieler1handkarten.add(eichelNeuner);
                }
                else if (handKarten.get(i).gebeWert() == Werte.ZEHNER){
                    JButton eichelZehner = new JButton();
                    eichelZehner.addActionListener(e -> System.out.println("hhhhh"));
                    eichelZehner.setIcon(eichelKarte10);
                    eichelZehner.setBorderPainted(false);
                    eichelZehner.setContentAreaFilled(false);
                    eichelZehner.setFocusPainted(false);
                    spieler1handkarten.add(eichelZehner);
                }
                else if (handKarten.get(i).gebeWert() == Werte.SAU){
                    JButton eichelSau = new JButton();
                    eichelSau.addActionListener(e -> System.out.println("hhhhh"));
                    eichelSau.setIcon(eichelKarteAss);
                    eichelSau.setBorderPainted(false);
                    eichelSau.setContentAreaFilled(false);
                    eichelSau.setFocusPainted(false);
                    spieler1handkarten.add(eichelSau);
                }
                else if (handKarten.get(i).gebeWert() == Werte.UNTER){
                    JButton eichelUnter = new JButton();
                    eichelUnter.addActionListener(e -> System.out.println("hhhhh"));
                    eichelUnter.setIcon(eichelKarteUnter);
                    eichelUnter.setBorderPainted(false);
                    eichelUnter.setContentAreaFilled(false);
                    eichelUnter.setFocusPainted(false);
                    spieler1handkarten.add(eichelUnter);
                }
                else if (handKarten.get(i).gebeWert() == Werte.OBER){
                    JButton eichelOber = new JButton();
                    eichelOber.addActionListener(e -> System.out.println("hhhhh"));
                    eichelOber.setIcon(eichelKarteOber);
                    eichelOber.setBorderPainted(false);
                    eichelOber.setContentAreaFilled(false);
                    eichelOber.setFocusPainted(false);
                    spieler1handkarten.add(eichelOber);
                }
                else if (handKarten.get(i).gebeWert() == Werte.KOENIG){
                    JButton eichelKoenig = new JButton();
                    eichelKoenig.addActionListener(e -> System.out.println("hhhhh"));
                    eichelKoenig.setIcon(eichelKarteKoenig);
                    eichelKoenig.setBorderPainted(false);
                    eichelKoenig.setContentAreaFilled(false);
                    eichelKoenig.setFocusPainted(false);
                    spieler1handkarten.add(eichelKoenig);
                }
            }
            else if (handKarten.get(i).gebeFarbe() == Farbe.HERZ){
                if (handKarten.get(i).gebeWert() == Werte.SIEBENER){
                    JButton herzSiebener = new JButton();
                    herzSiebener.addActionListener(e -> System.out.println("hhhhh"));
                    herzSiebener.setIcon(HerzKarte7);
                    herzSiebener.setBorderPainted(false);
                    herzSiebener.setContentAreaFilled(false);
                    herzSiebener.setFocusPainted(false);
                    spieler1handkarten.add(herzSiebener);
                }
                else if (handKarten.get(i).gebeWert() == Werte.ACHTER){
                    JButton herzAchtner = new JButton();
                    herzAchtner.addActionListener(e -> System.out.println("hhhhh"));
                    herzAchtner.setIcon(HerzKarte8);
                    herzAchtner.setBorderPainted(false);
                    herzAchtner.setContentAreaFilled(false);
                    herzAchtner.setFocusPainted(false);
                    spieler1handkarten.add(herzAchtner);
                }
                else if (handKarten.get(i).gebeWert() == Werte.NEUNER){
                    JButton herzNeuner = new JButton();
                    herzNeuner.addActionListener(e -> System.out.println("hhhhh"));
                    herzNeuner.setIcon(HerzKarte9);
                    herzNeuner.setBorderPainted(false);
                    herzNeuner.setContentAreaFilled(false);
                    herzNeuner.setFocusPainted(false);
                    spieler1handkarten.add(herzNeuner);
                }
                else if (handKarten.get(i).gebeWert() == Werte.ZEHNER){
                    JButton herzZehner = new JButton();
                    herzZehner.addActionListener(e -> System.out.println("hhhhh"));
                    herzZehner.setIcon(HerzKarte10);
                    herzZehner.setBorderPainted(false);
                    herzZehner.setContentAreaFilled(false);
                    herzZehner.setFocusPainted(false);
                    spieler1handkarten.add(herzZehner);
                }
                else if (handKarten.get(i).gebeWert() == Werte.SAU){
                    JButton herzSau = new JButton();
                    herzSau.addActionListener(e -> System.out.println("hhhhh"));
                    herzSau.setIcon(HerzKarteAss);
                    herzSau.setBorderPainted(false);
                    herzSau.setContentAreaFilled(false);
                    herzSau.setFocusPainted(false);
                    spieler1handkarten.add(herzSau);
                }
                else if (handKarten.get(i).gebeWert() == Werte.UNTER){
                    JButton herzUnter = new JButton();
                    herzUnter.addActionListener(e -> System.out.println("hhhhh"));
                    herzUnter.setIcon(HerzKarteUnter);
                    herzUnter.setBorderPainted(false);
                    herzUnter.setContentAreaFilled(false);
                    herzUnter.setFocusPainted(false);
                    spieler1handkarten.add(herzUnter);
                }
                else if (handKarten.get(i).gebeWert() == Werte.OBER){
                    JButton herzOber = new JButton();
                    herzOber.addActionListener(e -> System.out.println("hhhhh"));
                    herzOber.setIcon(HerzKarteOber);
                    herzOber.setBorderPainted(false);
                    herzOber.setContentAreaFilled(false);
                    herzOber.setFocusPainted(false);
                    spieler1handkarten.add(herzOber);
                }
                else if (handKarten.get(i).gebeWert() == Werte.KOENIG){
                    JButton herzKoenig = new JButton();
                    herzKoenig.addActionListener(e -> System.out.println("hhhhh"));
                    herzKoenig.setIcon(HerzKarteKoenig);
                    herzKoenig.setBorderPainted(false);
                    herzKoenig.setContentAreaFilled(false);
                    herzKoenig.setFocusPainted(false);
                    spieler1handkarten.add(herzKoenig);
                }
            }
        }
        handkartenAusteilen(spieler1handkarten);
    }

    public void absichtAbfragen(){

    }

    public void handkartenAusteilen(ArrayList<JButton> spieler1handkarten){
        for (int i = 0; i < spieler1handkarten.size(); i++){
            mainFrame.add(spieler1handkarten.get(i));
            spieler1handkarten.get(i).setBounds((400 + i*60) + 100, 100, 100, 600);
        }
    }
    public void farbeFuerSpielabsicht(){
        JFrame farbauswahlFenster = new JFrame("Farbe auswählen");
        farbauswahlFenster.setSize(300, 100);
        farbauswahlFenster.setLayout(null);


        JButton schellenButton = new JButton("Schellen");
        schellenButton.setBounds(100, 30, 100, 50);
        schellenButton.addActionListener(e -> System.out.println("hi"));
        farbauswahlFenster.add(schellenButton);


        JButton grasButton = new JButton("Gras");
        grasButton.setBounds(100, 80, 100, 50);
        farbauswahlFenster.add(grasButton);

        JButton eichelButton = new JButton("Eichel");
        eichelButton.setBounds(100, 130, 100, 50);
        farbauswahlFenster.add(eichelButton);

    }

}