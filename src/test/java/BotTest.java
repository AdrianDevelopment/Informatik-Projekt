//Programmierer Tim

import InformatikProjekt.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BotTest {

    /*
        Testet, ob der Bot das richtige Spiel wählt.
     */
    @Test
    public void testSpielWaehlen() {


        Bot bot = new Bot();

        //festlegen der Hand
        ArrayList<Spielkarte> hand = new ArrayList<>();
        hand.add(new Spielkarte(Farbe.HERZ, Werte.UNTER));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.UNTER));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.OBER));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.OBER));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.ZEHNER));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.UNTER));
        hand.add(new Spielkarte(Farbe.EICHEL, Werte.SIEBENER));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.NEUNER));

        //Hand setzten
        bot.rundeStarten(hand, 0);

        //Überprüfung
        assertEquals(SpielArt.KEINSPIEL, bot.spielAbsichtWaehlen(SpielArt.SOLO), "Es sollte keine andere Spielart gewählt werden. D");
        assertEquals(SpielArt.SAUSPIEL, bot.spielAbsichtWaehlen(SpielArt.KEINSPIEL), "Der Bot sollte sich für kein Spiel entscheiden");
        assertEquals(Farbe.EICHEL, bot.farbeFuerSpielAbsichtGewaehlt(SpielArt.SAUSPIEL), "Der Bot sollte sich für ein Sauspiel entscheiden");


    }

    /*
           Testet, ob das legen von Karten planmäßig funktioniert.
    */
    @Test
    public void testKarteLegen() {

        Bot bot = new Bot();


        //Hand festlegen
        ArrayList<Spielkarte> hand = new ArrayList<>();
        hand.add(new Spielkarte(Farbe.HERZ, Werte.UNTER));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.UNTER));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.OBER));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.OBER));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.ZEHNER));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.KOENIG));
        hand.add(new Spielkarte(Farbe.EICHEL, Werte.SIEBENER));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.NEUNER));

        //Hand setzten
        bot.rundeStarten((ArrayList<Spielkarte>) hand.clone(), 0);

        // bot.spielArtEntschieden(1, new Spielkarte(Farbe.SCHELLEN, Werte.SAU), null, SpielArt.SAUSPIEL);
        bot.spielArtEntschieden(1, Farbe.SCHELLEN, SpielArt.SAUSPIEL);

        //Legen einer Spielkarte simulieren.
        bot.karteWurdeGelegt(new Spielkarte(Farbe.EICHEL, Werte.SAU), 1, wiederholung);

        assertEquals(new Spielkarte(Farbe.EICHEL, Werte.SIEBENER), bot.waehleEineKarte(), "Testet ob Eichel 7 gelegt wird, wenn Eichel die geforderte Farbe ist");

        //Stich beenden
        bot.stichGewonnen(1);

        assertEquals(7, bot.gibAnzahlKartenInHand(), "Bot hat die Karte nicht aus seiner Hand gelöscht.");

        //Simulieren von dem Legen mehrer Karten.
        bot.karteWurdeGelegt(new Spielkarte(Farbe.EICHEL, Werte.SAU), 1, wiederholung);
        bot.karteWurdeGelegt(new Spielkarte(Farbe.SCHELLEN, Werte.SIEBENER), 2, wiederholung);
        bot.karteWurdeGelegt(new Spielkarte(Farbe.HERZ, Werte.ZEHNER), 3, wiederholung);


        assertEquals(21, bot.gibWertFuerBisherGelegteKarten(), "Es wurde die falsche Punkteanzahl berechnet");
        bot.rundeGewonnen(null, null);


    }

    /*
             Testet, ob der Mitspieler erkannt wird.
      */
    @Test
    public void testErkenntMitspieler() {
        Bot bot = new Bot();
        bot.rundeStarten(null, 0);
        //bot der Sau ausgerufen hat findet Mitspieler der Sau gelegt hat.
        bot.spielArtEntschieden(0, Farbe.GRAS, SpielArt.SAUSPIEL);
        bot.karteWurdeGelegt(new Spielkarte(Farbe.GRAS, Werte.SAU), 2, wiederholung);
        assertEquals(2, bot.gibTeamSpieler(), "Der Mitspieler wurde nicht erkannt");
        //bot hat nicht Sau ausgerufen und findet Mitspieler der nicht sau gelegt hat
        bot.spielArtEntschieden(1, Farbe.GRAS, SpielArt.SAUSPIEL);
        bot.karteWurdeGelegt(new Spielkarte(Farbe.GRAS, Werte.SAU), 2, wiederholung);
        assertEquals(3, bot.gibTeamSpieler(), "Der Mitspieler wurde nicht erkannt");
    }
}
