package InformatikProjekt;

public abstract class Mitspieler {

    abstract  void  RundeStarten(
            SpielKarte[] karten, int wieVielterSpieler
    );
    abstract SpielKarte Spielabsichtfragen( SpielArt höchsteSpiel);
    abstract void ersterSpielerSetzen(int ersterSpieler);
    abstract SpielKarte DuBistDran();
    abstract void RundeNeuStarten(SpielKarte[] karten);
    abstract void KarteWurdeGelegt(SpielKarte Karte, int SpielerHatGelget);


}
