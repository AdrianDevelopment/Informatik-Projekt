package InformatikProjekt;

public enum SpielArt {
    KEINSPIEL(0),
    SAUSPIEL(1),
    WENS(2),
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
