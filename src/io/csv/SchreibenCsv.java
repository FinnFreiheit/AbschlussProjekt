package io.csv;

import model.HistorischeDatenListe;
import model.TagesInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

    private static void appendToKaufHistorie(List<String> data)
    {
        String fileName = "database/KaufHistorie.csv";
        FileWriter csvWriter = null;
        try {
            csvWriter = new FileWriter(fileName, true);  // true --> append
            csvWriter.append(String.join(",", data));
            csvWriter.append("\n");
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void schreibeKaufen(String datum, String aktie, String anzahl)
    {
        List<String> data = Arrays.asList(datum, aktie, anzahl, "k");
        appendToKaufHistorie(data);
    }

    public static void schreibeVerkaufen(String datum, String aktie, String anzahl)
    {
        List<String> data = Arrays.asList(datum, aktie, anzahl, "v");
        appendToKaufHistorie(data);
    }
}
