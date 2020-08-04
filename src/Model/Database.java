package Model;

import java.util.ArrayList;

/**
 * Die Klasse Database, beinhaltet alle historische Datenlisten.
 */
public class Database
{
    //Objekt Variablen
    private ArrayList<HistorischeDatenListe> datenBasis;

    /**
     * Konstruktor
     */
    public Database()
    {
        datenBasis = new ArrayList<>();
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
       return null;
    }
}
