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

    static File file = new File("/Users/Finn/IdeaProjects/Aktien/DAI.DE.csv");

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
                tagesInfoListe.add(speicherInhaltZeileInTagesinfo(zeile));
            }
        }
        return tagesInfoListe;
    }


    private static TagesInfo speicherInhaltZeileInTagesinfo(String zeile) throws FehlerCSVInhalt
    {
        String[] getrennteZeile = zeile.split(",");

        if(getrennteZeile.length != 7) throw new FehlerCSVInhalt("In der CSV-Datei einer Aktie gibt es ein " +
                "inhaltlichen Fehler. Anstatt von 7 Informationen pro Zeile sind " + getrennteZeile.length +
                " vorhanden");

        String datum = getrennteZeile[0];
        double open = Double.parseDouble(getrennteZeile[1]);
        double high = Double.parseDouble(getrennteZeile[2]);
        double low = Double.parseDouble(getrennteZeile[3]);
        double close = Double.parseDouble(getrennteZeile[4]);
        double volume = Double.parseDouble(getrennteZeile[5]);

        return new TagesInfo(datum,open,close,high,low,volume);

    }
}

