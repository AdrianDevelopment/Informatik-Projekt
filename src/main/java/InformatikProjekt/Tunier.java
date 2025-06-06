package InformatikProjekt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Tunier {
    private final Speicherung speicherung;
    private final TunierModel tunierModel;
    private final ArrayList<Mitspieler> spieler;

    public Tunier(int anzahlRunden, Spieler echterSpieler) {
        this.spieler = new ArrayList<>(4);
        this.speicherung = Speicherung.speicherungErstellen();
        this.tunierModel = new TunierModel(anzahlRunden, echterSpieler);
    }

    public void tunierStarten() {
        int positionSpieler = initialisiereSpielerUndPosition();
        ArrayList<Spielkarte> spielKarten = mischeKarten();
        int vorhand = 0;

        for (int i = 0; i < tunierModel.gebeAnzahlRunden(); i++) {
            Runde runde = new Runde(spieler, tunierModel.gebeEchterSpieler(), spielKarten, positionSpieler, speicherung);
            int[] sieger = runde.starteRunde(vorhand);

            if (sieger != null) {
                tunierModel.erhoehePunkteTunierUmEins(sieger[0]);
                tunierModel.erhoehePunkteTunierUmEins(sieger[1]);
            }

            vorhand = (vorhand + 1) % 4; // Zyklische Änderung der Vorhand
        }

        wertungAusgeben(positionSpieler);
    }

    private int initialisiereSpielerUndPosition() {
        Random random = new Random();
        int positionSpieler = random.nextInt(4);

        for (int i = 0; i < 4; i++) {
            spieler.add(i == positionSpieler ? tunierModel.gebeEchterSpieler() : new Bot());
        }

        return positionSpieler;
    }

    private ArrayList<Spielkarte> mischeKarten() {
        ArrayList<Spielkarte> spielKarten = new ArrayList<>();
        for (Farbe farbe : Farbe.values()) {
            for (Werte wert : Werte.values()) {
                spielKarten.add(new Spielkarte(farbe, wert));
            }
        }
        Collections.shuffle(spielKarten);
        return spielKarten;
    }

    private void wertungAusgeben(int positionSpieler) {
        int hoechstePunkte = -1;
        int tunierSieger = -1;

        for (int i = 0; i < 4; i++) {
            int punkte = tunierModel.gebePunkteTunier(i);
            if (punkte > hoechstePunkte) {
                hoechstePunkte = punkte;
                tunierSieger = i;
            }
        }

        if (tunierSieger == positionSpieler) {
            speicherung.TurnierGewonnen();
            System.out.println("Herzlichen Glückwunsch! Sie haben das Turnier gewonnen!");
        } else {
            speicherung.TurnierVerloren();
            System.out.println("Das Turnier wurde verloren. Der Sieger ist Spieler " + tunierSieger + ".");
        }
    }
}