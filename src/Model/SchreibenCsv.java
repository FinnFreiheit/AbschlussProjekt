package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


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
