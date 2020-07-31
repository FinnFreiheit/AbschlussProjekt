/**
 * Die Klasse Lesen CSV dient dazu eine CSV Datei einzulesen.
 * Die Informationen werden in der ArrayListe HistorischeDatenListe gespeichert.
 */
package Model;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;


public class LesenCsv
{

    static File file = new File("/Users/Finn/IdeaProjects/Aktien/DAI.DE.csv");


    public static ArrayList<TagesInfo> lesenCSV(File file) throws IOException
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

    // TODO: 22.07.20 Not Null exception
    private static TagesInfo speicherInhaltZeileInTagesinfo(String zeile)
    {
        String[] getrennteZeile = zeile.split(",");

        String datum = getrennteZeile[0];
        double open = Double.parseDouble(getrennteZeile[1]);
        double high = Double.parseDouble(getrennteZeile[2]);
        double low = Double.parseDouble(getrennteZeile[3]);
        double close = Double.parseDouble(getrennteZeile[4]);
        double volume = Double.parseDouble(getrennteZeile[5]);

        return new TagesInfo(datum,open,close,high,low,volume);

    }
}

