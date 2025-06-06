package InformatikProjekt;

import java.util.ArrayList;

// Programmierer: Adrian

public class Runde {
    private ArrayList<Mitspieler> spieler;
    private Speicherung speicherung;
    private RundeModel rundeModel;
    private Turnier turnier;

    public Runde(ArrayList<Mitspieler> spieler, ArrayList<Spielkarte> spielKarten, int positionSpieler, Speicherung speicherung, int vorhand, Turnier turnier, int wiederholungRunden, Spieler echterSpieler) {
        this.spieler = spieler;
        this.speicherung = speicherung;
        this.turnier = turnier;
        rundeModel = new RundeModel(positionSpieler, vorhand, wiederholungRunden, echterSpieler);
        //spieler.get(positionSpieler).setzeRundeReferenz(this);

        for (int i = 0; i < 4; i++) {
            ArrayList<Spielkarte> spielKartenProSpieler = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                spielKartenProSpieler.add(spielKarten.get(i + j));
            }

            spieler.get(i).rundeStarten(spielKartenProSpieler, positionSpieler);
        }

        System.out.println("DEBUG: Karten wurden gesetz, jetzt hinlegen");
        for (int i = 0; i < spieler.size(); i++) {
            spieler.get(i).setzeRunde(this);
        }
        rundeModel.gebeEchterSpieler().kartenHinlegen(0, vorhand);
    }

    // Spielabsicht fragen
    public void spielAbsichtFragenRunde(int wiederholung, int vorhand) {
        System.out.println("DEBUG: Warte auf Spielabsicht von Spieler " + vorhand);
        spieler.get(vorhand).setzeRunde(this);
        spieler.get(vorhand).spielabsichtFragen(wiederholung, rundeModel.gebeHoechsteSpielart(), vorhand);
    }

    public void spielabsichtFragenAufgerufen(int wiederholung, SpielArt spielArt, int vorhand) {
        rundeModel.setzeAktuelleSpielArt(spielArt);
        if (spielArt.compareTo(rundeModel.gebeHoechsteSpielart()) > 0) {
            rundeModel.setzeHoechsteSpielart(spielArt);
            rundeModel.setzeAusrufer(vorhand);
            rundeModel.setzeAusruferReferenz(spieler.get(vorhand));
            System.out.println("DEBUG: aktuell hoechste Spielart: " + rundeModel.gebeHoechsteSpielart());
        }
        if (wiederholung < 3) {
            if (vorhand < 3) {
                spieler.get(vorhand).spielabsichtFragen(wiederholung + 1, vorhand + 1, rundeModel.gebeAktuelleSpielArt(), rundeModel.gebeAusrufer());
            }
            else {
                spieler.get(vorhand).spielabsichtFragen(wiederholung + 1, 0, rundeModel.gebeAktuelleSpielArt(), rundeModel.gebeAusrufer());
            }
        }
        else {
            if (rundeModel.gebeHoechsteSpielart() == SpielArt.KEINSPIEL) {
                turnier.rundeStarten(rundeModel.gebeWiederholungenRunden(), rundeModel.gebeSiegerArray());
            }
            else if (rundeModel.gebeHoechsteSpielart() == SpielArt.SAUSPIEL) {
                spielAbsichtAusgeben();
            }
            else {
                System.out.println("ERROR: Spielart nicht bekannt!");
            }
        }
    }

    // SAUSPIEL:
    // Spielabsicht ausgeben
    public void spielAbsichtAusgeben() {
        for (Mitspieler aktuellerSpieler : spieler) {
            aktuellerSpieler.spielerHatSpielabsichtGesagt(rundeModel.gebeHoechsteSpielart(), rundeModel.gebeAusrufer());
        }
    }

    // fragt den Ausrufer, auf welche Sau er spielen möchte (von Spieler aufgerufen)
    public void farbeFuerSpielAbsicht() {
        if (rundeModel.gebeAusruferReferenz() == null) {
            System.out.println("DEBUG: rundeModel.gebeAusruferReferenz() == null in farbeFuerSpielAbsicht() -> NullPointerException");
            return;
        }
        rundeModel.gebeAusruferReferenz().farbeFuerSpielAbsicht(rundeModel.gebeHoechsteSpielart());
        System.out.println("Warte auf Farbe von Spieler " + rundeModel.gebeAusrufer());
    }

    // Farbe ausgeben
    public void farbeFuerSpielAbsichtAufgerufen(Farbe farbe) {
        for (Mitspieler aktuellerSpieler : spieler) {
            aktuellerSpieler.spielArtEntschieden(rundeModel.gebeAusrufer(), farbe, rundeModel.gebeHoechsteSpielart());
        }
    }

    // Runde spielen
    public void stichSpielen(int wiederholungStiche) {
        if (wiederholungStiche < 7) {
            karteAbfragen(0, rundeModel.gebeVorhand());
        }
        else {
            rundeModel.setzeSiegerArray(rundenSiegerErmitteln());
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
            turnier.rundeStarten(rundeModel.gebeWiederholungenRunden() + 1, rundeModel.gebeSiegerArray());
        }
    }

    // Stich spielen
    public void karteAbfragen(int wiederholung, int vorhand) {
        spieler.get(vorhand).legeEineKarte(wiederholung, 0);
        System.out.println("Warte auf Spielabsicht von Spieler " + vorhand);
    }

    public void karteAbfragenAufgerufen(int wiederholung, Spielkarte karte, int vorhand) {
        rundeModel.setzeAktuellenStich(wiederholung, karte);
        for (Mitspieler aktuellerSpieler : spieler) {
            aktuellerSpieler.karteWurdeGelegt(karte, vorhand);
        }
        if (wiederholung < 3) {
            if (vorhand < 3) {
                karteAbfragen(wiederholung + 1, vorhand + 1);
            }
            else {
                karteAbfragen(wiederholung + 1, 0);
            }
        }
        else {
            auswertungStich();
        }
    }

    public void auswertungStich() {
        int sieger = ermittleSieger(rundeModel.gebeAktuellerStichArray());
        rundeModel.setzeVorhand(sieger);
        rundeModel.kopiereInLetzerStich(rundeModel.gebeAktuellerStichArray());
        rundeModel.addierePunkte(sieger, ermittlePunkte(rundeModel.gebeAktuellerStichArray()));
        for (Mitspieler aktuellerSpieler : spieler) {
            aktuellerSpieler.stichGewonnen(sieger);
        }

        // Speicherung
        speicherung.KarteGespielt();
        speicherung.DatenSpeichern();
    }



//    public int[] starteRunde(int vorhand) {
//        SpielArt aktuellHoechstesSpiel;
//        SpielArt hoechstesSpiel = SpielArt.KEINSPIEL;
//        Mitspieler ausruferObjekt = null;
//
//        for (int i = 0; i < 4; i++) {
//            // do {
//            aktuellHoechstesSpiel = spieler.get(i).spielabsichtFragen(hoechstesSpiel);
////            System.out.println("DEBUG: aktuellHoechstesSpiel: " + aktuellHoechstesSpiel);
////            } while (aktuellHoechstesSpiel != SpielArt.KEINSPIEL || aktuellHoechstesSpiel.compareTo(hoechstesSpiel) > 0); // zur Sicherheit wird hier nochmal geschaut, ob das gegebene höchste Spiel auch legal ist, sollte aber schon in Mitspieler geregelt werden
//            if (aktuellHoechstesSpiel.compareTo(hoechstesSpiel) > 0) {
//                hoechstesSpiel = aktuellHoechstesSpiel;
//                rundeModel.setzeAusrufer(i);
//                ausruferObjekt = spieler.get(i);
//            }
//        }
//        if (hoechstesSpiel == SpielArt.KEINSPIEL) {
//            return null;
//        }
//
//        for (Mitspieler aktuellerSpieler : spieler) {
//            aktuellerSpieler.spielerHatSpielabsichtGesagt(hoechstesSpiel, rundeModel.gebeAusrufer());
//        }
//
//        Farbe farbe = ausruferObjekt.farbeFuerSpielAbsicht(hoechstesSpiel);
//
//        for (Mitspieler aktuellerSpieler : spieler) {
//            aktuellerSpieler.spielArtEntschieden(rundeModel.gebeAusrufer(), farbe, hoechstesSpiel);
//        }
//
//        switch (hoechstesSpiel) {
////            case KEINSPIEL:
////                starteRunde(vorhand); // oder Ramsch; Methode muss möglicherweise extern erneut aufgerufen werden, ohne Rekursion
////                return null;
//            case SAUSPIEL:
//                spielSchleifeSau(8, vorhand);
//                int[] sieger = rundenSiegerErmitteln();
//                for (Mitspieler aktuellerSpieler : spieler) {
//                    aktuellerSpieler.rundeGewonnen(sieger, rundeModel.gebePunkteArray());
//                }
//
//                // Speicherung
//                if (sieger[0] == rundeModel.gebePositionSpieler() || sieger[1] == rundeModel.gebePositionSpieler()) {
//                    speicherung.gesamtePunkteErhoehen(rundeModel.gebePunkte(sieger[0]) + rundeModel.gebePunkte(sieger[1])); // Speicherung der zusammengerechneten Punkte der Sieger
//                    if (rundeModel.gebePunkte(sieger[0]) + rundeModel.gebePunkte(sieger[1]) > 120) {
//                        // Methode SpielGewonnen Schneider noch nicht vorhanden
//                    }
//                    else {
//                        speicherung.SpielGewonnen(SpielArt.SAUSPIEL);
//                    }
//                }
//                else if (rundeModel.gebePunkte(sieger[0]) + rundeModel.gebePunkte(sieger[1]) > 90) { // unter 30 Punkte ist man Schneider
//                    speicherung.SpielVerlorenSchneider(SpielArt.SAUSPIEL);
//                }
//                else {
//                    speicherung.SpielVerloren(SpielArt.SAUSPIEL);
//                }
//                speicherung.RundePunktzahlMelden(rundeModel.gebePunkte(rundeModel.gebePositionSpieler()));
//                speicherung.DatenSpeichern();
//
//                return sieger;
//            case WENZ:
//                // spielSchleifeWenz();
//                return null;
//            case SOLO:
//                // spielSchleifeSolo();
//                return null;
//        }
//
//        System.out.println("ERROR: Fehler in starteRunde()");
//        return null;
//    }
//
//    // TODO: spielSchleifeWenz(), spielSchleifeSolo()
//    private void spielSchleifeSau(int anzahlKarten, int vorhand) {
//
//        // einen Stich spielen
//        int amZug = vorhand;
//        for (int i = 0; i < 4; i++) {
//            // Spieler "amZug" fragen welche Karte er legen möchte
//            Spielkarte aktuelleSpielkarte = spieler.get(amZug).legeEineKarte();
//            rundeModel.setzeAktuellenStich(amZug, aktuelleSpielkarte);
//
//            for (Mitspieler aktuellerSpieler : spieler) {
//                aktuellerSpieler.karteWurdeGelegt(aktuelleSpielkarte, amZug);
//            }
//
//            amZug = (amZug == 3) ? 0 : amZug + 1;
//        }
//
//        // Speicherung
//        speicherung.KarteGespielt();
//        speicherung.DatenSpeichern();
//
//        // Auswertung des Stichs
//        int sieger = ermittleSieger(rundeModel.gebeAktuellerStichArray());
//        Spielkarte[] letzterStich = rundeModel.gebeAktuellerStichArray().clone(); // nicht mit Referenz übergeben, da aktuellerStich sich ändert
//        rundeModel.addierePunkte(sieger, ermittlePunkte(rundeModel.gebeAktuellerStichArray()));
//
//        // Ausgabe des Stichergebnisses
//        for (Mitspieler aktuellerSpieler : spieler) {
//            aktuellerSpieler.stichGewonnen(sieger);
//        }
//
//        // Rekursionsschritt
//        if (anzahlKarten > 0) {
//            spielSchleifeSau(anzahlKarten - 1, sieger); // Sieger wird neue Vorhand
//        }
//    }

    public int ermittleSieger(Spielkarte[] aktuellerStich) {
        Spielkarte hoechsteKarte = aktuellerStich[0];
        boolean trumpfImStich = istTrumpf(hoechsteKarte);
        Farbe farbe = hoechsteKarte.gebeFarbe();
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
            }
        }

        else if (wert == Werte.UNTER){
            switch (farbe) {
                case EICHEL: return 4;
                case GRAS: return 5;
                case HERZ: return 6;
                case SCHELLEN: return 7;
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
            }
        }

        System.out.println("ERROR: Fehler bei der Berechnung des Trumpf-Rangs");
        return -1;
    }

    public int gebeFarbeRang(Spielkarte karte) {
        Werte wert = karte.gebeWert();

        switch (wert) {
            case SAU: return 0;
            case ZEHNER: return 1;
            case KOENIG: return 2;
            case NEUNER: return 3;
            case ACHTER: return 4;
            case SIEBENER: return 5;
        }

        System.out.println("ERROR: Fehler bei der Berechnung des Rangs einer Farbe");
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
        if (mitspieler == -1) System.out.println("ERROR: Position des echten Spielers ist falsch!");

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
