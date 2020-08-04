package Model;

import java.io.IOException;
import java.util.ArrayList;

// TODO: 01.08.20 Depot in CSV Speichern

public class Depot
{
    private ArrayList<Aktie> depot;
    private double wert;

    public Depot()
    {
        depot = new ArrayList<>();
        this.wert = 0;
    }


    public void loeschen(String datum, int anz, String name) throws IOException
    {

    }

    public void hinzufuegen(String datum, int anz, HistorischeDatenListe hdl) throws IOException
    {
        boolean temp = aktieIstVorhanden(hdl.getName());

        if (!temp)
        {
            Aktie aktie;
            aktie = new Aktie(hdl.getTagesInformationen(datum), hdl.getName(), anz);
            this.depot.add(aktie);
        }
        else
        {
            getAktie(hdl.getName()).setPreis(hdl.getTagesInformationen(datum).durchschnittsTagesPreis(), anz);
            getAktie(hdl.getName()).setAnzahl(getAktie(hdl.getName()).getAnzahl() + anz);
        }
    }

    public double wertDepot()
    {
        double wert = 0;

        for (Aktie a : this.depot)
        {
            wert += a.getPreis() * a.getAnzahl();

        }
        return wert;
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

    public boolean aktieIstVorhanden(String name)
    {
        for (Aktie a : this.depot)
        {
            if (a.getName().equals(name)) return true;
        }
        return false;
    }

    public Aktie getAktie(String name)
    {
        for (Aktie a : this.depot)
        {
            if (a.getName().equals(name)) return a;
        }
        return null;
    }


}
