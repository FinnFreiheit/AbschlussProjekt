package Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Die Kaufhistorie beinhaltet alle Transaktionen, die der Nutzer getätigt hat.
 * Die Kaufhistorie besteht aus einer Liste aus Transaktionen.
 */
public class KaufHistorie
{
    //Objektvariablen
    public ArrayList<Transaktion> kaufHistorie = new ArrayList<>();

    /**
     * Konstruktor
     *
     * @param file Die Transaktionen werden aus einer CSV Datei eingelesen.
     * @throws IOException the io exception bei fehler mit der Datei
     */
    public KaufHistorie(File file) throws IOException
    {
        this.kaufHistorie = LesenCSVKaufHistorie.lesenCSVtoTransaktionenListe(file);
    }

    /**
     * Erstellt ein Objekt der Klasse Depot anhand der Kaufhistorie
     *
     * @param datenbasis die Datenbasis
     * @return das Depot
     * @throws IOException the io exception bei fehler mit dem Datum
     */
    public Depot depotErstellen(Database datenbasis) throws IOException
    {
        Depot depot = new Depot();


        for (Transaktion t : this.kaufHistorie)
        {
            HistorischeDatenListe historischeDatenListe;
            historischeDatenListe = datenbasis.getHistorischeDatenListeAusDatenBasis(t.aktienName);
            if(t.handelsAktion)
            {
                depot.hinzufuegen(t.datum, t.anzahl, historischeDatenListe);
            }

        }
        return depot;
    }

}
