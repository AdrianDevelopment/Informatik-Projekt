//Programmierer: Tim
package InformatikProjekt;

import java.util.ArrayList;

// TODO: ich (Adrian, also Runde) übergeb bisher nur die Position des echten Spielers. Brauchst du aber immer jeweils die eigene Position, auch bei Bots?

public abstract class Mitspieler {


    //Fragt die Spielabsicht der Mitspieler ab.
    public abstract SpielArt spielabsichtFragen(SpielArt hoechstesSpiel);

    //Fragt die Farbe für das Spiel von dem Spieler, der das hoechste Spiel angeboten hat ab. (Farbe bei Sau fürs Ausrufen, bei Solo für Trumpf)
    public abstract Farbe farbeFuerSpielAbsicht(SpielArt spielArt);

    //Nachricht an Mitspieler, nachdem ein Spieler eine Spielabsicht abgegeben hat.
    public abstract void spielerHatSpielabsichtGesagt(SpielArt spielAbsicht, int spieler);

    //Forder einen Mitspieler auf eine legale Karte zu legen.
    public abstract Spielkarte legeEineKarte();

    //Legt die Startwerte fuer eine neue Runde fest.
    public abstract void rundeStarten(ArrayList<Spielkarte> karten, int wieVielterSpieler);

    //Nachricht an Mitspieler welche Spielart gespielt wird, ueberreicht Farbe wenn Solo und Sau wenn Sauspiel.
    public abstract void spielArtEntschieden(int spieler, Farbe farbe, SpielArt spielArt);

    //Nachricht an Mitspieler welcher spieler die Runde gewonnen hat.
    public abstract void rundeGewonnen(int[] gewinner, int[] punkte);

    //Nachricht an Mitspieler welche Karte von einem Mitspieler gelegt wurde(auch wenn der Mitspieler selbst die Karte gelegt hat).
    public abstract void karteWurdeGelegt(Spielkarte karte, int spielerHatGelegt);

    //Nachricht an Mitspieler welcher Spieler den Stich gewonnen hat.
    public abstract void stichGewonnen(int spieler);

    public abstract int gebeMitspieler();

    //Methode fuer Spieler und Bot die eine ArrayList mit allen Karten die gelegt werden koennen zurueckgibt.
    //Diese Methode nicht Aufrufen, wenn die erste Karte gelegt wird!!!
    public ArrayList<Spielkarte> gibErlaubteKarten(ArrayList<Spielkarte> hand, SpielArt spielArt, Spielkarte sau, Spielkarte vorgegebeneKarte, Farbe soloFarbe, boolean sauFarbeVorhandGespielt) {

        ArrayList<Spielkarte> gezwungeneKarten = new ArrayList<Spielkarte>();
        switch (spielArt) {
            case SOLO:

                gezwungeneKarten = this.soloErlaubteKarten(hand, soloFarbe, vorgegebeneKarte);

                break;
            case WENZ:

                if (vorgegebeneKarte.gebeWert() == Werte.UNTER) {
                    for (Spielkarte karte : hand) {
                        if (karte.gebeWert() == Werte.UNTER) {
                            gezwungeneKarten.add(karte);
                        }
                    }

                } else {
                    for (Spielkarte karte : hand) {
                        if (karte.gebeFarbe() == vorgegebeneKarte.gebeFarbe() && karte.gebeWert() != Werte.UNTER) {
                            gezwungeneKarten.add(karte);
                        }

                    }
                }

                break;
            case SAUSPIEL:
                //todo alternative zu clone finden
                int anzahlSauFarbeKarten = 0;
                boolean hatSau = false;

                //Gesuchte Sau darf nicht gelegt werden, wenn nicht die richtige Farbe liegt.
                //Sonst darf Sau nur gelegt werden, nachdem die Farbe mindestens einmal in Vorhand gespielt wurde oder es der letzte Stich ist.
                if (vorgegebeneKarte.gebeFarbe() != sau.gebeFarbe() && !sauFarbeVorhandGespielt && hand.size() > 1) {


                    if (hand.contains(sau)) {
                        hand.remove(sau);
                    }


                }


                for (Spielkarte karte : hand) {
                    if (vorgegebeneKarte.gebeFarbe() == sau.gebeFarbe() && karte.gebeFarbe() == sau.gebeFarbe() && !karte.istTrumpf(spielArt, soloFarbe)) {
                        anzahlSauFarbeKarten++;
                        if (karte.gebeWert() == Werte.SAU) {
                            hatSau = true;
                        }

                    }

                }
                if (hatSau && anzahlSauFarbeKarten < 4) {
                    gezwungeneKarten.add(sau);
                    return gezwungeneKarten;
                }

                if (vorgegebeneKarte.istTrumpf(spielArt, soloFarbe)) {
                    for (Spielkarte karte : hand) {
                        if (karte.istTrumpf(spielArt, soloFarbe)) {

                            gezwungeneKarten.add(karte);
                        }
                    }

                } else {
                    for (Spielkarte karte : hand) {
                        if (karte.gebeFarbe() == vorgegebeneKarte.gebeFarbe()) {

                            gezwungeneKarten.add(karte);
                        }

                    }

                }


                break;
        }
        if (!gezwungeneKarten.isEmpty()) {
            return gezwungeneKarten;
        } else {
            return hand;
        }

    }

    private ArrayList<Spielkarte> soloErlaubteKarten(ArrayList<Spielkarte> hand, Farbe farbeSolo, Spielkarte vorgegebeneKarte) {
        ArrayList<Spielkarte> gezwungeneKarten = new ArrayList<Spielkarte>();
        if (vorgegebeneKarte.gebeFarbe() == farbeSolo || vorgegebeneKarte.gebeWert() == Werte.OBER || vorgegebeneKarte.gebeWert() == Werte.UNTER) {
            for (Spielkarte karte : hand) {
                if (karte.gebeFarbe() == farbeSolo || karte.gebeWert() == Werte.OBER || karte.gebeWert() == Werte.UNTER) {
                    gezwungeneKarten.add(karte);
                }
            }
            return gezwungeneKarten;

        } else {
            for (Spielkarte karte : hand) {
                if (karte.gebeFarbe() == vorgegebeneKarte.gebeFarbe()) {
                    gezwungeneKarten.add(karte);
                }

            }
            return gezwungeneKarten;

        }

    }

    //Gibt alle Farben zurueck fuer, die der Spieler eine Sau ausrufen kann. Die Farbe mit der niedrigsten Anzahl an Karten hat Index 0.
    public ArrayList<Farbe> sauZumAusrufen(ArrayList<Spielkarte> hand) {
        int anzahlEichel = 0;
        int anzahlGras = 0;
        int anzahlSchellen = 0;

        boolean hatEichelSau = false;
        boolean hatGrasau = false;
        boolean hatSchellenSau = false;

        for (Spielkarte karte : hand) {
            switch (karte.gebeFarbe()) {
                case SCHELLEN:
                    anzahlSchellen++;
                    if (karte.gebeWert() == Werte.SAU) {
                        hatSchellenSau = true;
                    }
                    break;
                case GRAS:
                    anzahlGras++;
                    if (karte.gebeWert() == Werte.SAU) {
                        hatGrasau = true;
                    }
                    break;
                case EICHEL:
                    anzahlEichel++;
                    if (karte.gebeWert() == Werte.SAU) {
                        hatEichelSau = true;
                    }
                    break;
                case HERZ:

                    break;
            }
        }
        ArrayList<Farbe> erlaubteFarben = new ArrayList<>();
        if (anzahlGras != 0 && !hatGrasau) {
            erlaubteFarben.add(Farbe.GRAS);
        }
        if (anzahlSchellen != 0 && !hatSchellenSau) {
            if (anzahlSchellen < anzahlGras) {
                erlaubteFarben.add(0, Farbe.SCHELLEN);
            } else {
                erlaubteFarben.add(Farbe.SCHELLEN);
            }

        }
        if (anzahlEichel != 0 && !hatEichelSau) {
            int positionInListe = 0;
            for (Farbe farbe : erlaubteFarben) {
                if (farbe == Farbe.GRAS && anzahlEichel > anzahlGras) {
                    positionInListe++;
                }
                if (farbe == Farbe.SCHELLEN && anzahlEichel > anzahlSchellen) {
                    positionInListe++;
                }

            }
            erlaubteFarben.add(positionInListe, Farbe.EICHEL);
        }
        return erlaubteFarben;

    }
}

