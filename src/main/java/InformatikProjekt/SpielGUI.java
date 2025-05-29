package InformatikProjekt;

import javax.swing.*;

class LeckMich {
    public void main(String[] args) {
        JFrame mainFrame = new JFrame();
        mainFrame.setSize(1400, 700); //ersetzt duch frame.pack() !frame.pack() muss am ende stehen!
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);

        //Bilder Als Variablen festlegen
        ImageIcon playerIcon = new ImageIcon("src\\main\\resources\\spieler.png");
        ImageIcon botIcon = new ImageIcon("src\\main\\resources\\bot.png");


        //Spieler1
        JLabel Spieler1 = new JLabel();
        Spieler1.setText("Spieler 1");
        Spieler1.setIcon(playerIcon);
        Spieler1.setVerticalTextPosition(JLabel.TOP);
        Spieler1.setHorizontalTextPosition(JLabel.CENTER);
        Spieler1.setBounds(600,450,160,160);

        //Spieler2
        JLabel Spieler2 = new JLabel();
        Spieler2.setText("Spieler 2");
        Spieler2.setIcon(botIcon);
        Spieler2.setVerticalTextPosition(JLabel.TOP);
        Spieler2.setHorizontalTextPosition(JLabel.CENTER);
        Spieler2.setBounds(150,350,60,80);

        //Spieler3
        JLabel Spieler3 = new JLabel();
        Spieler3.setText("Spieler 3");
        Spieler3.setIcon(botIcon);
        Spieler3.setVerticalTextPosition(JLabel.TOP);
        Spieler3.setHorizontalTextPosition(JLabel.CENTER);
        Spieler3.setBounds(600,150,60,80);

        //Spieler4
        JLabel Spieler4 = new JLabel();
        Spieler4.setText("Spieler 4");
        Spieler4.setIcon(botIcon);
        Spieler4.setVerticalTextPosition(JLabel.TOP);
        Spieler4.setHorizontalTextPosition(JLabel.CENTER);
        Spieler4.setBounds(1050,350,60,80);



        //Sachen zum Frame hinzufügen
        mainFrame.add(Spieler1);
        mainFrame.add(Spieler2);
        mainFrame.add(Spieler3);
        mainFrame.add(Spieler4);
    }

    /*
    public String spielabsichtFragen(SpielArt hoechstesSpiel) {
        System.out.println("Das aktuell höchste Spiel ist:" + hoechstesSpiel + "Gib bitte deine Spielabsicht ein, also entweder 'weiter', 'Sau', 'Wenz' oder 'Solo'");
        return benutzerEingabe.nextLine();
        //Handkarten müssen währenddessen angezeigt werden + die bisher höchste Spielart und von wem ausgerufen;
    }
    */

}