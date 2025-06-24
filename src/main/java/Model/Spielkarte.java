package Model;

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
        // Wird von Java-Standardmethoden wie ArrayList.contains zum Vergleichen verwendet
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

    @Override
    public String toString(){
        // Wird verwendet, wenn mit dem "+"-Operator ein String mit einer Spielkarte verbunden wird
        String ergebnis = "";
        switch (farbe){
            case GRAS:
                ergebnis = "Gras ";
                break;
            case HERZ:
                ergebnis = "Herz ";
                break;
            case EICHEL:
                ergebnis = "Eichel ";
                break;
            case SCHELLEN:
                ergebnis = "Schellen ";
                break;
        }
        switch (wert){
            case SAU:
                ergebnis += "Sau";
                break;
            case OBER:
                ergebnis += "Ober";
                break;
            case UNTER:
                ergebnis += "Unter";
                break;
            case KOENIG:
                ergebnis += "König";
                break;
            case ACHTER:
                ergebnis += "8";
                break;
            case NEUNER:
                ergebnis += "9";
                break;
            case ZEHNER:
                ergebnis += "10";
                break;
            case SIEBENER:
                ergebnis += "7";
                break;
        }
        return  ergebnis;
    }

    /* Programmierer Tim
            Gibt je nach Spielart zurück, ob eine Karte ein Trumpf ist.
            Nutzbar in mehreren Klassen.
     */
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


}

