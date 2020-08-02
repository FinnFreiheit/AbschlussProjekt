package Model;

import java.io.IOException;
import java.util.ArrayList;

// TODO: 01.08.20 Depot in CSV Speichern

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
        boolean temp = aktieIstVorhanden(datum);

        if(!temp)
        {
            Aktie aktie;
            aktie = new Aktie(hdl.getTagesInformationen(datum), anz, hdl.getName());
            this.depot.add(aktie);
        }
        else
        {
           getAktie(datum).setAnzahl(getAktie(datum).getAnzahl() + anz);

        }

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

    public int getAnzahlAktien()
    {
        int zaehler = 0;
        for (Aktie a : this.depot)
        {
            zaehler += a.getAnzahl();
        }
        return zaehler;
    }

    public boolean aktieIstVorhanden(String datum)
    {
        for (Aktie a : this.depot)
        {
            if(a.getDatum().equals(datum)) return true;
        }
        return false;
    }

    public Aktie getAktie(String datum)
    {
        for (Aktie a : this.depot)
        {
            if (a.getDatum().equals(datum)) return a;
        }
        return null;
    }

}
