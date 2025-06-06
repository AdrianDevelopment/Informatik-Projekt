package InformatikProjekt;

import javax.swing.*;
import java.awt.desktop.SystemSleepEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//Programmierer: Tom

public class Spieler extends Mitspieler {
    private SpielerModel model; //speichert Daten des Spielers
    private SpielGUI gui;
    private Runde runde;
    private Turnier turnier;

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
    public void spielGUIErstellen(Turnier turnier) {
        gui = new SpielGUI(this);
        this.turnier = turnier;
        JButton okButton = gui.gebeOkButton();
        okButton.setVisible(true);
        int[] i = new int[]{-1, -1};

        okButton.addActionListener(e -> turnier.rundeStarten(0, i)); //1. Runde starten
        //model.setzeListeOkButton(gui.gebeListeOkButton());
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
        int[] i = new int[]{-1, -1};
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
            handButtons.get(i).addActionListener(e -> karteGelegt(handKarten.get(finalI), finalI)); //gibt Spielkarte weiter und Index für handButtons
            handButtons.get(i).validate();
        }
    }

    public void buttonKartenZuorndenKeineReaktion() {
        System.out.println("Handkarten aus");
        ArrayList<JButton> handButtons = model.gebeHandButtons();
        ArrayList<Spielkarte> handKarten = model.gebeHandkarten();
        //Zuweisung von den passenden Bildern zu den Buttons
        for (int i = 0; i < handKarten.size(); i++) {
            handButtons.get(i).setIcon(gibBild(handKarten.get(i)));
            int finalI = i; //für Lambda Expression
            System.out.println(handKarten.get(i).gebeFarbe() + "|" + handKarten.get(i).gebeWert());
            actionListenerLoeschen(handButtons.get(i));
            handButtons.get(i).validate();
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
        actionListenerLoeschen(gui.gebeOkButton());
        spielabsichtButtons.get(0).addActionListener(e -> spielabsichtGesagt(SpielArt.KEINSPIEL));
        spielabsichtButtons.get(0).setVisible(true);
        spielabsichtButtons.get(1).addActionListener(e -> spielabsichtGesagt(SpielArt.SAUSPIEL));
        spielabsichtButtons.get(1).setVisible(true);
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

        SpielArt spielArt = spielabsicht;
        gui.setzeSpielabsichtUnsichtbar(); //setzt die spielabsichtButtons auf nicht visible
        System.out.println("debug: Spielabsicht auf unsichtbar gesetzt");
        ///Überprüfen, ob überhaupt möglich: kann auf eine Sau ausgerufen werden?
        ArrayList<Farbe> farbe = sauZumAusrufen(model.gebeHandkarten());
        if (farbe.isEmpty()) {
            gui.ungueltigeEingabe("Du kannst auf keine Sau ausrufen. Du musst also weiter sagen");
            spielArt = SpielArt.KEINSPIEL;
        }
        runde.spielabsichtFragenAufgerufen(model.gebeWiederholung(), model.gebeVorhand(), spielArt);
    }

    /*Nachricht für GUI, nachdem ein Spieler eine Spielabsicht abgegeben, die an GUI zur Anzeige übergeben werden muss*/
    public void spielerHatSpielabsichtGesagt2(int wiederholung, int vorhand, SpielArt spielAbsicht) {
        WelcherSpieler welcherSpieler = wieVielterSpieler(vorhand);
        JLabel jLabel = new JLabel();
        String text = ausgabeBeimAusrufen(spielAbsicht, welcherSpieler, null);
        gui.spielerHatAusgerufenHinzufuegen(jLabel); // @Thiemo kann ich auch nur die Methode aufrufen, statt spielerHatAusgerufen?
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

    @Override
    public void spielerHatSpielabsichtGesagt(SpielArt spielAbsicht, int spieler) { //TODO braucht es die?

    }

    public void spielAbsichtAusgeben(int ausrufer, SpielArt hoechsteSpielart) {
        WelcherSpieler welcherspieler = wieVielterSpieler(ausrufer);
        String text = ausgabeBeimAusrufen(hoechsteSpielart, welcherspieler, null);
        gui.spielerHatAusgerufenHinzufuegen(new JLabel(text));
        actionListenerLoeschen(gui.gebeOkButton());
        gui.gebeOkButton().addActionListener(e -> runde.farbeFuerSpielAbsicht());

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
        ArrayList<Farbe> f = sauZumAusrufen(model.gebeHandkarten());
        for (int i = 0; i < f.size(); i++) {
            if (farbe == f.get(i)) {
                runde.farbeFuerSpielAbsichtAufgerufen(farbe); //Farbe darf gelegt werden und wir weitergegeben
            }
        }
        System.out.println("Farbe darf nicht gelegt werden."); //TODO: nochmal aufrufen?
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

        JLabel jLabel = new JLabel();
        String text = ausgabeBeimAusrufen(spielArt, welcherSpieler, farbe);
        jLabel.setText(text);
        gui.spielerHatAusgerufen(jLabel, text);
        if (spielArt == SpielArt.KEINSPIEL) {
            text += "Spiel abgebrochen wegen ungültiger Spielart";
            jLabel.setText(text);
            System.out.println("Spiel abgebrochen wegen ungültiger Spielart");
        }
        actionListenerLoeschen(gui.gebeOkButton());
        gui.gebeOkButton().setVisible(true);
        gui.gebeOkButton().addActionListener(e -> runde.stichSpielen(0));
        gui.gebeOkButton().setVisible(true);
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
        System.out.println("Karte gelegt");
        if (!model.gebeDranLegen()) { //Spieler soll keine Karte legen → nichts soll passieren
            return;
        }
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
           //gui.handkartenAktualisieren(index); //damit wird der Button aus der GUI gelöscht
            model.gebeHandButtons().remove(index);
            model.gebeHandkarten().remove(spielkarte);
            System.out.println(model.gebeHandkarten().size());
            buttonKartenZuorndenKeineReaktion();
            runde.karteAbfragenAufgerufen(model.gebeWiederholung(), spielkarte, model.gebeVorhand());
            gui.gebeOkButton().addActionListener(e-> runde.stichSpielen(model.gebeWiederholung()));
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
        switch (spielArt) {
            case KEINSPIEL -> ausgabe += " 'weiter' gesagt";
            case SAUSPIEL -> {
                ausgabe += " ein Sauspiel ";
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
                    case null, default:
                        break;
                }
                ausgabe += "ausgerufen";
            }
            case null -> {
                break;
            }
            default -> ausgabe += " eine ungültige Spielabsicht ausgerufen";
        }
        return ausgabe;
    }


    /**
     * gibt Karte, die gelegt wurde mit dem Spieler der GUI weiter
     */
    @Override
    public void karteWurdeGelegt(Spielkarte karte, int spielerHatGelegt) {
        WelcherSpieler welcherSpieler = wieVielterSpieler(spielerHatGelegt);
        JLabel label = new JLabel();
        label.setIcon(gibBild(karte));
        gui.karteInDieMitte(label, welcherSpieler);
        model.setzeGelegteKarte(karte);
        //Tim Anfang //TODO: anpassen mit Solofarbe anstatt null @Tim
        //Nachdem die Farbe der gesuchten Sau gespielt wird, darf die gesuchte wie jede andere Karte einer Farbe frei gespielt werden.
        if (model.gebeAnzahlSpielerSchonGelegt() == 0 && !karte.istTrumpf(model.gebeSpielArt(), null) && karte.gebeFarbe() == model.gebeFarbe()) {
            model.setzteSauFarbeVorhandGespielt(true);
        } //Tim Ende
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
        JLabel jLabel = new JLabel();
        jLabel.setText(ausgabeBeimAusrufen(null, welcherSpieler, null) + " den Stich gewonnen.");

        gui.spielerHatAusgerufenHinzufuegen(jLabel);
        /*TODO: gui.spielerHatAusgerufenEntfernen() irgendwann aufrufen
            - am besten mit einem weiter Button, der nach jedem Stich bzw. nach dem Ausrufen betätigt werden muss
         */
        gui.mitteAufrauemen();
        model.stichBeendet();
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
        JLabel jLabel = new JLabel();
        String text = gewinner1.gebeName() + " und " + gewinner2.gebeName() + "haben gewonnen und " + punkte[0] + "Punkte gesammelt." + "Du hast " + punkte[2] + " Punkte gesammelt.";
        jLabel.setText(text);
        gui.spielerHatAusgerufenHinzufuegen(jLabel);
        /*TODO: gui.spielerHatAusgerufenEntfernen() irgendwann aufrufen
            - am besten mit einem weiter Button, der nach jedem Stich bzw. nach dem Ausrufen betätigt werden muss
         */
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
            System.out.println("Fehler in Methode wieVielterSpieler" + spieler); //Test
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

    private void actionListenerLoeschen(JButton button){

        for( ActionListener al : button.getActionListeners() ) {
            button.removeActionListener( al );
        }
    }
}
