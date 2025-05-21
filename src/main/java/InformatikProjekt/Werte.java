package InformatikProjekt;

public enum Werte {
    SAU(11),
    ZEHNER(10),
    KOENIG(4),
    OBER(3),
    UNTER(2),
    NEUNER(0),
    ACHTER(0),
    SIEBENER(0);

    private final int wert;

    Werte(int i) {
        this.wert = i;
    }

    public int gebeWerteID() {
        return wert;
    }
}
