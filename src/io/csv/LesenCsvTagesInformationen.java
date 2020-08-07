/**
 * Die Klasse Lesen CSV dient dazu eine CSV Datei einzulesen.
 * Die Informationen werden in der ArrayListe HistorischeDatenListe gespeichert.
 */
package io.csv;
import error.FehlerCSVInhalt;
import model.TagesInfo;

import java.io.*;
import java.util.*;

//TODO Beide LesenCSV können auch in eine Klasse
public class LesenCsvTagesInformationen
{

    static File file = new File("/database/DAI.DE.csv");

    public static ArrayList<TagesInfo> lesenCSV(File file) throws IOException, FehlerCSVInhalt
    {
        ArrayList<TagesInfo> tagesInfoListe = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String zeile;
            //Erste zeile von CSV Datei wird nicht benoetigt.
            reader.readLine();
            boolean speicherFehler = false;
            while((zeile = reader.readLine()) != null)
            {
                speicherFehler = true;
                try {
                    TagesInfo ti = speicherInhaltZeileInTagesinfo(zeile);
                    speicherFehler = false;
                }
                catch(FehlerCSVInhalt e)
                {
                    System.err.println(e.getMessage());
                }
                if(!speicherFehler) tagesInfoListe.add(speicherInhaltZeileInTagesinfo(zeile));

            }
        }
        return tagesInfoListe;
    }


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
        double volume = Double.parseDouble(getrennteZeile[5]);

        return new TagesInfo(datum,open,close,high,low,volume);

    }
}

