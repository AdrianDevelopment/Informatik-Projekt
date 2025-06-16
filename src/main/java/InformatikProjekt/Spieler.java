package InformatikProjekt;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//Programmierer: Tom

public class Spieler extends Mitspieler {
    private final SpielerModel model; //speichert Daten des Spielers
    private SpielGUI gui;
    private Runde runde;


    public Spieler() {
        model = new SpielerModel();
    }

    @Override
    public void setzeRunde(Runde runde) {
        this.runde = runde;
    }

    /**
     * Aufruf von Runde
     * - im Model Übergabewerte setzen
     * - Buttons Bilder zuordnen + GUI aufrufen (→ soll Handkarten anzeigen)
     */
    public void spielGUIErstellen(SpielGUI gui) {
        this.gui = gui;
        JButton okButton = gui.gebeOkButton();
        okButton.setVisible(true);

    }

    @Override
    public void rundeStarten(ArrayList<Spielkarte> karten, int wieVielterSpieler) {
        model.setzeHandkarten(karten);
        model.setzeWelcherSpieler(wieVielterSpieler);
        model.setzeHandButtons(gui.spieler1ButtonsErstellen());
        buttonKartenZuorndenKeineReaktion();
        gui.handkartenAusteilen();
    }

    public void kartenHinlegen(int wiederholung, int vorhand) {
        actionListenerLoeschen(gui.gebeOkButton());
        gui.gebeOkButton().addActionListener(e -> runde.spielAbsichtFragenRunde(wiederholung, vorhand));
        gui.gebeOkButton().setVisible(true);
    }

    /*Buttons bekommen Icons zugewiesen*/
    public void buttonKartenZuornden() {
        System.out.println("Handkarten an");
        ArrayList<JButton> handButtons = model.gebeHandButtons();
        ArrayList<Spielkarte> handKarten = model.gebeHandkarten();
        //Zuweisung von den passenden Bildern zu den Buttons
        for (int i = 0; i < handKarten.size(); i++) {
            handButtons.get(i).setIcon(gibBild(handKarten.get(i)));
            int finalI = i; //für Lambda Expression
            actionListenerLoeschen(handButtons.get(i));
            handButtons.get(i).addActionListener(e -> karteGelegt(handKarten.get(finalI), finalI)); //gibt Spielkarte weiter und Index für handButtons

        }
    }

    public void buttonKartenZuorndenKeineReaktion() {
        System.out.println("Handkarten aus");
        ArrayList<JButton> handButtons = model.gebeHandButtons();
        ArrayList<Spielkarte> handKarten = model.gebeHandkarten();
        //Zuweisung von den passenden Bildern zu den Buttons
        for (int i = 0; i < handKarten.size(); i++) {
            handButtons.get(i).setIcon(gibBild(handKarten.get(i)));
            System.out.println(handKarten.get(i).gebeFarbe() + "|" + handKarten.get(i).gebeWert());
            actionListenerLoeschen(handButtons.get(i));
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
     * Aufforderung der Runde die Spielabsicht zu sagen
     * - im Model Übergabewerte setzen
     * - Buttons sichtbar und drückbar machen
     * Überprüfung, ob Sauspiel möglich → Rückgabe + eventuell Ausgabe an User
     */
    @Override
    public void spielabsichtFragen(int wiederholung, SpielArt hoechstesSpiel, int vorhand) {
        gui.gebeOkButton().setVisible(true);
        model.setzeWiederholung(wiederholung);
        model.setzeVorhand(vorhand);
        model.setzeDranSpielabsicht(true);
        ArrayList<JButton> spielabsichtButtons = gui.spielabsichtFragen();
        spielabsichtButtons.get(1).setVisible(false);
        actionListenerLoeschen(gui.gebeOkButton());
        spielabsichtButtons.get(0).addActionListener(e -> spielabsichtGesagt(SpielArt.KEINSPIEL));
        spielabsichtButtons.get(0).setVisible(true);
        //Überprüfen, ob überhaupt möglich: kann auf eine Sau ausgerufen werden?
        ArrayList<Farbe> farbe = sauZumAusrufen(model.gebeHandkarten());
        if (farbe.isEmpty()) {
            gui.hinweisAnNutzer("Du kannst auf keine Sau ausrufen. Du musst also weiter sagen.");
        } else if (hoechstesSpiel == SpielArt.SAUSPIEL) {
            gui.hinweisAnNutzer("Es wurde schon ein Sauspiel ausgerufen. Du musst also weiter sagen.");
        } else {
            spielabsichtButtons.get(1).addActionListener(e -> spielabsichtGesagt(SpielArt.SAUSPIEL));
            spielabsichtButtons.get(1).setVisible(true);
        }
    }

    /**
     * wird von spielabsichtButtons aufgerufen
     * - setzt spielabsichtButtons
     *
     * @param spielabsicht: wird überprüft, ob es überhaupt geht
     */
    public void spielabsichtGesagt(SpielArt spielabsicht) {
        if (!model.gebeDranSpielabsicht()) { //Spieler soll keine Karte legen → nichts soll passieren
            return;
        }
        model.setzeDranSpielabsicht(false);

        System.out.println("debug: Button Spielabsicht mit " + spielabsicht.gebeSpielArtID() + " gedrückt");

        gui.setzeSpielabsichtUnsichtbar(); //setzt die spielabsichtButtons auf nicht visible
        gui.hinweisAnNutzer(""); //der Hinweistext ist nun nicht mehr sichtbar
        System.out.println("debug: Spielabsicht auf unsichtbar gesetzt");
        runde.spielabsichtFragenAufgerufen(model.gebeWiederholung(), model.gebeVorhand(), spielabsicht);
    }

    /*Nachricht für GUI, nachdem ein Spieler eine Spielabsicht abgegeben, die an GUI zur Anzeige übergeben werden muss*/
    public void spielerHatSpielabsichtGesagt(int wiederholung, int vorhand, SpielArt spielAbsicht) {
        WelcherSpieler welcherSpieler = wieVielterSpieler(vorhand);
        String text = ausgabeBeimAusrufen(spielAbsicht, welcherSpieler, null);
        gui.spielerHatAusgerufenHinzufuegen(text);
        if (vorhand < 3) {
            vorhand++;
        } else {
            vorhand = 0;
        }
        int finalI = vorhand;
        actionListenerLoeschen(gui.gebeOkButton());
        gui.gebeOkButton().setVisible(false);
        gui.gebeOkButton().addActionListener(e -> runde.spielAbsichtFragenRunde(wiederholung + 1, finalI));
        gui.gebeOkButton().setVisible(true);
    }

    public void spielAbsichtAusgeben(int ausrufer, SpielArt spielArt) {
        actionListenerLoeschen(gui.gebeOkButton());
        WelcherSpieler welcherspieler = wieVielterSpieler(ausrufer);
        String text = ausgabeBeimAusrufen(spielArt, welcherspieler, null);
        if (spielArt == SpielArt.KEINSPIEL) {
            text = "Spiel abgebrochen wegen ungültiger Spielart.";
            System.out.println("Spiel abgebrochen wegen ungültiger Spielart");
            gui.gebeOkButton().addActionListener(e -> System.out.println("Das Spiel wurde wegen einer ungültigen Spielart abgebrochen. Bitte starte ein neues Spiel."));
        } else {
            gui.gebeOkButton().addActionListener(e -> runde.farbeFuerSpielAbsicht());
        }
        gui.spielerHatAusgerufenHinzufuegen(text);
    }

    /**
     * Anfrage: an GUI für Farbe, nachdem man ausgerufen hat
     * Überprüfung, ob gewählte Farbe möglich → Rückgabe oder erneute GUI-Anfrage
     */
    @Override
    public void farbeFuerSpielAbsicht(SpielArt spielArt) {
        actionListenerLoeschen(gui.gebeOkButton());
        model.setzeDranFarbeSpielabsicht(true);
        ArrayList<JButton> jButtons = gui.farbeFuerSpielabsicht();
        jButtons.get(0).addActionListener(e -> farbeFeurSpielAbsichtGesagt(Farbe.SCHELLEN));
        jButtons.get(1).addActionListener(e -> farbeFeurSpielAbsichtGesagt(Farbe.GRAS));
        jButtons.get(2).addActionListener(e -> farbeFeurSpielAbsichtGesagt(Farbe.EICHEL));
        gui.setzeSichtbarkeitFarbeFuerSpielabsicht(true);
    }

    /**
     * an Runde
     */
    public void farbeFeurSpielAbsichtGesagt(Farbe farbe) {
        if (!model.gebeDranFarbeSpielabsicht()) { //Spieler soll nichts abgeben können, wenn nicht dran
            return;
        }
        model.setzeDranFarbeSpielabsicht(false);
        //Überprüfen, ob gewählte Farbe möglich
        boolean moeglich = false;
        ArrayList<Farbe> f = sauZumAusrufen(model.gebeHandkarten());
        for (int i = 0; i < f.size(); i++) {
            if (farbe == f.get(i)) {
                moeglich = true;
            }
        }
        if (moeglich) {
            gui.setzeSichtbarkeitFarbeFuerSpielabsicht(false);
            gui.hinweisAnNutzer("");
            runde.farbeFuerSpielAbsichtAufgerufen(farbe); //Farbe darf gelegt werden und wird weitergegeben
        } else {
            model.setzeDranFarbeSpielabsicht(true);
            gui.hinweisAnNutzer("Auf diese Sau kann nicht ausgerufen werden. Wähle eine andere.");
            System.out.println("Auf diese Sau kann nicht ausgerufen werden. Wähle eine andere.");
        }
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
        //Ausgabe in GUI
        String text = ausgabeBeimAusrufen(spielArt, welcherSpieler, farbe);
        gui.spielerHatAusgerufenHinzufuegen(text);
        //okButton
        actionListenerLoeschen(gui.gebeOkButton());
        gui.gebeOkButton().setVisible(true);
        gui.gebeOkButton().addActionListener(e -> runde.stichSpielen());
    }

    /**
     * Aufforderung der Runde eine Karte zu legen
     * - im Model Übergabewerte setzen
     * - Buttons wieder drückbar machen
     */
    @Override
    public void legeEineKarte(int wiederholung, int vorhand) {
        actionListenerLoeschen(gui.gebeOkButton());
        buttonKartenZuornden();
        model.setzeWiederholung(wiederholung);
        model.setzeVorhand(vorhand);
        model.setzeDranLegen(true);
        System.out.println("Lege eine Karte");
    }

    /**
     * gibt Runde Karte
     * Überprüfungen
     * - darf überhaupt was gerade gelegt werden?
     * - weglaufen ja/nein
     * - gelegte Karte passt mit erster Karte zusammen ja/nein?
     * ja → Spielkarte entfernen und Runde geben
     */
    public void karteGelegt(Spielkarte spielkarte, int index) {
        if (!model.gebeDranLegen() && index > model.gebeHandkarten().size()) { //Spieler soll keine Karte legen → nichts soll passieren
            return;
        }
        System.out.println("Karte gelegt");
        model.setzeDranLegen(false);
        System.out.println("button handkarten geklickt");

        //Überprüfung, ob Karte erlaubt ist
        boolean erlaubt = false;
        //Überprüfung, ob man weglaufen darf
        int anzahlSpielerSchonGelegt = model.gebeAnzahlSpielerSchonGelegt();
        ArrayList<Spielkarte> erlaubteKarten;
        if (anzahlSpielerSchonGelegt == 0) {
            Spielkarte sau = new Spielkarte(model.gebeFarbe(), Werte.SAU);
            erlaubteKarten = erlaubteKartenAusspielenBeiSauspiel(model.gebeHandkarten(), sau);
            for (int i = 0; i < erlaubteKarten.size(); i++) {
                if (spielkarte.equals(spielkarte)) {
                    erlaubt = true;
                    break;
                }
            }
        }
        //Überprüfung, welche Karte gelegt werden darf, wenn schon eine liegt
        if (anzahlSpielerSchonGelegt != 0) {
            erlaubteKarten = gibErlaubteKarten((ArrayList<Spielkarte>) model.gebeHandkarten().clone(), model.gebeSpielArt(), new Spielkarte(model.gebeFarbe(), Werte.SAU), model.gebeVorgegebeneKarte(), model.gebeFarbe(), model.gebeSauFarbeVorhandGespielt());
            for (int i = 0; i < erlaubteKarten.size(); i++) {
                if (spielkarte.equals(erlaubteKarten.get(i))) {
                    erlaubt = true;
                    break;
                }
            }
        }
        if (erlaubt) {
            System.out.println("Karte erlaubt");
            buttonKartenZuorndenKeineReaktion();
            actionListenerLoeschen(model.gebeHandButtons().get(index));
            model.gebeHandButtons().get(index).setVisible(false);
            model.gebeHandButtons().remove(index);
            model.gebeHandkarten().remove(spielkarte);
            System.out.println(model.gebeHandkarten().size());
            runde.karteAbfragenAufgerufen(spielkarte);
        }
    }


    /**
     * gibt Ausgabetext zurück, der alle Übergabeparameter beachtet
     */
    public String ausgabeBeimAusrufen(SpielArt spielArt, WelcherSpieler welcherSpieler, Farbe farbe) {
        String ausgabe = welcherSpieler.gebeName();
        if (welcherSpieler == WelcherSpieler.NUTZER) {
            ausgabe += " hast";
        } else {
            ausgabe += " hat";
        }
        if (spielArt != null) {
            switch (spielArt) {
                case KEINSPIEL -> ausgabe += " 'weiter' gesagt";
                case SAUSPIEL -> {
                    ausgabe += " ein Sauspiel ";
                    if (farbe != null) {
                        switch (farbe) {
                            case SCHELLEN:
                                ausgabe += "auf die Bumbe ";
                                break;
                            case GRAS:
                                ausgabe += "auf die Blaue ";
                                break;
                            case EICHEL:
                                ausgabe += "auf die Alte ";
                                break;
                            default:
                                ausgabe += "auf eine ungültige Karte ";
                                System.out.println("ERROR: Eine ungültige Farbe wurde beim Sauspiel ausgerufen.");
                        }
                    }
                    ausgabe += "ausgerufen.";
                }
                default -> {
                    ausgabe += " eine ungültige Spielabsicht ausgerufen.";
                    System.out.println("ERROR: Eine ungültige Spielabicht wurde ausgerufen.");
                }
            }
        }
        return ausgabe;
    }


    /**
     * gibt Karte, die gelegt wurde mit dem Spieler der GUI weiter
     */
    //Ich habe die Buttons in Labels geändert und das ganze in der GUI angepasst
    @Override
    public void karteWurdeGelegt(Spielkarte karte, int spielerHatGelegt, int wiederholung) {
        buttonKartenZuorndenKeineReaktion();
        WelcherSpieler welcherSpieler = wieVielterSpieler(spielerHatGelegt);
        gui.karteInDieMitte(gibBild(karte), welcherSpieler);
        model.setzeGelegteKarte(karte);
        model.setzeWiederholung(wiederholung);
        model.setzeVorhand(spielerHatGelegt);
        //Tim Anfang //TODO: anpassen mit Solofarbe anstatt null @Tim
        //Nachdem die Farbe der gesuchten Sau gespielt wird, darf die gesuchte wie jede andere Karte einer Farbe frei gespielt werden.
        if (model.gebeAnzahlSpielerSchonGelegt() == 0 && !karte.istTrumpf(model.gebeSpielArt(), null) && karte.gebeFarbe() == model.gebeFarbe()) {
            model.setzteSauFarbeVorhandGespielt(true);
        } //Tim Ende
        //Überprüfung, ob gesuchte Sau gelegt wird → wenn ja, dann speichern, wer Mitspieler ist
        if (karte.gebeFarbe() == model.gebeFarbe() && karte.gebeWert() == Werte.SAU) {
            model.setzeMitspieler(spielerHatGelegt);
        }
        actionListenerLoeschen(gui.gebeOkButton());
        gui.gebeOkButton().addActionListener(e -> runde.frageStichVorbei());
    }

    /**
     * wenn Stich fertig ist
     * - an GUI weitergeben, wer gewonnen hat
     * - in Model letzterStich Karten überschreiben + Stichkarten löschen
     */
    @Override
    public void stichGewonnen(int spieler) {

        model.setzeWiederholung(0);
        model.setzeVorhand(0);
        WelcherSpieler welcherSpieler = wieVielterSpieler(spieler);
        String text = ausgabeBeimAusrufen(null, welcherSpieler, null) + " den Stich gewonnen.";

        gui.spielerHatAusgerufenHinzufuegen(text);
        gui.mitteAufrauemen();
        model.stichBeendet();
        actionListenerLoeschen(gui.gebeOkButton());
        gui.gebeOkButton().setVisible(true);
        gui.gebeOkButton().addActionListener(e -> runde.stichSpielen());
    }


    /**
     * berechnet Gewinner und Punkte
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
        //Ausgabe
        String text = gewinner1.gebeName() + " und " + gewinner2.gebeName() + "haben gewonnen und " + punkte[0] + "Punkte gesammelt." + "Du hast " + punkte[2] + " Punkte gesammelt.";
        gui.spielerHatAusgerufenHinzufuegen(text);
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
            System.out.println("ERROR: Methode wieVielterSpieler" + spieler); //Test
        }
        return spielerImUhrzeigersinn;
    }

    /*Methode, die GUI aufruft, wenn Spieler den letzten Stich sehen will*/
    public ArrayList<Spielkarte> gebeLetztenStich() {
        return model.gebeLetzterStich(); //TODO: Rückgabewert bei Implementierung in GUI ändern
    }

    /*Methode, die von Runde aufgerufen wird, um den Mitspieler herauszubekommen*/
    public int gebeMitspieler() {
        return model.gebeMitspieler();
    }

    private void actionListenerLoeschen(JButton button) {
        for (ActionListener al : button.getActionListeners()) {
            button.removeActionListener(al);
        }
    }
}
