package InformatikProjekt;

public class Spielkarte {
    private Farbe farbe;
    private Werte wert;

    public Spielkarte(Farbe neueFarbe, Werte neuerWert ) {
        farbe = neueFarbe;
        wert = neuerWert;
    }

    public Farbe gebeFarbe(){
        return this.farbe;
    }

    public Werte gebeWert(){
        return this.wert;
    }

    // Nachfolgender Code von Robin
    public Spielkarte(int kartenID){
        farbe = Farbe.values()[kartenID >> 16];
        wert = Werte.values()[kartenID & 0xFFFF];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)return false;
        if (obj.getClass() != Spielkarte.class) return  false;
        Spielkarte karte = (Spielkarte) obj;
        System.out.println(((karte.gebeFarbe().gebeFarbeID()) == (this.gebeFarbe().gebeFarbeID())) &&
                ((karte.gebeWert().gebeWerteID()) == (this.gebeWert().gebeWerteID())));
        return (((karte.gebeFarbe()) == (this.gebeFarbe())) &&
                ((karte.gebeWert()) == (this.gebeWert())));
    }

    @Override
    public int hashCode(){
        return (this.gebeFarbe().gebeFarbeID() << 16) | (this.gebeWert().gebeWerteID());
    }

    public int gibKartenID(){
        return hashCode();
    }
}
