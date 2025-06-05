package InformatikProjekt;

public class Spielkarte {
    private final Farbe farbe;
    private final Werte wert;

    public Spielkarte(Farbe neueFarbe, Werte neuerWert) {
        farbe = neueFarbe;
        wert = neuerWert;
    }

    // Nachfolgender Code von Robin
    public Spielkarte(int kartenID) {
        farbe = Farbe.values()[kartenID >> 16];
        wert = Werte.values()[kartenID & 0xFFFF];
    }

    public Farbe gebeFarbe() {
        return this.farbe;
    }

    public Werte gebeWert() {
        return this.wert;
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

        switch (spielArt) {
            case KEINSPIEL:
                break;
            case SAUSPIEL:
                return (wert == Werte.OBER || wert == Werte.UNTER || farbe == Farbe.HERZ);
            case WENZ:
                return (wert == Werte.UNTER);
            case SOLO:
                return (wert == Werte.OBER || wert == Werte.UNTER || farbe == soloSpielFarbe);
        }
        return false;
    }

    //Int zum Vergleichen welche Karte welche Sticht bei ver. Spielarten
    public int vergleichsWert(SpielArt spielArt, Spielkarte vorgebeneKarte) {
        //Todo Spielart und Vorgebene Karte 
        int kartenStaerke = 0;
        switch (spielArt) {
            case KEINSPIEL:
                break;
            case SAUSPIEL:
                //todo
                break;
            case WENZ:
                break;
            case SOLO:
                break;
        }
        Werte[] werteReihenfolge = new Werte[8];
        Farbe[] farbeReihenfolge = new Farbe[4];

        return 0;
    }
}

