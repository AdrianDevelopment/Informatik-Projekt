package Model;

public class BotMitspielerDatenModel {
    /*
        Speicherung ob der Spieler noch Karten mit einem bestimmten Wert oder einer bestimmten Farbe hat.
        Benötigt um Spielentscheidungen zu treffen.
     */
    private boolean hatOber;
    private boolean hatUnter;

    private boolean hatHerz;
    private boolean hatSchellen;
    private boolean hatEichel;
    private boolean hatGras;

    BotMitspielerDatenModel() {
        hatOber = true;
        hatUnter = true;
        hatHerz = true;
        hatSchellen = true;
        hatEichel = true;
        hatGras = true;


    }

    void setzteHatOber(boolean b) {
        hatOber = b;
    }

    void setzteHatUnter(boolean b) {
        hatUnter = b;
    }

    void setzteHatHerz(boolean b) {
        hatHerz = b;
    }

    void setzteHatSchellen(boolean b) {
        hatSchellen = b;
    }

    void setzteHatEichel(boolean b) {
        hatEichel = b;
    }

    void setzteHatGras(boolean b) {
        hatGras = b;
    }


    boolean gebeHatOber() {
        return hatOber;
    }

    boolean gebeHatUnter() {
        return hatUnter;
    }

    boolean gebetHerz() {
        return hatHerz;
    }

    boolean gebeHatSchellen() {
        return hatSchellen;
    }

    boolean gebeHatEichel() {
        return hatEichel;
    }

    boolean gebeHatGras() {
        return hatGras;
    }
}
