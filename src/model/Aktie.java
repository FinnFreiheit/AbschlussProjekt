package model;


/**
 * Die Klasse {@code Aktie} besitzt die Objektvariablen {@code Aktienname}, {@code Anzahl} der Aktien, die man besitzt,
 * und den {@code Durchschnittspreis} für die man die Aktien erworben hat.
 */
public class Aktie
{
    //Objekt Variablen
    private int anzahl;
    private String name;
    private double preis;

    /**
     * Konstruktor
     *
     * @param name   Der Name der Aktie bsp.: DAI.DE, SIE.DE
     * @param anzahl die Anzahl der Aktien, die man besitzt.
     * @param preis  der Durchschnittspreis der erworbenen Aktien
     */
    public Aktie(String name, int anzahl, double preis)
    {
        this.name = name;
        this.anzahl = anzahl;
        this.preis = preis;
    }

    /**
     * Konstruktor
     *
     * @param tagesInfo Die Informationen einer Aktie von einem speziellem Tag. Der Preis lässt sich daraus ermitteln
     * @param name      Der Name der Aktie bsp.: DAI.DE, SIE.DE
     * @param anzahl    die Anzahl der Aktien, die man besitzt.
     */
    public Aktie(TagesInfo tagesInfo, String name, int anzahl)
    {
        this.name = name;
        this.anzahl = anzahl;
        this.preis = tagesInfo.durchschnittsTagesPreis();
    }

    /**
     * Get anzahl.
     *
     * @return the anzahl
     */
    public int getAnzahl()
    {
        return anzahl;
    }

    /**
     * Set anzahl.
     *
     * @param anzahl zu setzende Anzahl
     */
    public void setAnzahl(int anzahl)
    {
        this.anzahl = anzahl;
    }

    /**
     * Get preis.
     *
     * @return  preis
     */
    public double getPreis()
    {
        return preis;
    }

    /**
     * Gets name.
     *
     * @return  name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets preis.
     *
     * @param preis Preis der Aktien
     * @param anz   anzahl der Aktien
     */
    public void setPreis(double preis, int anz)
    {
        double alterWert = this.preis * this.anzahl;
        double neuerWert = alterWert + (preis * anz);

        this.preis = (neuerWert / (this.anzahl + anz));
    }
    @Override
    public String toString()
    {
        return this.name + " " + this.anzahl + " " + this.preis;
    }
}
