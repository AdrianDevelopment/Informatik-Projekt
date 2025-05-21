package InformatikProjekt;

import java.util.Random;

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

    Runde() {
        spieler = new Mitspieler[4];
        Random random = new Random();
        int randomNumber = random.nextInt(4);
        spieler[randomNumber] = new Spieler();
        for (int i = 0; i < 4; i++) {
            if (i != randomNumber) {
                spieler[i] = new Bot();
            }
        }
    }

    private void starteRunde() {
        SpielArt aktuellHöchstesSpiel;
        SpielArt höchstesSpiel;
        höchstesSpiel = SpielArt.KEINSPIEL;
        // TODO: rotieren der Spieler
        for (int i = 0; i < 4; i++) {
            do {
                aktuellHöchstesSpiel = spieler[i].spielabsichtFragen(höchstesSpiel); // TODO: in GUI Methode implementieren, return 0, für weiter; 1 für Sau; 2 für Wenz; 3 für Solo
            } while (aktuellHöchstesSpiel.gebeSpielArtID() == 0 || aktuellHöchstesSpiel.gebeSpielArtID() > höchstesSpiel.gebeSpielArtID());
            if (aktuellHöchstesSpiel.gebeSpielArtID() > höchstesSpiel.gebeSpielArtID()) {
                höchstesSpiel = aktuellHöchstesSpiel;
                ausrufer = i;
            }
        }

        switch (höchstesSpiel.gebeSpielArtID()) {
            case 0:
                starteRunde(); // oder Ramsch; Methode muss möglicherweise extern erneut aufgerufen werden, ohne Rekursion
                break;
            case 1:
                spielSchleifeSau(8, 1);
                break;
            case 2:
//              spielSchleifeWenz();
                break;
            case 3:
//              spielSchleifeSolo();
                break;
        }
    }

    // TODO: spielSchleifeWenz(), spielSchleifeSolo()
    private void spielSchleifeSau(int anzahlKarten, int stich) {

        // einen Stich spielen
        for (int i = 0; i < 4; i++) {
            // Spieler "amZug" fragen welche Karte er legen möchte
            aktuelleSpielKarte = this.spieler[i].legeEineKarte();
        }

        int sieger = ermittleSieger(aktuellerStich);
        for (int i = 0; i < 4; i++) {
            spieler[i].siegerVerkünden(sieger);
        }
        letzterStich = aktuellerStich;
        gewonneneStiche[stich] = aktuellerStich;

        if (anzahlKarten > 0) {
            spielSchleifeSau(anzahlKarten, stich + 1);
        }
    }

    private int ermittleSieger(Spielkarte[] aktuellerStich) {
        // TODO: höchste Karte herausfinden
        return 0;
    }
}