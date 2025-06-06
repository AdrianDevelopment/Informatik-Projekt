//Programmierer: Tim
package InformatikProjekt;

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
        //Todo besondereKarten objekt statt array
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
        //Sauspiel, wenn eine Sau ausgerufen werden kann und mindestens 5 Trümpfe auf der Hand sind.
        if (!farbenZumAusrufen.isEmpty() && anzahlOU + besondereKarten[4] >= 5 && hoechsteSpiel.gebeSpielArtID() < SpielArt.SAUSPIEL.gebeSpielArtID()) {
            //Speichert die Sau die Ausgerufen werden soll im Model ab.
            //Die Methode farbenZumAusrufen gibt die beste Sau zum Ausrufen an Stelle 0 zurück.
            model.setzteSau(new Spielkarte(farbenZumAusrufen.get(0), Werte.SAU));
            return SpielArt.SAUSPIEL;
        }
        //Damit immer ein Spiel stattfindet --> Kein Spiel bei Rudne implimentieren
        //Eigentlich kein Spiel
        return SpielArt.SAUSPIEL;
    }


    // Gibt die Farbe für die Spielabsicht entweder die Farbe für ein Solo oder die ausgerufene Sau.
    @Override
    public void farbeFuerSpielAbsicht(SpielArt spielArt) {

        runde.farbeFuerSpielAbsichtAufgerufen(farbeFuerSpielAbsichtGewaehlt(spielArt));
    }

    public Farbe farbeFuerSpielAbsichtGewaehlt(SpielArt spielArt) {
        if (spielArt == null){
            System.out.println("farbeFuerSpielAbsichtGewaehlt() bei bot wurde null übergeben");
        }

        switch (spielArt) {
            case KEINSPIEL:
                break;
            case SAUSPIEL:
                return model.gibSau().gebeFarbe();
            case WENZ:
                break;
            case SOLO:
                return model.gibsoloFarbe();
        }
        return Farbe.SCHELLEN;
    }

    /*
        Wählt je nach Spielart eine Karte aus.
        Dafür werden zuerst alle Karten bestimmt, die nach den Regeln gelegt werden könnten.
        Danach wird eine der erlaubten Karten nach einem System ausgewählt.
     */
    @Override
    public void legeEineKarte(int wiederholung, int vorhand) {
        runde.karteAbfragenAufgerufen(wiederholung, waehleEineKarte(), vorhand);
    }

    public Spielkarte waehleEineKarte() {
        ArrayList<Spielkarte> moeglicheKarten = new ArrayList<>();
        //Überprüfen, ob es der Beginn des Stiches ist und demanch erlaubte Karten bestimmen.
        if (model.gibStichGelegteKartenAnzahl() == 0) {
            moeglicheKarten = erlaubteKartenAusspielenBeiSauspiel(model.gibHand(), model.gibSau());
        } else {
            moeglicheKarten = gibErlaubteKarten((ArrayList<Spielkarte>) model.gibHand().clone(), model.gibSpielArt(), model.gibSau(), model.gibErsteKarteAufTisch(), model.gibsoloFarbe(), model.gibSauFarbeVorhandGespielt());
            System.out.println("DEBUG: Größe moeglicheKarten in Bot: " + moeglicheKarten.size());
        }
        Spielkarte gewaelteKarte = null;
        //Wahl der Karte nach Spielart unterscheiden.
        switch (model.gibSpielArt()) {
            case KEINSPIEL:
                break;
            case SAUSPIEL:
                gewaelteKarte = sauSpielKarteWaehlen(moeglicheKarten);
            case WENZ:
                break;
            case SOLO:
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

        int[] besondereKarten = wieVieleBesondereKarten();
        if (gibWertFuerBisherGelegteKarten() > 20) {
            //todo
        }
        return erlaubteKarten.get(zufaelligerIndex);
        //todo Spieltips nochmal anschauen
        //todo wenn Teammitglied hohen Trump hat dann Schmieren
        //todo wenn gegner keine Trümpe und andere Farben mehr haben dann diese Farben legen
        //todo Wenn stich nicht gewinnen kann dan niedrige Werte

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
        model.setzteSpielArt(spielArt);
        model.setzteSoloFarbe(farbe);
    }

    /*
        Mit dieser Methode werden folgende Daten abgespeichert:
            Welche Karten gelegt wurden
            wer in dem Stich eine Karte gelegt hat
            ob die Farbe der Gesuchten sau gespielt wurde
            sofern die gesuchte Sau gespielt, wird kann der Mitspieler abgespeichert werden.
            //todo abspeichern von Informationen über die Art der Karten auf der Hand der anderen Spieler
     */
    @Override
    public void karteWurdeGelegt(Spielkarte karte, int spielerHatGelegt) {
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

        //Todo abspeichern welche Karten die Mitspieler noch haben
        //Todo bei Spielart Unterscheiden --> in mehrere Methoden aufteilen
        /*
        if (model.gibErsteKarteAufTisch().gebeWert() != Werte.OBER) {
            model.setzteMitspielerDaten(null, Werte.OBER, false, spielerHatGelegt);
        }
        */


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

    //Kein Nutzen für Bot
    @Override
    public void spielerHatSpielabsichtGesagt(SpielArt spielAbsicht, int spieler) {

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
        Bestimmt die Anzahl von Karten Typen die für Entscheidung über Spielzüge elementar sind.
        //todo Methode muss noch an Spielarten angepasst werden. (Bei Wenz ist der Ober eine Farbe)
        //todo Ein Object mit den jeweiligen Arten von Karten als Attributen, um die Nutzung der Daten zu erleichtern. (Anstatt Array position zu merken)
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
                }
            }


        }
        return new int[]{anzahlOber, anzahlUnter, anzahlSau, anzahlHerz, anzahlGras, anzahlEichel, anzahlSchellen};
    }

    /*
        Methode um die Daten von der Arraylist mit BotMitspielerDatenModel zu verändern.
        Benötigt um Informationen über die Hand der andern Spieler abzuspeichern.
     */
    public void setzteMitspielerDaten(Farbe farbe, Werte werte, boolean vorhanden, int spielerNr) {
        // != null da auch null als Parameter übergeben werden kann. Da nur entweder bei einer Farbe oder einem Wert eine änderung vorgenommen werden soll.
        if (farbe != null) {
            switch (farbe) {
                case SCHELLEN:
                    model.setzteMitspielerHatSchellen(spielerNr % 3, vorhanden);
                    break;
                case HERZ:
                    model.setzteMitspielerHatHerz(spielerNr % 3, vorhanden);
                    break;
                case GRAS:
                    model.setzteMitspielerHatGras(spielerNr % 3, vorhanden);
                    break;
                case EICHEL:
                    model.setzteMitspielerHatEichel(spielerNr % 3, vorhanden);
                    break;
            }
        }
        if (werte != null) {
            switch (werte) {
                case SAU:
                    break;
                case ZEHNER:
                    break;
                case KOENIG:
                    break;
                case OBER:
                    model.setzteMitspielerHatOber(spielerNr % 3, vorhanden);
                    break;
                case UNTER:
                    model.setzteMitspielerHatUnter(spielerNr % 3, vorhanden);
                    break;
                case NEUNER:
                    break;
                case ACHTER:
                    break;
                case SIEBENER:
                    break;
            }
        }

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
