package InformatikProjekt;
import java.util.ArrayList;


public abstract class Mitspieler {
    
     
    //Fragt die Spielabsicht der Mitspieler ab. 
    abstract SpielKarte spielabsichtFragen(SpielArt höchsteSpiel);
    //Forder einen Mitspieler auf eine legale Karte zu legen. 
    abstract SpielKarte duBistDran(SpielArt spielArt);
    //Legt die Startwerte für eine neue Runde fest.
    abstract void rundeStarten(
            SpielKarte[] karten, int wieVielterSpieler
    );
    //Nachricht an Mitspieler welche Sau gesucht wird. (Senden nach dem finden der Spielart)
    abstract void karteGesucht(int spieler, SpielKarte Sau);
    //Nutzen nicht ersichtlich?
    abstract void ersterSpielerSetzen(int ersterSpieler);
    //Nachricht an Mitspieler welcher spieler die Runde gewonnen hat.
    abstract void rundeGewonnen(int spieler);
    //Nachricht an Mitspieler welche Karte von einem Mitspieler gelegt wurde(auch wenn der Mitspieler selbst die Karte gelegt hat).
    abstract void karteWurdeGelegt(SpielKarte karte, int spielerHatGelegt);

    //Methode für Spieler und Bot die eine ArrayList mit allen Karten die gelegt werden können zurück gibt.
    ArrayList<SpielKarte> legaleKarten(ArrayList<SpielKarte> hand, SpielArt spielArt)
    {

         ArrayList<SpielKarte> legaleKarten = new ArrayList<SpielKarte>();
        return legaleKarten;
    }
}
