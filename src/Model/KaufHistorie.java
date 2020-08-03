package Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class KaufHistorie
{
    public ArrayList<Transaktion> kaufHistorie = new ArrayList<>();
    private double wert;

    public KaufHistorie(File file) throws IOException
    {
        this.kaufHistorie = LesenCSVKaufHistorie.lesenCSVtoTransaktionenListe(file);
    }

    public Depot depotErstellen(Database datenbasis) throws IOException
    {
        Depot depot = new Depot();


        for (Transaktion t : this.kaufHistorie)
        {
            HistorischeDatenListe historischeDatenListe;
            historischeDatenListe = datenbasis.getHistorischeDatenListeAusDatenBasis(t.aktienName);
            if(!t.handelsAktion)
            {
                depot.hinzufuegen(t.datum, t.anzahl, historischeDatenListe);
            }

        }
        return depot;
    }

}
