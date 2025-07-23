//Programmierer: Tim
package Controler;

import Model.Farbe;
import Model.SpielArt;
import Model.Spielkarte;
import Model.Werte;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Comparator;

public abstract class Mitspieler {
    boolean kannDavonLaufen = false;


    //Fragt die Spielabsicht der Mitspieler ab.
    public abstract void spielabsichtFragen(int wiederholung, SpielArt hoechstesSpiel, int vorhand);

    //Fragt die Farbe für das Spiel von dem Spieler ab, der das höchste Spiel angeboten hat. (Farbe bei Sau fürs Ausrufen, bei Solo für Trumpf)
    public abstract void farbeFuerSpielAbsicht(SpielArt spielArt);

    //Fordert einen Mitspieler auf eine erlaubte Karte zu legen.
    public abstract void legeEineKarte(int wiederholung, int vorhand);

    //Legt die Startwerte für eine neue Runde fest.
    public abstract void rundeStarten(ArrayList<Spielkarte> karten, int wieVielterSpieler);

    //Nachricht an Mitspieler, welche Spielart gespielt wird, überreicht Farbe als Solo und Sau als Sauspiel.
    public abstract void spielArtEntschieden(int spieler, Farbe farbe, SpielArt spielArt);

    //Nachricht an Mitspieler, welcher Spieler die Runde gewonnen hat.
    public abstract void rundeGewonnen(int[] gewinner, int[] punkte);

    //Nachricht an Mitspieler, welche Karte von einem Mitspieler gelegt wurde (auch wenn der Mitspieler selbst die Karte gelegt hat).
    public abstract void karteWurdeGelegt(Spielkarte karte, int spielerHatGelegt, int wiederholung);

    //Nachricht an Mitspieler, welcher Spieler den Stich gewonnen hat.
    public abstract void stichGewonnen(int spieler);

    //Gibt eine Identifikation der Art des Mitspielers zurück (Bot oder Spieler)
    public abstract int gebeMitspieler();

    //Setzte eine Referenz zum Objekt Runde
    public abstract void setzeRunde(Runde runde);

    private boolean sauDarfGespieltWerden(Spielkarte vorgegebeneKarte, Spielkarte sau, boolean sauFarbeVorhandGespielt, int handSize) {
        return (vorgegebeneKarte.gebeFarbe() == sau.gebeFarbe() || sauFarbeVorhandGespielt || handSize <= 1)
                && !vorgegebeneKarte.istTrumpf(SpielArt.SAUSPIEL, null);
    }


    /*
        Gibt eine Arraylist mit allen Karten zurück, welche unter Berücksichtigung der Spielart
        und deren Eigenschaften und der Karte die von der Vorhand gespielt wurde.
     */
    public ArrayList<Spielkarte> gibErlaubteKarten(
            ArrayList<Spielkarte> hand,
            SpielArt spielArt,
            Spielkarte sau,
            Spielkarte vorgegebeneKarte,
            Farbe soloFarbe,
            boolean sauFarbeVorhandGespielt
    ) {
        ArrayList<Spielkarte> gezwungeneKarten = new ArrayList<>();

        if (hand.size() == 1) {
            gezwungeneKarten.add(hand.getFirst());
            return gezwungeneKarten;
        }

        switch (spielArt) {
            case SOLO:
                if (vorgegebeneKarte.istTrumpf(spielArt, soloFarbe)) {
                    for (Spielkarte karte : hand) {
                        if (karte.istTrumpf(spielArt, soloFarbe)) {
                            gezwungeneKarten.add(karte);
                        }
                    }
                } else {
                    for (Spielkarte karte : hand) {
                        if (karte.gebeFarbe() == vorgegebeneKarte.gebeFarbe() &&
                                !karte.istTrumpf(spielArt, soloFarbe)) {
                            gezwungeneKarten.add(karte);
                        }
                    }
                }
                break;

            case WENZ:
                if (vorgegebeneKarte.istTrumpf(spielArt, soloFarbe)) {
                    for (Spielkarte karte : hand) {
                        if (karte.gebeWert() == Werte.UNTER) {
                            gezwungeneKarten.add(karte);
                        }
                    }
                } else {
                    for (Spielkarte karte : hand) {
                        if (karte.gebeFarbe() == vorgegebeneKarte.gebeFarbe() &&
                                karte.gebeWert() != Werte.UNTER) {
                            gezwungeneKarten.add(karte);
                        }
                    }
                }
                break;

            case SAUSPIEL:
                boolean sauErlaubt = sauDarfGespieltWerden(
                        vorgegebeneKarte,
                        sau,
                        sauFarbeVorhandGespielt,
                        hand.size()
                );

                // Wenn exakt die gesuchte Sau gespielt werden muss
                for (Spielkarte karte : hand) {
                    if (karte.gebeWert() == Werte.SAU &&
                            karte.gebeFarbe() == sau.gebeFarbe() &&
                            vorgegebeneKarte.gebeFarbe() == sau.gebeFarbe() &&
                            !karte.istTrumpf(spielArt, soloFarbe)) {
                        if (sauErlaubt) {
                            gezwungeneKarten.add(karte);
                        }
                        return gezwungeneKarten;
                    }
                }

                if (vorgegebeneKarte.istTrumpf(spielArt, soloFarbe)) {
                    for (Spielkarte karte : hand) {
                        if (karte.istTrumpf(spielArt, soloFarbe)) {
                            if (!(karte.equals(sau) && !sauErlaubt)) {
                                gezwungeneKarten.add(karte);
                            }
                        }
                    }
                } else {
                    for (Spielkarte karte : hand) {
                        if (karte.gebeFarbe() == vorgegebeneKarte.gebeFarbe() &&
                                !karte.istTrumpf(spielArt, soloFarbe)) {
                            if (!(karte.equals(sau) && !sauErlaubt)) {
                                gezwungeneKarten.add(karte);
                            }
                        }
                    }
                }
                break;

            default:
                System.out.println("Unbekannte Spielart. Karten können nicht bestimmt werden für: " + spielArt);
                break;
        }

        if (!gezwungeneKarten.isEmpty()) {
            return gezwungeneKarten;
        } else {
            return new ArrayList<>(hand); // Kein Klonen nötig – keine Änderungen mehr
        }
    }


    /*
        Bestimmt, welche Karten die Vorhand beim Sauspiel spielen darf.
     */

    public ArrayList<Spielkarte> erlaubteKartenAusspielenBeiSauspiel(ArrayList<Spielkarte> hand, Spielkarte sau) {
        ArrayList<Spielkarte> gezwungeneKarten = new ArrayList<>();
        int anzahlSauFarbeKarten = 0;
        boolean hatSau = false;
        //Bestimmt, ob die gesuchte Sau auf der Hand ist und wie viele Karten die Farbe der Sau auf der Hand sind.
        for (Spielkarte karte : hand) {
            if (karte.gebeFarbe() == sau.gebeFarbe() && !karte.istTrumpf(SpielArt.SAUSPIEL, null)) {
                anzahlSauFarbeKarten++;
                if (karte.gebeWert() == Werte.SAU) {
                    hatSau = true;
                }
            }
        }
        // Wenn der Spieler die Sau nicht hat, darf er alles legen.
        if(!hatSau){
            return  hand;
        }
        //Wenn mehr als 4 Karten der Farbe der Sau auf der Hand sind, die keine Trümpfe sind, kann davongelaufen werden.
        for (Spielkarte karte : hand) {
            if (anzahlSauFarbeKarten >= 4 || kannDavonLaufen) {
                //Es kann davon gelaufen werden → alle Karten dürfen gelegt werden.
                kannDavonLaufen = true;
                gezwungeneKarten.add(karte);
            } else if (karte.gebeFarbe() != sau.gebeFarbe() || karte.istTrumpf(SpielArt.SAUSPIEL, null) || karte.gebeWert() == Werte.SAU) {
                //Es kann nicht davon gelaufen werden, andere Karten, die keine Trümpfe sind und die gleiche Farbe wie die gesuchte Sau haben, dürfen nicht gelegt werden.
                gezwungeneKarten.add(karte);
            }
        }
        return gezwungeneKarten;
    }

    /*
        Bestimmt, welches Sauen ein Spieler aufgrund von seiner Hand ausrufen kann.
        Gleichzeitig wird das mögliche Sauen nach der Anzahl der Karten, der jeweiligen Farbe der Sau, nach sortiert.
        Dabei ist die Sau mit der wenigstens Karte, die die gleiche Farbe hat, auf der Hand bei Index 0.
        Dies ist hilfreich, da der Bot dadurch einfach die beste Sau zum Ausrufen wählen kann.
     */

    public ArrayList<Farbe> sauZumAusrufen(ArrayList<Spielkarte> hand) {
        EnumMap<Farbe, Integer> anzahlProFarbe = new EnumMap<>(Farbe.class);
        EnumSet<Farbe> farbenMitSau = EnumSet.noneOf(Farbe.class);

        // Initialisiere Zähler für relevante Farben
        for (Farbe farbe : List.of(Farbe.EICHEL, Farbe.GRAS, Farbe.SCHELLEN)) {
            anzahlProFarbe.put(farbe, 0);
        }

        // Zähle Karten und Sauen pro Farbe
        for (Spielkarte karte : hand) {
            if (!karte.istTrumpf(SpielArt.SAUSPIEL, null)) {
                Farbe farbe = karte.gebeFarbe();
                if (anzahlProFarbe.containsKey(farbe)) {
                    anzahlProFarbe.put(farbe, anzahlProFarbe.get(farbe) + 1);
                    if (karte.gebeWert() == Werte.SAU) {
                        farbenMitSau.add(farbe);
                    }
                }
            }
        }

        // Suche alle Farben ohne Sau, aber mit mindestens einer Karte
        List<Farbe> erlaubteFarben = new ArrayList<>();
        for (Farbe farbe : List.of(Farbe.EICHEL, Farbe.GRAS, Farbe.SCHELLEN)) {
            if (!farbenMitSau.contains(farbe) && anzahlProFarbe.get(farbe) > 0) {
                erlaubteFarben.add(farbe);
            }
        }

        // Sortiere die erlaubten Farben nach Kartenanzahl aufsteigend
        erlaubteFarben.sort(Comparator.comparingInt(anzahlProFarbe::get));

        return new ArrayList<>(erlaubteFarben);
    }

}

