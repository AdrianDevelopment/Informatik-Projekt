import InformatikProjekt.Mitspieler;
import InformatikProjekt.Spieler;
import InformatikProjekt.Bot;

import java.util.Random;
import org.junit.jupiter.api.Test;

public class RundeTest {

    private Mitspieler[] spieler;

    @Test
    public void testRunde() {
        spieler = new Mitspieler[4];
        Random random = new Random();
        int randomNumber = random.nextInt(4);
        spieler[randomNumber] = new Spieler();
        for (int i = 0; i < 4; i++) {
            if (i != randomNumber) {
                spieler[i] = new Bot();
            }
        }
    }
}
