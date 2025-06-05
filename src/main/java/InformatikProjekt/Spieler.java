package InformatikProjekt;

import java.util.ArrayList;

//Programmierer: Tom

public class Spieler extends Mitspieler {
    private SpielerModel model; //speichert Daten des Spielers
    private SpielGUI gui;

    public Spieler() {
        model = new SpielerModel();
    }

    public void setzeGUI(SpielGUI spielGUI) {
        gui = spielGUI;
    }

    /**
     * Model + GUI:
     * - Übergibt die wichtigen Dinge dem SpielerModel
     * - gibt wieVielterSpieler an GUI weiter zur Anzeige
     */
    @Override
    public void rundeStarten(ArrayList<Spielkarte> karten, int wieVielterSpieler) {
        model.setzeHandkarten(karten);
        model.setzeWelcherSpieler(wieVielterSpieler);
        gui.zeigeHandkarten(karten);
    }

    /**
     * Anfrage: an GUI für Spielabsicht
     * Überprüfung, ob Sauspiel möglich → Rückgabe oder erneute GUI-Anfrage
     */
    @Override
    public SpielArt spielabsichtFragen(SpielArt hoechstesSpiel) {
        SpielArt spielabsicht = null;
        gui.spielabsichtFragen();
        //wartet bis GUI Nutzereingabe dem Controller meldet
        while (spielabsicht == null) {
            spielabsicht = model.gebeSpielabsicht();
        }

        //Überprüfen, ob überhaupt möglich
        //ist Sauspiel schon das höchste Spiel?
        if (spielabsicht == hoechstesSpiel) {
            gui.ungueltigeEingabe("Es wurde schon ein Sauspiel ausgerufen. Du musst also höher ausrufen oder weiter sagen.");
            //"Rekursionsschritt"
            model.setzeSpielabsicht(null); //Abbruchbedingung zurücksetzen
            return spielabsichtFragen(hoechstesSpiel);
        }
        //Kann auf eine Sau ausgerufen werden?
        ArrayList<Farbe> farbe = sauZumAusrufen(model.gebeHandkarten());
        if (farbe.isEmpty()) {
            gui.ungueltigeEingabe("Du kannst auf keine Sau ausrufen");
            //"Rekursionsschritt"
            model.setzeSpielabsicht(null); //Abbruchbedingung der while-Schleife zurücksetzen
            return spielabsichtFragen(hoechstesSpiel);
        }
        return spielabsicht;
    }

    /*Methode wird von GUI aufgerufen und übergibt dem model die Spielabsicht*/
    public void spielabsichtGUI(SpielArt spielabsicht) {
        model.setzeSpielabsicht(spielabsicht);
    }

    /**
     * Anfrage: an GUI für Farbe, nachdem man ausgerufen hat
     * Überprüfung, ob gewählte Farbe möglich → Rückgabe oder erneute GUI-Anfrage
     */
    @Override
    public Farbe farbeFuerSpielAbsicht(SpielArt spielArt) {
        Farbe spielasichtFarbe = null;
        gui.farbeFuerSpielAbsicht();
        //wartet bis GUI Nutzereingabe dem Controller meldet
        while (spielasichtFarbe == null) {
            spielasichtFarbe = model.gebeSpielabsichtFarbe();
        }
        //Überprüfen, ob gewählte Farbe möglich
        ArrayList<Farbe> farbe = sauZumAusrufen(model.gebeHandkarten());
        for (int i = 0; i < farbe.size(); i++) {
            if (spielasichtFarbe == farbe.get(i)) {
                return spielasichtFarbe; //Farbe ist erlaubt → Rückgabe
            }
        }
        gui.ungueltigeEingabe("Du kannst auf diese Sau nicht ausrufen.");
        //"Rekursionsschritt"
        model.setzeSpielabsichtFarbe(null); //Abbruchbedingung der while-Schleife zurücksetzen
        return farbeFuerSpielAbsicht(spielArt);
    }

    /*Methode wird von GUI aufgerufen und übergibt dem Model die Farbe für die Sau*/
    public void farbeFuerSpielAbsichtGUI(Farbe farbe) {
        model.setzeSpielabsichtFarbe(farbe);
    }

    /*Nachricht für GUI, nachdem ein Spieler eine Spielabsicht abgegeben, die an GUI zur Anzeige übergeben werden muss*/
    public void spielerHatSpielabsichtGesagt(SpielArt spielAbsicht, int spieler) {
        WelcherSpieler welcherSpieler = wieVielterSpieler(spieler);
        gui.spielerHatSpielerabsichtGesagt(spielAbsicht, welcherSpieler);
    }

    /**
     * gibt Spielart, ausgerufenen spieler und Farbe an Model und GUI weiter
     * spieler: int Wert, wie ihn die Runde speichert (braucht ihn dann wieder, deswegen Übergabe an Model)
     * welcherSpieler: Wert, wie ihn Spieler-Klassen, z.B. GUI, nutzen
     */
    @Override
    public void spielArtEntschieden(int spieler, Farbe farbe, SpielArt spielArt) {
        WelcherSpieler welcherSpieler = wieVielterSpieler(spieler);
        model.setzeSpielArt(welcherSpieler, spielArt, farbe, spieler);
        gui.spielArtEntschieden(welcherSpieler, spielArt, farbe);
    }


    /**
     * → Aufruf an GUI, eine Karte zu legen
     * - schauen, ob ich der erste in der Lege-/Stichrunde bin
     * → keine Überprüfung, weil jede Karte gelegt werden kann
     * - wenn ich nicht der erste in der Lege-/Stichrunde bin
     * → model abfragen, welche Karte ich legen muss
     * → überprüfen, ob ich Karte legen darf
     * → überprüfen, ob ich andere Karte hätte legen müssen (ist in der vorherigen Überprüfung mit drin)
     */
    @Override
    public Spielkarte legeEineKarte() {
        int anzahlSpielerSchonGelegt = model.gebeAnzahlSpielerSchonGelegt();
        ArrayList<Spielkarte> erlaubteKarten;
        Spielkarte zuLegendeKarte = null;

        gui.legeKarte();
        //wartet bis GUI Nutzereingabe dem Controller meldet
        while (zuLegendeKarte == null) {
            zuLegendeKarte = model.gebeZuLegendeKarte();
        }
        //Überprüfung, ob Karte erlaubt ist
        //Überprüfung, ob man weglaufen darf
        if (anzahlSpielerSchonGelegt == 0) {
            Spielkarte sau = new Spielkarte(model.gebeFarbe(), Werte.SAU);
            erlaubteKarten = erlaubteKartenAusspielenBeiSauspiel(model.gebeHandkarten(), sau);
            for (Spielkarte spielkarte : erlaubteKarten) {
                if (zuLegendeKarte.equals(spielkarte)) {
                    return zuLegendeKarte; //Karte ist erlaubt und wird zurückgegeben
                }
            }
        }
        //Überprüfung, welche Karte gelegt werden darf, wenn schon eine liegt
        if (anzahlSpielerSchonGelegt != 0) {
            erlaubteKarten = gibErlaubteKarten((ArrayList<Spielkarte>) model.gebeHandkarten().clone(), model.gebeSpielArt(), new Spielkarte(model.gebeFarbe(), Werte.SAU), model.gebeVorgegebeneKarte(), model.gebeFarbe(), model.gebeSauFarbeVorhandGespielt()); //TODO: anpassen, wenn Tim Methode anpasst
            for (int i = 0; i < erlaubteKarten.size(); i++) {
                if (zuLegendeKarte.equals(erlaubteKarten.get(i))) {
                    return zuLegendeKarte; //Karte ist erlaubt und wird zurückgegeben
                }
            }
        }
        //Karte war nicht erlaubt
        gui.ungueltigeEingabe("Die Karte kann nicht gelegt werden.");
        //"Rekursionsschritt"
        model.setzeZuLegendeKarte(null); //Abbruchbedingung der while-Schleife zurücksetzen
        return legeEineKarte();
    }

    /*Methode wird von GUI aufgerufen und übergibt dem Model die zu legende Karte*/
    public void legeEineKarteGUI(Spielkarte spielkarte) {
        model.setzeZuLegendeKarte(spielkarte);
    }

    /**
     * - Überprüfung für Sau legen bzw. weglaufen
     * - gibt die gelegte Karte und welcher Spieler diese gelegt hat zurück
     * - Überprüfung, ob gesuchte Sau gelegt wurde → Model übergeben
     */
    @Override
    public void karteWurdeGelegt(Spielkarte karte, int spielerHatGelegt) {
        //Tim
        //todo anpassen mit Solofarbe anstatt null
        //Nachdem die Farbe der gesuchten Sau gespielt wird, darf die gesuchte wie jede andere Karte einer Farbe frei gespielt werden.
        if (model.gebeAnzahlSpielerSchonGelegt() == 0 && !karte.istTrumpf(model.gebeSpielArt(), null) && karte.gebeFarbe() == model.gebeFarbe()) {
            model.setzteSauFarbeVorhandGespielt(true);
        }
        //Tim

        WelcherSpieler welcherSpieler = wieVielterSpieler(spielerHatGelegt);
        model.setzeGelegteKarte(karte);
        gui.zeigeGelegteKarte(karte, welcherSpieler);

        //Überprüfung, ob gesuchte Sau gelegt wird → wenn ja, dann speichern, wer Mitspieler ist
        if (karte.gebeFarbe() == model.gebeFarbe() && karte.gebeWert() == Werte.SAU) {
            model.setzeMitspieler(spielerHatGelegt);
        }
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


    /**
     * //TODO
     * @param gewinner: übergibt die int Werte, wo die Gewinner sitzen
     * @param uebergebenePunkte: übergibt die int Werte, nach Sitzreihenfolge (von 0 bis 3)
     */
    @Override
    public void rundeGewonnen(int[] gewinner, int[] uebergebenePunkte) {
        WelcherSpieler gewinner1 = wieVielterSpieler(gewinner[0]);
        WelcherSpieler gewinner2 = wieVielterSpieler(gewinner[1]);
        //Punkte in ein Array sortieren
        int[] punkte = new int[3]; //richtig sortierte Punkte: gewinnerteamPunkte, verliererteamPunkte, spielerPunkte
        punkte[0] = uebergebenePunkte[gewinner[0]] + uebergebenePunkte[gewinner[1]];
        punkte[1] = 120 - punkte[0];
        punkte[2] = uebergebenePunkte[model.gebeWelcherSpieler()];

        //TODO: wie soll das der GUI übergeben werden?
        gui.rundeGewonnen(punkte);
    }

    /**
     * gibt den Spieler von unten (Nutzer) im Uhrzeigersinn aus
     * @param spieler: von Runde übergeben
     */
    public WelcherSpieler wieVielterSpieler(int spieler) {
        WelcherSpieler spielerImUhrzeigersinn = null;
        int rechnung = spieler - model.gebeWelcherSpieler(); //positive Zahlen im Uhrzeigersinn; negative gegen den Uhrzeigersinn
        if (rechnung == 0) {
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

    /*Methode, die GUI aufruft, wenn Spieler den letzten Stich sehen will*/
    public ArrayList<Spielkarte> gebeLetztenStich() {
        return model.gebeLetzterStich(); //TODO: @Thiemo Rückgabewert abklären
    }

    /*Methode, die von Runde aufgerufen wird, um den Mitspieler raus zu bekommen*/
    public int gebeMitspieler() {
        return model.gebeMitspieler();
    }
}
