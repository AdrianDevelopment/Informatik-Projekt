package InformatikProjekt;

import java.util.ArrayList;

// Programmierer: Adrian

public class Runde {
    private final ArrayList<Mitspieler> spieler; // Queue, da erster Mitspieler in Queue Vorhand ist, gecycelt wird in Tunier
    private int[] punkte;
    private int ausrufer;
    private Mitspieler ausruferObjekt;
    private Spielkarte[] aktuellerStich;
    private Spielkarte aktuelleSpielKarte;

    // @params Array mit Mitspielern, int welcher Index der Spieler ist, int wer die Runde startet (Vorhand)
    public Runde(ArrayList<Mitspieler> spieler, ArrayList<Spielkarte> spielKarten, int positionSpieler) {
        this.spieler = spieler;

//        for (int i = 0; i < 4; i++) {
//            spieler.get(i).rundeStarten(spielKarten, positionSpieler);
//        }
    }

    public int[] starteRunde(int vorhand) {
        SpielArt aktuellHoechstesSpiel;
        SpielArt hoechstesSpiel = SpielArt.KEINSPIEL;

        for (int i = 0; i < 4; i++) {
            // do {
            aktuellHoechstesSpiel = spieler.get(i).spielabsichtFragen(hoechstesSpiel);
            // } while (aktuellHoechstesSpiel != SpielArt.KEINSPIEL || aktuellHoechstesSpiel.compareTo(hoechstesSpiel) > 0); // zur Sicherheit wird hier nochmal geschaut, ob das gegebene höchste Spiel auch legal ist, sollte aber schon in Mitspieler geregelt werden
            if (aktuellHoechstesSpiel.compareTo(hoechstesSpiel) > 0) {
                hoechstesSpiel = aktuellHoechstesSpiel;
                ausrufer = i;
                ausruferObjekt = spieler.get(i);
            }
        }

        for (Mitspieler aktuellerSpieler : spieler) {
            aktuellerSpieler.spielerHatSpielabsichtGesagt(hoechstesSpiel, ausrufer);
        }

        Farbe farbe = ausruferObjekt.farbeFuerSpielAbsicht(hoechstesSpiel);

        for (Mitspieler aktuellerSpieler : spieler) {
            aktuellerSpieler.spielArtEntschieden(ausrufer, farbe, hoechstesSpiel);
        }

        switch (hoechstesSpiel) {
            case KEINSPIEL:
                starteRunde(vorhand); // oder Ramsch; Methode muss möglicherweise extern erneut aufgerufen werden, ohne Rekursion
                return punkte;
            case SAUSPIEL:
                spielSchleifeSau(8, vorhand);
                return punkte;
            case WENZ:
                // spielSchleifeWenz();
                return punkte;
            case SOLO:
                // spielSchleifeSolo();
                return punkte;
        }

        System.out.println("ERROR: Fehler in starteRunde()");
        return punkte;
    }

    // TODO: spielSchleifeWenz(), spielSchleifeSolo()
    private void spielSchleifeSau(int anzahlKarten, int vorhand) {

        // einen Stich spielen
        int amZug = vorhand;
        for (int i = 0; i < 4; i++) {
            // Spieler "amZug" fragen welche Karte er legen möchte
            Spielkarte aktuelleSpielkarte = spieler.get(amZug).legeEineKarte();
            aktuellerStich[amZug] = aktuelleSpielkarte;

            for (Mitspieler aktuellerSpieler : spieler) {
                aktuellerSpieler.karteWurdeGelegt(aktuelleSpielkarte, amZug);
            }

            amZug = (amZug == 3) ? 0 : amZug + 1;
        }

        // Auswertung des Stichs
        int sieger = ermittleSieger(aktuellerStich);
        Spielkarte[] letzterStich = aktuellerStich.clone(); // nicht mit Referenz übergeben, da aktuellerStich sich ändert
        punkte[sieger] += ermittlePunkte(aktuellerStich);

        // Ausgabe des Stichergebnisses
        for (Mitspieler aktuellerSpieler : spieler) {
            aktuellerSpieler.stichGewonnen(sieger);
        }

        // Rekursionsschritt
        if (anzahlKarten > 0) {
            spielSchleifeSau(anzahlKarten - 1, sieger); // Sieger wird neue Vorhand
        }
    }

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

        System.out.println("DEBUG: Fehler bei der Berechnung des Trumpf-Rangs");
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

        System.out.println("DEBUG: Fehler bei der Berechnung des Rangs einer Farbe");
        return -1;
    }

    public int ermittlePunkte(Spielkarte[] aktuellerStich) {
        int aktuellePunkte = 0;

        for (int i = 0; i < 4; i++) {
            aktuellePunkte += aktuellerStich[i].gebeWert().gebePunktzahl();
        }

        return aktuellePunkte;
    }
}