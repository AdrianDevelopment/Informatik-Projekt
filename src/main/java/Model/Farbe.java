package Model;

public enum Farbe {
    SCHELLEN(0),
    HERZ(1),
    GRAS(2),
    EICHEL(3);

    private final int farbe;
    Farbe(int i){
        farbe = i;
    }
    public int gebeFarbeID(){
        return farbe;
    }
}
