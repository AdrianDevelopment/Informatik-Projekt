package Test;

import Controler.Spieler;
import Model.Farbe;
import Model.SpielArt;
import Model.Spielkarte;
import Model.Werte;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//Programmierer: Tom

public class SpielerTest {
    @Test
    /*Test für aufgetretenes Problem*/
    public void testKarteGelegt() {
        //Problem Alman 30.06. — 19:25 Uhr
        //Hand wird festgelegt
        ArrayList<Spielkarte> hand = new ArrayList<>();
        hand.add(new Spielkarte(Farbe.GRAS, Werte.KOENIG));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.ZEHNER));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.ZEHNER));

        //Verschiedene Karten werden aussprobiert
        Spielkarte vorgegebeneKarte = new Spielkarte(Farbe.EICHEL, Werte.KOENIG);
        Farbe farbe = Farbe.EICHEL;
        boolean handVersuch1 = karteGelegt(hand.get(0), 3, hand, farbe, vorgegebeneKarte);
        boolean handVersuch2 = karteGelegt(hand.get(1), 3, hand, farbe, vorgegebeneKarte);
        boolean handVersuch3 = karteGelegt(hand.get(2), 3, hand, farbe, vorgegebeneKarte);
        farbe = Farbe.SCHELLEN;
        boolean handVersuch4 = karteGelegt(hand.get(0), 3, hand, farbe, vorgegebeneKarte);
        boolean handVersuch5 = karteGelegt(hand.get(1), 3, hand, farbe, vorgegebeneKarte);
        boolean handVersuch6 = karteGelegt(hand.get(2), 3, hand, farbe, vorgegebeneKarte);
        farbe = Farbe.GRAS;
        boolean handVersuch7 = karteGelegt(hand.get(0), 3, hand, farbe, vorgegebeneKarte);
        boolean handVersuch8 = karteGelegt(hand.get(1), 3, hand, farbe, vorgegebeneKarte);
        boolean handVersuch9 = karteGelegt(hand.get(2), 3, hand, farbe, vorgegebeneKarte);


        //Überprüfung mit Antwort, ob Karte gelegt werden darf
        assertTrue(handVersuch1, "handVersuch1");
        assertTrue(handVersuch2, "handVersuch2");
        assertTrue(handVersuch3, "handVersuch3");
        assertTrue(handVersuch4, "handVersuch4");
        assertTrue(handVersuch5, "handVersuch5");
        assertTrue(handVersuch6, "handVersuch6");
        assertTrue(handVersuch7, "handVersuch7");
        assertTrue(handVersuch8, "handVersuch8");
        assertTrue(handVersuch9, "handVersuch9");


        //Problem PandaGG 30.06. — 20:42 Uhr
        //Hand wird festgelegt
        ArrayList<Spielkarte> hand2 = new ArrayList<>();
        hand2.add(new Spielkarte(Farbe.HERZ, Werte.ZEHNER));
        hand2.add(new Spielkarte(Farbe.EICHEL, Werte.SAU));
        hand2.add(new Spielkarte(Farbe.HERZ, Werte.SIEBENER));
        hand2.add(new Spielkarte(Farbe.HERZ, Werte.KOENIG));
        hand2.add(new Spielkarte(Farbe.HERZ, Werte.OBER));
        hand2.add(new Spielkarte(Farbe.EICHEL, Werte.SIEBENER));
        hand2.add(new Spielkarte(Farbe.GRAS, Werte.KOENIG));
        hand2.add(new Spielkarte(Farbe.GRAS, Werte.NEUNER));

        //Verschiedene Karten werden aussprobiert
        boolean hand2Versuch1 = karteGelegt(hand2.get(0), 3, hand2, Farbe.EICHEL, new Spielkarte(Farbe.SCHELLEN, Werte.ACHTER));
        boolean hand2Versuch2 = karteGelegt(hand2.get(1), 3, hand2, Farbe.EICHEL, new Spielkarte(Farbe.SCHELLEN, Werte.ACHTER));
        boolean hand2Versuch3 = karteGelegt(hand2.get(2), 3, hand2, Farbe.EICHEL, new Spielkarte(Farbe.SCHELLEN, Werte.ACHTER));
        boolean hand2Versuch4 = karteGelegt(hand2.get(3), 3, hand2, Farbe.EICHEL, new Spielkarte(Farbe.SCHELLEN, Werte.ACHTER));
        boolean hand2Versuch5 = karteGelegt(hand2.get(4), 3, hand2, Farbe.EICHEL, new Spielkarte(Farbe.SCHELLEN, Werte.ACHTER));
        boolean hand2Versuch6 = karteGelegt(hand2.get(5), 3, hand2, Farbe.EICHEL, new Spielkarte(Farbe.SCHELLEN, Werte.ACHTER));
        boolean hand2Versuch7 = karteGelegt(hand2.get(6), 3, hand2, Farbe.EICHEL, new Spielkarte(Farbe.SCHELLEN, Werte.ACHTER));
        boolean hand2Versuch8 = karteGelegt(hand2.get(7), 3, hand2, Farbe.EICHEL, new Spielkarte(Farbe.SCHELLEN, Werte.ACHTER));

        //Überprüfung mit Antwort, ob Karte gelegt werden darf
        assertTrue(hand2Versuch1, "hand2Versuch1");
        assertFalse(hand2Versuch2, "hand2Versuch2");
        assertTrue(hand2Versuch3, "hand2Versuch3");
        assertTrue(hand2Versuch4, "hand2Versuch4");
        assertTrue(hand2Versuch5, "hand2Versuch5");
        assertTrue(hand2Versuch6, "hand2Versuch6");
        assertTrue(hand2Versuch7, "hand2Versuch7");
        assertTrue(hand2Versuch8, "hand2Versuch8");

    }

    /*entspricht Teil der Methode karteGelegt() am 30.06. [mit Änderungen, wie model. entfernen]*/
    public boolean karteGelegt(Spielkarte spielkarte, int anzahlSpielerSchonGelegt, ArrayList<Spielkarte> handkarten, Farbe sauFarbe, Spielkarte vorgegebeneKarte) {
        boolean erlaubt = false;
        Spieler spieler = new Spieler();
        Spielkarte sau = new Spielkarte(sauFarbe, Werte.SAU);
        ArrayList<Spielkarte> erlaubteKarten;
        erlaubteKarten = spieler.erlaubteKartenAusspielenBeiSauspiel(handkarten, sau);

        //Überprüfung, welche Karte gelegt werden darf, wenn schon eine liegt
        if (anzahlSpielerSchonGelegt != 0) {
            erlaubteKarten = spieler.gibErlaubteKarten((ArrayList<Spielkarte>) handkarten.clone(), SpielArt.SAUSPIEL, sau, vorgegebeneKarte, sauFarbe, false);
            for (int i = 0; i < erlaubteKarten.size(); i++) {
                if (spielkarte.equals(erlaubteKarten.get(i))) {
                    erlaubt = true;
                    break;
                }
            }
        }
        return erlaubt;
    }
}
