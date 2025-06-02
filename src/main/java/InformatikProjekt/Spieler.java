package InformatikProjekt;

import java.util.ArrayList;

//Programmierer: Tom

public class Spieler extends Mitspieler { //TODO: Methoden sortieren
    private SpielerModel model; //speichert Daten des Spielers
    private SpielerGUI gui;

    public Spieler() {
        model = new SpielerModel();
        gui = new SpielerGUI(this);
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
        //TODO: gui
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

    public void spielabsichtSpieler(SpielArt spielabsicht) {
        model.setzeSpielabsichtSpieler(spielabsicht);
    }

    @Override
    public Farbe farbeFuerSpielAbsicht(SpielArt spielArt) {
        return null;
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
    public void spielArtEntschieden(int spieler, Farbe farbe, SpielArt spielArt) {
        WelcherSpieler welcherSpieler = wieVielterSpieler(spieler);
        model.setzeSpielArt(welcherSpieler, spielArt, farbe);

        String ausgabe = "";
        switch (spielArt) {
            case KEINSPIEL:
                ausgabe = "Niemand wollte spielen";
            case SAUSPIEL:
                ausgabe = "Sauspiel auf die ";
                switch (farbe) {
                    case SCHELLEN:
                        ausgabe += "Bumbe";
                        break;
                    case GRAS:
                        ausgabe += "Blaue";
                        break;
                    case EICHEL:
                        ausgabe += "Alte";
                        break;
                }
            case WENZ:
                ausgabe = "Wenz";
            case SOLO:
                ausgabe = "Solo mit der Farbe " + farbe; //TODO: Farbe mit switch in ausgabe reinschreiben
            default:
                break;
        }
        gui.spielArtEntschieden(spieler, ausgabe);
    }


    /**
     → Aufruf an GUI, eine Karte zu legen
        - schauen, ob ich der erste in der Lege-/Stichrunde bin
        → keine Überprüfung, weil jede Karte gelegt werden kann
     - wenn ich nicht der erste in der Lege-/Stichrunde bin
        → model abfragen, welche Karte ich legen muss
        → überprüfen, ob ich Karte legen darf
        → überprüfen, ob ich andere Karte hätte legen müssen (ist in der vorherigen Überprüfung mit drin)
     */
    @Override
    public Spielkarte legeEineKarte() {
        int anzahlSpielerSchonGelegt = model.gebeAnzahlSpielerSchonGelegt();
        ArrayList<Spielkarte> erlaubteKarten;
        boolean karteIstErlaubt;

        Spielkarte zuLegendeKarte = gui.legeKarte(); //TODO: mit Thiemo überprüfen, ob Rückgabewert so Sinn macht

        //Überprüfung, ob Karte erlaubt ist
        if (anzahlSpielerSchonGelegt != 0) {
            karteIstErlaubt = false;
            erlaubteKarten = gibErlaubteKarten(model.gebeHandkarten(), model.gebeSpielArt(), new Spielkarte(model.gebeFarbe(), Werte.SAU),model.gebeVorgegebeneKarte(), model.gebeFarbe()); //TODO: anpassen, wenn Tim Methode anpasst
            for (int i = 0; i < erlaubteKarten.size(); i++) {
                if (zuLegendeKarte.equals(erlaubteKarten.get(i))) {
                    karteIstErlaubt = true;
                    break;
                }
            }
        } else {
            karteIstErlaubt = true;
        }
        //Karte zurückgeben, wenn erlaubt //TODO: Verkürzung möglich?
        //sonst nochmal
        if (karteIstErlaubt) {
            return zuLegendeKarte;
        } else {
            gui.ungueltigeEingabe();
            return legeEineKarte();
        }
    }

    @Override
    public void karteWurdeGelegt(Spielkarte karte, int spielerHatGelegt) {
        WelcherSpieler welcherSpieler = wieVielterSpieler(spielerHatGelegt);
        gui.zeigeGelegteKarte(karte, welcherSpieler);
        model.setzeGelegteKarte(karte);
    }

    /**
     * wenn Stich fertig ist
     * - an GUI weitergeben, wer gewonnen hat
     * - in Model letzterStich Karten überschreiben + Stichkarten löschen
     */
    @Override
    public void stichGewonnen(int spieler) {
        WelcherSpieler welcherSpieler = wieVielterSpieler(spieler);
        gui.stichGewonnen(welcherSpieler);
        model.stichBeendet();
    }


    @Override
    public void rundeGewonnen(int[] gewinner, int[] uebergebenePunkte) {
        WelcherSpieler gewinner1 = wieVielterSpieler(gewinner[0]);
        WelcherSpieler gewinner2 = wieVielterSpieler(gewinner[1]);

        int[] punkte = new int[3]; //richtig sortierte Punkte: gewinnerteamPunkte, verliererteamPunkte, spielerPunkte
        punkte[0] = uebergebenePunkte[gewinner[0]] + uebergebenePunkte[gewinner[1]];
        punkte[1] = 120 - punkte[0];
        punkte[2] = uebergebenePunkte[model.gebeWelcherSpieler()];

        //TODO: wie soll das der GUI übergeben werden?
        gui.rundeGewonnen(punkte);
    }

    /**
     * Gibt den Spieler von unten (Nutzer) im Uhrzeigersinn aus
     * @param spieler: von Runde übergeben
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

    /**
     * Methode, die GUI aufruft, wenn Spieler den letzten Stich sehen will
     */
    public ArrayList<Spielkarte> gebeLetztenStich() {
        return model.gebeLetzterStich(); //TODO: @Thiemo Rückgabewert abklären
    }


}
