package io.csv;

import error.FehlerCSVInhalt;
import model.Transaktion;

import java.io.*;
import java.util.ArrayList;

/**
 * Die CSV Datei der Kaufhistorie wird eingelesen. Sie beinhaltet eine Liste der getätigten Transaktionen.
 */
public class LesenCSVKaufHistorie
{
    /**
     * Die CSV Datei wird eingelesen und aus einer Zeile wird ein Objekt der Klasse Transaktion erstellt.
     * Die Methode liefert eine ArrayList aus Transaktionen zurück.
     * @pre Die CSV Datei ist in der richtigen Formatierung im Ordner database vorhanden.
     * @post Alle wichtigen Informationen der CSV Datei werden als ArrayListe dem Aufrufer übergeben.
     * @param file die CSV Datei
     * @return ArrayList aus Transaktionen
     * @throws IOException     the io exception
     * @throws FehlerCSVInhalt the fehler csv inhalt
     */
    public static ArrayList<Transaktion> lesenCSVtoTransaktionenListe(File file) throws IOException, FehlerCSVInhalt
    {
        ArrayList<Transaktion> transaktionenListe = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String zeile;
            reader.readLine();

            while((zeile = reader.readLine()) != null)
            {
                if(!zeile.isEmpty())  // letzte Zeile nach Zeilenumbruch
                transaktionenListe.add(speicherInhaltZeileInTransaktionen(zeile));
            }
        }
        return transaktionenListe;
    }

    /**
     * Die Informationen einer Zeile aus der CSV Datei werden in Objekte der Klasse Transaktion gespeichert.
     * @inv Die Informationen sind in der richtigen Reinfolge. Transaktionen dürfen nicht älter als ein Jahr sein,
     * sie dürfen nicht in der Zukunft liegen und die Aktien müssen im DAX sein.
     * @param zeile eine Zeile der CSV Datei
     * @return ein Objekt der Klasse Transaktion
     * @throws FehlerCSVInhalt Exception wenn es einen Inhaltlichen fehler in der CSV datei gibt.
     */
    private static Transaktion speicherInhaltZeileInTransaktionen(String zeile) throws FehlerCSVInhalt
    {
        String[] getrennteZeile = zeile.split(",");

        if(getrennteZeile.length != 4) throw new FehlerCSVInhalt("In der CSV-Datei Kaufhistorie gibt es ein " +
                "inhaltlichen Fehler. Anstatt 4 Informationen pro Zeile sind " + getrennteZeile.length +
                " vorhanden");

        String datum = getrennteZeile[0];
        String aktienName = getrennteZeile[1];
        int anzahl = Integer.parseInt(getrennteZeile[2]);
        String handelsAktion = getrennteZeile[3];

        return new Transaktion(datum,aktienName,anzahl,handelsAktion);
    }
}
