package InformatikProjekt;

import java.util.ArrayList;

//Programmierer: Tom

public class Spieler extends Mitspieler { //TODO: Methoden sortieren
    private SpielerModel model; //speichert Daten des Spielers
    private SpielerGUI gui;

    public Spieler() {
        model = new SpielerModel();
        gui = new SpielerGUI();
    }

    /**
     * Model + GUI:
     * - Übergibt die wichtigen Dinge dem SpielerModel
     * - gibt wieVielterSpieler an GUI weiter zur Anzeige
     *
     * @param karten
     * @param wieVielterSpieler
     */
    @Override
    public void rundeStarten(ArrayList<Spielkarte> karten, int wieVielterSpieler) {
        model.setzeHandkarten(karten);
        model.setzeWelcherSpieler(wieVielterSpieler);

        gui.zeigeHandkarten(karten);
        gui.zeigeWelcherSpieler(wieVielterSpieler);
    }

    /**
     * Anfrage:
     * Anfrage an User für Spielabsicht
     *
     * @param hoechstesSpiel
     * @return
     */
    @Override
    public SpielArt spielabsichtFragen(SpielArt hoechstesSpiel) {
        String spielabsicht = gui.spielabsichtFragen(hoechstesSpiel);
        switch (spielabsicht) {
            case "weiter":
                return SpielArt.KEINSPIEL;
            case "Sau":
                return SpielArt.SAUSPIEL;
            case "Wenz":
                return SpielArt.WENZ;
            case "Solo":
                return SpielArt.SOLO;
            default:
                gui.ungueltigeEingabe();
                return spielabsichtFragen(hoechstesSpiel);
        }
    }

    /**
     * GUI:
     * Nachricht für GUI, nachdem ein Spieler eine Spielabsicht abgegeben, die an GUI zur Anzeige übergeben werden muss
     *
     * @param spielAbsicht
     * @param spieler
     */
    public void spielerHatSpielabsichtGesagt(SpielArt spielAbsicht, int spieler) {

        //TODO: GUI übergeben, wer welches Spiel ausgerufen hat

    }

    @Override
    public void spielArtEntschieden(int spieler, Spielkarte sau, Farbe farbeSolo, SpielArt spielArt) {
        WelcherSpieler welcherSpieler = wieVielterSpieler(spieler);
        model.setzeSpielArt(welcherSpieler, spielArt, sau, farbeSolo);

        String ausgabe = "";
        switch (spielArt) {
            case KEINSPIEL:
                ausgabe = "Niemand wollte spielen";
            case SAUSPIEL:
                ausgabe = "Sauspiel auf die ";
                Farbe sauFarbe = sau.gebeFarbe();
                switch (sauFarbe) { //TODO: Namen der Sau raussuchen
                    case SCHELLEN:
                        break;
                    case HERZ:
                        break;
                    case GRAS:
                        break;
                    case EICHEL:
                        break;
                }
            case WENZ:
                ausgabe = "Wenz";
            case SOLO:
                ausgabe = "Solo mit der Farbe " + farbeSolo; //TODO: Farbe mit switch in ausgabe reinschreiben
            default:
                break;
        }
        gui.spielArtEntschieden(spieler, ausgabe);
    }

    /**
     * gibt den erstenSpieler, der nach einem Stich wieder anfängt
     * @param ersterSpieler
     */
    @Override
    public void setzeErsterSpieler(int ersterSpieler) {
        WelcherSpieler leger = wieVielterSpieler(ersterSpieler);
        model.setzeErsterSpieler(leger);
    }

    @Override
    public Spielkarte legeEineKarte() {
        /* TODO:
            - schauen, ob ich der erste in der Lege-/Stichrunde bin
                -> Aufruf an GUI, eine Karte zu legen
                -> überprüfen, ob Karte gelegt werden darf
            - wenn ich nicht der erste in der Lege-/Stichrunde bin
                -> model abfragen, welche Karte ich legen muss
                -> überprüfen, ob ich Karte legen darf
                -> überprüfen, ob ich andere Karte hätte legen müssen
            -> model und GUI meine gelegte Karte geben und @return
         */
        return null;
    }

    @Override
    public void karteWurdeGelegt(Spielkarte karte, int spielerHatGelegt) {
        WelcherSpieler welcherSpieler = wieVielterSpieler(spielerHatGelegt);
        gui.zeigeGelegteKarte(karte, welcherSpieler);
        model.setzeGelegteKarte(karte); //TODO: in Model einfügen
    }

    @Override
    public void stichGewonnen(int spieler) {

    }

    @Override
    public void stichGewonnen(int spieler) {

    }


    @Override
    public void rundeGewonnen(int spieler) {

    }

    /**
     * Gibt den Spieler von unten (Nutzer) im Uhrzeigersinn aus
     * @param spieler: von Runde übergeben
     * @return
     */
    public WelcherSpieler wieVielterSpieler(int spieler) {
        WelcherSpieler spielerImUhrzeigersinn = null;
        int rechnung = spieler - model.gebeWelcherSpieler(); //positive Zahlen im Uhrzeigersinn; negative gegen den Uhrzeigersinn
        if(rechnung == 0) {
            spielerImUhrzeigersinn = WelcherSpieler.NUTZER; //Nutzer
        } else if (rechnung == 1 || rechnung == -3) {
            spielerImUhrzeigersinn = WelcherSpieler.LINKER; //linker Spieler
        } else if (rechnung == 2 || rechnung == -2) {
            spielerImUhrzeigersinn = WelcherSpieler.OBERER; //oberer Spieler
        } else if (rechnung == 3 || rechnung == -1) {
            spielerImUhrzeigersinn = WelcherSpieler.RECHTER; //rechter Spieler
        } else {
            System.out.println("Fehler in Methode wieVielterSpieler"); //Test
        }
        return spielerImUhrzeigersinn;
    }


}
