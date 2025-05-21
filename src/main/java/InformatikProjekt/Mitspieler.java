package InformatikProjekt;

import java.util.ArrayList;

//Programmierer: Tim

public abstract class Mitspieler {


    //Fragt die Spielabsicht der Mitspieler ab.
    public abstract SpielArt spielabsichtFragen(SpielArt hoechstesSpiel);

    //Nachricht an Mitspieler, nachdem ein Spieler eine Spielabsicht abgegeben hat.
    public abstract void spielerHatSpielabsichtGesagt(SpielArt spielAbsicht, int spieler);
    //Forder einen Mitspieler auf eine legale Karte zu legen.
    public abstract Spielkarte legeEineKarte();

    //Legt die Startwerte fuer eine neue Runde fest.
    public abstract void rundeStarten(
            ArrayList<Spielkarte> karten, int wieVielterSpieler
    );

    //Nachricht an Mitspieler welche Spielart gespielt wird, ueberreicht Farne wenn Solo und Sau wenn Sauspiel.
    public abstract void spielArtEntschieden(int spieler, Spielkarte sau, Farbe farbeSolo, SpielArt spielArt);

    //Nutzen nicht ersichtlich?
    public abstract void setzeErsterSpieler(int ersterSpieler);

    //Nachricht an Mitspieler welcher spieler die Runde gewonnen hat.
    public abstract void rundeGewonnen(int spieler);

    //Nachricht an Mitspieler welche Karte von einem Mitspieler gelegt wurde(auch wenn der Mitspieler selbst die Karte gelegt hat).
    public abstract void karteWurdeGelegt(Spielkarte karte, int spielerHatGelegt);

    //Nachricht an Mitspieler welche Karte von einem Mitspieler gelegt wurde(auch wenn der Mitspieler selbst die Karte gelegt hat).
    public abstract void spielAusgerufen(SpielArt ausgerufenesSpiel, int spieler);

    //Nachricht an Mitspieler welcher Spieler den Stich gewonnen hat, angabe der Punkte, zusammenrechnen der Punket am besten in Mitspieler
    public abstract void stichGewonnen(int spieler, int[] punkte);

    //Methode fuer Spieler und Bot die eine ArrayList mit allen Karten die gelegt werden koennen zurueckgibt.
    public ArrayList<Spielkarte> gibErlaubteKarten(ArrayList<Spielkarte> hand, SpielArt spielArt, Spielkarte sau, Spielkarte vorgegebeneKarte, Farbe soloFarbe) {

        ArrayList<Spielkarte> legaleKarten = new ArrayList<Spielkarte>();
        switch (spielArt) {
            case SOLO:

                legaleKarten = this.soloErlaubteKarten(hand, soloFarbe, vorgegebeneKarte);

                break;
            case WENZ:
                if (vorgegebeneKarte.gebeWert() == Werte.UNTER) {
                    for (Spielkarte karte : hand) {
                        if (karte.gebeWert() == Werte.UNTER) {
                            legaleKarten.add(karte);
                        }
                    }

                } else {
                    for (Spielkarte karte : hand) {
                        if (karte.gebeFarbe() == vorgegebeneKarte.gebeFarbe()) {
                            legaleKarten.add(karte);
                        }

                    }
                }

                break;
            case SAUSPIEL:
                for (Spielkarte karte : hand) {
                    if (karte == sau) {
                        legaleKarten.add(karte);
                        return legaleKarten;
                    }
                }
                if (vorgegebeneKarte.gebeFarbe() == Farbe.HERZ || vorgegebeneKarte.gebeWert() == Werte.OBER || vorgegebeneKarte.gebeWert() == Werte.UNTER) {
                    for (Spielkarte karte : hand) {
                        if (karte.gebeFarbe() == Farbe.HERZ || karte.gebeWert() == Werte.OBER || karte.gebeWert() == Werte.UNTER) {
                            legaleKarten.add(karte);
                        }
                    }

                } else {
                    for (Spielkarte karte : hand) {
                        if (karte.gebeFarbe() == vorgegebeneKarte.gebeFarbe()) {
                            legaleKarten.add(karte);
                        }

                    }

                }


                break;
        }
        if (!legaleKarten.isEmpty()) {
            return legaleKarten;
        } else {
            return hand;
        }

    }

    private ArrayList<Spielkarte> soloErlaubteKarten(ArrayList<Spielkarte> hand, Farbe farbeSolo, Spielkarte vorgegebeneKarte) {
        ArrayList<Spielkarte> legaleKarten = new ArrayList<Spielkarte>();
        if (vorgegebeneKarte.gebeFarbe() == farbeSolo || vorgegebeneKarte.gebeWert() == Werte.OBER || vorgegebeneKarte.gebeWert() == Werte.UNTER) {
            for (Spielkarte karte : hand) {
                if (karte.gebeFarbe() == farbeSolo || karte.gebeWert() == Werte.OBER || karte.gebeWert() == Werte.UNTER) {
                    legaleKarten.add(karte);
                }
            }
            return legaleKarten;

        } else {
            for (Spielkarte karte : hand) {
                if (karte.gebeFarbe() == vorgegebeneKarte.gebeFarbe()) {
                    legaleKarten.add(karte);
                }

            }
            return legaleKarten;

        }

    }
}
