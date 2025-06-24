package Test;

//Programmierer: Tim

import Controler.Bot;
import Controler.Mitspieler;
import Model.Spielkarte;
import Model.Farbe;
import Model.SpielArt;
import Model.Werte;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MitSpielerTest {
    /*
        Test, ob die Methode gibErlaubteKarten richtig funktioniert.
     */
    @Test
    public void testErlaubteKarten() {

        Bot bot = new Bot();
        //Hand wird festgelegt
        ArrayList<Spielkarte> hand = new ArrayList<>();
        hand.add(new Spielkarte(Farbe.HERZ, Werte.UNTER));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.UNTER));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.OBER));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.SAU));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.ZEHNER));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.KOENIG));
        hand.add(new Spielkarte(Farbe.EICHEL, Werte.SIEBENER));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.NEUNER));

        //Verschiedene Spielsituationen werden vorgeben
        ArrayList<Spielkarte> erlaubteKartenSauTrumpf = bot.gibErlaubteKarten(hand, SpielArt.SAUSPIEL, new Spielkarte(Farbe.GRAS, Werte.SAU), new Spielkarte(Farbe.EICHEL, Werte.OBER), Farbe.GRAS, false);
        ArrayList<Spielkarte> erlaubteKartenSauFarbe = bot.gibErlaubteKarten(hand, SpielArt.SAUSPIEL, new Spielkarte(Farbe.GRAS, Werte.SAU), new Spielkarte(Farbe.EICHEL, Werte.ZEHNER), Farbe.GRAS, false);

        ArrayList<Spielkarte> erlaubteKartenWenzTrumpf = bot.gibErlaubteKarten(hand, SpielArt.WENZ, new Spielkarte(Farbe.GRAS, Werte.SAU), new Spielkarte(Farbe.EICHEL, Werte.UNTER), Farbe.GRAS, false);
        ArrayList<Spielkarte> erlaubteKartenWenzFarbe = bot.gibErlaubteKarten(hand, SpielArt.WENZ, new Spielkarte(Farbe.GRAS, Werte.SAU), new Spielkarte(Farbe.HERZ, Werte.ZEHNER), Farbe.GRAS, false);

        ArrayList<Spielkarte> erlaubteKartenSoloGrasTrumpf = bot.gibErlaubteKarten(hand, SpielArt.SOLO, new Spielkarte(Farbe.GRAS, Werte.SAU), new Spielkarte(Farbe.EICHEL, Werte.UNTER), Farbe.GRAS, false);
        ArrayList<Spielkarte> erlaubteKartenSoloGrasFarbe = bot.gibErlaubteKarten(hand, SpielArt.SOLO, new Spielkarte(Farbe.GRAS, Werte.SAU), new Spielkarte(Farbe.HERZ, Werte.ZEHNER), Farbe.GRAS, false);

        //Überprüfung mithilfe der Anzahl der Karten die gelegt werden dürfen.
        assertEquals(5, erlaubteKartenSauTrumpf.size(), "Die Anzahl der Karten die gelegt werden dürfen ist für ein Sauspiel mit einem Trumpf(Eichel Ober) in Vorhand zu groß.");
        assertEquals(1, erlaubteKartenSauFarbe.size(), "Die Anzahl der Karten die gelegt werden dürfen ist für ein Sauspiel mit einer Farbe(Eichel) in Vorhand zu groß.");

        assertEquals(2, erlaubteKartenWenzTrumpf.size(), "Die Anzahl der Karten die gelegt werden dürfen ist für ein Wenz mit einem Trumpf(Eichel Unter) in Vorhand zu groß.");
        assertEquals(2, erlaubteKartenWenzFarbe.size(), "\"Die Anzahl der Karten die gelegt werden dürfen ist für ein Wenz mit einer Farbe(Herz) in Vorhand zu groß.");

        assertEquals(4, erlaubteKartenSoloGrasTrumpf.size(), "Die Anzahl der Karten die gelegt werden dürfen ist für ein Solo mit einem Trumpf(Eichel Unter) in Vorhand zu groß.");
        assertEquals(2, erlaubteKartenSoloGrasFarbe.size(), "Die Anzahl der Karten die gelegt werden dürfen ist für ein Solo mit einer Farbe(Herz) in Vorhand zu groß.");


    }

    /*
           Test, ob erkannt wird, wann davongelaufen werden kann.

        */
    @Test
    public void testErlaubteKartenDavonlaufen() {

        Mitspieler bot = new Bot();

        //Hand wird festgelegt
        ArrayList<Spielkarte> hand = new ArrayList<>();
        hand.add(new Spielkarte(Farbe.GRAS, Werte.SIEBENER));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.KOENIG));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.NEUNER));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.SAU));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.SAU));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.KOENIG));
        hand.add(new Spielkarte(Farbe.EICHEL, Werte.SIEBENER));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.NEUNER));

        //Methode die getestet werden soll wird aufgerufen
        ArrayList<Spielkarte> erlaubteKartenSauDarfDavonlaufen = bot.erlaubteKartenAusspielenBeiSauspiel(hand, new Spielkarte(Farbe.GRAS, Werte.SAU));
        assertEquals(8, erlaubteKartenSauDarfDavonlaufen.size(), "Darf Davonlaufen");

        //neue Hand wird festgelegt
        ArrayList<Spielkarte> hand2 = new ArrayList<>();
        hand2.add(new Spielkarte(Farbe.SCHELLEN, Werte.SIEBENER));
        hand2.add(new Spielkarte(Farbe.GRAS, Werte.KOENIG));
        hand2.add(new Spielkarte(Farbe.GRAS, Werte.NEUNER));
        hand2.add(new Spielkarte(Farbe.HERZ, Werte.SAU));
        hand2.add(new Spielkarte(Farbe.GRAS, Werte.SAU));
        hand2.add(new Spielkarte(Farbe.SCHELLEN, Werte.KOENIG));
        hand2.add(new Spielkarte(Farbe.EICHEL, Werte.SIEBENER));
        hand2.add(new Spielkarte(Farbe.HERZ, Werte.NEUNER));

        //Methode die getestet werden soll wird aufgerufen
        ArrayList<Spielkarte> erlaubteKartenSauDarfNichtDavonlaufen = bot.erlaubteKartenAusspielenBeiSauspiel(hand2, new Spielkarte(Farbe.GRAS, Werte.SAU));
        assertEquals(6, erlaubteKartenSauDarfNichtDavonlaufen.size(), "Darf nicht Davonlaufen");

        //neue Hand wird festgelegt
        ArrayList<Spielkarte> hand3 = new ArrayList<>();
        hand3.add(new Spielkarte(Farbe.SCHELLEN, Werte.SIEBENER));
        hand3.add(new Spielkarte(Farbe.GRAS, Werte.KOENIG));
        hand3.add(new Spielkarte(Farbe.GRAS, Werte.NEUNER));
        hand3.add(new Spielkarte(Farbe.HERZ, Werte.SAU));
        hand3.add(new Spielkarte(Farbe.GRAS, Werte.ZEHNER));
        hand3.add(new Spielkarte(Farbe.SCHELLEN, Werte.KOENIG));
        hand3.add(new Spielkarte(Farbe.EICHEL, Werte.SIEBENER));
        hand3.add(new Spielkarte(Farbe.HERZ, Werte.NEUNER));

        //Methode die getestet werden soll wird aufgerufen
        ArrayList<Spielkarte> darfAllesLegen = bot.erlaubteKartenAusspielenBeiSauspiel(hand3, new Spielkarte(Farbe.GRAS, Werte.SAU));
        assertEquals(8, darfAllesLegen.size(), "Darf alles Legen");

    }

    /*
         Test, der Überprüft, dass die Sau nicht unerlaubt ausgelegt wird.
         //todo andere Möglichkeit für das Testen finden. Da Bot eine Zufällige Karte legt und der Test passen könnte auch wenn die Methode nicht funktioniert.
      */
    @Test
    public void testErlaubteKartenSauLegen() {

        Bot bot = new Bot();

        //Hand wird festgelegt
        ArrayList<Spielkarte> hand = new ArrayList<>();
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.SIEBENER));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.KOENIG));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.OBER));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.SAU));
        hand.add(new Spielkarte(Farbe.EICHEL, Werte.SAU));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.KOENIG));
        hand.add(new Spielkarte(Farbe.EICHEL, Werte.SIEBENER));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.NEUNER));

        //Eine Runde wird simuliert
        Spielkarte gesuchteSau = new Spielkarte(Farbe.EICHEL, Werte.SAU);
        bot.rundeStarten(hand, 3);
        bot.spielArtEntschieden(1, Farbe.EICHEL, SpielArt.SAUSPIEL);

        //Eine Vorhand Karte wird festgelegt, welche nicht erlaubt das die Sau, vor dem letzten Stich gelegt werden darf.
        bot.karteWurdeGelegt(new Spielkarte(Farbe.GRAS, Werte.ACHTER), 1, 0);

        bot.waehleEineKarte();
        bot.waehleEineKarte();
        bot.waehleEineKarte();
        bot.waehleEineKarte();
        bot.waehleEineKarte();
        bot.waehleEineKarte();
        bot.waehleEineKarte();

        Spielkarte sau = bot.waehleEineKarte();
        assertEquals(sau, gesuchteSau, "Gesuchte Sau sollte als Letzte Karte im letzten Stich gelegt werden.");
    }

    /*
         Test, ob richtig bestimmt wird welche Farben ausgerufen werden dürfen.
      */
    @Test
    public void testsauZumAusrufen() {
        Bot bot = new Bot();

        //Hand wird festgelegt
        ArrayList<Spielkarte> hand = new ArrayList<>();
        hand.add(new Spielkarte(Farbe.HERZ, Werte.SAU));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.UNTER));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.OBER));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.SAU));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.ZEHNER));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.KOENIG));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.SIEBENER));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.NEUNER));

        //Nur die Schellen Sau darf ausgerufen werden.
        ArrayList<Farbe> richtig = new ArrayList<Farbe>();
        richtig.add(Farbe.SCHELLEN);
        assertEquals(richtig, bot.sauZumAusrufen(hand), "Eine Sau durfte nicht Ausgerufen werden");
        //Schellen Sau aus Hand entfernen
        hand.remove(5);
        assertEquals(new ArrayList<Farbe>(), bot.sauZumAusrufen(hand), "Keine Sau darf ausgerufen werden");


        //Hand wird festgelegt
        ArrayList<Spielkarte> hand2 = new ArrayList<>();
        hand2.add(new Spielkarte(Farbe.HERZ, Werte.SAU));
        hand2.add(new Spielkarte(Farbe.EICHEL, Werte.UNTER));
        hand2.add(new Spielkarte(Farbe.SCHELLEN, Werte.OBER));
        hand2.add(new Spielkarte(Farbe.HERZ, Werte.ZEHNER));
        hand2.add(new Spielkarte(Farbe.GRAS, Werte.SAU));
        hand2.add(new Spielkarte(Farbe.GRAS, Werte.KOENIG));
        hand2.add(new Spielkarte(Farbe.GRAS, Werte.SIEBENER));
        hand2.add(new Spielkarte(Farbe.GRAS, Werte.NEUNER));

        //Nur die Schellen Sau darf ausgerufen werden.
        ArrayList<Farbe> richtig2 = new ArrayList<Farbe>();
        assertEquals(richtig2, bot.sauZumAusrufen(hand), "Keine Sau durfte Ausgerufen werden");





    }
}