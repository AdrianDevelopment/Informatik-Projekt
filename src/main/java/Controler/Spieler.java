package Controler;

import Model.*;
import View.SpielGUI;

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

    public void spielGUIErstellen(SpielGUI gui) {
        this.gui = gui;
        gui.okButtonSichtbarkeit(true);
    }

    /**
     * Aufruf von Runde
     * - im Model karten und Spieler-Nummer setzen
     * - Buttons Bilder zuordnen + GUI aufrufen (→ soll Handkarten anzeigen)
     * - GUI zurücksetzen
     */
    @Override
    public void rundeStarten(ArrayList<Spielkarte> karten, int wieVielterSpieler) {
        model.setzeHandkarten(karten);
        model.setzeWelcherSpieler(wieVielterSpieler);
        gui.buttonKartenZuorndenKeineReaktion(model.gebeHandkarten());
        gui.handkartenAusteilen();
        //für eine neue Runde wird alles zurückgesetzt
        gui.neueRundeButtonSichtbarkeit(false);
        gui.textAusgeben("");
        gui.hinweisAnNutzer("");
        gui.endtextAusblenden();
        gui.spielGUISichtbar();
    }

    public void kartenHingelegt(int wiederholung, int vorhand) {
        gui.okBuActLiSetzenSpielabsicht(runde, wiederholung, vorhand);
        gui.okButtonSichtbarkeit(true);
    }

    /**
     * Aufforderung der Runde die Spielabsicht zu sagen
     * - im Model Übergabewerte setzen
     * - Buttons sichtbar und drückbar machen
     * - Überprüfung, ob Sauspiel möglich → Ausgabe an Nutzer
     */
    @Override
    public void spielabsichtFragen(int wiederholung, SpielArt hoechstesSpiel, int vorhand) {
        gui.okButtonSichtbarkeit(false);
        model.setzeWiederholung(wiederholung);
        model.setzeVorhand(vorhand);
        model.setzeDranSpielabsicht(true);
        gui.spielabsichtFragen();
        gui.spielAbsichtButtonsSichtbarkeitSetzen(false, 1);
        gui.okButtonActionListenerLoeschen();
        gui.weiterButtonActionListener(this);
        gui.spielAbsichtButtonsSichtbarkeitSetzen(true, 0);
        //Überprüfen, ob überhaupt möglich: kann auf eine Sau ausgerufen werden?
        ArrayList<Farbe> farbe = sauZumAusrufen(model.gebeHandkarten());
        if (farbe.isEmpty()) {
            gui.hinweisAnNutzer("Du kannst auf keine Sau ausrufen. Du musst also weiter sagen.");
        } else if (hoechstesSpiel == SpielArt.SAUSPIEL) {
            gui.hinweisAnNutzer("Es wurde schon ein Sauspiel ausgerufen. Du musst also weiter sagen.");
        } else {
            gui.sauButtonActionListener(this);
            gui.spielAbsichtButtonsSichtbarkeitSetzen(true, 1); //Saubutton wird angezeigt, weil es möglich ist, die Sau auszurufen
        }
    }

    /**
     * wird von spielabsichtButtons aufgerufen
     *
     * @param spielabsicht: übergibt das an Runde
     */
    public void spielabsichtGesagt(SpielArt spielabsicht) {
        if (!model.gebeDranSpielabsicht()) { //Spieler soll keine Karte legen → nichts soll passieren
            return;
        }
        System.out.println("DEBUG: Button Spielabsicht mit " + spielabsicht.gebeSpielArtID() + " gedrückt");
        model.setzeDranSpielabsicht(false);
        gui.setzeSpielabsichtUnsichtbar(); //setzt die spielabsichtButtons auf nicht visible
        System.out.println("DEBUG: Spielabsicht auf unsichtbar gesetzt");
        gui.hinweisAnNutzer(""); //der Hinweistext ist nun nicht mehr sichtbar
        runde.spielabsichtFragenAufgerufen(model.gebeWiederholung(), model.gebeVorhand(), spielabsicht);
    }

    /**
     * Ausgabe für GUI, nachdem ein Spieler eine Spielabsicht abgegeben, die an GUI zur Anzeige übergeben werden muss
     * okButton ActionListener neu setzen lassen
     */
    public void spielerHatSpielabsichtGesagt(int wiederholung, int vorhand, SpielArt spielAbsicht) {
        WelcherSpieler welcherSpieler = wieVielterSpieler(vorhand);
        String text = ausgabeBeimAusrufen(spielAbsicht, welcherSpieler, null);
        gui.textAusgeben(text);
        if (vorhand < 3) {
            vorhand++;
        } else {
            vorhand = 0;
        }
        int finalI = vorhand;
        gui.okButtonActionListenerLoeschen();
        gui.okBuActLiSetzenSpielabsicht(runde, wiederholung + 1, finalI);
        gui.okButtonSichtbarkeit(true);
    }

    /**
     * Ausgabe für GUI
     * - entweder wurde kein Spiel ausgerufen und die Runde wird abgebrochen
     * - oder der okButton wird so gesetzt, dass die Runde fortgesetzt werden kann
     */
    public void spielAbsichtAusgeben(int ausrufer, SpielArt spielArt) {
        gui.okButtonActionListenerLoeschen();
        WelcherSpieler welcherspieler = wieVielterSpieler(ausrufer);
        String text = ausgabeBeimAusrufen(spielArt, welcherspieler, null);
        if (spielArt == SpielArt.KEINSPIEL) {
            text = "Runde abgebrochen wegen ungültiger Spielart.";
            System.out.println(text);
            gui.okButtonActionListenerLoeschen();
            gui.okButtonSichtbarkeit(false);
            gui.neueRundeButtonSichtbarkeit(true);
            gui.neueRundeButtonText("Turnier-Punkte Übersicht");
            gui.neueRundeBuActLiSetzenNeueRundeStarten(this, spielArt);
        } else {
            gui.okBuActLiSetzenFarbeSpielabsicht(runde);
        }
        gui.textAusgeben(text);
    }

    /**
     * Aufforderung von Runde, die Sau-Farbe zu sagen
     * → Aufruf der GUI, damit Pop-Up angezeigt wird
     */
    @Override
    public void farbeFuerSpielAbsicht(SpielArt spielArt) {
        gui.okButtonActionListenerLoeschen();
        model.setzeDranFarbeSpielabsicht(true);
        gui.farbeFuerSpielabsicht();
        gui.farbeFuerSpielabsichtButtonsActionListener(this);
        gui.setzeSichtbarkeitFarbeFuerSpielabsicht(true);
        gui.okButtonSichtbarkeit(false);
        gui.hinweisAnNutzer("Wähle die Sau-Farbe im Pop-up.");
    }

    /**
     * wird von farbauswahlButtons aufgerufen
     * - Überprüfung, ob gewählte Farbe möglich → Aufruf der Runde oder erneute Aufforderung
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
                break;
            }
        }
        if (moeglich) {
            gui.setzeSichtbarkeitFarbeFuerSpielabsicht(false);
            gui.hinweisAnNutzer("");
            runde.farbeFuerSpielAbsichtAufgerufen(farbe); //Farbe darf gelegt werden und wird weitergegeben
        } else {
            model.setzeDranFarbeSpielabsicht(true);
            String text = "Auf diese Sau kann nicht ausgerufen werden. Wähle eine andere.";
            gui.hinweisAnNutzer(text);
            System.out.println(text);
        }
    }

    /**
     * Aufruf von Runde, welche Spielart ausgerufen wurde
     * - spieler: int Wert, wie die Runde ihn speichert (braucht ihn dann wieder, deswegen Übergabe an Model)
     * - Ausgabe für GUI, wer und was ausgerufen wurde
     */
    @Override
    public void spielArtEntschieden(int spieler, Farbe farbe, SpielArt spielArt) {
        WelcherSpieler welcherSpieler = wieVielterSpieler(spieler); //Wert, wie ihn Spieler-Klassen, z.B. GUI, nutzen
        model.setzeSpielArt(welcherSpieler, spielArt, farbe, spieler);
        //Ausgabe in GUI
        String text = ausgabeBeimAusrufen(spielArt, welcherSpieler, farbe);
        gui.textAusgeben(text);
        //okButton
        gui.okButtonActionListenerLoeschen();
        gui.okButtonSichtbarkeit(true);
        gui.okBuActLiSetzenStichSpielen(runde);
    }

    /**
     * Aufforderung der Runde eine Karte zu legen
     * - im Model Übergabewerte setzen
     * - Buttons wieder drückbar machen
     */
    @Override
    public void legeEineKarte(int wiederholung, int vorhand) {
        gui.okButtonActionListenerLoeschen();
        gui.okButtonSichtbarkeit(false);
        gui.buttonKartenZuornden(this, model.gebeHandkarten());
        model.setzeWiederholung(wiederholung);
        model.setzeVorhand(vorhand);
        model.setzeDranLegen(true);
        gui.hinweisAnNutzer("Lege eine Karte.");
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
        if (!model.gebeDranLegen() || index > model.gebeHandkarten().size()) { //Spieler soll keine Karte legen → nichts soll passieren
            System.out.println("return");
            model.setzeDranLegen(true);
            return;
        }
        model.setzeDranLegen(false);
        System.out.println("DEBUG: button handkarten geklickt");

        //Überprüfung, ob Karte erlaubt ist
        boolean erlaubt = false;
        //Überprüfung, ob man weglaufen darf
        int anzahlSpielerSchonGelegt = model.gebeAnzahlSpielerSchonGelegt();
        ArrayList<Spielkarte> erlaubteKarten;
        if (anzahlSpielerSchonGelegt == 0) {
            Spielkarte sau = new Spielkarte(model.gebeFarbe(), Werte.SAU);
            erlaubteKarten = erlaubteKartenAusspielenBeiSauspiel(model.gebeHandkarten(), sau);
            for (int i = 0; i < erlaubteKarten.size(); i++) {
                if (spielkarte.equals(erlaubteKarten.get(i))) {
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
            System.out.println("DEBUG: Karte erlaubt");
            gui.buttonKartenZuorndenKeineReaktion(model.gebeHandkarten());
            gui.handkartenSichtbarkeitSetzen(false, index);
            gui.entferneIndexVonHandButtons(index);
            model.gebeHandkarten().remove(spielkarte);
            System.out.println("DEBUG: " + model.gebeHandkarten().size());
            gui.okButtonSichtbarkeit(true);
            gui.hinweisAnNutzer("");
            runde.karteAbfragenAufgerufen(spielkarte);
        } else {
            System.out.println("DEBUG: Karte nicht erlaubt");
            gui.hinweisAnNutzer("Diese Karte darf nicht gelegt werden. Lege eine andere.");
            model.setzeDranLegen(true);
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
                    System.out.println("ERROR: Eine ungültige Spielabsicht wurde ausgerufen.");
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
        WelcherSpieler welcherSpieler = wieVielterSpieler(spielerHatGelegt);
        gui.karteInDieMitte(karte, welcherSpieler);
        model.setzeGelegteKarte(karte);
        model.setzeWiederholung(wiederholung);
        model.setzeVorhand(spielerHatGelegt);
        //Nachdem die Farbe der gesuchten Sau gespielt wird, darf die gesuchte wie jede andere Karte einer Farbe frei gespielt werden.
        if (model.gebeAnzahlSpielerSchonGelegt() == 0 && !karte.istTrumpf(model.gebeSpielArt(), null) && karte.gebeFarbe() == model.gebeFarbe()) {
            model.setzteSauFarbeVorhandGespielt(true);
        }
        //Überprüfung, ob gesuchte Sau gelegt wird → wenn ja, dann speichern, wer Mitspieler ist
        if (karte.gebeFarbe() == model.gebeFarbe() && karte.gebeWert() == Werte.SAU) {
            model.setzeMitspieler(spielerHatGelegt);
        }
        gui.okButtonActionListenerLoeschen();
        gui.okBuActLiSetzenFrageStichVorbei(runde);
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

        gui.textAusgeben(text);
        gui.mitteAufrauemen();
        model.stichBeendet();
        gui.okButtonActionListenerLoeschen();
        gui.okButtonSichtbarkeit(true);
        gui.okBuActLiSetzenStichSpielen(runde);
    }


    /**
     * berechnet Gewinner und Punkte
     *
     * @param gewinner:          übergibt die int Werte, wo die Gewinner sitzen
     * @param uebergebenePunkte: übergibt die int Werte, nach Sitzreihenfolge (von 0 bis 3)
     */
    @Override
    public void rundeGewonnen(int[] gewinner, int[] uebergebenePunkte) {
        int[] verlierer = {-1, -1}; //speichert int der Verlierer zwischen; default auf -1
        int i = 0; //Zählvariable, um das Array zu füllen
        for (int j = 0; j < 4; j++) {
            //Überprüft, ob int j schon im Gewinnerteam, sonst wird er gespeichert
            if (gewinner[0] != j && gewinner[1] != j) {
                verlierer[i] = j;
                i++;
            }
        }
        //Speicherung in WelcherSpieler-Werten
        WelcherSpieler[] spieler = new WelcherSpieler[4]; //speichert erst die beiden Gewinner, dann die beiden Verlierer
        spieler[0] = wieVielterSpieler(gewinner[0]);
        spieler[1] = wieVielterSpieler(gewinner[1]);
        spieler[2] = wieVielterSpieler(verlierer[0]);
        spieler[3] = wieVielterSpieler(verlierer[1]);
        //Punkte in ein Array sortieren
        int[] punkte = new int[2]; //richtig sortierte Punkte: gewinnerteamPunkte, verliererteamPunkte
        punkte[0] = uebergebenePunkte[gewinner[0]] + uebergebenePunkte[gewinner[1]];
        punkte[1] = 120 - punkte[0];
        //Ausgabe
        gui.textAusgeben("");
        gui.endtextAnzeigen(spieler, punkte);
        gui.mitteAufrauemen();
        gui.okButtonActionListenerLoeschen();
        gui.okButtonSichtbarkeit(false);
        gui.neueRundeButtonSichtbarkeit(true);
        gui.neueRundeButtonText("Turnier-Punkte Übersicht");
        gui.neueRundeBuActLiSetzenNeueRundeStarten(this, model.gebeSpielArt());
    }

    public void neueRundeStarten(SpielArt spielArt) {
        gui.neueRundeButtonActionListenerLoeschen();
        gui.spielGUIUnsichtbar();
        runde.neueRundeStarten(spielArt);
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

    /*Methode, die von Runde aufgerufen wird, um den Mitspieler herauszubekommen*/
    public int gebeMitspieler() {
        return model.gebeMitspieler();
    }
}
