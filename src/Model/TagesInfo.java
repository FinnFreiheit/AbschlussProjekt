package Model;

import java.util.Date;
import java.util.Locale;

public class TagesInfo implements CSVSchreibbar
{

    // TODO: 01.08.20 Volumen
    //__________________________________________________________________________________________________________________
    //Obejkt Variablen
    private String datum;
    private double startWert;
    private double schlussWert;
    private double tagesHoch;
    private double tagesTief;
    private double volumen;

    //__________________________________________________________________________________________________________________
    //Konstruktoren
    public TagesInfo(String datum, double startWert, double schlussWert, double tagesHoch, double tagesTief, double volumen)
    {
        this.datum = datum;
        this.startWert = startWert;
        this.schlussWert = schlussWert;
        this.tagesHoch = tagesHoch;
        this.tagesTief = tagesTief;
        this.volumen = volumen;
    }

    //Copy Konstruktor
    public TagesInfo(TagesInfo info)
    {
        this.datum = info.datum;
        this.startWert = info.startWert;
        this.schlussWert = info.schlussWert;
        this.tagesHoch = info.tagesHoch;
        this.tagesTief = info.tagesTief;
    }

    //__________________________________________________________________________________________________________________
    // Getter Setter

    public String getDatum()
    {
        return datum;
    }

    /**
     * Der Kaufpreis
     * @return Kaufpreis
     */
    public double durchschnittsTagesPreis()
    {
        return ((this.tagesHoch + this.tagesTief) / 2 );
    }

    //__________________________________________________________________________________________________________________
    //Methoden

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

    @Override
    public boolean equals( Object o )
    {
        if ( o == null )
            return false;

        if ( o == this )
            return true;

        if (!(o instanceof TagesInfo))
            return false;

        TagesInfo that = (TagesInfo) o;

        return    this.datum.equals(that.datum)
                && this.startWert == that.startWert
                && this.schlussWert == that.schlussWert
                && this.tagesHoch == that.tagesHoch
                && this.tagesTief == that.tagesTief;
    }

    public void ausgabeConsoleTagesListe()
    {
        System.out.println(toString());
    }
}
