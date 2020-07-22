/**
 * Die Klasse Lesen CSV dient dazu eine CSV Datei einzulesen.
 * Die Informationen werden in der ArrayListe HistorischeDatenListe gespeichert.
 */
package Model;
import java.io.*;
import java.util.*;


public class LesenCsv
{

    static File file = new File("/Users/Finn/IdeaProjects/Aktien/DAI.DE.csv");


    public static void lesenCSV(File file) throws IOException
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String zeile;
            while((zeile = reader.readLine()) != null)
            {

                speicherInhaltZeileInTagesinfo(zeile);
                // TODO: 22.07.20 TagesInfo in Liste Speichern.
            }
        }
    }

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





    // TODO: 22.07.20 File Methode,

    public static void main(String[] args) throws IOException
    {
        lesenCSV(file);
    }


}

