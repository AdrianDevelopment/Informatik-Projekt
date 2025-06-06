package InformatikProjekt;

import java.util.ArrayList;

public class Runde {
    private final ArrayList<Mitspieler> spieler;
    private final Speicherung speicherung;
    private final RundeModel rundeModel;

    public Runde(ArrayList<Mitspieler> spieler, Spieler echterSpieler, ArrayList<Spielkarte> spielKarten, int positionSpieler, Speicherung speicherung) {
        this.spieler = spieler;
        this.speicherung = speicherung;
        this.rundeModel = new RundeModel(positionSpieler, echterSpieler);

        CLI cli = new CLI();
        echterSpieler.setzeCLI(cli);

        for (int i = 0; i < 4; i++) {
            ArrayList<Spielkarte> spielKartenProSpieler = new ArrayList<>(spielKarten.subList(i * 8, (i + 1) * 8));
            spieler.get(i).rundeStarten(spielKartenProSpieler, positionSpieler);
        }
    }

    public int[] starteRunde(int vorhand) {
        SpielArt hoechstesSpiel = ermittleHoechstesSpiel();
        Mitspieler ausrufer = spieler.get(rundeModel.gebeAusrufer());

        if (hoechstesSpiel == SpielArt.KEINSPIEL) {
            keinSpielAlle();
            return rundeModel.gebePunkteArray();
        }

        informiereSpieler(hoechstesSpiel, ausrufer);

        switch (hoechstesSpiel) {
            case SAUSPIEL:
                return starteSauspiel(vorhand);
            case WENZ:
                // TODO: Wenz-Logik
                return null;
            case SOLO:
                // TODO: Solo-Logik
                return null;
            default:
                System.err.println("Fehler in starteRunde(): Unbekannte Spielart.");
                return null;
        }
    }

    private SpielArt ermittleHoechstesSpiel() {
        SpielArt hoechstesSpiel = SpielArt.KEINSPIEL;

        for (int i = 0; i < 4; i++) {
            SpielArt aktuell = spieler.get(i).spielabsichtFragen(hoechstesSpiel);
            if (aktuell.compareTo(hoechstesSpiel) > 0) {
                hoechstesSpiel = aktuell;
                rundeModel.setzeAusrufer(i);
            }
        }

        return hoechstesSpiel;
    }

    private void keinSpielAlle() {
        for (Mitspieler aktuellerSpieler : spieler) {
            aktuellerSpieler.keinSpiel();
        }
    }

    private void informiereSpieler(SpielArt hoechstesSpiel, Mitspieler ausrufer) {
        for (Mitspieler aktuellerSpieler : spieler) {
            aktuellerSpieler.spielerHatSpielabsichtGesagt(hoechstesSpiel, rundeModel.gebeAusrufer());
        }

        Farbe farbe = ausrufer.farbeFuerSpielAbsicht(hoechstesSpiel);

        for (Mitspieler aktuellerSpieler : spieler) {
            aktuellerSpieler.spielArtEntschieden(rundeModel.gebeAusrufer(), farbe, hoechstesSpiel);
        }
    }

    private int[] starteSauspiel(int vorhand) {
        spielSchleifeSau(8, vorhand);
        int[] sieger = rundenSiegerErmitteln();

        for (Mitspieler aktuellerSpieler : spieler) {
            aktuellerSpieler.rundeGewonnen(sieger, rundeModel.gebePunkteArray());
        }

        verarbeiteSpeicherung(sieger);
        return sieger;
    }

    private void verarbeiteSpeicherung(int[] sieger) {
        int spielerPunkte = rundeModel.gebePunkte(sieger[0]) + rundeModel.gebePunkte(sieger[1]);

        if (rundeModel.gebeAusrufer() == rundeModel.gebePositionSpieler() || sieger[1] == rundeModel.gebePositionSpieler()) {
            speicherung.gesamtePunkteErhoehen(spielerPunkte);
            if (spielerPunkte > 120) {
                // TODO: Schneider-Logik hinzufügen
            } else {
                speicherung.SpielGewonnen(SpielArt.SAUSPIEL);
            }
        } else if (spielerPunkte > 90) {
            speicherung.SpielVerlorenSchneider(SpielArt.SAUSPIEL);
        } else {
            speicherung.SpielVerloren(SpielArt.SAUSPIEL);
        }

        speicherung.RundePunktzahlMelden(rundeModel.gebePunkte(rundeModel.gebePositionSpieler()));
        speicherung.DatenSpeichern();
    }

    private void spielSchleifeSau(int anzahlKarten, int vorhand) {
        int amZug = vorhand;

        for (int i = 0; i < 4; i++) {
            Spielkarte aktuelleSpielkarte = spieler.get(amZug).legeEineKarte();
            rundeModel.setzeAktuellenStich(amZug, aktuelleSpielkarte);

            for (Mitspieler aktuellerSpieler : spieler) {
                aktuellerSpieler.karteWurdeGelegt(aktuelleSpielkarte, amZug);
            }

            amZug = (amZug + 1) % 4;
        }

        speicherung.KarteGespielt();
        speicherung.DatenSpeichern();

        int sieger = ermittleSieger(rundeModel.gebeAktuellerStichArray());
        rundeModel.addierePunkte(sieger, ermittlePunkte(rundeModel.gebeAktuellerStichArray()));

        for (Mitspieler aktuellerSpieler : spieler) {
            aktuellerSpieler.stichGewonnen(sieger);
        }

        if (anzahlKarten > 1) {
            spielSchleifeSau(anzahlKarten - 1, sieger);
        }
    }

    public int ermittleSieger(Spielkarte[] aktuellerStich) {
        Spielkarte hoechsteKarte = aktuellerStich[0];
        boolean trumpfImStich = istTrumpf(hoechsteKarte);
        Farbe farbe = hoechsteKarte.gebeFarbe();
        int sieger = 0;

        for (int i = 1; i < aktuellerStich.length; i++) {
            if (trumpfImStich && istTrumpf(aktuellerStich[i]) && gebeTrumpfRang(aktuellerStich[i]) < gebeTrumpfRang(hoechsteKarte)) {
                hoechsteKarte = aktuellerStich[i];
                sieger = i;
            } else if (!trumpfImStich && istTrumpf(aktuellerStich[i])) {
                hoechsteKarte = aktuellerStich[i];
                trumpfImStich = true;
                sieger = i;
            } else if (!trumpfImStich && !istTrumpf(aktuellerStich[i]) && aktuellerStich[i].gebeFarbe() == farbe
                    && gebeFarbeRang(aktuellerStich[i]) < gebeFarbeRang(hoechsteKarte)) {
                hoechsteKarte = aktuellerStich[i];
                sieger = i;
            }
        }

        return sieger;
    }

    public boolean istTrumpf(Spielkarte karte) {
        return karte.gebeFarbe() == Farbe.HERZ || karte.gebeWert() == Werte.UNTER || karte.gebeWert() == Werte.OBER;
    }

    public int gebeTrumpfRang(Spielkarte karte) {
        Farbe farbe = karte.gebeFarbe();
        Werte wert = karte.gebeWert();

        if (wert == Werte.OBER) {
            return switch (farbe) {
                case EICHEL -> 0;
                case GRAS -> 1;
                case HERZ -> 2;
                case SCHELLEN -> 3;
            };
        } else if (wert == Werte.UNTER) {
            return switch (farbe) {
                case EICHEL -> 4;
                case GRAS -> 5;
                case HERZ -> 6;
                case SCHELLEN -> 7;
            };
        } else {
            return switch (wert) {
                case SAU -> 8;
                case ZEHNER -> 9;
                case KOENIG -> 10;
                case NEUNER -> 11;
                case ACHTER -> 12;
                case SIEBENER -> 13;
                default -> {
                    System.err.println("Unbekannter Kartenwert: " + karte.gebeWert());
                    yield -1; // Rückgabewert bei unbekanntem Kartenwert
                }
            };
        }
    }

    public int gebeFarbeRang(Spielkarte karte) {
        return switch (karte.gebeWert()) {
            case SAU -> 0;
            case ZEHNER -> 1;
            case KOENIG -> 2;
            case NEUNER -> 3;
            case ACHTER -> 4;
            case SIEBENER -> 5;
            default -> {
                System.err.println("Unbekannter Kartenwert: " + karte.gebeWert());
                yield -1; // Rückgabewert bei unbekanntem Kartenwert
            }
        };
    }

    public int ermittlePunkte(Spielkarte[] aktuellerStich) {
        int punkte = 0;
        for (Spielkarte karte : aktuellerStich) {
            punkte += karte.gebeWert().gebePunktzahl();
        }
        return punkte;
    }

    public int[] rundenSiegerErmitteln() {
        int mitspieler = spieler.get(rundeModel.gebePositionSpieler()).gebeMitspieler();
        int punkteSpieler = rundeModel.gebePunkte(rundeModel.gebeAusrufer()) + rundeModel.gebePunkte(mitspieler);

        if (punkteSpieler >= 60) {
            return new int[] {rundeModel.gebeAusrufer(), mitspieler};
        } else {
            return spieler.stream()
                    .filter(s -> s != spieler.get(rundeModel.gebeAusrufer()) && s != spieler.get(mitspieler))
                    .mapToInt(spieler::indexOf)
                    .toArray();
        }
    }
}