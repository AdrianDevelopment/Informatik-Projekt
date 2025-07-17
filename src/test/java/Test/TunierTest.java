package Test;

// Programmierer: Adrian

import Controler.Turnier;
import Model.Spielkarte;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TunierTest {
    private final Turnier turnier;

    public TunierTest() {
        turnier = new Turnier(4);
    }

    @Test
    public void turnierTest() {
        ArrayList<Spielkarte> spielKarten = turnier.spielKartenVorbereiten();
        boolean mehrfachVorhande = false;

        HashSet<Spielkarte> kartenSet = new HashSet<>();
        for (Spielkarte karte : spielKarten) {
            if (!kartenSet.add(karte)) { // Fügt die Karte hinzu und prüft, ob sie bereits existiert
                mehrfachVorhande = true;
            }
        }

        assertEquals(32, spielKarten.size(), "Fehler in spielkartenVorbereiten()!");
        assertFalse(mehrfachVorhande, "Fehler in spielkartenVorbereiten()!");
    }
}
