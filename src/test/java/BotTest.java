import InformatikProjekt.*;
import org.junit.jupiter.api.Test;
import java.io.Console;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class BotTest {
    @Test
    public void testSpielWaehlen() {
        //todo test fürs davon laufen schreiben

        Mitspieler bot = new Bot();

        //Hand
        ArrayList<Spielkarte>  hand = new ArrayList<>();
        hand.add(new Spielkarte(Farbe.HERZ, Werte.UNTER));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.UNTER));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.OBER));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.OBER));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.ZEHNER));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.KOENIG));
        hand.add(new Spielkarte(Farbe.EICHEL, Werte.SIEBENER));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.NEUNER));

        //Hand setzten
        bot.rundeStarten(hand, 0);

        //Überprüfung
        assertEquals(SpielArt.SAUSPIEL, bot.spielabsichtFragen(SpielArt.KEINSPIEL), "Testet ob Sauspiel bei erfüllten Anforderungen gewählt wird");
        assertEquals(SpielArt.KEINSPIEL, bot.spielabsichtFragen(SpielArt.SOLO), "Testet ob keine Spielart gewählt wird wenn das höchste Spiel zu groß ist");


    }
    @Test
    public void testKarteLegen() {
        //todo test fürs davon laufen schreiben

        Bot bot = new Bot();


        //Hand
        ArrayList<Spielkarte>  hand = new ArrayList<>();
        hand.add(new Spielkarte(Farbe.HERZ, Werte.UNTER));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.UNTER));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.OBER));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.OBER));
        hand.add(new Spielkarte(Farbe.GRAS, Werte.ZEHNER));
        hand.add(new Spielkarte(Farbe.SCHELLEN, Werte.KOENIG));
        hand.add(new Spielkarte(Farbe.EICHEL, Werte.SIEBENER));
        hand.add(new Spielkarte(Farbe.HERZ, Werte.NEUNER));

        //Hand setzten
        bot.rundeStarten(hand, 0);

        // bot.spielArtEntschieden(1, new Spielkarte(Farbe.SCHELLEN, Werte.SAU), null, SpielArt.SAUSPIEL);
        bot.spielArtEntschieden(1, Farbe.SCHELLEN, SpielArt.SAUSPIEL);

        bot.karteWurdeGelegt(new Spielkarte(Farbe.EICHEL, Werte.SAU), 1);

        assertEquals(new Spielkarte(Farbe.EICHEL, Werte.SIEBENER), bot.legeEineKarte(), "Testet ob Eichel 7 gelegt wird, wenn Eichel die geforderte Farbe ist");
        bot.stichGewonnen(1);
        assertEquals(7, bot.gibAnzahlKartenInHand(), "Bot hat die Karte nicht aus seiner Hand gelöscht.");
        Spielkarte karte = bot.legeEineKarte();
        //Entfernt Schellen 7 aus Hand ArrayList
        hand.remove(5);

        boolean karteIstVorhanden = false;
        for (Spielkarte k : hand){
            if(k==karte){
                karteIstVorhanden = true;
            }
        }
        assertEquals(true, karteIstVorhanden, "Karte ist nicht auf der Hand");





    }

}
