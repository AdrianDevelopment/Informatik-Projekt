//Programmierer: Tim
package Model;

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

    public BotModel() {
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

    public boolean hatAusgerufeneSau(){
        for(Spielkarte karte : hand){
            if (karte == sau){
                return  true;
            }
        }
        return  false;
    }

    public int gibStichGelegteKartenAnzahl() {
        return spielzugReihenfolge.size();
    }
    public ArrayList<Integer> gibSpielerDieImStichGelegtHaben() {
        return spielzugReihenfolge;
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

    public void setzeMitspielerHatOber(int spielerNummer, boolean istVorhanden) {
        mitspielerDaten[spielerNummer].setzeHatOber(istVorhanden);
    }

    public void setzeMitspielerHatUnter(int spielerNummer, boolean istVorhanden) {
        mitspielerDaten[spielerNummer].setzeHatUnter(istVorhanden);
    }

    public void setzeMitspielerHatSchellen(int spielerNummer, boolean istVorhanden) {
        mitspielerDaten[spielerNummer].setzeHatSchellen(istVorhanden);
    }

    public void setzeMitspielerHatHerz(int spielerNummer, boolean istVorhanden) {
        mitspielerDaten[spielerNummer].setzeHatHerz(istVorhanden);
    }

    public void setzeMitspielerHatEichel(int spielerNummer, boolean istVorhanden) {
        mitspielerDaten[spielerNummer].setzeHatEichel(istVorhanden);
    }

    public void setzeMitspielerHatGras(int spielerNummer, boolean istVorhanden) {
        mitspielerDaten[spielerNummer].setzeHatGras(istVorhanden);
    }

    public void setzeTeamSpieler(int nSpielerNummer) {
        teamSpieler = nSpielerNummer;
    }

    public void setzeSau(Spielkarte nSau) {
        sau = nSau;
    }

    public void setzeSpielArt(SpielArt nSpielArt) {
        spielArt = nSpielArt;
    }

    public void setzeSoloFarbe(Farbe nSoloFarbe) {
        soloFarbe = nSoloFarbe;
    }

    public void setzeHand(ArrayList<Spielkarte> nHand) {
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
