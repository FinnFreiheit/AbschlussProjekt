package model;

import error.AktieNichtVorhanden;
import error.DatumFehler;

import java.io.IOException;
import java.util.ArrayList;

// TODO: 01.08.20 Depot in CSV Speichern


/**
 * Das Depot ist eine Liste aus Aktien, und es besitzt ein gesammt Wert, der sich aus allen Aktien zusammensetzt.
 */
public class Depot
{
    //Objektvariablen
    private ArrayList<Aktie> depot;
    private double wert;

    /**
     * Konstruktor
     */
    public Depot()
    {
        depot = new ArrayList<>();
        this.wert = 0;
    }


    /**
     * Loeschen.
     *
     * @param preis verkaufspreis
     * @param anz   the anz
     * @param name  the name
     * @throws IOException the io exception
     */
    public void verkaufen(double preis, int anz, String name) throws AktieNichtVorhanden
    {
        if(!aktieIstVorhanden(name))
        {
            throw new AktieNichtVorhanden("Fehler: Aktie nicht vorhanden");
        }
        else
        {
          getAktie(name).setPreis(preis,-anz);
          getAktie(name).setAnzahl(getAktie(name).getAnzahl() - anz);
        }

    }

    /**
     * Dem Depot wird eine neue Aktie hinzugefügt. Dazu werden die Informationen benötigt, wann man die Aktie
     * gekauft hat, Welche Aktie man gekauft hat und wie viele Aktien man kauft.
     * Es wird ein neues Objekt der Klasse Aktie anhand dieser Informationen erstellt.
     *
     * @param datum das Datum an dem eine Aktie gekauft wurde
     * @param anz   die Anzahl der gekauften Aktien
     * @param hdl   Die historischen Kursdaten, der Aktie. Anhand der Liste und des Datums wird der Preis ermittelt
     * @throws DatumFehler the io exception wenn das Datum nicht vorhanden ist, oder falsch.
     */
    public void kaufen(String datum, int anz, HistorischeDatenListe hdl) throws DatumFehler
    {
        //Wenn die Aktie noch nicht im depot vorhanden ist, wird ein neues Objekt der Klasse Aktie erzeugt
        if (!aktieIstVorhanden(hdl.getName()))
        {
            Aktie aktie;
            aktie = new Aktie(hdl.getTagesInformationen(datum), hdl.getName(), anz);
            this.depot.add(aktie);
        }
        //wenn die Aktie schon im Depot vorhanden ist, wird die Anzahl und der Durchschnittspreis angepasst
        else
        {
            getAktie(hdl.getName()).setPreis(hdl.getTagesInformationen(datum).durchschnittsTagesPreis(), anz);
            getAktie(hdl.getName()).setAnzahl(getAktie(hdl.getName()).getAnzahl() + anz);
        }
    }

    // TODO: 04.08.20 Wert
    /**
     * Wert Depot double.
     *
     * @return Wert vom Depot
     */
    public double wertDepot()
    {
        double wert = 0;

        for (Aktie a : this.depot)
        {
            wert += a.getPreis() * a.getAnzahl();

        }
        return wert;
    }

    /**
     * Ausgabe Depot auf Konsole.
     */
    public void ausgabeDepot()
    {
        for (Aktie a : this.depot)
        {
            System.out.println(a.toString());
        }
    }

    /**
     * Gets anzahl aktien.
     *
     * @return the anzahl aktien
     */
    public int getAnzahlAktien()
    {
        int zaehler = 0;
        for (Aktie a : this.depot)
        {
            zaehler += a.getAnzahl();
        }
        return zaehler;
    }

    /**
     * überprüft ob die Aktie im Depot vorhanden ist boolean.
     *
     * @param name Name der Aktie
     * @return the boolean true wenn vorhanden, false wenn nicht
     */
    public boolean aktieIstVorhanden(String name)
    {
        for (Aktie a : this.depot)
        {
            if (a.getName().equals(name)) return true;
        }
        return false;
    }

    /**
     * Gets aktie.
     *
     * @param name Name der Aktie bsp.: DAI.DE, SIE.DE
     * @return die Aktie mit dem Namen
     */
    public Aktie getAktie(String name)
    {
        for (Aktie a : this.depot)
        {
            if (a.getName().equals(name)) return a;
        }
        return null;
    }
}
