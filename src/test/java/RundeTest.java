import InformatikProjekt.*;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RundeTest {

    @Test
    public void ermittleSiegerTesten() {
        Runde runde = new Runde();

        Spielkarte[] aktuellerStich = new Spielkarte[4];
        aktuellerStich[0] = new Spielkarte(Farbe.GRAS, Werte.SIEBENER);
        aktuellerStich[1] = new Spielkarte(Farbe.GRAS, Werte.KOENIG);
        aktuellerStich[2] = new Spielkarte(Farbe.SCHELLEN, Werte.SAU);
        aktuellerStich[3] = new Spielkarte(Farbe.GRAS, Werte.ZEHNER);

        int sieger = runde.ermittleSieger(aktuellerStich);

        assertEquals(3,sieger, "Fehler in ermittleSieger!");
    }
}
