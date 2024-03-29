package model;

import error.AktieNichtVorhanden;
import error.DatumFehler;
import error.TagesInformationenNichtVorhanden;
import io.csv.SchreibenCsv;

import java.util.ArrayList;

/**
 * Das Depot ist eine Liste aus Aktien, und es besitzt ein gesammt Wert, der sich aus allen Aktien zusammensetzt.
 */
public class Depot
{
    /**
     * Liste aus Aktien
     */
    private ArrayList<Aktie> depot;

    /**
     * die Summe der Beträge, die gezahlt wurden, um die Aktien im Depot zu erwerben.
     */
    private double investition;

    /**
     * Konstruktor
     */
    public Depot()
    {
        this.depot = new ArrayList<>();
        this.investition = 0;

    }

    /**
     * Aktien, die sich im Depot befinden werden verkauft. Beim verkaufen einer Aktie wird die Anzahl der Aktien und
     * ihr durchschnittspreis angepasst.
     * @param datum Das Datum an dem eine Aktie verkauft wurde.
     * @param preis verkaufspreis
     * @param anz   the anz
     * @param name  the name
     * @throws AktieNichtVorhanden Exception wenn die Aktie die Verkauft werden soll nicht
     * vorhanden ist.
     * @pre man muss die Aktie besitzen wenn man sie Verkaufen möchte. Man kann nicht mehr Aktien verkaufen als man
     * besitzt
     * @inv Das Verkaufsdatum darf nicht in der Zukunft liegen.
     */
    public void verkaufen(String datum, double preis, int anz, String name) throws AktieNichtVorhanden
    {
        if(aktieIstNichtVorhanden(name))
        {
            throw new AktieNichtVorhanden("Die Aktie, die verkauft werden soll, ist nicht vorhanden");
        }
        else
        {
          this.getAktie(name).setPreis(preis,-anz);
          this.getAktie(name).setAnzahl(getAktie(name).getAnzahl() - anz);
        }
        this.investitionsBetragDepot();
    }

    /**
     * Dem Depot wird eine neue Aktie hinzugefügt. Dazu werden die Informationen benötigt wann man die Aktie
     * gekauft hat, welche Aktie man gekauft hat und wie viele Aktien man kauft.
     * Es wird ein neues Objekt der Klasse Aktie anhand dieser Informationen erstellt.
     * Wenn die Aktie bereits im Depot ist, wird die Anzahl und der Durchschnittspreis der Aktie angepasst.
     *
     * @param datum das Datum an dem eine Aktie gekauft wurde
     * @param anz   die Anzahl der gekauften Aktien
     * @param hdl   Die historischen Kursdaten, der Aktie. Anhand der Liste und des Datums wird der Preis ermittelt
     * @throws AktieNichtVorhanden Exception wenn die Aktien nicht vorhanden ist.
     * @throws DatumFehler Exception wenn das Datum nicht vorhanden ist, oder falsch.
     * @throws TagesInformationenNichtVorhanden Exception wenn die Tagesinformationen nicht vorhanden sind.
     * @pre die Börse war zum Kaufzeitpunkt geöffnet. Der Kauf ist nicht länger als ein Jahr her.
     * @inv die Aktie stammt aus dem DAX
     */
    public void kaufen(String datum, int anz, HistorischeDatenListe hdl) throws DatumFehler, AktieNichtVorhanden, TagesInformationenNichtVorhanden
    {
        //Wenn die Aktie noch nicht im depot vorhanden ist, wird ein neues Objekt der Klasse Aktie erzeugt
        if (aktieIstNichtVorhanden(hdl.getName()))
        {
            Aktie aktie;
            aktie = new Aktie(hdl.getTagesInformationen(datum), hdl.getName(), anz);
            this.depot.add(aktie);
        }
        //wenn die Aktie schon im Depot vorhanden ist, wird die Anzahl und der Durchschnittspreis angepasst
        else
        {
            this.getAktie(hdl.getName()).setPreis(hdl.getTagesInformationen(datum).durchschnittsTagesPreis(), anz);
            this.getAktie(hdl.getName()).setAnzahl(getAktie(hdl.getName()).getAnzahl() + anz);
        }
        this.investitionsBetragDepot();
    }


    /**
     * Wert Depot vom Typ {@code double}.
     */
    public void investitionsBetragDepot()
    {
        this.investition = 0;

        for (Aktie a : this.depot)
        {
            this.investition += a.getPreis() * a.getAnzahl();
        }
    }

    /**
     * Ausgabe des Depots auf Konsole.
     */
    public void ausgabeDepot()
    {
        System.out.println("Im Depot befinden sich folgende Aktien: ");
        System.out.println("");
        System.out.println(String.format("%-10s, %-10s, %-10s","Name der Aktie","Anzahl der Aktien",
                                         "Durchschnittskaufpreis"));
        for (Aktie a : this.depot)
        {
            System.out.println(a.toString());
        }
        System.out.println();
    }

    /**
     * Gibt die Anzahl der Aktien die sich im Depot befinden zurück.
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

    public double getInvestition()
    {
        return investition;
    }

    /**
     * überprüft ob die Aktie im Depot vorhanden ist. Typ {@code boolean}.
     *
     * @param name Name der Aktie
     * @return the boolean true wenn vorhanden, false wenn nicht
     */
    private boolean aktieIstNichtVorhanden(String name)
    {
        for (Aktie a : this.depot)
        {
            if (a.getName().equals(name)) return false;
        }
        return true;
    }

    /**
     * Gibt die Aktie mit dem gesuchten Namen zurück.
     *
     * @param name Name der Aktie bsp.: DAI.DE, SIE.DE
     * @return die Aktie mit dem Namen
     * @throws AktieNichtVorhanden Exception wenn die Aktie nicht vorhanden ist
     */
    public Aktie getAktie(String name) throws AktieNichtVorhanden
    {
        for (Aktie a : this.depot)
        {
            if (a.getName().equals(name)) return a;
        }
        throw new AktieNichtVorhanden("Aktie mit dem namen " + name + " ist nicht im Depot vorhanden vorhanden");
    }

    /**
     * Bestimmt den Wert des Depots zu einem bestimmten Zeitpunkt.
     *
     * @param datum Das Datum, zu dem der Wert des Depots bestimmt werden soll.
     * @param datenbasis Die Datenbasis.
     * @return Der Wert zu einem bestimmten Zeitpunkt.
     * @throws DatumFehler Exception bei einem Fehler mit dem Datum.
     * @throws TagesInformationenNichtVorhanden Exception wenn die Tagesinformation nicht vorhanden ist.
     */
    public double depotWertZumZeitpunkt(String datum, Database datenbasis) throws DatumFehler, TagesInformationenNichtVorhanden
    {
        double wert = 0;

        for (Aktie a : this.depot)
        {
            HistorischeDatenListe hdl = datenbasis.getHistorischeDatenListeAusDatenBasis(a.getName());
            double preis = hdl.getTagesInformationen(datum).durchschnittsTagesPreis();

            //Wert = (anzahl * durchschnitts Kaufpreis) - (anzahl * preis zum Datum x)
            wert += (a.getAnzahl() * a.getPreis()) - (a.getAnzahl() * preis);

        }
        return wert;
    }

    /**
     * Gibt das Depot zurück
     * @return Depot
     */
    public ArrayList<Aktie> getDepot()
    {
        return depot;
    }
}
