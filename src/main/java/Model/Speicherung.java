package Model;
// Speicherlogik von Robin

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Locale;

public class Speicherung {
    /**
     *
     * @return
     */
    // Speichert ein globales Speicherung-Objekt zwischen ==> Singleton
    // Mehrfaches erstellen führt zu Problemen beim Zugriff auf die Datei
    private static Speicherung speicherung = null;
    public static Speicherung speicherungErstellen(){
        if (speicherung==null)speicherung = new Speicherung();
        return  speicherung;
    }
    // alle Statistiken als Attribute
    private int gewonneneSpiele;
    private int verloreneSpiele;
    private int verloreneSpieleSchneider;
    private int gespielteSpiele;
    // Index ist jeweils SpielArt.gebeSpielArtID()
    private int[] gespielteModi;
    private int[] gewonneneModi;
    private int[] verloreneModi;
    private int[] verloreneModiSchneider;
    private int gesamtePunkte;
    private int gespielteKarten;
    private int gespielteTurniere;
    private int gewonneneTurniere;
    private int verloreneTurniere;
    private int hoechstePunktzahlRunde;
    private boolean datenGeaendert;
    private ArrayList<TurnierStatistik> alteTurniere;
    private int gewonneneSpieleSchneider;
    private int[] gewonneneModiSchneider;

    // Setter setzen datenGeaendert auf true um DatenSpeichern zu informieren
    public void KarteGespielt(){
        gespielteKarten++;
        datenGeaendert = true;
    }
    public void gesamtePunkteErhoehen(int punkte){
        gesamtePunkte += punkte;
        datenGeaendert = true;
    }
    public void TurnierGewonnen(){
        gewonneneTurniere++;
        gespielteTurniere++;
        datenGeaendert = true;
    }
    public void TurnierVerloren(){
        verloreneTurniere++;
        gespielteTurniere++;
        datenGeaendert = true;
    }
    
    public void TurnierZuEnde(int punkte, boolean gewonnen){
        alteTurniere.add(new TurnierStatistik(punkte,gewonnen));
        datenGeaendert = true;
    }
    
    public void RundePunktzahlMelden(int punkte){
        if (punkte>hoechstePunktzahlRunde){
            hoechstePunktzahlRunde=punkte;
            datenGeaendert = true;
        }
    }
    public void SpielGewonnen(SpielArt art){
        gespielteSpiele++;
        gewonneneSpiele++;
        gespielteModi[art.gebeSpielArtID() - 1]++;
        gewonneneModi[art.gebeSpielArtID() - 1]++;
        datenGeaendert = true;
    }
    public void SpielVerloren(SpielArt art){
        verloreneSpiele++;
        gespielteSpiele++;
        gespielteModi[art.gebeSpielArtID() - 1]++;
        verloreneModi[art.gebeSpielArtID() - 1]++;
        datenGeaendert = true;
    }
    public void SpielVerlorenSchneider(SpielArt art){
        verloreneSpieleSchneider++;
        verloreneModiSchneider[art.gebeSpielArtID() - 1]++;
        datenGeaendert = true;
    }
    public void SpielGewonnenSchneider(SpielArt art){
        gewonneneSpieleSchneider++;
        gewonneneModiSchneider[art.gebeSpielArtID() - 1]++;
        datenGeaendert = true;
    }
    public ArrayList<TurnierStatistik> gebeAlteTurnierstatistiken(){
        return alteTurniere;
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
    public int modusGewinneSchneiderGeben(SpielArt art){ return gewonneneModiSchneider[art.gebeSpielArtID() - 1];}
    public int gespielteKartenGeben(){
        return gespielteKarten;
    }
    public int gesamtePunkteGeben(){
        return gesamtePunkte;
    }
    public int gespielteSpieleGeben(){
        return gespielteSpiele;
    }
    public int gewonneneSpieleSchneiderGeben(){
        return gewonneneSpieleSchneider;
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
    public int gespielteTurniereGeben(){
        return gespielteTurniere;
    }
    public int verloreneTurniereGeben(){
        return verloreneTurniere;
    }
    public int gewonneneTurniereGeben(){
        return  gewonneneTurniere;
    }
    public int hoechstePunktzahlRundeGeben(){
        return  hoechstePunktzahlRunde;
    }
    private void zahlenArraysInitialisieren(){
        // Initialisiert Arrays sodass keine NullPointerException beim Zugriff entsteht
        gespielteModi = new int[3];
        gewonneneModi = new int[3];
        verloreneModi = new int [3];
        verloreneModiSchneider = new int [3];
        alteTurniere = new ArrayList<TurnierStatistik>();
        gewonneneModiSchneider = new int[3];
    }
    private static void ArraySetzenAuf(int array[], int zahl){
        // Füllt ein Array array mit dem Wert zahl
        for (int i=0;i<array.length;++i){
            array[i]=zahl;
        }
    }
    private void zurücksetzen(){
        // Setzt alle Werte auf Standardwerte wenn das Laden fehlschlägt oder die Datei nicht vorhanden ist
        gewonneneSpiele = 0;
        gespielteSpiele = 0;
        verloreneSpiele = 0;
        verloreneSpieleSchneider = 0;
        gesamtePunkte = 0;
        gespielteKarten = 0;
        gewonneneTurniere = 0;
        verloreneTurniere = 0;
        gespielteTurniere = 0;
        hoechstePunktzahlRunde = 0;
        zahlenArraysInitialisieren();
        ArraySetzenAuf(gespielteModi,0);
        ArraySetzenAuf(gewonneneModi,0);
        ArraySetzenAuf(verloreneModi,0);
        ArraySetzenAuf(verloreneModiSchneider,0);
        alteTurniere = new ArrayList<TurnierStatistik>();
        ArraySetzenAuf(gewonneneModiSchneider,0);
        gewonneneSpieleSchneider = 0;
        datenGeaendert = true;
    }
    private int zahlLesenMindestVersion(FileInputStream fis,
                                       int versionAktuell,
                                       int versionMindestens,
                                       int standard) throws IOException{
        // Liest eine Zahl wenn die Dateiversion mindestens versionMindestens ist oder gibt standard zurück
        return (versionAktuell>=versionMindestens?zahlLesen(fis):standard);
    }
    private void zahlArrayLesenMindestVersion(FileInputStream fis,
                                              int versionAktuell,
                                              int versionMindestens,
                                              int[] array,
                                              int standard) throws IOException{
        // Liest ein Array wenn die Dateiversion versionMindestens ist oder setzt alle Einträge auf standard
        if (versionAktuell>=versionMindestens)zahlArrayLesen(fis,array);
        else ArraySetzenAuf(array,standard);
    }
    private int zahlLesen(FileInputStream fis) throws IOException {
        // liest einen int als 4 bytes
            int b1 = fis.read();
            int b2 = fis.read();
            int b3 = fis.read();
            int b4 = fis.read();
            return (b4 << 24) | (b3 << 16) | (b2 << 8) | b1;
    }
    private void zahlArrayLesen(FileInputStream fis, int array[]) 
        throws IOException{
        // liest einen vorher erstellten array von zahlen mit array.length
        for (int i=0;i<array.length;++i){
            array[i] = zahlLesen(fis);
        }
    }
    private void zahlSchreiben(FileOutputStream fos, int zahl) throws IOException{
        // schreibt eine 4-byte Zahl die von zahlLesen gelesen wird
        fos.write(zahl & 0xFF);
        fos.write((zahl >> 8) & 0xFF);
        fos.write((zahl >> 16) & 0xFF);
        fos.write((zahl >> 24) & 0xFF);
    }
    private void zahlArraySchreiben(FileOutputStream fos, int zahlen[])
        throws IOException{
        // schreibt ein Zahlenarray das von zahlArrayLesen gelesen wird
        // zahlen muss beim schreiben dieselbe Länge haben wie array beim lesen
        for (int i=0;i<zahlen.length;++i){
            zahlSchreiben(fos, zahlen[i]);
        }
    }
    private Speicherung() {
        // privater Konstruktor, der die Daten beim erstellen einliest oder auf Standardwerte setzt.
        // wird von SpeicherungErstellen aufgerufen
        FileInputStream fis;
        try {
            fis = new FileInputStream("statistiken.dat");
        } catch (FileNotFoundException e) {
            zurücksetzen();
            return;
        }
        try {
            // Dateiversionen ermöglichen das laden von Spielständen aus vorherigen Spielversionen
            // Dateien sind sowohl aufwärts- als auch abwärtskompatibel :
            // Felder neuerer Versionen stehen am Dateiende und werden ignoriert
            // Durch alte Versionen fehlende Felder werden mit Standardwerten initialisiert
            int version = zahlLesen(fis);
            gewonneneSpiele = zahlLesen(fis);
            gespielteSpiele = zahlLesen(fis);
            verloreneSpiele = zahlLesen(fis);
            verloreneSpieleSchneider = zahlLesen(fis);
            gesamtePunkte = zahlLesen(fis);
            gespielteKarten = zahlLesen(fis);
            gespielteTurniere = zahlLesen(fis);
            gewonneneTurniere = zahlLesen(fis);
            verloreneTurniere = zahlLesen(fis);
            hoechstePunktzahlRunde = zahlLesen(fis);
            zahlenArraysInitialisieren();
            zahlArrayLesen(fis, gespielteModi);
            zahlArrayLesen(fis, gewonneneModi);
            zahlArrayLesen(fis, verloreneModi);
            zahlArrayLesen(fis, verloreneModiSchneider);
            if (version>=1){
                // Turnierhistorie einlesen
                int anzalTurniere = zahlLesen(fis);
                alteTurniere.ensureCapacity(anzalTurniere);
                for (int i=0;i<anzalTurniere;++i){
                    alteTurniere.add(new TurnierStatistik(
                            zahlLesen(fis),
                            fis.read() == 1,
                            zahlLesen(fis),
                            fis.read(),
                            fis.read(),
                            fis.read(),
                            fis.read()
                    ));
                }
            }
            zahlArrayLesenMindestVersion(fis,version,2,gewonneneModiSchneider,0);
            gewonneneSpieleSchneider = zahlLesenMindestVersion(fis,version,2,0);
            if (version==2)datenGeaendert = false;
            else datenGeaendert = true; // Version muss aktualisiert werden
        } catch (IOException e) {
            zurücksetzen();
            try {
                fis.close();
            } catch (IOException e2) {

            }
            return;
        }
        try {
            fis.close();
        } catch (IOException e) {

        }
    }
    public void DatenSpeichern(){
        if (!datenGeaendert)return; // kein Speichervorgang nötig wenn es keine Änderungen gibt
        FileOutputStream fos;
        try{
            fos = new FileOutputStream("statistiken.dat");
        }catch(FileNotFoundException e){
            return;
        }
        try{
            zahlSchreiben(fos, 2); // Dateiversion
            // Siehe Kommentar in Konstruktor
            zahlSchreiben(fos, gewonneneSpiele);
            zahlSchreiben(fos, gespielteSpiele);
            zahlSchreiben(fos, verloreneSpiele);
            zahlSchreiben(fos, verloreneSpieleSchneider);
            zahlSchreiben(fos, gesamtePunkte);
            zahlSchreiben(fos, gespielteKarten);
            zahlSchreiben(fos, gespielteTurniere);
            zahlSchreiben(fos, gewonneneTurniere);
            zahlSchreiben(fos, verloreneTurniere);
            zahlSchreiben(fos, hoechstePunktzahlRunde);
            zahlArraySchreiben(fos, gespielteModi);
            zahlArraySchreiben(fos, gewonneneModi);
            zahlArraySchreiben(fos, verloreneModi);
            zahlArraySchreiben(fos, verloreneModiSchneider);
            zahlSchreiben(fos, alteTurniere.size());
            for (int i=0;i<alteTurniere.size();++i){
                // Turniere speichern : punkte, gewonnen, jahr, monat, tag, stunde, minute
                TurnierStatistik aktuellesTurnier = alteTurniere.get(i);
                zahlSchreiben(fos,aktuellesTurnier.punkteGeben());
                if (aktuellesTurnier.wurdeGewonnen()){
                    fos.write(1);
                }else{
                    fos.write(0);
                }
                LocalDate datum = aktuellesTurnier.DatumGeben();
                zahlSchreiben(fos,datum.getYear());
                fos.write(datum.getMonthValue());
                fos.write(datum.getDayOfMonth());
                LocalTime zeit = aktuellesTurnier.ZeitGeben();
                fos.write(zeit.getHour());
                fos.write(zeit.getMinute());
            }
            zahlArraySchreiben(fos,gewonneneModiSchneider);
            zahlSchreiben(fos,gewonneneSpieleSchneider);
            datenGeaendert = false;
        }catch (IOException e){
            
        }
        try{
            fos.close();
        }catch(IOException e){
            
        }
    }
    @Override
    protected void finalize() throws Throwable{
        // wird beim Zerstören aufgerufen
        DatenSpeichern();
        super.finalize();
    }
}
