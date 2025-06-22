package Test;

// Programmierer: Adrian

import Controler.Turnier;
import Controler.Runde;
import Controler.Speicherung;
import Controler.Mitspieler;
import Controler.Spieler;
import Controler.Bot;
import Model.Farbe;
import Model.Werte;
import Model.Spielkarte;

import View.SpielGUI;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RundeTest {

    private final ArrayList<Mitspieler> spieler;
    private final ArrayList<Spielkarte> spielKarten;
    private final int positionSpieler;
    private final Speicherung speicherung;
    private final int vorhand;
    private final Turnier turnier;
    private final int wiederholungenRunde;
    private final Spieler echterSpieler;

    private RundeTest() {
        spieler = new ArrayList<>(4);
        positionSpieler = 0;
        spielKarten = new ArrayList<>(32);
        speicherung = Speicherung.speicherungErstellen();
        vorhand = 0;
        turnier = new Turnier(4);
        wiederholungenRunde = 8;
        echterSpieler = new Spieler();

        echterSpieler.spielGUIErstellen(new SpielGUI());

        spieler.add(echterSpieler);
        spieler.add(new Bot());
        spieler.add(new Bot());
        spieler.add(new Bot());

        for (Farbe farbe : Farbe.values()) {
            for (Werte wert : Werte.values()) {
                spielKarten.add(new Spielkarte(farbe, wert));
            }
        }
        Collections.shuffle(spielKarten);

    }

    @Test
    public void ermittleSiegerTesten() {
        Runde runde = new Runde(spieler, spielKarten, positionSpieler, speicherung, vorhand, turnier, wiederholungenRunde, echterSpieler);

        Spielkarte[] aktuellerStich = new Spielkarte[4];
        aktuellerStich[0] = new Spielkarte(Farbe.GRAS, Werte.SIEBENER);
        aktuellerStich[1] = new Spielkarte(Farbe.GRAS, Werte.KOENIG);
        aktuellerStich[2] = new Spielkarte(Farbe.SCHELLEN, Werte.SAU);
        aktuellerStich[3] = new Spielkarte(Farbe.GRAS, Werte.ZEHNER);

        int sieger = runde.ermittleSieger(aktuellerStich);
        int punkte = runde.ermittlePunkte(aktuellerStich);
        assertEquals(3, sieger, "Fehler in ermittleSieger!");
        assertEquals(25, punkte, "Fehler in hoechsteFarbeGewinntTest!");
    }

    @Test
    public void alleKartenGleicheFarbeTest() {
        Runde runde = new Runde(spieler, spielKarten, positionSpieler, speicherung, vorhand, turnier, wiederholungenRunde, echterSpieler);
        Spielkarte[] aktuellerStich = new Spielkarte[4];
        aktuellerStich[0] = new Spielkarte(Farbe.SCHELLEN, Werte.ACHTER);
        aktuellerStich[1] = new Spielkarte(Farbe.SCHELLEN, Werte.ZEHNER);
        aktuellerStich[2] = new Spielkarte(Farbe.SCHELLEN, Werte.SAU);
        aktuellerStich[3] = new Spielkarte(Farbe.SCHELLEN, Werte.KOENIG);

        int sieger = runde.ermittleSieger(aktuellerStich);
        int punkte = runde.ermittlePunkte(aktuellerStich);
        assertEquals(2, sieger, "Fehler in alleKartenGleicheFarbeTest!");
        assertEquals(25, punkte, "Fehler in hoechsteFarbeGewinntTest!");
    }

    @Test
    public void trumpfKarteSchlaegtAndereTest() {
        Runde runde = new Runde(spieler, spielKarten, positionSpieler, speicherung, vorhand, turnier, wiederholungenRunde, echterSpieler);
        Spielkarte[] aktuellerStich = new Spielkarte[4];
        aktuellerStich[0] = new Spielkarte(Farbe.SCHELLEN, Werte.NEUNER);
        aktuellerStich[1] = new Spielkarte(Farbe.GRAS, Werte.KOENIG);
        aktuellerStich[2] = new Spielkarte(Farbe.HERZ, Werte.ACHTER);
        aktuellerStich[3] = new Spielkarte(Farbe.EICHEL, Werte.SAU);

        int sieger = runde.ermittleSieger(aktuellerStich);
        int punkte = runde.ermittlePunkte(aktuellerStich);
        assertEquals(2, sieger, "Fehler in trumpfKarteSchlaegtAndereTest!");
        assertEquals(15, punkte, "Fehler in hoechsteFarbeGewinntTest!");
    }

    @Test
    public void hoechsteTrumpfKarteGewinntTest() {
        Runde runde = new Runde(spieler, spielKarten, positionSpieler, speicherung, vorhand, turnier, wiederholungenRunde, echterSpieler);
        Spielkarte[] aktuellerStich = new Spielkarte[4];
        aktuellerStich[0] = new Spielkarte(Farbe.HERZ, Werte.UNTER);
        aktuellerStich[1] = new Spielkarte(Farbe.HERZ, Werte.OBER);
        aktuellerStich[2] = new Spielkarte(Farbe.GRAS, Werte.ZEHNER);
        aktuellerStich[3] = new Spielkarte(Farbe.EICHEL, Werte.KOENIG);

        int sieger = runde.ermittleSieger(aktuellerStich);
        int punkte = runde.ermittlePunkte(aktuellerStich);
        assertEquals(1, sieger, "Fehler in hoechsteTrumpfKarteGewinntTest!");
        assertEquals(19, punkte, "Fehler in hoechsteFarbeGewinntTest!");
    }

    @Test
    public void farbMischungTest() {
        Runde runde = new Runde(spieler, spielKarten, positionSpieler, speicherung, vorhand, turnier, wiederholungenRunde, echterSpieler);
        Spielkarte[] aktuellerStich = new Spielkarte[4];
        aktuellerStich[0] = new Spielkarte(Farbe.EICHEL, Werte.SAU);
        aktuellerStich[1] = new Spielkarte(Farbe.GRAS, Werte.ZEHNER);
        aktuellerStich[2] = new Spielkarte(Farbe.EICHEL, Werte.KOENIG);
        aktuellerStich[3] = new Spielkarte(Farbe.SCHELLEN, Werte.ACHTER);

        int sieger = runde.ermittleSieger(aktuellerStich);
        int punkte = runde.ermittlePunkte(aktuellerStich);
        assertEquals(0, sieger, "Fehler in farbMischungTest!");
        assertEquals(25, punkte, "Fehler in hoechsteFarbeGewinntTest!");
    }

    @Test
    public void mehrereHerzTruempfeTest() {
        Runde runde = new Runde(spieler, spielKarten, positionSpieler, speicherung, vorhand, turnier, wiederholungenRunde, echterSpieler);
        Spielkarte[] aktuellerStich = new Spielkarte[4];
        aktuellerStich[0] = new Spielkarte(Farbe.HERZ, Werte.OBER);
        aktuellerStich[1] = new Spielkarte(Farbe.HERZ, Werte.UNTER);
        aktuellerStich[2] = new Spielkarte(Farbe.HERZ, Werte.SIEBENER);
        aktuellerStich[3] = new Spielkarte(Farbe.GRAS, Werte.SAU);

        int sieger = runde.ermittleSieger(aktuellerStich);
        int punkte = runde.ermittlePunkte(aktuellerStich);
        assertEquals(0, sieger, "Fehler in mehrereHerzTruempfeTest!");
        assertEquals(16, punkte, "Fehler in hoechsteFarbeGewinntTest!");
    }

    @Test
    public void alleTruempfeTest() {
        Runde runde = new Runde(spieler, spielKarten, positionSpieler, speicherung, vorhand, turnier, wiederholungenRunde, echterSpieler);
        Spielkarte[] aktuellerStich = new Spielkarte[4];
        aktuellerStich[0] = new Spielkarte(Farbe.HERZ, Werte.SIEBENER);
        aktuellerStich[1] = new Spielkarte(Farbe.HERZ, Werte.SAU);
        aktuellerStich[2] = new Spielkarte(Farbe.HERZ, Werte.ZEHNER);
        aktuellerStich[3] = new Spielkarte(Farbe.HERZ, Werte.KOENIG);

        int sieger = runde.ermittleSieger(aktuellerStich);
        int punkte = runde.ermittlePunkte(aktuellerStich);
        assertEquals(1, sieger, "Fehler in alleTruempfeTest!");
        assertEquals(25, punkte, "Fehler in hoechsteFarbeGewinntTest!");
    }

    @Test
    public void keinTrumpfTest() {
        Runde runde = new Runde(spieler, spielKarten, positionSpieler, speicherung, vorhand, turnier, wiederholungenRunde, echterSpieler);
        Spielkarte[] aktuellerStich = new Spielkarte[4];
        aktuellerStich[0] = new Spielkarte(Farbe.GRAS, Werte.ZEHNER);
        aktuellerStich[1] = new Spielkarte(Farbe.SCHELLEN, Werte.SAU);
        aktuellerStich[2] = new Spielkarte(Farbe.EICHEL, Werte.ZEHNER);
        aktuellerStich[3] = new Spielkarte(Farbe.GRAS, Werte.SAU);

        int sieger = runde.ermittleSieger(aktuellerStich);
        int punkte = runde.ermittlePunkte(aktuellerStich);
        assertEquals(3, sieger, "Fehler in keinTrumpfTest!");
        assertEquals(42, punkte, "Fehler in hoechsteFarbeGewinntTest!");
    }

    @Test
    public void ersteFarbeGewinntTest() {
        Runde runde = new Runde(spieler, spielKarten, positionSpieler, speicherung, vorhand, turnier, wiederholungenRunde, echterSpieler);

        Spielkarte[] aktuellerStich = new Spielkarte[4];
        aktuellerStich[0] = new Spielkarte(Farbe.EICHEL, Werte.ZEHNER);
        aktuellerStich[1] = new Spielkarte(Farbe.SCHELLEN, Werte.SAU);
        aktuellerStich[2] = new Spielkarte(Farbe.EICHEL, Werte.SIEBENER);
        aktuellerStich[3] = new Spielkarte(Farbe.GRAS, Werte.KOENIG);

        int sieger = runde.ermittleSieger(aktuellerStich);
        int punkte = runde.ermittlePunkte(aktuellerStich);
        assertEquals(0, sieger, "Fehler in ersteFarbeGewinntTest!");
        assertEquals(25, punkte, "Fehler in hoechsteFarbeGewinntTest!");
    }

    @Test
    public void hoechteFarbeGewinntTest() {
        Runde runde = new Runde(spieler, spielKarten, positionSpieler, speicherung, vorhand, turnier, wiederholungenRunde, echterSpieler);

        Spielkarte[] aktuellerStich = new Spielkarte[4];
        aktuellerStich[0] = new Spielkarte(Farbe.SCHELLEN, Werte.ACHTER);
        aktuellerStich[1] = new Spielkarte(Farbe.GRAS, Werte.SIEBENER);
        aktuellerStich[2] = new Spielkarte(Farbe.EICHEL, Werte.KOENIG);
        aktuellerStich[3] = new Spielkarte(Farbe.SCHELLEN, Werte.KOENIG);

        int sieger = runde.ermittleSieger(aktuellerStich);
        int punkte = runde.ermittlePunkte(aktuellerStich);
        assertEquals(3, sieger, "Fehler in hoechsteFarbeGewinntTest!");
        assertEquals(8, punkte, "Fehler in hoechsteFarbeGewinntTest!");
    }
}