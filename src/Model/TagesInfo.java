package Model;

import java.util.Date;

public class TagesInfo
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
        return "TagesInfo{"
                + "startWert=" + startWert
                + ", schlussWert=" + schlussWert
                + ", tagesHoch=" + tagesHoch
                + ", tagesTief=" + tagesTief
                + '}';
    }

    public void ausgabeConsoleTagesListe()
    {
        System.out.println(toString());
    }
}
