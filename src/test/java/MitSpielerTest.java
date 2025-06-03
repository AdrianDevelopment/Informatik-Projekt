//Programmierer: Tim

import InformatikProjekt.*;
import org.junit.jupiter.api.Test;

import java.io.Console;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MitSpielerTest {

    @Test
    public void testErlaubteKarten() {
        //todo test fürs davon laufen schreiben

        Mitspieler bot = new Bot();

        //Hand
        ArrayList<Spielkarte> hand = new ArrayList<>();
        hand.add(new Spielkarte(Farbe.HERZ, Werte.UNTER));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.UNTER));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.OBER));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.SAU));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.ZEHNER));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.KOENIG));
        hand.add(new Spielkarte(Farbe.EICHEL, Werte.SIEBENER));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.NEUNER));

        //Verschiedene Spielsituationen
        ArrayList<Spielkarte> erlaubteKartenSauTrumpf = bot.gibErlaubteKarten(hand, SpielArt.SAUSPIEL, new Spielkarte(Farbe.GRAS, Werte.SAU), new Spielkarte(Farbe.EICHEL, Werte.OBER), Farbe.GRAS, false);
        ArrayList<Spielkarte> erlaubteKartenSauFarbe = bot.gibErlaubteKarten(hand, SpielArt.SAUSPIEL, new Spielkarte(Farbe.GRAS, Werte.SAU), new Spielkarte(Farbe.EICHEL, Werte.ZEHNER), Farbe.GRAS, false);

        ArrayList<Spielkarte> erlaubteKartenWenzTrumpf = bot.gibErlaubteKarten(hand, SpielArt.WENZ, new Spielkarte(Farbe.GRAS, Werte.SAU), new Spielkarte(Farbe.EICHEL, Werte.UNTER), Farbe.GRAS, false);
        ArrayList<Spielkarte> erlaubteKartenWenzFarbe = bot.gibErlaubteKarten(hand, SpielArt.WENZ, new Spielkarte(Farbe.GRAS, Werte.SAU), new Spielkarte(Farbe.HERZ, Werte.ZEHNER), Farbe.GRAS, false);

        ArrayList<Spielkarte> erlaubteKartenSoloGrasTrumpf = bot.gibErlaubteKarten(hand, SpielArt.SOLO, new Spielkarte(Farbe.GRAS, Werte.SAU), new Spielkarte(Farbe.EICHEL, Werte.UNTER), Farbe.GRAS, false);
        ArrayList<Spielkarte> erlaubteKartenSoloGrasFarbe = bot.gibErlaubteKarten(hand, SpielArt.SOLO, new Spielkarte(Farbe.GRAS, Werte.SAU), new Spielkarte(Farbe.HERZ, Werte.ZEHNER), Farbe.GRAS, false);

        //Überprüfung
        assertEquals(5, erlaubteKartenSauTrumpf.size(), "Testet erlaubteKartenSauTrumpf");
        assertEquals(1, erlaubteKartenSauFarbe.size(), "Testet erlaubteKartenSauFarbe");

        assertEquals(2, erlaubteKartenWenzTrumpf.size(), "Testet erlaubteKartenWenzTrumpf");
        assertEquals(2, erlaubteKartenWenzFarbe.size(), "Testet erlaubteKartenWenzFarbe");

        assertEquals(4, erlaubteKartenSoloGrasTrumpf.size(), "Testet erlaubteKartenSoloGrasTrumpf");
        assertEquals(3, erlaubteKartenSoloGrasFarbe.size(), "Testet erlaubteKartenSoloGrasFarbe");


    }

    @Test
    public void testErlaubteKartenWegLaufen() {

        Mitspieler bot = new Bot();

        //Hand
        ArrayList<Spielkarte> hand = new ArrayList<>();
        hand.add(new Spielkarte(Farbe.GRAS, Werte.SIEBENER));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.KOENIG));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.NEUNER));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.SAU));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.SAU));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.KOENIG));
        hand.add(new Spielkarte(Farbe.EICHEL, Werte.SIEBENER));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.NEUNER));

        //Verschiedene Spielsituationen
        ArrayList<Spielkarte> erlaubteKartenSauDavonlaufen = bot.gibErlaubteKarten(hand, SpielArt.SAUSPIEL, new Spielkarte(Farbe.GRAS, Werte.SAU), new Spielkarte(Farbe.GRAS, Werte.ACHTER), Farbe.GRAS, false);

        assertEquals(4, erlaubteKartenSauDavonlaufen.size(), "Test Davonlaufen");

        //Weiteren Test schreiben

    }

    @Test
    public void testErlaubteKartenSauLegen() {

        Mitspieler bot = new Bot();

        //Hand
        ArrayList<Spielkarte> hand = new ArrayList<>();
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.SIEBENER));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.KOENIG));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.OBER));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.SAU));
        hand.add(new Spielkarte(Farbe.EICHEL, Werte.SAU));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.KOENIG));
        hand.add(new Spielkarte(Farbe.EICHEL, Werte.SIEBENER));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.NEUNER));

        //Verschiedene Spielsituationen
        Spielkarte gesuchteSau = new Spielkarte(Farbe.EICHEL, Werte.SAU);
        bot.rundeStarten(hand, 3);

        bot.spielArtEntschieden(1, Farbe.EICHEL, SpielArt.SAUSPIEL);
        bot.karteWurdeGelegt(new Spielkarte(Farbe.GRAS, Werte.ACHTER), 1);

        bot.legeEineKarte();
        bot.legeEineKarte();
        bot.legeEineKarte();
        bot.legeEineKarte();
        bot.legeEineKarte();
        bot.legeEineKarte();
        bot.legeEineKarte();

        Spielkarte sau = bot.legeEineKarte();

        assertEquals(sau, gesuchteSau, "Gesuchte Sau sollte als Letzte Karte im letzten Stich gelegt werden.");

        //Weiteren Test schreiben

    }

    @Test
    public void testsauZumAusrufen() {
        Mitspieler bot = new Bot();

        //Hand
        ArrayList<Spielkarte> hand = new ArrayList<>();
        hand.add(new Spielkarte(Farbe.HERZ, Werte.UNTER));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.UNTER));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.OBER));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.SAU));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.ZEHNER));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.KOENIG));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.SIEBENER));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.NEUNER));

        ArrayList<Farbe> erlaubteZumAusrufen = bot.sauZumAusrufen(hand);
        ArrayList<Farbe> richtig = new ArrayList<Farbe>();
        richtig.add(Farbe.SCHELLEN);


        assertEquals(richtig, erlaubteZumAusrufen, "Eine Sau durfte nicht Ausgerufen werden");
    }
}