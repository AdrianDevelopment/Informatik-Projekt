package Model;

public enum SpielArt {
    KEINSPIEL(0),
    SAUSPIEL(1),
    WENZ(2),
    SOLO(3);
//    GRASSOLO(3),
//    EICHELSOLO(3),
//    SCHELLENSOLO(3),

    private final int wert;

    SpielArt(int wert) {
        this.wert = wert;
    }

    public int gebeSpielArtID() {
        return wert;
    }
}
