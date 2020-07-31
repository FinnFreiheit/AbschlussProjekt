package Model;

import java.util.Date;
import java.util.Locale;

public class TagesInfo implements CSVSchreibbar
{

    private String datum;
    private double startWert;
    private double schlussWert;
    private double tagesHoch;
    private double tagesTief;
    private double volumen;

    public TagesInfo(String datum, double startWert, double schlussWert, double tagesHoch, double tagesTief, double volumen)
    {
        this.datum = datum;
        this.startWert = startWert;
        this.schlussWert = schlussWert;
        this.tagesHoch = tagesHoch;
        this.tagesTief = tagesTief;
        this.volumen = volumen;
    }

    @Override
    public String toString()
    {
        return String.format(Locale.US,"%s %f %f %f %f",datum,startWert,schlussWert,tagesHoch,tagesTief);
    }

    @Override
    public String toCSV()
    {
        return String.format(Locale.US,"%s,%f,%f,%f,%f",datum,startWert,schlussWert,tagesHoch,tagesTief);
    }

    public void ausgabeConsoleTagesListe()
    {
        System.out.println(toString());
    }
}
