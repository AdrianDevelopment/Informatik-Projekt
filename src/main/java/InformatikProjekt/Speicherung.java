package InformatikProjekt;
// Speicherlogik von Robin

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
public class Speicherung {
    /**
     *
     * @return
     */
    private int gewonneneSpiele;
    private int verloreneSpiele;
    private int verloreneSpieleSchneider;
    private int gespielteSpiele;
    private int[] gespielteModi;
    private int[] gewonneneModi;
    private int[] verloreneModi;
    private int[] verloreneModiSchneider;
    private int gesamtePunkte;
    private int gespielteKarten;

    public void KarteGespielt(){gespielteKarten++;}
    public void gesamtePunkteErhöhen(int punkte){gesamtePunkte += punkte;}
    public void SpielGewonnen(SpielArt art){
        gespielteSpiele++;
        gewonneneSpiele++;
        gespielteModi[art.gebeSpielArtID() - 1]++;
        gewonneneModi[art.gebeSpielArtID() - 1]++;
    }
    public void SpielVerloren(SpielArt art){
        verloreneSpiele++;
        gespielteSpiele++;
        gespielteModi[art.gebeSpielArtID() - 1]++;
        verloreneModi[art.gebeSpielArtID() - 1]++;
    }
    public void SpielVerlorenSchneider(SpielArt art){
        verloreneSpieleSchneider++;
        verloreneModiSchneider[art.gebeSpielArtID() - 1]++;
    }
    public int modusSpielzahlGeben(SpielArt art){
        return gespielteModi[art.gebeSpielArtID() - 1];
    }
    public int modusGewinneGeben(SpielArt art){
        return gewonneneModi[art.gebeSpielArtID() - 1];
    }
    public int modusNiederlagenGeben(SpielArt art){
        return verloreneModi[art.gebeSpielArtID() - 1];
    }
    public int modusNiederlagenSchneiderGeben(SpielArt art){
        return verloreneModiSchneider[art.gebeSpielArtID() - 1];
    }
    public int gespielteKartenGeben(){
        return gespielteKarten;
    }
    public int gesamtePunkteGeben(){
        return gesamtePunkte;
    }
    public int gespielteSpieleGeben(){
        return gespielteSpiele;
    }
    public int verloreneSpieleSchneiderGeben(){
        return verloreneSpieleSchneider;
    }
    public int verloreneSpieleGeben(){
        return verloreneSpiele;
    }
    public int gewonneneSpieleGeben(){
        return gewonneneSpiele;
    }
    private void zahlenArraysInitialisieren(){
        gespielteModi = new int[3];
        gewonneneModi = new int[3];
        verloreneModi = new int [3];
        verloreneModiSchneider = new int [3];
    }
    private static void ArraySetzenAuf(int array[], int zahl){
        for (int i=0;i<array.length;++i){
            array[i]=zahl;
        }
    }
    private void zurücksetzen(){
        gewonneneSpiele = 0;
        gespielteSpiele = 0;
        verloreneSpiele = 0;
        verloreneSpieleSchneider = 0;
        gesamtePunkte = 0;
        gespielteKarten = 0;
        zahlenArraysInitialisieren();
        ArraySetzenAuf(gespielteModi,0);
        ArraySetzenAuf(gewonneneModi,0);
        ArraySetzenAuf(verloreneModi,0);
        ArraySetzenAuf(verloreneModiSchneider,0);
    }
    private int zahlLesen(FileInputStream fis) throws IOException {
            int b1 = fis.read();
            int b2 = fis.read();
            int b3 = fis.read();
            int b4 = fis.read();
            return (b4 << 24) | (b3 << 16) | (b2 << 8) | b1;
    }
    private void zahlArrayLesen(FileInputStream fis, int array[]) 
        throws IOException{
        for (int i=0;i<array.length;++i){
            array[i] = zahlLesen(fis);
        }
    }
    private void zahlSchreiben(FileOutputStream fos, int zahl) throws IOException{
        fos.write(zahl & 0xFF);
        fos.write((zahl >> 8) & 0xFF);
        fos.write((zahl >> 16) & 0xFF);
        fos.write((zahl >> 24) & 0xFF);
    }
    private void zahlArraySchreiben(FileOutputStream fos, int zahlen[])
        throws IOException{
        for (int i=0;i<zahlen.length;++i){
            zahlSchreiben(fos, zahlen[i]);
        }
    }
    Speicherung(){
        FileInputStream fis;
        try{
            fis = new FileInputStream("statistiken.dat");
        }catch (FileNotFoundException e){
            zurücksetzen();
            return;
        }
        try{
            gewonneneSpiele = zahlLesen(fis);
            gespielteSpiele = zahlLesen(fis);
            verloreneSpiele = zahlLesen(fis);
            verloreneSpieleSchneider = zahlLesen(fis);
            gesamtePunkte = zahlLesen(fis);
            gespielteKarten = zahlLesen(fis);
            zahlenArraysInitialisieren();
            zahlArrayLesen(fis,gespielteModi);
            zahlArrayLesen(fis,gewonneneModi);
            zahlArrayLesen(fis,verloreneModi);
            zahlArrayLesen(fis,verloreneModiSchneider);
        }catch (IOException e){
            zurücksetzen();
            try{
                fis.close();
            }catch(IOException e2){
                
            }
            return;
        }
        try{
            fis.close();
        }catch(IOException e){
            
        }
    }
    public void DatenSpeichern(){
        FileOutputStream fos;
        try{
            fos = new FileOutputStream("statistiken.dat");
        }catch(FileNotFoundException e){
            return;
        }
        try{
            zahlSchreiben(fos, gewonneneSpiele); // Spiele: Tunier -> Runden sollten auch gespeichert werden
            zahlSchreiben(fos, gespielteSpiele);
            zahlSchreiben(fos, verloreneSpiele);
            zahlSchreiben(fos, verloreneSpieleSchneider); // bisher nicht implementiert
            zahlSchreiben(fos, gesamtePunkte);
            zahlSchreiben(fos, gespielteKarten);
            zahlArraySchreiben(fos, gespielteModi);
            zahlArraySchreiben(fos, gewonneneModi);
            zahlArraySchreiben(fos, verloreneModi);
            zahlArraySchreiben(fos, verloreneModiSchneider);
        }catch (IOException e){
            
        }
        try{
            fos.close();
        }catch(IOException e){
            
        }
    }

}
