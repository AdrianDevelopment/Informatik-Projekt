package Model;

public class BotMitspielerDatenModel {
    /*
        Speicherung, ob der Spieler noch Karten mit einem bestimmten Wert oder einer bestimmten Farbe hat.
        Benötigt, um Spielentscheidungen zu treffen.
     */
    private boolean hatOber;
    private boolean hatUnter;

    private boolean hatHerz;
    private boolean hatSchellen;
    private boolean hatEichel;
    private boolean hatGras;

    BotMitspielerDatenModel() {
        //annahme am Anfang der Runde, das alle Mitspieler diese Karten haben könnten.
        hatOber = true;
        hatUnter = true;
        hatHerz = true;
        hatSchellen = true;
        hatEichel = true;
        hatGras = true;
    }

    void setzeHatOber(boolean b) {
        hatOber = b;
    }

    void setzeHatUnter(boolean b) {
        hatUnter = b;
    }

    void setzeHatHerz(boolean b) {
        hatHerz = b;
    }

    void setzeHatSchellen(boolean b) {
        hatSchellen = b;
    }

    void setzeHatEichel(boolean b) {
        hatEichel = b;
    }

    void setzeHatGras(boolean b) {
        hatGras = b;
    }


    public boolean gebeHatOber() {
        return hatOber;
    }

    public boolean gebeHatUnter() {
        return hatUnter;
    }

    public boolean gebetHerz() {
        return hatHerz;
    }

    public boolean gebeHatSchellen() {
        return hatSchellen;
    }

    public boolean gebeHatEichel() {
        return hatEichel;
    }

    public boolean gebeHatGras() {
        return hatGras;
    }
    public boolean gebeHatTrumpf() {
        return hatGras || hatOber || hatUnter;
    }
}
