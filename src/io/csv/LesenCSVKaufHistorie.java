package io.csv;

import error.FehlerCSVInhalt;
import model.Transaktion;

import java.io.*;
import java.util.ArrayList;

public class LesenCSVKaufHistorie
{
    public static ArrayList<Transaktion> lesenCSVtoTransaktionenListe(File file) throws IOException, FehlerCSVInhalt
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

    private static Transaktion speicherInhaltZeileInTransaktionen(String zeile) throws FehlerCSVInhalt
    {
        String[] getrennteZeile = zeile.split(",");

        if(getrennteZeile.length != 4) throw new FehlerCSVInhalt("In der CSV-Datei Kaufhistorie gibt es ein " +
                "inhaltlichen Fehler. Anstatt von 4 Informationen pro Zeile sind " + getrennteZeile.length +
                " vorhanden");

        String datum = getrennteZeile[0];
        String aktienName = getrennteZeile[1];
        int anzahl = Integer.parseInt(getrennteZeile[2]);
        String handelsAktion = getrennteZeile[3];

        return new Transaktion(datum,aktienName,anzahl,handelsAktion);
    }
}
