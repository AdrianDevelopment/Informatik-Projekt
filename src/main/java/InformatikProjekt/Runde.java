package InformatikProjekt;

import java.util.random.RandomGenerator;

public class Runde {
    private Mitspieler[] spieler;
    private int amZug;
    private int ausrufer;
    private SpielKarte ausgerufen;
    private int ausruferMitspieler;
    private int[] punkte;
    private SpielKarte[] letzterStich;
    private SpielKarte[] aktuellerStich;
    private SpielArt höchstesSpiel;

    Runde() {
        spieler = new Mitspieler[4];
    }

    private int starteRunde() {

        // am Ende

        return 0;
    }

    private void gameloop() {

    }
}