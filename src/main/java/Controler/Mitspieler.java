//Programmierer: Tim
package Controler;

import Model.Farbe;
import Model.SpielArt;
import Model.Spielkarte;
import Model.Werte;

import java.util.ArrayList;

public abstract class Mitspieler {


    //Fragt die Spielabsicht der Mitspieler ab.
    public abstract void spielabsichtFragen(int wiederholung, SpielArt hoechstesSpiel, int vorhand);

    //Fragt die Farbe für das Spiel von dem Spieler, der das höchste Spiel angeboten hat ab. (Farbe bei Sau fürs Ausrufen, bei Solo für Trumpf)
    public abstract void farbeFuerSpielAbsicht(SpielArt spielArt);

    //Fordert einen Mitspieler auf eine erlaubte Karte zu legen.
    public abstract void legeEineKarte(int wiederholung, int vorhand);

    //Legt die Startwerte für eine neue Runde fest.
    public abstract void rundeStarten(ArrayList<Spielkarte> karten, int wieVielterSpieler);

    //Nachricht an Mitspieler welche Spielart gespielt wird, überreicht Farbe wenn Solo und Sau wenn Sauspiel.
    public abstract void spielArtEntschieden(int spieler, Farbe farbe, SpielArt spielArt);

    //Nachricht an Mitspieler welcher spieler die Runde gewonnen hat.
    public abstract void rundeGewonnen(int[] gewinner, int[] punkte);

    //Nachricht an Mitspieler welche Karte von einem Mitspieler gelegt wurde (auch wenn der Mitspieler selbst die Karte gelegt hat).
    public abstract void karteWurdeGelegt(Spielkarte karte, int spielerHatGelegt, int wiederholung);

    //Nachricht an Mitspieler welcher Spieler den Stich gewonnen hat.
    public abstract void stichGewonnen(int spieler);

    //Gibt eine Identifikation der Art des Mitspielers zurück (Bot oder Spieler)
    public abstract int gebeMitspieler();

    //Setzte eine Referenz zum Objekt Runde
    public abstract void setzeRunde(Runde runde);

    /*
        Gibt eine Arraylist mit allen Karten zurück, welche unter Berücksichtigung der Spielart
        und dessen Eigenschaften und der Karte die von der Vorhand gespielt wurde.
     */
    public ArrayList<Spielkarte> gibErlaubteKarten(ArrayList<Spielkarte> hand, SpielArt spielArt, Spielkarte sau, Spielkarte vorgegebeneKarte, Farbe soloFarbe, boolean sauFarbeVorhandGespielt) {

        ArrayList<Spielkarte> gezwungeneKarten = new ArrayList<Spielkarte>();
        //Wenn nur noch eine Karte auf der Hand ist, gibt es keine Wahl, welche Karte gespielt werden kann.
        if (hand.size() == 1) {
            gezwungeneKarten.add(hand.get(0));
            return gezwungeneKarten;
        }
        //Unterscheidung der Spielart.
        switch (spielArt) {

            case SOLO:
                //Unterscheiden, ob die Karte von der Vorhand ein Trumpf oder eine Farbe ist.
                if (vorgegebeneKarte.istTrumpf(spielArt, soloFarbe)) {
                    for (Spielkarte karte : hand) {
                        //Alle Trümpfe dürfen gespielt werden.
                        if (karte.istTrumpf(spielArt, soloFarbe)) {
                            gezwungeneKarten.add(karte);
                        }
                    }
                } else {
                    for (Spielkarte karte : hand) {
                        //Alle Karten der Farbe, von der Karte der Vorhand, dürfen gespielt werden.
                        if (karte.gebeFarbe() == vorgegebeneKarte.gebeFarbe() && !karte.istTrumpf(spielArt, soloFarbe)) {
                            gezwungeneKarten.add(karte);
                        }
                    }
                    return gezwungeneKarten;
                }
                break;
            case WENZ:
                //Unterscheiden, ob die Karte von der Vorhand ein Trumpf oder eine Farbe ist.
                if (vorgegebeneKarte.istTrumpf(spielArt, soloFarbe)) {
                    for (Spielkarte karte : hand) {
                        //Alle Trümpfe dürfen gespielt werden.
                        if (karte.gebeWert() == Werte.UNTER) {
                            gezwungeneKarten.add(karte);
                        }
                    }

                } else {
                    for (Spielkarte karte : hand) {
                        //Alle Karten der Farbe, von der Karte der Vorhand, dürfen gespielt werden.
                        if (karte.gebeFarbe() == vorgegebeneKarte.gebeFarbe() && !karte.istTrumpf(spielArt, soloFarbe)) {
                            gezwungeneKarten.add(karte);
                        }
                    }
                }

                break;
            case SAUSPIEL:

                //Gesuchte Sau darf nicht gelegt werden, wenn die Vorhand nicht die Farbe der Sau hat.
                //Sonst darf Sau nur gelegt werden, nachdem die Farbe mindestens einmal in Vorhand gespielt wurde oder es der letzte Stich ist.
                if ((vorgegebeneKarte.gebeFarbe() != sau.gebeFarbe() && !sauFarbeVorhandGespielt && hand.size() > 1)|| vorgegebeneKarte.istTrumpf(SpielArt.SAUSPIEL,null) ) {
                    //Entfernt Sau von der Hand, damit sie nicht mehr gespielt werden kann.
                    hand.remove(sau); //Grund für das clonen von Hand
                }

                //Sofern die Farbe der Karte von der Vorhand und der Gesuchten sau gleich sind, darf nur die Sau gelegt werden.
                for (Spielkarte karte : hand) {
                    if (karte.gebeWert() == Werte.SAU && vorgegebeneKarte.gebeFarbe() == sau.gebeFarbe() && karte.gebeFarbe() == sau.gebeFarbe() && !karte.istTrumpf(SpielArt.SAUSPIEL, soloFarbe)) {
                        gezwungeneKarten.add(karte);
                        return gezwungeneKarten;
                    }
                }
                if (vorgegebeneKarte.istTrumpf(spielArt, soloFarbe)) {
                    for (Spielkarte karte : hand) {
                        //Alle Trümpfe dürfen gespielt werden.
                        if (karte.istTrumpf(spielArt, soloFarbe)) {
                            gezwungeneKarten.add(karte);
                        }
                    }
                } else {
                    for (Spielkarte karte : hand) {
                        //Alle Karten der Farbe, von der Karte der Vorhand, dürfen gespielt werden.
                        if (karte.gebeFarbe() == vorgegebeneKarte.gebeFarbe() && !karte.istTrumpf(spielArt, soloFarbe)) {
                                gezwungeneKarten.add(karte);
                        }
                    }
                }
                break; //TOM
            default:
                System.out.println("Unbekannte Spielart. Karten können nicht bestimmt werden für:" + spielArt);
                break;

        }
        //Sofern keine Karte zwingend gespielt werden muss, dürfen alle Karten gespielt werden.

        if (!gezwungeneKarten.isEmpty()) {
            return gezwungeneKarten;
        } else {
            return (ArrayList<Spielkarte>) hand.clone();
        }

    }

    /*
        Bestimmt welche Karten die Vorhand beim Sauspiel spielen darf.
     */

    public ArrayList<Spielkarte> erlaubteKartenAusspielenBeiSauspiel(ArrayList<Spielkarte> hand, Spielkarte sau) {
        ArrayList<Spielkarte> gezwungeneKarten = new ArrayList<Spielkarte>();
        int anzahlSauFarbeKarten = 0;
        boolean hatSau = false;
        //Bestimmt, ob die gesuchte Sau auf der Hand ist und wie viele Karten der Farbe der Sau auf der Hand sind.
        for (Spielkarte karte : hand) {
            if (karte.gebeFarbe() == sau.gebeFarbe() && !karte.istTrumpf(SpielArt.SAUSPIEL, null)) {
                anzahlSauFarbeKarten++;
                if (karte.gebeWert() == Werte.SAU) {
                    hatSau = true;
                }
            }
        }
        //Wenn Spieler die Sau nicht hat, darf er alles legen.
        if(!hatSau){
            return  hand;
        }
        //Wenn mehr als 4 Karten der Farbe der Sau auf der Hand sind, die keine Trümpfe sind, kann davongelaufen werden.
        for (Spielkarte karte : hand) {
            if (anzahlSauFarbeKarten >= 4) {
                //Es kann davon gelaufen werden → alle Karten dürfen gelegt werden.
                gezwungeneKarten.add(karte);
            } else if (karte.gebeFarbe() != sau.gebeFarbe() || karte.istTrumpf(SpielArt.SAUSPIEL, null) || karte.gebeWert() == Werte.SAU) {
                //Es kann nicht davon gelaufen werden, andere Karten die keine Trümpfe sind und die gleiche Farbe wie die gesuchte Sau haben, dürfen nicht gelegt werden.
                gezwungeneKarten.add(karte);
            }
        }
        return gezwungeneKarten;
    }

    /*
        Bestimmt welches Sauen ein Spieler aufgrund von seiner Hand ausrufen kann.
        Gleichzeitig werden dem möglichen Sauen nach der Anzahl der Karten, der jeweiligen Farbe der Sau, nach sortiert.
        Dabei ist die Sau mit dem wenigstens Karte, die die gleiche Farbe haben, auf der Hand bei Index 0.
        Dies ist hilfreich da der Bot dadurch einfach die beste Sau zum Ausrufen wählen kann.
     */

    public ArrayList<Farbe> sauZumAusrufen(ArrayList<Spielkarte> hand) {

        int anzahlEichel = 0;
        int anzahlGras = 0;
        int anzahlSchellen = 0;

        boolean hatEichelSau = false;
        boolean hatGrasau = false;
        boolean hatSchellenSau = false;

        //Bestimmung welche, Sauen und wie viele Karten der jeweiligen Farbe auf der Hand sind.
        for (Spielkarte karte : hand) {
            if(!karte.istTrumpf(SpielArt.SAUSPIEL, null)){
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

        }
        //Überprüft, ob eine Sau ausgerufen werden kann und sortiert sie in die Arraylist ein.
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

