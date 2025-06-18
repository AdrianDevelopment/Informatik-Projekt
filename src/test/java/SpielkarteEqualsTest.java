package InformatikProjekt;
import Model.Spielkarte;
import Model.Farbe;
import Model.Werte;

import java.util.ArrayList;

public class SpielkarteEqualsTest
{
    public static boolean testFalsch(){
        ArrayList<Spielkarte>  hand = new ArrayList<>();
        hand.add(new Spielkarte(Farbe.HERZ, Werte.UNTER));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.UNTER));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.OBER));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.SAU));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.ZEHNER));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.KOENIG));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.SIEBENER));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.NEUNER));
        return hand.contains(new Spielkarte(Farbe.EICHEL, Werte.NEUNER));
    }
    public static boolean testWahr(){
        ArrayList<Spielkarte>  hand = new ArrayList<>();
        hand.add(new Spielkarte(Farbe.HERZ, Werte.UNTER));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.UNTER));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.OBER));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.SAU));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.ZEHNER));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.KOENIG));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.SIEBENER));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.NEUNER));
        return hand.contains(new Spielkarte(Farbe.HERZ, Werte.NEUNER));
    }
}
