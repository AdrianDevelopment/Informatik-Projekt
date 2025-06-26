package Controler;

import Model.*;

import java.util.ArrayList;

// Programmierer: Adrian

public class Runde {
    private final ArrayList<Mitspieler> spieler;
    private final Speicherung speicherung;
    private final RundeModel rundeModel;
    private final Turnier turnier;

    // debug hardcoded
    boolean debug = true;

    public Runde(ArrayList<Mitspieler> spieler, ArrayList<Spielkarte> spielKarten, int positionSpieler, Speicherung speicherung, Turnier turnier, int wiederholungRunden, Spieler echterSpieler) {
        this.spieler = spieler;
        this.speicherung = speicherung;
        this.turnier = turnier;
        int vorhand = wiederholungRunden % 4;
        rundeModel = new RundeModel(positionSpieler, vorhand, wiederholungRunden, echterSpieler, spieler.getFirst());

        for (int i = 0; i < 4; i++) {
            ArrayList<Spielkarte> spielKartenProSpieler = new ArrayList<>(spielKarten.subList(i * 8, (i + 1) * 8));
            spieler.get(i).rundeStarten(spielKartenProSpieler, i);
        }

        System.out.println("DEBUG: Karten wurden gesetz, jetzt hinlegen");
        for (Mitspieler mitspieler : spieler) {
            mitspieler.setzeRunde(this);
        }
        rundeModel.gebeEchterSpieler().kartenHinlegen(0, vorhand);
    }

    // Spielabsicht fragen
    public void spielAbsichtFragenRunde(int wiederholung, int vorhand) {
        if (wiederholung < 4) {
            if (debug) System.out.println("DEBUG: Warte auf Spielabsicht von Spieler " + vorhand);
            spieler.get(vorhand).setzeRunde(this);
            spieler.get(vorhand).spielabsichtFragen(wiederholung, rundeModel.gebeHoechsteSpielart(), vorhand);
        }
        else {

            rundeModel.gebeEchterSpieler().spielAbsichtAusgeben(rundeModel.gebeAusrufer(), rundeModel.gebeHoechsteSpielart());
        }
    }

    public void spielabsichtFragenAufgerufen(int wiederholung, int vorhand, SpielArt spielArt) {
        rundeModel.setzeAktuelleSpielArt(spielArt);
        if (spielArt.compareTo(rundeModel.gebeHoechsteSpielart()) > 0) {
            rundeModel.setzeHoechsteSpielart(spielArt);
            rundeModel.setzeAusrufer(vorhand);
            rundeModel.setzeAusruferReferenz(spieler.get(vorhand));
            if (debug) System.out.println("DEBUG: aktuell hoechste Spielart: " + rundeModel.gebeHoechsteSpielart());
        }
        rundeModel.gebeEchterSpieler().spielerHatSpielabsichtGesagt(wiederholung, vorhand, spielArt);
    }

    // SAUSPIEL:
    // fragt den Ausrufer, auf welche Sau er spielen möchte (von Spieler aufgerufen)
    public void farbeFuerSpielAbsicht() {
        if (rundeModel.gebeAusruferReferenz() == null) {
            if (debug) System.out.println("DEBUG: rundeModel.gebeAusruferReferenz() == null in farbeFuerSpielAbsicht() -> NullPointerException");
            return;
        }
        if (debug) System.out.println("DEBUG: Warte auf Farbe von Spieler " + rundeModel.gebeAusrufer());
        rundeModel.gebeAusruferReferenz().farbeFuerSpielAbsicht(rundeModel.gebeHoechsteSpielart());
    }

    // Farbe ausgeben
    public void farbeFuerSpielAbsichtAufgerufen(Farbe farbe) {
        if (debug) System.out.println("DEBUG: Farbe ausgerufen: " + farbe);
        for (Mitspieler aktuellerSpieler : spieler) {
            aktuellerSpieler.spielArtEntschieden(rundeModel.gebeAusrufer(), farbe, rundeModel.gebeHoechsteSpielart());
        }
    }

    // Runde spielen
    public void stichSpielen() {
        if (rundeModel.gebeStichWiederholung() < 8) {
            karteAbfragen();
        }
        else {
            rundeModel.setzeSiegerArray(rundenSiegerErmitteln());
            for (Mitspieler mitspieler : spieler) {
                mitspieler.rundeGewonnen(rundeModel.gebeSiegerArray(), rundeModel.gebePunkteArray());
            }
            if (rundeModel.gebeSieger(0) == rundeModel.gebePositionSpieler() || rundeModel.gebeSieger(1) == rundeModel.gebePositionSpieler()) {
                speicherung.gesamtePunkteErhoehen(rundeModel.gebePunkte(rundeModel.gebeSieger(0)) + rundeModel.gebePunkte(rundeModel.gebeSieger(1))); // Speicherung der zusammengerechneten Punkte der Sieger
                if (rundeModel.gebePunkte(rundeModel.gebeSieger(0)) + rundeModel.gebePunkte(rundeModel.gebeSieger(1)) > 120) {
                    // Methode SpielGewonnen Schneider noch nicht vorhanden
                }
                else {
                    speicherung.SpielGewonnen(SpielArt.SAUSPIEL);
                }
            }
            else if (rundeModel.gebePunkte(rundeModel.gebeSieger(0)) + rundeModel.gebePunkte(rundeModel.gebeSieger(1)) > 90) { // unter 30 Punkte ist man Schneider
                speicherung.SpielVerlorenSchneider(SpielArt.SAUSPIEL);
            }
            else {
                speicherung.SpielVerloren(SpielArt.SAUSPIEL);
            }
            speicherung.RundePunktzahlMelden(rundeModel.gebePunkte(rundeModel.gebePositionSpieler()));
            speicherung.DatenSpeichern();
            //turnier.rundeStarten(rundeModel.gebeWiederholungenRunden() + 1, rundeModel.gebeSiegerArray()); //TOM Methode startet eine neue Runde, die die Karten anzeigt und den neuen okButton
        }
    }

    // Stich spielen
    public void karteAbfragen() {
        spieler.get(rundeModel.gebeVorhand()).legeEineKarte(rundeModel.gebeWiederholung(), rundeModel.gebeVorhand());

        if (debug) System.out.println("DEBUG: Warte auf Spielabsicht von Spieler " + rundeModel.gebeVorhand());
    }

    public void karteAbfragenAufgerufen( Spielkarte karte ) {
        rundeModel.setzeAktuellenStich(rundeModel.gebeVorhand(), karte);

        for (Mitspieler aktuellerSpieler : spieler) {
            aktuellerSpieler.karteWurdeGelegt(karte, rundeModel.gebeVorhand(), rundeModel.gebeWiederholung());
        }
    }

    public void frageStichVorbei(){
        if (rundeModel.gebeWiederholung() < 3) {
            rundeModel.setzeVorhand((rundeModel.gebeVorhand() + 1) % 4);
            rundeModel.setzteWiederholung(rundeModel.gebeWiederholung() + 1);
            karteAbfragen();

        }
        else {
            auswertungStich();
        }
    }

    public void auswertungStich() {
        int sieger = ermittleSieger(rundeModel.gebeAktuellerStichArray());

        rundeModel.setzeVorhand(sieger);
        rundeModel.setzteWiederholung(0);
        rundeModel.setzeStichWiederholung(rundeModel.gebeStichWiederholung() + 1);
        rundeModel.setzeLetzterStich(rundeModel.gebeAktuellerStichArray());

        rundeModel.addierePunkte(sieger, ermittlePunkte(rundeModel.gebeAktuellerStichArray()));
        for (Mitspieler aktuellerSpieler : spieler) {
            aktuellerSpieler.stichGewonnen(sieger);
        }

        // Speicherung
        speicherung.KarteGespielt();
        speicherung.DatenSpeichern();
    }

    public void neuRundeStarten() {
        turnier.rundeStarten(rundeModel.gebeWiederholungenRunden() + 1, rundeModel.gebeSiegerArray());
    }

    public int ermittleSieger(Spielkarte[] aktuellerStich) {
        Spielkarte hoechsteKarte = aktuellerStich[0];
        boolean trumpfImStich = istTrumpf(hoechsteKarte);
        Farbe farbe = aktuellerStich[0].gebeFarbe();
        int sieger = 0;

        for (int i = 1; i < 4; i++) {
            // wenn im Stich bisher min. ein Trumpf ist, wird gecheckt, ob der aktuelle Trumpf höher ist
            if (trumpfImStich && istTrumpf(aktuellerStich[i])) {
                if (gebeTrumpfRang(aktuellerStich[i]) < gebeTrumpfRang(hoechsteKarte)) {
                    hoechsteKarte = aktuellerStich[i];
                    sieger = i;
                }
            }
            // wenn im Stich bisher kein Trumpf ist und die aktuelle Karte ein Trumpf ist, gewinnt diese sofort
            else if (!trumpfImStich && istTrumpf(aktuellerStich[i])) {
                hoechsteKarte = aktuellerStich[i];
                trumpfImStich = true;
                sieger = i;
            }
            // wenn im Stich bisher kein Trumpf ist und die aktuelle Karte dieselbe Farbe hat, wie die erste Karte, gewinnt diese, wenn sie höher ist
            else if (!trumpfImStich && !istTrumpf(aktuellerStich[i]) && aktuellerStich[i].gebeFarbe() == farbe) {
                if (gebeFarbeRang(aktuellerStich[i]) < gebeFarbeRang(hoechsteKarte)) {
                    hoechsteKarte = aktuellerStich[i];
                    sieger = i;
                }
            }
        }
        // sieger wird relativ zu der Reihenfolge des aktuellen Stiches ermittelt
//        int vorhand = rundeModel.gebeVorhand() + 1;
//        return (vorhand + sieger) % 4;
        return sieger;
    }

    public boolean istTrumpf(Spielkarte karte) {
        return karte.gebeFarbe() == Farbe.HERZ || karte.gebeWert() == Werte.UNTER || karte.gebeWert() == Werte.OBER;
    }

    public int gebeTrumpfRang(Spielkarte karte) {
        Farbe farbe = karte.gebeFarbe();
        Werte wert = karte.gebeWert();

        if (wert == Werte.OBER){
            switch (farbe) {
                case EICHEL: return 0;
                case GRAS: return 1;
                case HERZ: return 2;
                case SCHELLEN: return 3;
                default: System.out.println("ERROR: Fehler bei der Berechnung des Trumpf-Ranges");
            }
        }

        else if (wert == Werte.UNTER){
            switch (farbe) {
                case EICHEL: return 4;
                case GRAS: return 5;
                case HERZ: return 6;
                case SCHELLEN: return 7;
                default: System.out.println("ERROR: Fehler bei der Berechnung des Trumpf-Ranges");
            }
        }

        else {
            switch (wert) {
                case SAU: return 8;
                case ZEHNER: return 9;
                case KOENIG: return 10;
                case NEUNER: return 11;
                case ACHTER: return 12;
                case SIEBENER: return 13;
                default: System.out.println("ERROR: Fehler bei der Berechnung des Trumpf-Ranges");
            }
        }

        System.out.println("ERROR: Fehler bei der Berechnung des Trumpf-Ranges");
        return -1;
    }

    public int gebeFarbeRang(Spielkarte karte) {
        Werte wert = karte.gebeWert();

        switch (wert) {
            case SAU: return 0;
            case ZEHNER: return 1;
            case KOENIG: return 2;
            case OBER: return 3; // OBER für andere Spielarten als Sauspiel
            case UNTER: return 4; // UNTER für andere Spielarten als Sauspiel
            case NEUNER: return 5;
            case ACHTER: return 6;
            case SIEBENER: return 7;
            default: System.out.println("ERROR: Fehler bei der Berechnung des Farb-Ranges");
        }

        System.out.println("ERROR: Fehler bei der Berechnung des Farb-Ranges");
        return -1;
    }

    public int ermittlePunkte(Spielkarte[] aktuellerStich) {
        int aktuellePunkte = 0;

        for (int i = 0; i < 4; i++) {
            aktuellePunkte += aktuellerStich[i].gebeWert().gebePunktzahl();
        }

        return aktuellePunkte;
    }

    public int[] rundenSiegerErmitteln() {
        int mitspieler = spieler.get(rundeModel.gebePositionSpieler()).gebeMitspieler();
        int punkteSpieler = rundeModel.gebePunkte(rundeModel.gebeAusrufer()) + rundeModel.gebePunkte(mitspieler);
        int[] gegenspieler = new int[2];
        int position = 0;

        if (punkteSpieler >= 60) {
            return new int[] {rundeModel.gebeAusrufer(), mitspieler};
        }
        else {
            for (int i = 0; i < 4; i++) {
                if (i != rundeModel.gebeAusrufer() && i != mitspieler) {
                    gegenspieler[position] = i;
                    position++;
                }
            }
            return gegenspieler;
        }
    }
}