package InformatikProjekt;

public class Spielkarte {
    private final Farbe farbe;
    private final Werte wert;

    public Spielkarte(Farbe neueFarbe, Werte neuerWert) {
        farbe = neueFarbe;
        wert = neuerWert;
    }

    public Farbe gebeFarbe() {
        return this.farbe;
    }

    public Werte gebeWert() {
        return this.wert;
    }

    // Nachfolgender Code von Robin
    public Spielkarte(int kartenID) {
        farbe = Farbe.values()[kartenID >> 16];
        wert = Werte.values()[kartenID & 0xFFFF];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj.getClass() != Spielkarte.class) return false;
        return obj.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode() {
        return (this.gebeFarbe().gebeFarbeID() << 16) | (this.gebeWert().gebeIndex());
    }

    public int gibKartenID() {
        return hashCode();
    }

    public boolean istTrumpf(SpielArt spielArt, Farbe soloSpielFarbe) {
        //todo return fuer die anderen Spielarten festlegen
        switch (spielArt) {
            case KEINSPIEL:
                break;
            case SAUSPIEL:
                if (wert == Werte.OBER || wert == Werte.UNTER || farbe == Farbe.HERZ) {
                    return true;
                }
                break;
            case WENZ:
                break;
            case SOLO:
                break;
        }
        return false;
    }
}

