//Programmierer: Tim
package InformatikProjekt;

import java.util.ArrayList;

public class BotModel {
    private ArrayList<Spielkarte> hand;
    private Spielkarte sau;
    private SpielArt spielArt;
    private Farbe soloFarbe;
    private ArrayList<Spielkarte> gelegteKarten;
    private int spielerIndex;

    private ArrayList<Integer> spielzugReihenfolge;
    private BotMitspielerDatenModel[] mitspielerDaten;
    private int spielerHatSauAusgerufen;
    private int teamSpieler;
    private boolean sauFarbeVorhandGespielt;

    BotModel() {
        spielzugReihenfolge = new ArrayList<Integer>();
        gelegteKarten = new ArrayList<Spielkarte>();
        teamSpieler = -1;
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


    //Todo name ändern
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

    ;

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
