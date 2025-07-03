package Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class TurnierStatistik {
    private LocalDate datum;
    private LocalTime zeit;
    private int punkte;
    private boolean wurdeGewonnen;
    public TurnierStatistik(int punkte, boolean wurdeGewonnen){
        this.punkte = punkte;
        this.wurdeGewonnen = wurdeGewonnen;
        zeit = LocalTime.now();
        datum = LocalDate.now();
    }
    public TurnierStatistik(int punkte, boolean wurdeGewonnen, int jahr, int monat, int tag, int stunde, int minute){
        this.punkte = punkte;
        this.wurdeGewonnen = wurdeGewonnen;
        zeit = LocalTime.of(stunde,minute);
        datum = LocalDate.of(jahr, monat, tag);
    }
    public LocalDate DatumGeben(){
        return datum;
    }
    public LocalTime ZeitGeben(){
        return zeit;
    }
    public int punkteGeben(){
        return punkte;
    }
    public boolean wurdeGewonnen(){
        return wurdeGewonnen;
    }
}
