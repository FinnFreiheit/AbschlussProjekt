package Model;

import java.io.IOException;
import java.util.ArrayList;

public class Depot
{
    private ArrayList<Aktie> depot;

    public Depot()
    {
        depot = new ArrayList<>();
    }


    public void loeschen(String datum, int anz,String name) throws IOException
    {
        for (Aktie a : this.depot)
        {
            if(a.getDatum().equals(datum))
            {
                if (a.getAnzahl() < anz) throw new IOException();
                else if (a.getAnzahl() == anz) this.depot.remove(a);
                else if (a.getAnzahl() > anz) a.setAnzahl(a.getAnzahl() - anz);
            }
        }

    }

    public void hinzufuegen(String datum, int anz, HistorischeDatenListe hdl) throws IOException
    {
        Aktie aktie;
        aktie = new Aktie(hdl.getTagesInformationen(datum), anz,hdl.getName());
        this.depot.add(aktie);

    }

    public double wertDepot()
    {
        double wert = 0;

        for (Aktie a : this.depot)
        {
            wert += a.durchschnittsTagesPreis() * a.getAnzahl();

        }
        return  wert;
    }

    public void ausgabeDepot()
    {
        for (Aktie a : this.depot)
        {
            System.out.println(a.toString());
        }
    }
}
