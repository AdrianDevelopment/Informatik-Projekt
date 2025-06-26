package Model;

import Controler.Mitspieler;
import Controler.Spieler;

public class RundeModel {
    private int[] punkte;
    private int ausrufer;
    private int positionSpieler;
    private Spielkarte[] aktuellerStich;
    private Spielkarte[] letzterStich;
    private SpielArt hoechsteSpielart;
    private Mitspieler ausruferReferenz;
    private int vorhand;
    private int[] sieger;
    private int wiederholungenRunden;
    private Spieler echterSpieler;
    private SpielArt aktuelleSpielArt;
    private int stichWiederholung;
    private int wiederholung;

    public RundeModel(int positionSpieler, int vorhand, int wiederholungenRunden, Spieler echterSpieler, Mitspieler ausruferReferenz) {
        this.punkte = new int[4];
        this.ausrufer = 0;
        this.positionSpieler = positionSpieler;
        this.aktuellerStich = new Spielkarte[4];
        this.letzterStich = new Spielkarte[4];
        this.hoechsteSpielart = SpielArt.KEINSPIEL;
        this.ausruferReferenz = ausruferReferenz;
        this.vorhand = vorhand;
        this.sieger = new int[2];
        this.wiederholungenRunden = wiederholungenRunden;
        this.echterSpieler = echterSpieler;
        this.aktuelleSpielArt = SpielArt.KEINSPIEL;
        this.stichWiederholung = 0;
        this.wiederholung = 0;
    }


    // Geber
    public int gebePunkte(int index) {
        return punkte[index];
    }

    public int[] gebePunkteArray() {
        return punkte;
    }

    public int gebeAusrufer() {
        return ausrufer;
    }

    public int gebePositionSpieler() {
        return positionSpieler;
    }

    public Spielkarte[] gebeAktuellerStichArray() {
        return aktuellerStich;
    }

    public SpielArt gebeHoechsteSpielart() {
        return hoechsteSpielart;
    }

    public Mitspieler gebeAusruferReferenz() {
        return ausruferReferenz;
    }

    public int gebeVorhand() {
        return vorhand;
    }

    public int gebeSieger(int index) {
        return sieger[index];
    }

    public int[] gebeSiegerArray() {
        return sieger;
    }

    public int gebeWiederholungenRunden() {
        return wiederholungenRunden;
    }

    public Spieler gebeEchterSpieler() {
        return echterSpieler;
    }

    public int gebeStichWiederholung(){
        return  stichWiederholung;
    }

    public int gebeWiederholung(){
        return  wiederholung;
    }


    // Setzer
    public void addierePunkte(int index, int punktzahl) {
        this.punkte[index] += punktzahl;
    }

    public void setzeAusrufer(int ausrufer) {
        this.ausrufer = ausrufer;
    }

    public void setzeAktuellenStich(int index, Spielkarte spielkarte) {
        this.aktuellerStich[index] = spielkarte;
    }

    public void setzeLetzterStich(Spielkarte[] aktuellerStich) {
        this.letzterStich = aktuellerStich.clone();
    }

    public void setzeHoechsteSpielart(SpielArt hoechsteSpielart) {
        this.hoechsteSpielart = hoechsteSpielart;
    }

    public void setzeAusruferReferenz(Mitspieler ausruferReferenz) {
        this.ausruferReferenz = ausruferReferenz;
    }

    public void setzeVorhand(int vorhand) {
        this.vorhand = vorhand;
    }

    public void setzeSiegerArray(int[] sieger) {
        this.sieger = sieger;
    }

    public void setzeAktuelleSpielArt(SpielArt aktuelleSpielArt) {
        this.aktuelleSpielArt = aktuelleSpielArt;
    }

    public void setzeStichWiederholung(int n){
         stichWiederholung = n;
    }

    public  void setzteWiederholung(int n){
        wiederholung = n;
    }


    // -------------------------------------------------------------------------------------------
    // Geber, die aktuell nicht benutz werden
    public Spielkarte gebeAktuellerStich(int index) {
        return aktuellerStich[index];
    }

    public Spielkarte gebeLetzterStichArray(int index) {
        return letzterStich[index];
    }

    public SpielArt gebeAktuelleSpielArt() {
        return aktuelleSpielArt;
    }


    // Setzer, die aktuell nicht benutz werden
    public void setzePunkte(int index, int punktzahl) {
        this.punkte[index] = punktzahl;
    }

    public void setzePositionSpieler(int positionSpieler) {
        this.positionSpieler = positionSpieler;
    }

    public void setzeSieger(int index, int sieger) {
        this.sieger[index] = sieger;
    }

    public void setzeWiederholungenRunden(int wiederholungenRunden) {
        this.wiederholungenRunden = wiederholungenRunden;
    }

    public void setzeEchterSpieler(Spieler echterSpieler) {
        this.echterSpieler = echterSpieler;
    }
}
