import InformatikProjekt.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BotTest {
    @Test
    public void testSpielWaehlen() {
        //todo test fürs davon laufen schreiben

        Bot bot = new Bot(null);

        //Hand
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
        assertEquals(SpielArt.KEINSPIEL, bot.spielAbsichtWaehlen(SpielArt.SOLO), "Testet ob keine Spielart gewählt wird wenn das höchste Spiel zu groß ist");
        assertEquals(SpielArt.SAUSPIEL, bot.spielAbsichtWaehlen(SpielArt.KEINSPIEL), "Testet ob Sauspiel bei erfüllten Anforderungen gewählt wird");


        assertEquals(Farbe.EICHEL, bot.farbeFuerSpielAbsichtGewaehlt(SpielArt.SAUSPIEL), "Testet Eichel als Farbe fuer das Sauspiel gewaelt wird");


    }

    @Test
    public void testKarteLegen() {
        //todo test fürs davon laufen schreiben

        Bot bot = new Bot(null);


        //Hand
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

        bot.karteWurdeGelegt(new Spielkarte(Farbe.EICHEL, Werte.SAU), 1);

        assertEquals(new Spielkarte(Farbe.EICHEL, Werte.SIEBENER), bot.waehleEineKarte(), "Testet ob Eichel 7 gelegt wird, wenn Eichel die geforderte Farbe ist");
        bot.stichGewonnen(1);
        assertEquals(7, bot.gibAnzahlKartenInHand(), "Bot hat die Karte nicht aus seiner Hand gelöscht.");
        Spielkarte karte = bot.waehleEineKarte();
        //Entfernt Schellen 7 aus Hand ArrayList
        hand.remove(6);

        boolean karteIstVorhanden = false;

        for (Spielkarte k : hand) {
            if (k == karte) {

                karteIstVorhanden = true;
            }
        }
        assertEquals(true, karteIstVorhanden, "Karte die Bot gelegt hast ist nicht auf seiner Hand");
        bot.stichGewonnen(0);
        bot.karteWurdeGelegt(new Spielkarte(Farbe.EICHEL, Werte.SAU), 1);
        bot.karteWurdeGelegt(new Spielkarte(Farbe.SCHELLEN, Werte.SIEBENER), 2);
        bot.karteWurdeGelegt(new Spielkarte(Farbe.HERZ, Werte.ZEHNER), 3);


        assertEquals(21, bot.gibWertFuerBisherGelegteKarten(), "Es wurde die falsche Punkteanazhl berechnet");
        bot.rundeGewonnen(null, null);


    }

    @Test
    public void testErkenntMitspieler() {
        Bot bot = new Bot(null);
        bot.rundeStarten(null, 0);
        //bot der Sau ausgerufen hat findet Mitspieler der Sau gelegt hat.
        bot.spielArtEntschieden(0, Farbe.GRAS, SpielArt.SAUSPIEL);
        bot.karteWurdeGelegt(new Spielkarte(Farbe.GRAS, Werte.SAU), 2);
        assertEquals(2, bot.gibTeamSpieler());
        //bot hat nicht Sau ausgerufen und findet Mitspieler der nicht sau gelegt hat
        bot.spielArtEntschieden(1, Farbe.GRAS, SpielArt.SAUSPIEL);
        bot.karteWurdeGelegt(new Spielkarte(Farbe.GRAS, Werte.SAU), 2);
        assertEquals(3, bot.gibTeamSpieler());
    }
}
