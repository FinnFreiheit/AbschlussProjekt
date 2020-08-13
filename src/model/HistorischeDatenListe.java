package model;

import error.DatumFehler;
import error.FehlerCSVInhalt;
import error.TagesInformationenNichtVorhanden;
import io.csv.LesenCsvTagesInformationen;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Die historische Datenliste besteht aus Tagesinformationen und dem Namen der Firma.
 * Ein historische Datenliste beinhaltet alle Informationen, die aus einer CSV Datei ausgelesen werden konnte.
 */
public class HistorischeDatenListe
{

    /**
     * Liste aus Tagesinformationen
     */
    public List<TagesInfo> historischeDatenListe;

    /**
     * Name der Aktie
     */
    private String name;


    /**
     * Konstruktor
     *
     * @param file das File der CSV Datei.
     * @throws IOException     the io exception bei einem Fehler mit der Datei
     * @throws IOException     input, output Exception
     * @throws FehlerCSVInhalt Exception wenn ein Fehler mit dem Inhalt der CSV Datei vorliegt.
     * @pre CSV Datei besitzt das richtige Format.
     * @post eine ArrayList mit alles wichtigen Tagesinformationen vom letzten Jahr
     * @inv Die CSV Datei wurde unter dem richtigen Namen gespeichert, damit die Objektvariable "Name" richtig
     * bestimmt werden kann.
     */
    public HistorischeDatenListe(File file) throws IOException, FehlerCSVInhalt
    {
        this.historischeDatenListe = new ArrayList<>();
        this.historischeDatenListe = LesenCsvTagesInformationen.lesenCSV(file);
        this.name = getNameAusFile(file);
    }

    /**
     * Ausgabe der historische Datenliste auf die Konsole.
     */
    public void ausgabeHistorischeDatenListeKonsole()
    {
        for (TagesInfo t : this.historischeDatenListe)
        {
            System.out.println(t.toString());
        }
    }

    /**
     * gibt die Tagesinformationen an dem gesuchten Datum zurück.
     *
     * @param datum das Datum des Tages
     * @return die Informationen an diesem Tag
     * @throws DatumFehler                      the io exception bei einem fehler mit dem Datum
     * @throws DatumFehler                      Exception wenn ein Fehler mit dem Datum vorliegt.
     * @throws TagesInformationenNichtVorhanden Exception wenn die Tagesinformationen nicht vorhanden sind.
     * @pre Datum nicht in der Zukunft nicht älter als ein Jahr und nicht am Wochenende bzw. Feiertag.
     */
    public TagesInfo getTagesInformationen(String datum) throws DatumFehler, TagesInformationenNichtVorhanden
    {
        if (!eingabeDatumUeberpruefen(datum))
        {
            throw new DatumFehler("Das Datum entspricht nicht dem richtigen Format");
        }
        for (TagesInfo t : this.historischeDatenListe)
        {
            if (t.getDatum().equals(datum)) return t;
        }
        throw new TagesInformationenNichtVorhanden("Es existieren keine Tagesinformationen zu dem Datum " + datum);
    }

    /**
     * Rückgabe Durchschnittspreis an einem gesuchten Tag
     *
     * @param datum der gesuchte Tag
     * @return Durchschnnittspreis
     * @throws DatumFehler                      Exception bei einem Fehler mit dem Datum
     * @throws TagesInformationenNichtVorhanden Exception wenn die Tagesinformationen nicht vorhanden sind.
     * @pre Datum nicht in der Zukunft nicht älter als ein Jahr und nicht am Wochenende bzw. Feiertag.
     */
    public double getPreis(String datum) throws DatumFehler, TagesInformationenNichtVorhanden
    {
        TagesInfo ti = this.getTagesInformationen(datum);
        return ti.durchschnittsTagesPreis();
    }

    /**
     * Ueberprueft, ob das Datum dem Format JJJJ-MM-DD entspricht.
     *
     * @param datum zu ueberpruefendes Datum
     * @return true wenn es dem Format entspricht, false wenn nicht
     */
    private static boolean eingabeDatumUeberpruefen(String datum)
    {
        String[] datumSplit = datum.split("-");
        return datumSplit.length == 3 && datumSplit[0].length() == 4 && datumSplit[1].length() == 2 &&
                datumSplit[2].length() == 2;

    }

    /**
     * Der Dateiname beinhaltet den Namen der Aktie.
     *
     * @param file File der CSV Datei
     * @return Name der Aktie
     */
    private static String getNameAusFile(File file)
    {
        String stringFile = file.toString();
        String[] strings = stringFile.split("/");
        String nameMitDateiEndung = strings[strings.length - 1];
        String[] nameMitDateiEndungListe = nameMitDateiEndung.split("\\.");
        return nameMitDateiEndungListe[0] + "." + nameMitDateiEndungListe[1];
    }

    /**
     * Gibt den Namen der historischen Datenliste zurück.
     *
     * @return the name
     */
    public String getName()
    {
        return this.name;
    }
}
