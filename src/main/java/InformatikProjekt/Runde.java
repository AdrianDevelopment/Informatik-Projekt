package InformatikProjekt;

import java.util.ArrayList;
import java.util.Random;

// Programmierer: Adrian

public class Runde {
    private Mitspieler[] spieler;
    private int vorhand; // spielt die erste Karte
    private int ausrufer;
    private Spielkarte ausgerufeneSau;
    private int ausruferMitspieler;
    private int[] punkte;
    private Spielkarte[] letzterStich;
    private Spielkarte[] aktuellerStich;
    private Spielkarte aktuelleSpielKarte;
    private ArrayList<Werte> reihenfolgeKarten;
    private ArrayList<Spielkarte> truempfe;

    public Runde() {
        spieler = new Mitspieler[4];
        Random random = new Random();
        int randomNumber = random.nextInt(4);
        spieler[randomNumber] = new Spieler();
        for (int i = 0; i < 4; i++) {
            if (i != randomNumber) {
                spieler[i] = new Bot();
            }
        }

        reihenfolgeKarten = new ArrayList<>(6);
        reihenfolgeKarten.add(Werte.SIEBENER);
        reihenfolgeKarten.add(Werte.ACHTER);
        reihenfolgeKarten.add(Werte.NEUNER);
        reihenfolgeKarten.add(Werte.KOENIG);
        reihenfolgeKarten.add(Werte.ZEHNER);
        reihenfolgeKarten.add(Werte.SAU);

        truempfe = new ArrayList<>(14);
        truempfe.add(new Spielkarte(Farbe.HERZ, Werte.SIEBENER));
        truempfe.add(new Spielkarte(Farbe.HERZ, Werte.ACHTER));
        truempfe.add(new Spielkarte(Farbe.HERZ, Werte.NEUNER));
        truempfe.add(new Spielkarte(Farbe.HERZ, Werte.KOENIG));
        truempfe.add(new Spielkarte(Farbe.HERZ, Werte.ZEHNER));
        truempfe.add(new Spielkarte(Farbe.HERZ, Werte.SAU));
        truempfe.add(new Spielkarte(Farbe.SCHELLEN, Werte.UNTER));
        truempfe.add(new Spielkarte(Farbe.HERZ, Werte.UNTER));
        truempfe.add(new Spielkarte(Farbe.GRAS, Werte.UNTER));
        truempfe.add(new Spielkarte(Farbe.EICHEL, Werte.UNTER));
        truempfe.add(new Spielkarte(Farbe.SCHELLEN, Werte.OBER));
        truempfe.add(new Spielkarte(Farbe.HERZ, Werte.OBER));
        truempfe.add(new Spielkarte(Farbe.GRAS, Werte.OBER));
        truempfe.add(new Spielkarte(Farbe.EICHEL, Werte.OBER));
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
        // TODO: höchste Karte herausfinden
        Spielkarte hoechsteKarte = aktuellerStich[0];
        int sieger = 0;

        for (int i = 1; i < 4; i++) {
            // equals() Methode wurde in Spielkarte überschrieben, damit nicht das Objekt selber, sondern die Farbe bzw. Wert verglichen wird
            // wenn Karte Trumpf ist, wird gecheckt, ob der Trumpf höher ist
            if (truempfe.contains(aktuellerStich[i])) {
                if (truempfe.indexOf(aktuellerStich[i]) > truempfe.indexOf(hoechsteKarte)) {
                    hoechsteKarte = aktuellerStich[i];
                    sieger = i;
                }
            }
            // wenn Karte selbe Farbe ist, wird gecheckt, ob die Karte höher ist
            if (aktuellerStich[i].gebeFarbe().gebeFarbeID() == hoechsteKarte.gebeFarbe().gebeFarbeID()) {
                if (reihenfolgeKarten.indexOf(aktuellerStich[i]) > reihenfolgeKarten.indexOf(hoechsteKarte)) {
                    hoechsteKarte = aktuellerStich[i];
                    sieger = i;
                }
            }
        }

        return sieger;
    }

    private int ermittlePunkte(Spielkarte[] aktuellerStich) {
        // TODO: Punkte errrechnen aus aktuellem Stich
        int aktuellePunkte = 0;

        for (int i = 0; i < 4; i++) {
            aktuellePunkte += aktuellerStich[i].gebeWert().gebeWerteID();
        }

        return aktuellePunkte;
    }
}