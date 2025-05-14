package InformatikProjekt;

public abstract class Mitspieler {

    abstract  void rundeStarten(SpielKarte[] karten, int wieVielterSpieler);
    abstract SpielKarte spielabsichtFragen( SpielArt höchsteSpiel);
    abstract void ersterSpielerSetzen(int ersterSpieler);
    abstract SpielKarte duBistDran();
    abstract void rundeNeuStarten(SpielKarte[] karten);
    abstract void karteWurdeGelegt(SpielKarte Karte, int SpielerHatGelget);


}
