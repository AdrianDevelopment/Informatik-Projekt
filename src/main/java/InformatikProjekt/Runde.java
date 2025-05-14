package InformatikProjekt;

public class Runde {
    private Mitspieler[] spieler;
    private int amZug;
    private int ausrufer;
    private Karte ausgerufen;
    private int ausruferMitspieler;
    private int[] punkte;
    private Karte[] letzterStich;
    private Karte[] aktuellerStich;
    private SpielArt höchstesSpiel;

    Runde() {
        spieler = new Mitspieler[4];
    }

    private int starteRunde() {
        return 0;
    }
}