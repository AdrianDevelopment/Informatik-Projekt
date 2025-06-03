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
    //von 0 bis 4 speichert wie viele Karten in dem Spiel gelegt wurden.
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

    public int gibGelegteKartenAnzahl() {
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

    public boolean gebeSauFarbeVorhandGespielt() {
        return sauFarbeVorhandGespielt;
    }

    public BotMitspielerDatenModel gibMitspielerDaten(int spielerNummer) {
        return mitspielerDaten[spielerNummer % 3];
    }


    public void setzeSauFarbeVorhandGespielt(boolean b) {
        sauFarbeVorhandGespielt = b;
    }

    public void setzteMitspielerDaten(Farbe farbe, Werte werte, boolean vorhanden, int spielerNr) {
        switch (farbe) {
            case SCHELLEN:
                mitspielerDaten[spielerNr % 3].setzteHatSchellen(vorhanden);
                break;
            case HERZ:
                mitspielerDaten[spielerNr % 3].setzteHatHerz(vorhanden);
                break;
            case GRAS:
                mitspielerDaten[spielerNr % 3].setzteHatGras(vorhanden);
                break;
            case EICHEL:
                mitspielerDaten[spielerNr % 3].setzteHatEichel(vorhanden);
                break;
        }
        switch (werte) {
            case SAU:
                break;
            case ZEHNER:
                break;
            case KOENIG:
                break;
            case OBER:
                mitspielerDaten[spielerNr % 3].setzteHatOber(vorhanden);
                break;
            case UNTER:
                mitspielerDaten[spielerNr % 3].setzteHatUnter(vorhanden);
                break;
            case NEUNER:
                break;
            case ACHTER:
                break;
            case SIEBENER:
                break;
        }

    }

    public int gibWertFuerBisherGelegteKarten() {
        int gesamtWert = 0;
        for (Spielkarte karte : gelegteKarten.subList(gelegteKarten.size() - spielzugReihenfolge.size(), gelegteKarten.size())) {
            gesamtWert += karte.gebeWert().gebePunktzahl();
        }
        return gesamtWert;
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

    public void fuegeGelegteKarteHinzu(Spielkarte nKarte) {
        gelegteKarten.add(nKarte);
    }

    public void setzteHand(ArrayList<Spielkarte> nHand) {
        hand = nHand;
    }

    public void entferneKarteAusHand(Spielkarte karte) {
        hand.remove(karte);

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

    public void spielerNummerGelegteKarteZuruecksetzen() {
        spielzugReihenfolge.clear();
    }

    public int[] wieVieleBesondereKarten() {
        int anzahlOber = 0;
        int anzahlUnter = 0;
        int anzahlSau = 0;
        int anzahlHerz = 0;
        int anzahlGras = 0;
        int anzahlEichel = 0;
        int anzahlSchellen = 0;

        for (Spielkarte karte : hand) {
            if (karte.gebeWert() == Werte.OBER) {
                anzahlOber++;
            } else if (karte.gebeWert() == Werte.UNTER) {
                anzahlUnter++;
            } else {
                if (karte.gebeWert() == Werte.SAU) {
                    anzahlSau++;
                }
                switch (karte.gebeFarbe()) {
                    case SCHELLEN:
                        anzahlSchellen++;
                        break;
                    case HERZ:
                        anzahlHerz++;
                        break;
                    case GRAS:
                        anzahlGras++;
                        break;
                    case EICHEL:
                        anzahlEichel++;
                        break;
                }
            }


        }
        return new int[]{anzahlOber, anzahlUnter, anzahlSau, anzahlHerz, anzahlGras, anzahlEichel, anzahlSchellen};
    }


}
