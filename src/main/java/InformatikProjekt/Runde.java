package InformatikProjekt;

import javax.lang.model.util.SimpleTypeVisitor6;

public class Runde {
    private Mitspieler[] spieler;
    private int vorhand; // spielt die erste Karte
    private int ausrufer;
    private SpielKarte ausgerufeneSau;
    private int ausruferMitspieler;
    private int[] punkte;
    private SpielKarte[] letzterStich;
    private SpielKarte[] aktuellerStich;
    private SpielKarte[][] gewonneneStiche;
    private SpielKarte aktuelleSpielKarte;
    private int höchstesSpiel;
    private int aktuellHöchstesSpiel;

    Runde() {
        spieler = new Mitspieler[4];
        höchstesSpiel = 0;
    }

    private void starteRunde() {
        // TODO: rotieren der Spieler
        for (int i = 0; i < 4; i++) {
            do {
                aktuellHöchstesSpiel = GUI.spielAbfrage(spieler[i]); // TODO: in GUI Methode implementieren, return 0, für weiter; 1 für Sau; 2 für Wenz; 3 für Solo
            } while (aktuellHöchstesSpiel == 0 || aktuellHöchstesSpiel > höchstesSpiel);
            if (aktuellHöchstesSpiel > höchstesSpiel) {
                höchstesSpiel = aktuellHöchstesSpiel;
                ausrufer = i;
            }
        }

        switch (höchstesSpiel) {
            case 0:
                starteRunde(); // oder Ramsch; Methode muss möglicherweise extern erneut aufgerufen werden, ohne Rekursion
            case 1:
                spielSchleifeSau(8, 1);
            case 2:
//                spielSchleifeWenz();
            case 3:
//                spielSchleifeSolo();
        }
    }

    // TODO: spielSchleifeWenz(), spielSchleifeSolo()
    private void spielSchleifeSau(int anzahlKarten, int stich) {

        // einen Stich spielen
        for (int i = 0; i < 4; i++) {
            // Spieler "amZug" fragen welche Karte er legen möchte
            do {
                aktuelleSpielKarte = spieler[i].duBistDran();
            } while (spielKartePrüfen(aktuelleSpielKarte) == false); // TODO: Methode spielKartePrüfen()
            aktuellerStich[i] = aktuelleSpielKarte;
        }

        Mitspieler sieger = ermittleSieger(aktuellerStich); // TODO: Methode ermittleSieger()
        GUI.siegerVerkünden(sieger); // TODO: GUI implementieren
        letzterStich = aktuellerStich;
        gewonneneStiche[stich] = aktuellerStich;

        if (anzahlKarten > 0) {
            spielSchleifeSau(anzahlKarten, stich + 1);
        }
    }
}