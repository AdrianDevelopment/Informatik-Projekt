package InformatikProjekt;

import java.util.Random;

// Programmierer: Adrian

public class Tunier {
    // aktuell nur 1 Runde
    private Runde runde;
    private Mitspieler[] spieler;
    private int anzahlRunden;

    Tunier(int anzahlRunden) {
        spieler = new Mitspieler[4];
        this.anzahlRunden = anzahlRunden;
    }

    public void tunierStarten() {
        Random random = new Random();
        int randomNumber = random.nextInt(4);
        spieler[randomNumber] = new Spieler();
        for (int i = 0; i < 4; i++) {
            if (i != randomNumber) {
                spieler[i] = new Bot();
            }
        }

        for (int i = 0; i < anzahlRunden; i++) {
            runde = new Runde(spieler, i);
        }
    }
}
