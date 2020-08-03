package Model;

import java.util.ArrayList;

public class Database
{
    private ArrayList<HistorischeDatenListe> datenBasis;

    public Database()
    {
        datenBasis = new ArrayList<>();
    }

    public void addData(HistorischeDatenListe data)
    {
        this.datenBasis.add(data);
    }

    public HistorischeDatenListe getHistorischeDatenListeAusDatenBasis(String name)
    {
        for (HistorischeDatenListe historischeDatenListe : this.datenBasis)
        {
            if(historischeDatenListe.getName().equals(name)) return historischeDatenListe;
        }
       return null;
    }
}
