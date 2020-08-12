package model;

import error.FehlerCSVInhalt;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

/**
 * Die Klasse {@code Database}, beinhaltet alle historische Datenlisten.
 */
public class Database
{
    /**
     * Liste aus historischen Datenlisten.
     */
    private ArrayList<HistorischeDatenListe> datenBasis;

    /**
     * Ordnerpfad in dem sich die Daten befinden.
     */
    final static String PATH = "database/";

    /**
     * Konstruktor
     */
    private Database()
    {
        datenBasis = new ArrayList<>();
    }

    /**
     * Fabrikmethode zur Erzeugung der Database aller
     * Aktien aus dem Dax
     * @return die Database mit allen historischen Datenlisten der 30 DAX Unternehmen
     */
    public static Database generateDatabase()
    {
        System.out.println("    Erstelle Database ...");
        Database db = new Database();
        Set<String> dax = Dax.getDax().keySet(); // alle Aktienkuerzel aus dem Dax
        for(String key : dax)
        {
            File file = new File(PATH + key + ".csv");
            try {
                HistorischeDatenListe hdl = new HistorischeDatenListe(file);
                db.addData(hdl);
                System.out.printf("        HistorischeDatenListe von %-7s zur Database hinzugefuegt.%n", key);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (FehlerCSVInhalt fehlerCSVInhalt)
            {
                fehlerCSVInhalt.printStackTrace();
            }
        }
        System.out.println("    ... Database erstellt.");
        return db;
    }

    /**
     * Fügt der Datenbasis eine neue historische Datenliste hinzu.
     *
     * @param data historische Datenliste
     */
    public void addData(HistorischeDatenListe data)
    {
        this.datenBasis.add(data);
    }

    /**
     * liefert die historische Datenliste mit dem gesuchten Namen.
     *
     * @param name der Name der gesuchten Datenliste
     * @return gesuchte historische Datenliste
     */
    public HistorischeDatenListe getHistorischeDatenListeAusDatenBasis(String name)
    {
        for (HistorischeDatenListe historischeDatenListe : this.datenBasis)
        {
            if(historischeDatenListe.getName().equals(name)) return historischeDatenListe;
        }
        // TODO: 05.08.20 Fehlerbehandlung wenn nicht vorhanden
       return null;
    }
}
