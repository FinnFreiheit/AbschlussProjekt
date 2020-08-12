package io.csv;
import error.FehlerCSVInhalt;
import model.TagesInfo;

import java.io.*;
import java.util.*;

/**
 * Die Klasse Lesen CSV dient dazu eine CSV Datei einzulesen.
 * Die Informationen werden in der ArrayListe HistorischeDatenListe gespeichert.
 */
public class LesenCsvTagesInformationen
{
    final public static boolean DEBUG = false;

    /**
     * Die CSV Datei wird eingelesen und aus einer Zeile wird ein Objekt der Klasse TagesInfo  erstellt.
     * Die Methode liefert eine ArrayList aus Tagesinformationen zurück.
     * @pre Die CSV Datei ist in der richtigen Formatierung im Ordner database vorhanden.
     * @post Alle wichtigen Informationen der CSV Datei werden als ArrayListe dem Aufrufer übergeben.
     * @param file Die CSV Datei
     * @return ArrayList aus Tagesinformationen
     * @throws IOException     the io exception
     * @throws FehlerCSVInhalt the fehler csv inhalt
     */
    public static ArrayList<TagesInfo> lesenCSV(File file) throws IOException, FehlerCSVInhalt
    {
        ArrayList<TagesInfo> tagesInfoListe = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String zeile;
            //Erste zeile von CSV Datei wird nicht benoetigt.
            reader.readLine();
            while((zeile = reader.readLine()) != null)
            {
                try {
                    TagesInfo ti = speicherInhaltZeileInTagesinfo(zeile);
                    tagesInfoListe.add(ti);
                }
                catch(FehlerCSVInhalt e)
                {
                    if(DEBUG) System.err.println(e.getMessage());
                }
            }
        }
        return tagesInfoListe;
    }


    /**
     * Die Informationen einer Zeile aus der CSV Datei werden in Objekte der Klasse TagesInfo gespeichert.
     * @inv Die Informationen sind in der richtigen Reinfolge.
     * @param zeile eine Zeile der CSV Datei
     * @return ein Objekt der Klasse TagesInfo
     * @throws FehlerCSVInhalt Exception wenn es einen Inhaltlichen fehler in der CSV datei gibt.
     */
    private static TagesInfo speicherInhaltZeileInTagesinfo(String zeile) throws FehlerCSVInhalt
    {
        String[] getrennteZeile = zeile.split(",");

        if(getrennteZeile.length != 7) throw new FehlerCSVInhalt("In der CSV-Datei einer Aktie gibt es einen " +
                "inhaltlichen Fehler. Anstatt von 7 Informationen pro Zeile sind " + getrennteZeile.length +
                " vorhanden");

        // die csv-Dateien enthalten fuer den 20.Juli 2020 null-Eintraege
        if(getrennteZeile[1].equals("null")) throw new FehlerCSVInhalt("in der CSV-Datei sind fuer das Datum " + getrennteZeile[0] +
                " null-Eintraege enthalten");

        String datum = getrennteZeile[0];
        double open = Double.parseDouble(getrennteZeile[1]);
        double high = Double.parseDouble(getrennteZeile[2]);
        double low = Double.parseDouble(getrennteZeile[3]);
        double close = Double.parseDouble(getrennteZeile[4]);
        double volume = Double.parseDouble(getrennteZeile[6]);

        return new TagesInfo(datum,open,close,high,low,volume);

    }
}

