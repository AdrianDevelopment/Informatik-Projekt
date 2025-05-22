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
}


