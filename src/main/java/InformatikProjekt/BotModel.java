//Programmierer: Tim
package InformatikProjekt;

import java.util.ArrayList;

public class BotModel {
    private ArrayList<Spielkarte> hand; //Karten auf der Hand
    private Spielkarte sau; //Die ausgerufene Sau
    private SpielArt spielArt; //Die Spielart der moment Runde
    private Farbe soloFarbe; //Die Farbe des Solospieles;
    private ArrayList<Spielkarte> gelegteKarten; //Alle Karten die bisher gelegt wurden.
    private int spielerIndex; //Die einzigartige Spielernummer von diesem Bot.
    private ArrayList<Integer> spielzugReihenfolge; //Die Reihenfolge der Spieler, anhand ihrer Spielernummer, die in dem Stich Karten gelegt haben.
    private BotMitspielerDatenModel[] mitspielerDaten; //Abspeicherung von Informationen über die Hand der anderen Spieler.
    private int spielerHatSauAusgerufen; //Spielernummer des Spielers der die Sau ausgerufen hat.
    private int teamSpieler;//Spielernummer des Mitspielers
    private boolean sauFarbeVorhandGespielt; // Speicherung, ob die Farbe der ausgerufenen Sau schon gespielt wurde.

    BotModel() {
        sau = new Spielkarte(Farbe.GRAS, Werte.SAU);
        spielzugReihenfolge = new ArrayList<Integer>();
        gelegteKarten = new ArrayList<Spielkarte>();
        teamSpieler = -1; // -1 steht dafür das der Mitspieler noch nicht bekannt ist
        mitspielerDaten = new BotMitspielerDatenModel[3];
        mitspielerDaten[0] = new BotMitspielerDatenModel();
        mitspielerDaten[1] = new BotMitspielerDatenModel();
        mitspielerDaten[2] = new BotMitspielerDatenModel();
        sauFarbeVorhandGespielt = false;
    }

    public int gibStichGelegteKartenAnzahl() {
        return spielzugReihenfolge.size();
    }

    public ArrayList<Spielkarte> gibHand() {
        return hand;
    }

    public Spielkarte gibSau() {
        return sau;
    }

    public SpielArt gibSpielArt() {
        return spielArt;
    }

    public Farbe gibsoloFarbe() {
        return soloFarbe;
    }

    public Spielkarte gibLetzteGelegteKarte() {
        return gelegteKarten.get(gelegteKarten.size() - 1);
    }

    public Spielkarte gibErsteKarteAufTisch() {
        return gelegteKarten.get(gelegteKarten.size() - spielzugReihenfolge.size());
    }

    public int gibSpielerIndex() {
        return spielerIndex;
    }

    public int gibSpielerHatSauAusgerufen() {
        return spielerHatSauAusgerufen;
    }

    public boolean gibSauFarbeVorhandGespielt() {
        return sauFarbeVorhandGespielt;
    }

    public int gibTeamSpieler() {
        return teamSpieler;
    }

    public ArrayList<Spielkarte> gibAlleGelegteKarten() {
        return gelegteKarten;
    }

    public BotMitspielerDatenModel gibMitspielerDaten(int spielerNummer) {
        return mitspielerDaten[spielerNummer % 3];
    }



    public void setzeSauFarbeVorhandGespielt(boolean b) {
        sauFarbeVorhandGespielt = b;
    }

    public void setzteMitspielerHatOber(int spielerNummer, boolean istVorhanden) {
        mitspielerDaten[spielerNummer].setzteHatOber(istVorhanden);
    }

    public void setzteMitspielerHatUnter(int spielerNummer, boolean istVorhanden) {
        mitspielerDaten[spielerNummer].setzteHatUnter(istVorhanden);
    }

    public void setzteMitspielerHatSchellen(int spielerNummer, boolean istVorhanden) {
        mitspielerDaten[spielerNummer].setzteHatSchellen(istVorhanden);
    }

    public void setzteMitspielerHatHerz(int spielerNummer, boolean istVorhanden) {
        mitspielerDaten[spielerNummer].setzteHatHerz(istVorhanden);
    }

    public void setzteMitspielerHatEichel(int spielerNummer, boolean istVorhanden) {
        mitspielerDaten[spielerNummer].setzteHatEichel(istVorhanden);
    }

    public void setzteMitspielerHatGras(int spielerNummer, boolean istVorhanden) {
        mitspielerDaten[spielerNummer].setzteHatGras(istVorhanden);
    }

    public void setzteTeamSpieler(int nSpielerNummer) {
        teamSpieler = nSpielerNummer;
    }

    public void setzteSau(Spielkarte nSau) {
        sau = nSau;
    }

    public void setzteSpielArt(SpielArt nSpielArt) {
        spielArt = nSpielArt;
    }

    public void setzteSoloFarbe(Farbe nSoloFarbe) {
        soloFarbe = nSoloFarbe;
    }

    public void setzteHand(ArrayList<Spielkarte> nHand) {
        hand = nHand;
    }

    public void setzeSpielerIndex(int nSpielerNummer) {
        spielerIndex = nSpielerNummer;
    }

    public void setzeSpielerHatSauAusgerufen(int nSpielerNummer) {
        spielerHatSauAusgerufen = nSpielerNummer;
    }

    public void fuegeSpielerNummerGelegteKarteHinzu(int spielerNummer) {
        spielzugReihenfolge.add(spielerNummer);
    }

    public void entferneKarteAusHand(Spielkarte karte) {
        hand.remove(karte);

    }

    public void fuegeGelegteKarteHinzu(Spielkarte nKarte) {
        gelegteKarten.add(nKarte);
    }

    public void spielerNummerGelegteKarteZuruecksetzen() {
        spielzugReihenfolge.clear();
    }


}
