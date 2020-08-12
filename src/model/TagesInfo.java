package model;

import java.util.Locale;

/**
 * Die Klasse {@code TagesInfo} Beinhaltet alle Informationen einer Aktie an einem bestimmten Tag.
 */
public class TagesInfo implements CSVSchreibbar
{

    /**
     * das Datum zu den Tagesinformationen.
     */
    private String datum;

    /**
     * Wert der Aktie beim öffnen der Börse.
     */
    private double startWert;

    /**
     * Wert der Aktie beim schließen der Börse.
     */
    private double schlussWert;

    /**
     * Den Maximalwert an diesem Tag
     */
    private double tagesHoch;

    /**
     * Minimalwert an diesem Tag
     */
    private double tagesTief;

    /**
     * Handelsvolumen
     */
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
     * @param info Objekt der Klasse Tagesinformation
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
     * Gibt das Datum zurück.
     *
     * @return Datum
     */
    public String getDatum()
    {
        return datum;
    }

    /**
     * Ermittelt den Kaufpreis als mittelwert zwischen Tageshoch und Tagestief
     *
     * @return Kaufpreis double
     */
    public double durchschnittsTagesPreis()
    {
        return ((this.tagesHoch + this.tagesTief) / 2 );
    }


    /**
     * Fügt Datum, Startwert, Schlusswert, Tageshoch und Tagestief  in ein String zusammen
     * @return String mit denn Objektvariablen
     */
    @Override
    public String toString()
    {
        return String.format(Locale.US,"%s %f %f %f %f",datum,startWert,schlussWert,tagesHoch,tagesTief);
    }

    /**
     * Fügt Datum, Startwert, Schlusswert, Tageshoch und Tagestief  in ein String zusammen. Die einzelenen Werte werden
     * durch ein Komma getrennt, damit man aus dem String eine CSV Datei schreiben kann
     * @return String mit denn Objektvariablen
     */
    @Override
    public String toCSV()
    {
        return String.format(Locale.US,"%s,%f,%f,%f,%f",datum,startWert,schlussWert,tagesHoch,tagesTief);
    }


    /**
     * Vergleicht zwei Objekte von Tagesinfo miteinander.
     * @param o Das Objekt welches mit Tagesinformation verglichen werden soll
     * @return true wenn das Objekt der Tagesinformation entspricht false wenn nicht.
     */
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

