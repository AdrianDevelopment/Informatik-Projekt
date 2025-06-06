package InformatikProjekt;

import java.util.ArrayList;

public abstract class Mitspieler {

    // Fragt die Spielabsicht der Mitspieler ab.
    public abstract SpielArt spielabsichtFragen(SpielArt hoechstesSpiel);

    // Fragt die Farbe für das Spiel an (z. B. Sau fürs Ausrufen, bei Solo für Trumpf).
    public abstract Farbe farbeFuerSpielAbsicht(SpielArt spielArt);

    // Benachrichtigt die Mitspieler, wenn ein Spieler seine Spielabsicht erklärt hat.
    public abstract void spielerHatSpielabsichtGesagt(SpielArt spielAbsicht, int spieler);

    // Fordert einen Mitspieler auf, eine legale Karte zu legen.
    public abstract Spielkarte legeEineKarte();

    // Initialisiert die Werte für eine neue Runde.
    public abstract void rundeStarten(ArrayList<Spielkarte> karten, int wieVielterSpieler);

    // Benachrichtigt die Mitspieler über die ausgewählte Spielart, inkl. relevanter Farben oder eines Sauspiel.
    public abstract void spielArtEntschieden(int spieler, Farbe farbe, SpielArt spielArt);

    // Benachrichtigt die Mitspieler, welcher Spieler die Runde gewonnen hat.
    public abstract void rundeGewonnen(int[] gewinner, int[] punkte);

    // Benachrichtigt die Mitspieler über die Karte, die von einem Spieler gelegt wurde.
    public abstract void karteWurdeGelegt(Spielkarte karte, int spielerHatGelegt);

    // Benachrichtigt die Mitspieler, welcher Spieler den Stich gewonnen hat.
    public abstract void stichGewonnen(int spieler);

    // Liefert die Position des Mitspielers.
    public abstract int gebeMitspieler();

    // Benachrichtigt die Mitspieler, dass kein Spiel gespielt wird.
    public abstract void keinSpiel();

    // Gibt eine Liste von erlaubten Karten zurück, die gelegt werden dürfen.
    public ArrayList<Spielkarte> gibErlaubteKarten(ArrayList<Spielkarte> hand, SpielArt spielArt, Spielkarte sau, Spielkarte vorgegebeneKarte, Farbe soloFarbe, boolean sauFarbeVorhandGespielt) {
        ArrayList<Spielkarte> gezwungeneKarten = new ArrayList<>();

        if (hand.isEmpty()) {
            System.out.println("DEBUG: Keine Karten auf der Hand!");
            return gezwungeneKarten;
        }

        if (hand.size() == 1) {
            gezwungeneKarten.add(hand.get(0));
            return gezwungeneKarten;
        }

        // Filterkriterien abhängig von der Spielart
        switch (spielArt) {
            case SOLO:
                auswahlKartenSolo(hand, vorgegebeneKarte, soloFarbe, gezwungeneKarten);
                break;

            case WENZ:
                auswahlKartenWenz(hand, vorgegebeneKarte, gezwungeneKarten);
                break;

            case SAUSPIEL:
                auswahlKartenSauspiel(hand, sau, vorgegebeneKarte, sauFarbeVorhandGespielt, gezwungeneKarten);
                break;
        }

        return gezwungeneKarten.isEmpty() ? hand : gezwungeneKarten;
    }

    private void auswahlKartenSolo(ArrayList<Spielkarte> hand, Spielkarte vorgegebeneKarte, Farbe soloFarbe, ArrayList<Spielkarte> gezwungeneKarten) {
        if (vorgegebeneKarte.istTrumpf(SpielArt.SOLO, soloFarbe)) {
            for (Spielkarte karte : hand) {
                if (karte.istTrumpf(SpielArt.SOLO, soloFarbe)) {
                    gezwungeneKarten.add(karte);
                }
            }
        } else {
            for (Spielkarte karte : hand) {
                if (karte.gebeFarbe() == vorgegebeneKarte.gebeFarbe() && !karte.istTrumpf(SpielArt.SOLO, soloFarbe)) {
                    gezwungeneKarten.add(karte);
                }
            }
        }
    }

    private void auswahlKartenWenz(ArrayList<Spielkarte> hand, Spielkarte vorgegebeneKarte, ArrayList<Spielkarte> gezwungeneKarten) {
        if (vorgegebeneKarte.istTrumpf(SpielArt.WENZ, null)) {
            for (Spielkarte karte : hand) {
                if (karte.gebeWert() == Werte.UNTER) {
                    gezwungeneKarten.add(karte);
                }
            }
        } else {
            for (Spielkarte karte : hand) {
                if (karte.gebeFarbe() == vorgegebeneKarte.gebeFarbe() && !karte.istTrumpf(SpielArt.WENZ, null)) {
                    gezwungeneKarten.add(karte);
                }
            }
        }
    }

    private void auswahlKartenSauspiel(ArrayList<Spielkarte> hand, Spielkarte sau, Spielkarte vorgegebeneKarte, boolean sauFarbeVorhandGespielt, ArrayList<Spielkarte> gezwungeneKarten) {
        if (!sauFarbeVorhandGespielt && vorgegebeneKarte.gebeFarbe() != sau.gebeFarbe()) {
            hand.remove(sau); // Sau darf noch nicht gespielt werden.
        }

        for (Spielkarte karte : hand) {
            if (karte.gebeFarbe() == vorgegebeneKarte.gebeFarbe() && karte.gebeWert() == Werte.SAU) {
                gezwungeneKarten.add(karte);
                return;
            }
        }

        if (vorgegebeneKarte.istTrumpf(SpielArt.SAUSPIEL, null)) {
            for (Spielkarte karte : hand) {
                if (karte.istTrumpf(SpielArt.SAUSPIEL, null)) {
                    gezwungeneKarten.add(karte);
                }
            }
        } else {
            for (Spielkarte karte : hand) {
                if (karte.gebeFarbe() == vorgegebeneKarte.gebeFarbe() && !karte.istTrumpf(SpielArt.SAUSPIEL, null)) {
                    gezwungeneKarten.add(karte);
                }
            }
        }
    }

    // Gibt erlaubte Karten zurück, wenn man selbst die Sau hat und ausspielen muss.
    public ArrayList<Spielkarte> erlaubteKartenAusspielenBeiSauspiel(ArrayList<Spielkarte> hand, Spielkarte sau) {
        ArrayList<Spielkarte> gezwungeneKarten = new ArrayList<>();
        boolean hatSau = false;
        int anzahlSauFarbeKarten = 0;

        for (Spielkarte karte : hand) {
            if (karte.gebeFarbe() == sau.gebeFarbe() && !karte.istTrumpf(SpielArt.SAUSPIEL, null)) {
                anzahlSauFarbeKarten++;
                if (karte.gebeWert() == Werte.SAU) {
                    hatSau = true;
                }
            }
        }

        for (Spielkarte karte : hand) {
            if (hatSau && anzahlSauFarbeKarten >= 4) {
                gezwungeneKarten.add(karte);
            } else if (karte.gebeFarbe() != sau.gebeFarbe() || karte.istTrumpf(SpielArt.SAUSPIEL, null) || karte.gebeWert() == Werte.SAU) {
                gezwungeneKarten.add(karte);
            }
        }

        return gezwungeneKarten;
    }

    // Gibt alle Farben zurück, für die der Spieler eine Sau ausrufen kann.
    public ArrayList<Farbe> sauZumAusrufen(ArrayList<Spielkarte> hand) {
        int[] farbenZaehler = new int[Farbe.values().length];
        boolean[] hatSau = new boolean[Farbe.values().length];

        for (Spielkarte karte : hand) {
            int index = karte.gebeFarbe().ordinal();
            farbenZaehler[index]++;
            if (karte.gebeWert() == Werte.SAU) {
                hatSau[index] = true;
            }
        }

        ArrayList<Farbe> erlaubteFarben = new ArrayList<>();
        for (int i = 0; i < farbenZaehler.length; i++) {
            if (farbenZaehler[i] > 0 && !hatSau[i]) {
                erlaubteFarben.add(Farbe.values()[i]);
            }
        }

        erlaubteFarben.sort((f1, f2) -> Integer.compare(farbenZaehler[f1.ordinal()], farbenZaehler[f2.ordinal()]));
        return erlaubteFarben;
    }
}