package io.csv;

import model.HistorischeDatenListe;
import model.TagesInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// TODO: 31.07.20 Interface CSVSchreibbar einbinden
public class SchreibenCsv
{
    static String ersteZeile = "Datum,open,high,low,close";
    public static void SchreibeCSV(HistorischeDatenListe historischeDatenListe, File file)
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
        {
            writer.write(ersteZeile);
            writer.newLine();
            for (TagesInfo tagesinfo : historischeDatenListe.historischeDatenListe)
            {
                writer.write(tagesinfo.toCSV());
                writer.newLine();
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
