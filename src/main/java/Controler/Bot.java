//Programmierer: Tim
package Controler;

import Model.*;

import java.util.ArrayList;
import java.util.Random;

/*
       Logik der Methoden von dem Override der Methoden der abstrakten Klasse getrennt, damit diese einfach getestet werden können.
   */
public class Bot extends Mitspieler {
    BotModel model;
    Runde runde;

    public Bot() {
        model = new BotModel();
    }

    @Override
    public void setzeRunde(Runde runde) {
        this.runde = runde;
    }

    /*
         Bestimmt, ob es sich lohnt ein Spiel auszurufen oder nicht.
         Betrachtet wie viele Trümpfe und Karten einer Farbe auf der Hand sind.
         Wenn eine bestimmte Anzahl von Trümpfen auf der Hand sind, wird ein Spiel ausgerufen.
         Sonst wird kein Spiel zurückgegeben.
    */
    @Override
    public void spielabsichtFragen(int wiederholung, SpielArt hoechsteSpiel, int vorhand) {
        runde.spielabsichtFragenAufgerufen(wiederholung, vorhand, spielAbsichtWaehlen(hoechsteSpiel));
    }

    public SpielArt spielAbsichtWaehlen(SpielArt hoechsteSpiel) {
        //Bestimmt wieviele und welche Art von Karten auf der Hand sind.

        int[] besondereKarten = wieVieleBesondereKarten();
        int anzahlOU = besondereKarten[0] + besondereKarten[1];
        int indexFarbeMitMeistenKarten = besondereKarten[3];
        for (int i = 3; i <= 6; i++) {
            if (besondereKarten[indexFarbeMitMeistenKarten] < besondereKarten[i]) {
                indexFarbeMitMeistenKarten = i;
            }

        }
        //Außerhalb des Zieles des Prototypes
        /*
        //Solospiel wenn mindestens mehr als 4 Truempfe und mindestendes 2 Karten einer Farbe(keine Truempfe).
        if (anzahlOU >= 4 &&besondereKarten[indexFarbeMitMeistenKarten] >= 2 && hoechsteSpiel.gebeSpielArtID() < SpielArt.SOLO.gebeSpielArtID()){
            Farbe[] farben = new Farbe[4];
            farben[0] = Farbe.HERZ;
            farben[1] = Farbe.GRAS;
            farben[2] = Farbe.EICHEL;
            farben[3] = Farbe.SCHELLEN;
            model.setzteSoloFarbe(farben[indexFarbeMitMeistenKarten -3]);
            return SpielArt.SOLO;
        }

        //Wenz wenn mindestens 3 Unter und 2 Asse
        if (besondereKarten[1] >=3 && besondereKarten[2] >=2 && hoechsteSpiel.gebeSpielArtID() < SpielArt.WENZ.gebeSpielArtID()){
            return SpielArt.WENZ;
        }
        */

        //Bestimmung welche Sau farben ausgerufen werden können.
        ArrayList<Farbe> farbenZumAusrufen = sauZumAusrufen(model.gibHand());
        //Sauspiel, wenn eine Sau ausgerufen werden kann und mindestens 4 Trümpfe auf der Hand sind.
        if (!farbenZumAusrufen.isEmpty() && anzahlOU + besondereKarten[4] >= 4 && hoechsteSpiel.gebeSpielArtID() < SpielArt.SAUSPIEL.gebeSpielArtID()) {
            //Speichert die Sau die Ausgerufen werden soll im Model ab.
            //Die Methode farbenZumAusrufen gibt die beste Sau zum Ausrufen an Stelle 0 zurück.
            model.setzteSau(new Spielkarte(farbenZumAusrufen.get(0), Werte.SAU));
            return SpielArt.SAUSPIEL;
        }

        return SpielArt.KEINSPIEL;
    }


    // Gibt die Farbe für die Spielabsicht entweder die Farbe für ein Solo oder die ausgerufene Sau.
    @Override
    public void farbeFuerSpielAbsicht(SpielArt spielArt) {
        runde.farbeFuerSpielAbsichtAufgerufen(farbeFuerSpielAbsichtGewaehlt(spielArt));
    }

    public Farbe farbeFuerSpielAbsichtGewaehlt(SpielArt spielArt) {


        switch (spielArt) {
            case KEINSPIEL:
                break;
            case SAUSPIEL:
                return model.gibSau().gebeFarbe();
            case WENZ:
                break;
            case SOLO:
                return model.gibsoloFarbe();
            default:
                System.out.println("DEBUG: Spielart: "+ spielArt+" wird nicht behandelt");
                break;
        }
        return null;
    }

    /*
        Wählt je nach Spielart eine Karte aus.
        Dafür werden zuerst alle Karten bestimmt, die nach den Regeln gelegt werden könnten.
        Danach wird eine der erlaubten Karten nach einem System ausgewählt.
     */
    @Override
    public void legeEineKarte(int wiederholung, int vorhand) {
        runde.karteAbfragenAufgerufen(waehleEineKarte());
    }

    public Spielkarte waehleEineKarte() {
        ArrayList<Spielkarte> moeglicheKarten = new ArrayList<>();
        //Überprüfen, ob es der Beginn des Stiches ist und demanch erlaubte Karten bestimmen.
        if (model.gibStichGelegteKartenAnzahl() == 0) {
            moeglicheKarten = erlaubteKartenAusspielenBeiSauspiel(model.gibHand(), model.gibSau());
        } else {
            moeglicheKarten = gibErlaubteKarten((ArrayList<Spielkarte>) model.gibHand().clone(), model.gibSpielArt(), model.gibSau(), model.gibErsteKarteAufTisch(), model.gibsoloFarbe(), model.gibSauFarbeVorhandGespielt());

        }
        Spielkarte gewaelteKarte = moeglicheKarten.get(0);
        //Wahl der Karte nach Spielart unterscheiden.
        switch (model.gibSpielArt()) {
            case KEINSPIEL:
                System.out.println("DEBUG: Spielart: "+ model.gibSpielArt()+" wird nicht behandelt");
                break;
            case SAUSPIEL:
                gewaelteKarte = sauSpielKarteWaehlen(moeglicheKarten);
            case WENZ:
                System.out.println("DEBUG: Spielart: "+ model.gibSpielArt()+" wird nicht behandelt");
                break;
            case SOLO:
                System.out.println("DEBUG: Spielart: "+ model.gibSpielArt()+" wird nicht behandelt");
                break;
            default:
                System.out.println("DEBUG: Spielart: "+ model.gibSpielArt()+" wird nicht behandelt");
                break;
        }
        //Gewählte Karte aus Hand entfernen und zurückgeben.
        model.entferneKarteAusHand(gewaelteKarte);
        return gewaelteKarte;
    }

    /*
        System zur Bestimmung welche Karte bei einem Sauspiel gelegt werden soll.
        Für den Protypen wird eine zufällige Karte ausgewaählt.
     */
    public Spielkarte sauSpielKarteWaehlen(ArrayList<Spielkarte> erlaubteKarten) {
        Random zufall = new Random();
        int zufaelligerIndex = zufall.nextInt(erlaubteKarten.size());
        if(erlaubteKarten.size() == 1){
            return  erlaubteKarten.get(0);
        }
        //Legt die Farbe der gesuchten Sau auf Vorhand, sofern der Teamspieler unbekannt ist und man nicht der Ausrufer oder der Sau besitzer ist.
        //Ziel ist es den Mitspieler zu finden, damit intelligenter gespielt werden kann(z.b. schmieren)
        if (model.gibStichGelegteKartenAnzahl() == 0){
            if(model.gibTeamSpieler() == -1 && model.gibSpielerHatSauAusgerufen() != model.gibSpielerIndex()){
                Spielkarte niedrigsteKarteVonSauFarbe = null;
                for(Spielkarte karte: erlaubteKarten){
                    if(karte.istTrumpf(SpielArt.SAUSPIEL, null)&&karte.gebeFarbe() == model.gibSau().gebeFarbe()){

                        if(niedrigsteKarteVonSauFarbe == null || karte.gebeWert().gebePunktzahl()< niedrigsteKarteVonSauFarbe.gebeWert().gebePunktzahl()){
                            niedrigsteKarteVonSauFarbe = karte;
                        }
                    }
                }
                if (niedrigsteKarteVonSauFarbe != null){
                    return niedrigsteKarteVonSauFarbe;
                }
            }
            return erlaubteKarten.get(zufaelligerIndex);
        }


        int spielerGewinntStich = gibSpielerDerStichMomentanGewinnt();

        int kartenStaerkeVonStichGewinner = spielKartenStaerkeSauSpiel(model.gibAlleGelegteKarten().get(model.gibAlleGelegteKarten().size() - model.gibStichGelegteKartenAnzahl() + model.gibSpielerDieImStichGelegtHaben().indexOf(spielerGewinntStich)));


        //model.gibAlleGelegteKarten().stream().filter(karte -> karte.gebeWert() == Werte.OBER).count();

        //Entscheiden, ob Farbe oder Sau gespielt wird.
        int mindesAnforderungFuerSchmieren = 0;
        if(model.gibErsteKarteAufTisch().istTrumpf(SpielArt.SAUSPIEL,null)){
            mindesAnforderungFuerSchmieren=200;
        }else{
            mindesAnforderungFuerSchmieren=80;
        }

        //Überprüft, ob es Sinn macht für den Mitspieler zu schmieren.
        boolean mitspielerAlleinigTrumpf = false;
        ArrayList<Integer> gegenSpielerIndex = new ArrayList<>();
        if(model.gibTeamSpieler() >= 0){
            gegenSpielerIndex.add(0);
            gegenSpielerIndex.add(1);
            gegenSpielerIndex.add(2);
            gegenSpielerIndex.add(3);
            gegenSpielerIndex.removeIf(n -> n == model.gibSpielerIndex() || n == model.gibTeamSpieler());
            mitspielerAlleinigTrumpf = model.gibMitspielerDaten(model.gibTeamSpieler()).gebeHatTrumpf() && !model.gibMitspielerDaten(gegenSpielerIndex.get(0)).gebeHatTrumpf() && !model.gibMitspielerDaten(gegenSpielerIndex.get(1)).gebeHatTrumpf();
        }
        //wenn nach der Sau gesucht wird, soll versucht werden den Stich zu gewinnen(ein Trumpf legen)
        if(model.gibErsteKarteAufTisch().gebeFarbe() == model.gibSau().gebeFarbe() && !model.gibSauFarbeVorhandGespielt()){
            Spielkarte niedrigsterTrumpf = null;
            //sucht den niedrigsten Trumpf
            for(Spielkarte karte : erlaubteKarten){
                if(niedrigsterTrumpf == null  && karte.istTrumpf(SpielArt.SAUSPIEL, null)){
                    niedrigsterTrumpf = karte;
                }else if( niedrigsterTrumpf != null && karte.istTrumpf(SpielArt.SAUSPIEL, null)&& spielKartenStaerkeSauSpiel(karte) < spielKartenStaerkeSauSpiel(niedrigsterTrumpf)){
                    niedrigsterTrumpf = karte;
                }
            }
            if (niedrigsterTrumpf != null){
                System.out.println("DEBUG: Der Bot sticht die Ausgerufene Sau.");
                return  niedrigsterTrumpf;
            }
        }
        if ((spielerGewinntStich == model.gibTeamSpieler() && kartenStaerkeVonStichGewinner > mindesAnforderungFuerSchmieren)|| mitspielerAlleinigTrumpf) {//schmiert nur, wenn Teamspieler einen Unter oder Ober gelegt hat.
            Spielkarte karteMitHoechsterPunktzahl = model.gibHand().get(0);
            //sucht die Karte mit der höchsten Punktzahl
            for(Spielkarte karte : erlaubteKarten){
                 if( karte.gebeWert().gebePunktzahl() > karteMitHoechsterPunktzahl.gebeWert().gebePunktzahl()){
                    karteMitHoechsterPunktzahl = karte;
                }
            }
            //Die Karte muss mehr als 3 Punkte wert sein
            if (karteMitHoechsterPunktzahl != null){
                if(karteMitHoechsterPunktzahl.gebeWert().gebePunktzahl() > 3){
                    System.out.println("DEBUG: Der Bot hat geschmiert!");
                    return  karteMitHoechsterPunktzahl;
                }

            }
        }
        //versucht einen Stich zu gewinnen, wenn eine gewisse Anzahl von Punkten auf dem Tisch liegen
        if (gibWertFuerBisherGelegteKarten() > 10) {
            //Wenn möglich, legt die erste Karte die den Stich gewinnen kann
            for(Spielkarte karte : erlaubteKarten){
                if(spielKartenStaerkeSauSpiel(karte) >kartenStaerkeVonStichGewinner){
                    System.out.println("DEBUG: Der Bot versucht den Stich zu gewinnen!");
                        return  karte;


                }
            }
        }
        //Wenn die gegen Spieler den Stich gewinnen dann versucht er eine wertlose Karte zu legen, sofern die Karte, die den Stich gewinnt, mindestens eine Sau ist.
        if(spielerGewinntStich != model.gibTeamSpieler() && model.gibTeamSpieler() != -1 && kartenStaerkeVonStichGewinner > 90){
            for(Spielkarte karte : erlaubteKarten){
               if(karte.gebeWert().gebePunktzahl() == 0){
                   System.out.println("DEBUG: Der Bot legt eine wertlose Karte!");
                   return  karte;
               }
            }
        }
        //Defaultcase legt eine zufällige Karte
        return erlaubteKarten.get(zufaelligerIndex);


    }


    //Speichern von Daten im Model.
    @Override
    public void rundeStarten(ArrayList<Spielkarte> karten, int spielerIndex) {
        model.setzteHand(karten);
        model.setzeSpielerIndex(spielerIndex);
    }

    //Speichern von Daten im Model.
    @Override
    public void spielArtEntschieden(int spieler, Farbe farbe, SpielArt spielArt) {
        model.setzeSpielerHatSauAusgerufen(spieler);
        model.setzteSau(new Spielkarte(farbe, Werte.SAU));
        if (model.hatAusgerufeneSau()){
            model.setzteTeamSpieler(spieler);
        }
        model.setzteSpielArt(spielArt);
        model.setzteSoloFarbe(farbe);
    }

    /*
        Mit dieser Methode werden folgende Daten abgespeichert:
            Welche Karten gelegt wurden
            wer in dem Stich eine Karte gelegt hat
            ob die Farbe der Gesuchten sau gespielt wurde
            sofern die gesuchte Sau gespielt, wird kann der Mitspieler abgespeichert werden.

     */
    @Override
    public void karteWurdeGelegt(Spielkarte karte, int spielerHatGelegt, int wiederholung) {
        //Nachdem die Farbe der gesuchten Sau gespielt wird, darf die gesuchte, wie jede andere Karte einer Farbe frei gespielt werden.
        if (model.gibStichGelegteKartenAnzahl() == 0 && model.gibSau().gebeFarbe() == karte.gebeFarbe() && !karte.istTrumpf(model.gibSpielArt(), model.gibsoloFarbe())) {
            model.setzeSauFarbeVorhandGespielt(true);
        }
        model.fuegeGelegteKarteHinzu(karte);
        model.fuegeSpielerNummerGelegteKarteHinzu(spielerHatGelegt);
        //Legt Mitspieler fest, wenn die gesuchte Sau ausgerufen wird.
        if (model.gibSau().equals(karte)) {
            if (model.gibSpielerHatSauAusgerufen() == model.gibSpielerIndex()) {
                //Mitspieler ist der Spieler, er die Sau gelegt hat, sofern man selbst die Sau ausgerufen hat.
                model.setzteTeamSpieler(spielerHatGelegt);
            } else {
                //Mitspieler ist der Spieler der werder die Sau ausgerufen noch gelegt hat.
                model.setzteTeamSpieler(6 - (spielerHatGelegt + model.gibSpielerHatSauAusgerufen() + model.gibSpielerIndex()));
            }
        }


        //Überprüft ob Spieler keinen Trumpf mehr legen kann.
        if (model.gibErsteKarteAufTisch().istTrumpf(SpielArt.SAUSPIEL,null) && !karte.istTrumpf(SpielArt.SAUSPIEL,null)) {
            model.setzteMitspielerHatOber(spielerHatGelegt%3, false);
            model.setzteMitspielerHatUnter(spielerHatGelegt%3, false);
            model.setzteMitspielerHatHerz(spielerHatGelegt%3, false);
        }
        if (!model.gibErsteKarteAufTisch().istTrumpf(SpielArt.SAUSPIEL,null) && karte.gebeFarbe() != model.gibErsteKarteAufTisch().gebeFarbe()) {
            switch (model.gibErsteKarteAufTisch().gebeFarbe()){
                case SCHELLEN -> {
                    model.setzteMitspielerHatSchellen(spielerHatGelegt%3, false);
                    break;
                }
                case GRAS -> {
                    model.setzteMitspielerHatGras(spielerHatGelegt%3, false);
                    break;
                }
                case EICHEL -> {
                    model.setzteMitspielerHatEichel(spielerHatGelegt%3, false);
                    break;
                }
                case HERZ -> {
                    break;
                }
                default -> System.out.println("ERROR: Unbekannte Farbe");
            }
        }


    }


    //Zurücksetzen von Daten über den Stich im Model.
    @Override
    public void stichGewonnen(int spieler) {
        model.spielerNummerGelegteKarteZuruecksetzen();
    }

    @Override
    public int gebeMitspieler() {
        return -1;
    }

    @Override
    public void rundeGewonnen(int[] gewinner, int[] punkte) {
        //Werte vom Model zurücksetzen bei einer neuen Runde
        model = new BotModel();
    }

    //Hilfsmethoden

    /*
        Berechnet den Punktewert der Karten, die bisher in dem Stich gelegt wurden.
     */
    public int gibWertFuerBisherGelegteKarten() {
        int gesamtWert = 0;
        for (Spielkarte karte : model.gibAlleGelegteKarten().subList(model.gibAlleGelegteKarten().size() - model.gibStichGelegteKartenAnzahl(), model.gibAlleGelegteKarten().size())) {
            gesamtWert += karte.gebeWert().gebePunktzahl();
        }
        return gesamtWert;
    }
    /*
        Bestimmt welcher Spieler den Stich momentan gewinnt.
     */
    public int gibSpielerDerStichMomentanGewinnt(){
        int anzahlGelegterKarten = model.gibStichGelegteKartenAnzahl();
        int anzahlAllerGelegtenKarten  =model.gibAlleGelegteKarten().size();
        int indexDerHoechstenKarte = anzahlAllerGelegtenKarten - anzahlGelegterKarten;
        for (int i = anzahlAllerGelegtenKarten - anzahlGelegterKarten; i < anzahlAllerGelegtenKarten; i++){
            if(spielKartenStaerkeSauSpiel(model.gibAlleGelegteKarten().get(i))> spielKartenStaerkeSauSpiel(model.gibAlleGelegteKarten().get(indexDerHoechstenKarte)) ){ 
                indexDerHoechstenKarte = i;
            }
        }
        return model.gibSpielerDieImStichGelegtHaben().get(indexDerHoechstenKarte -(anzahlAllerGelegtenKarten - anzahlGelegterKarten));
    }
    /*
        Gibt einen int Wert der genutzt werden kann um mit vergleichen zu Bestimmen welche Karte die andere Schlägt.
        3 Stelle = Trumpf
        2 Stelle = Sau bis 7
        1 Stelle = Farbe
     */
    public int spielKartenStaerkeSauSpiel(Spielkarte karte){
        int staerke = 0;
        switch (karte.gebeWert()){
            case SAU -> {
                staerke += 90;
            }
            case ZEHNER -> {
                staerke += 80;
            }
            case KOENIG -> {
                staerke += 70;
            }
            case OBER -> {
                staerke += 300;
            }
            case UNTER -> {
                staerke += 200;
            }
            case NEUNER -> {
                staerke += 60;
            }
            case ACHTER -> {
                staerke += 50;
            }
            case SIEBENER -> {
                staerke += 40;
            }
            default -> System.out.println("ERROR: Unbekannter Wert");
        }
        switch (karte.gebeFarbe()){
            case HERZ -> {
                if(karte.gebeWert() == Werte.OBER || karte.gebeWert() == Werte.UNTER){
                    staerke += 3;
                }else{
                    staerke += 100;
                }
            }
            case SCHELLEN -> {
                staerke += 2;
            }

            case GRAS -> {
                staerke += 4;
            }
            case EICHEL -> {
                staerke += 5;
            }
            default -> System.out.println("ERROR: Unbekannte Farbe");
        }
        return  staerke;
    }


    /*
        Bestimmt die Anzahl von Karten Typen die für Entscheidung über Spielzüge elementar sind.

     */
    public int[] wieVieleBesondereKarten() {
        int anzahlOber = 0;
        int anzahlUnter = 0;
        int anzahlSau = 0;
        int anzahlHerz = 0;
        int anzahlGras = 0;
        int anzahlEichel = 0;
        int anzahlSchellen = 0;

        for (Spielkarte karte : model.gibHand()) {
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
                    default:
                        System.out.println("Error: Unbekannte Farbe");

                }
            }


        }
        return new int[]{anzahlOber, anzahlUnter, anzahlSau, anzahlHerz, anzahlGras, anzahlEichel, anzahlSchellen};
    }


    //Zugriff von Daten vom Model
    //benötigt zum Testen
    public int gibAnzahlKartenInHand() {
        return model.gibHand().size();
    }

    public int gibTeamSpieler() {
        return model.gibTeamSpieler();
    }


}
