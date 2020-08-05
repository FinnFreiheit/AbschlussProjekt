package io.csv;

import model.Transaktion;

import java.io.*;
import java.util.ArrayList;

public class LesenCSVKaufHistorie
{
    public static ArrayList<Transaktion> lesenCSVtoTransaktionenListe(File file) throws IOException
    {
        ArrayList<Transaktion> transaktionenListe = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String zeile;
            reader.readLine();

            while((zeile = reader.readLine()) != null)
            {
                transaktionenListe.add(speicherInhaltZeileInTransaktionen(zeile));
            }
        }
        return transaktionenListe;
    }

    private static Transaktion speicherInhaltZeileInTransaktionen(String zeile)
    {

        // TODO: 03.08.20 laenge StringArray auslesen falls != 4 IOException
        String[] getrennteZeile = zeile.split(",");

        String datum = getrennteZeile[0];
        String aktienName = getrennteZeile[1];
        int anzahl = Integer.parseInt(getrennteZeile[2]);
        String handelsAktion = getrennteZeile[3];

        return new Transaktion(datum,aktienName,anzahl,handelsAktion);
    }
}
