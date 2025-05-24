package InformatikProjekt;

// Programmierer: Adrian

public class Runde {
    private final Mitspieler[] spieler;
    private int vorhand; // spielt die erste Karte
    private int ausrufer;
    private Spielkarte ausgerufeneSau;
    private int ausruferMitspieler;
    private int[] punkte;
    private Spielkarte[] letzterStich;
    private Spielkarte[] aktuellerStich;
    private Spielkarte aktuelleSpielKarte;

    // @params Array mit Mitspielern, int welcher Index der Spieler ist, int wer die Runde startet (Vorhand)
    public Runde(Mitspieler[] spieler, int vorhand) {
        this.spieler = spieler;
        this.vorhand = vorhand;
    }

    // TODO: rundeStarten() Methode in Mitspieler aufrufen und Karten zufällig verteilen, und übergeben, wer der Spieler ist
    public void starteRunde() {
        SpielArt aktuellHoechstesSpiel;
        SpielArt hoechstesSpiel;
        hoechstesSpiel = SpielArt.KEINSPIEL;
        // TODO: rotieren der Spieler
        for (int i = 0; i < 4; i++) {
            do {
                aktuellHoechstesSpiel = spieler[i].spielabsichtFragen(hoechstesSpiel); // return 0, für weiter; 1 für Sau; 2 für Wenz; 3 für Solo
            } while (aktuellHoechstesSpiel.gebeSpielArtID() != 0 || aktuellHoechstesSpiel.gebeSpielArtID() <= hoechstesSpiel.gebeSpielArtID());
            if (aktuellHoechstesSpiel.gebeSpielArtID() > hoechstesSpiel.gebeSpielArtID()) {
                hoechstesSpiel = aktuellHoechstesSpiel;
                ausrufer = i;
            }
        }

        switch (hoechstesSpiel.gebeSpielArtID()) {
            case 0:
                starteRunde(); // oder Ramsch; Methode muss möglicherweise extern erneut aufgerufen werden, ohne Rekursion
                break;
            case 1:
                spielSchleifeSau(8);
                break;
            case 2:
                // spielSchleifeWenz();
                break;
            case 3:
                // spielSchleifeSolo();
                break;
        }
    }

    // TODO: spielSchleifeWenz(), spielSchleifeSolo()
    private void spielSchleifeSau(int anzahlKarten) {

        // einen Stich spielen
        for (int i = 0; i < 4; i++) {
            // Spieler "amZug" fragen welche Karte er legen möchte
            aktuelleSpielKarte = this.spieler[i].legeEineKarte();
        }

        // Auswertung des Stichs
        int sieger = ermittleSieger(aktuellerStich);
        letzterStich = aktuellerStich.clone(); // nicht mit Referenz übergeben, da aktuellerStich sich ändert
        punkte[sieger] += ermittlePunkte(aktuellerStich);

        // Ausgabe des Stichergebnisses
        for (int i = 0; i < 4; i++) {
            spieler[i].stichGewonnen(sieger);
        }

        // Rekursionsschritt
        if (anzahlKarten > 0) {
            spielSchleifeSau(anzahlKarten - 1);
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
            aktuellePunkte += aktuellerStich[i].gebeWert().gebeWerteID();
        }

        return aktuellePunkte;
    }
}