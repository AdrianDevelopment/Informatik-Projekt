package InformatikProjekt;

import javax.swing.*;
import java.util.ArrayList;

//Programmierer: Tom

public class Spieler extends Mitspieler {
    private SpielerModel model; //speichert Daten des Spielers
    private SpielGUI gui;
    private Runde runde;

    public Spieler(Runde runde) {
        model = new SpielerModel();
        gui = new SpielGUI(this);
        this.runde = runde;
    }

    /**
     * Aufruf von Runde
     * - im Model Übergabewerte setzen
     * - Buttons Bilder zuordnen + GUI aufrufen (→ soll Handkarten anzeigen)
     */
    @Override
    public void rundeStarten(ArrayList<Spielkarte> karten, int wieVielterSpieler) {
        model.setzeHandkarten(karten);
        model.setzeWelcherSpieler(wieVielterSpieler);
        model.setzeHandButtons(gui.spieler1ButtonsErstellen());
        buttonKartenZuornden();
        gui.handkartenAusteilen();
    }

    /*Buttons bekommen Icons zugewiesen*/
    public void buttonKartenZuornden() {
        ArrayList<JButton> handButtons = model.gebeHandButtons();
        ArrayList<Spielkarte> handKarten = model.gebeHandkarten();
        //Zuweisung von den passenden Bildern zu den Buttons
        for (int i = 0; i < handButtons.size(); i++) {
            handButtons.get(i).setIcon(gibBild(handKarten.get(i)));
            int finalI = i; //für Lambda Expression
            handButtons.get(i).addActionListener(e -> karteGelegt(handKarten.get(finalI), finalI)); //gibt Spielkarte weiter und Index für handButtons
        }
    }

    /*gibt zu einer Karte das ImageIcon mit passendem Bild (Co-Programmierer: Tim)*/
    private ImageIcon gibBild(Spielkarte karte) {
        String dateiname = "src\\main\\resources\\Karten\\";
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
        return new ImageIcon(dateiname);
    }

    /**
     * Aufforderung der Runde eine Karte zu legen
     * - im Model Übergabewerte setzen
     * - Buttons wieder drückbar machen
     */
    @Override
    public void legeEineKarte(int wiederholung, int vorhand) {
        model.setzeWiederholung(wiederholung);
        model.setzeVorhand(vorhand);
        model.setzeSpielerIstDran(true);
    }

    public void karteGelegt(Spielkarte spielkarte, int index) {
        if (!model.gebeSpielerIstDran()) { //Spieler soll keine Karte legen → nichts soll passieren
            return;
        }
        //TODO: Überprüfung Handkarte legen
        model.setzeSpielerIstDran(false);
        gui.handkartenAktualisieren(index); //damit wird der Button aus der GUI gelöscht
        model.gebeHandkarten().remove(spielkarte);
        runde.karteAbfragenAufgerufen(model.gebeWiederholung(), spielkarte, model.gebeVorhand());
    }

    /**
     * Aufforderung der Runde die Spielabsicht zu sagen
     * - im Model Übergabewerte setzen
     * - Buttons sichtbar und drückbar machen
     * Überprüfung, ob Sauspiel möglich → Rückgabe + eventuell Ausgabe an User
     */
    @Override
    public void spielabsichtFragen(int wiederholung, SpielArt hoechstesSpiel, int vorhand) {
        model.setzeWiederholung(wiederholung);
        model.setzeVorhand(vorhand);
        ArrayList<JButton> spielabsichtButtons = gui.spielabsichtFragen();
        spielabsichtButtons.get(0).addActionListener(e -> spielabsichtGesagt(SpielArt.KEINSPIEL));
        spielabsichtButtons.get(1).addActionListener(e -> spielabsichtGesagt(SpielArt.SAUSPIEL));
    }

    /**
     * wird von spielabsichtButtons aufgerufen
     * - setzt spielabsichtButtons
     *
     * @param spielabsicht: wird überprüft, ob es überhaupt geht
     */
    public void spielabsichtGesagt(SpielArt spielabsicht) {
        SpielArt spielArt = spielabsicht;
        gui.setzeSpielabsichtUnsichtbar(); //setzt die spielabsichtButtons auf nicht visible
        ///Überprüfen, ob überhaupt möglich: kann auf eine Sau ausgerufen werden?
        ArrayList<Farbe> farbe = sauZumAusrufen(model.gebeHandkarten());
        if (farbe.isEmpty()) {
            gui.ungueltigeEingabe("Du kannst auf keine Sau ausrufen. Du musst also weiter sagen");
            spielArt = SpielArt.KEINSPIEL;
        }
        runde.spielAbsichtFragenAufgerufen(model.gebeWiederholung(), spielArt, model.gebeVorhand());
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
        int zaehler = 0;
        while (zaehler < 1000) {
            zaehler++;
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

    /*Nachricht für GUI, nachdem ein Spieler eine Spielabsicht abgegeben, die an GUI zur Anzeige übergeben werden muss*/
    public void spielerHatSpielabsichtGesagt(SpielArt spielAbsicht, int spieler) {
        WelcherSpieler welcherSpieler = wieVielterSpieler(spieler);
        JLabel jLabel = new JLabel();
        String text = "";
        if (welcherSpieler == WelcherSpieler.NUTZER) {
            text += "Du hast";
        }
        gui.spielerHatAusgerufen(jLabel);
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
            erlaubteKarten = gibErlaubteKarten((ArrayList<Spielkarte>) model.gebeHandkarten().clone(), model.gebeSpielArt(), new Spielkarte(model.gebeFarbe(), Werte.SAU), model.gebeVorgegebeneKarte(), model.gebeFarbe(), model.gebeSauFarbeVorhandGespielt());
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
        WelcherSpieler welcherSpieler = wieVielterSpieler(spielerHatGelegt);
        JButton button = new JButton();
        button.setIcon(gibBild(karte));
        gui.karteInDieMitte(button, welcherSpieler);
        model.setzeGelegteKarte(karte);

        //TODO: ab hier?
        //Tim
        //todo anpassen mit Solofarbe anstatt null
        //Nachdem die Farbe der gesuchten Sau gespielt wird, darf die gesuchte wie jede andere Karte einer Farbe frei gespielt werden.
        if (model.gebeAnzahlSpielerSchonGelegt() == 0 && !karte.istTrumpf(model.gebeSpielArt(), null) && karte.gebeFarbe() == model.gebeFarbe()) {
            model.setzteSauFarbeVorhandGespielt(true);
        }
        //Tim
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
     *
     * @param gewinner:          übergibt die int Werte, wo die Gewinner sitzen
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
     *
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
