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

/**
 * Die CSV Datei Kaufhistorie kann kann mit käufen und verkäufen erweitert werde.
 */
public class SchreibenCsv
{
    /**
     * Zur CSV Datei Kaufhistorie werden weitere Transaktionen hinzugefügt.
     * @param data die Transaktionen, die in die CSV Datei geschrieben werden sollen.
     */
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

    /**
     * eine Aktie wird mit dem Transaktionsschlüssel "k" für kaufen in die Liste Data gespeichert.
     *
     * @param datum  Kaufdatum
     * @param aktie  Die Aktie die gekauft wurde
     * @param anzahl Die Anzahl der gekauften Aktien
     */
    public static void schreibeKaufen(String datum, String aktie, String anzahl)
    {
        List<String> data = Arrays.asList(datum, aktie, anzahl, "k");
        appendToKaufHistorie(data);
    }

    /**
     * eine Aktie wird mit dem Transaktionsschlüssel "v" für verkaufen in die Liste Data gespeichert.
     *
     * @param datum  Verkaufdatum
     * @param aktie  Die Aktie die verkauft wurde
     * @param anzahl Die Anzahl der verkauften Aktien
     */
    public static void schreibeVerkaufen(String datum, String aktie, String anzahl)
    {
        List<String> data = Arrays.asList(datum, aktie, anzahl, "v");
        appendToKaufHistorie(data);
    }
}
