package InformatikProjekt;

public class Spielkarte {
    private Farbe farbe;
    private Werte wert;

    Spielkarte(Farbe neueFarbe, Werte neuerWert ) {
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


