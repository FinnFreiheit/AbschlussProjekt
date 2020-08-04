package Model;

import java.util.Date;
import java.util.Locale;

/**
 * Beinhaltet alle Informationen einer Aktie an einem bestimmten Tag.
 */
public class TagesInfo implements CSVSchreibbar
{

    // TODO: 01.08.20 Volumen
    //Obejkt Variablen
    private String datum;
    private double startWert;
    private double schlussWert;
    private double tagesHoch;
    private double tagesTief;
    private double volumen;

    /**
     * Konstruktor
     *
     * @param datum       Datum
     * @param startWert   Startwert
     * @param schlussWert Schlusswert
     * @param tagesHoch   Tageshoch
     * @param tagesTief   Tagestief
     * @param volumen     Volumen
     */
    public TagesInfo(String datum, double startWert, double schlussWert, double tagesHoch, double tagesTief, double volumen)
    {
        this.datum = datum;
        this.startWert = startWert;
        this.schlussWert = schlussWert;
        this.tagesHoch = tagesHoch;
        this.tagesTief = tagesTief;
        this.volumen = volumen;
    }

    /**
     * Copy Konstruktor
     */
    public TagesInfo(TagesInfo info)
    {
        this.datum = info.datum;
        this.startWert = info.startWert;
        this.schlussWert = info.schlussWert;
        this.tagesHoch = info.tagesHoch;
        this.tagesTief = info.tagesTief;
    }

    /**
     * Get Datum.
     *
     * @return Datum
     */
    public String getDatum()
    {
        return datum;
    }

    /**
     * Der Kaufpreis
     *
     * @return Kaufpreis double
     */
    public double durchschnittsTagesPreis()
    {
        return ((this.tagesHoch + this.tagesTief) / 2 );
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

    // TODO: 03.08.20 HashCode
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

    /**
     * Ausgabe console Tagesinformation.
     */
    public void ausgabeConsoleTagesInformation()
    {
        System.out.println(toString());
    }
}

