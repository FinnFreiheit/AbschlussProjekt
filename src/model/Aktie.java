package model;


/**
 * Die Klasse {@code Aktie} besitzt die Objektvariablen {@code Aktienname}, {@code Anzahl} der Aktien, die man besitzt,
 * und den {@code Durchschnittspreis} für die man die Aktien erworben hat.
 */
public class Aktie
{
    /**
     * Anzahl der Aktien.
     */
    private int anzahl;

    /**
     * Name der Aktie.
     */
    private String name;

    /**
     * Preis der Aktie.
     */
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
     * gibt die Anzahl der Aktien zurück
     *
     * @return the anzahl
     */
    public int getAnzahl()
    {
        return anzahl;
    }

    /**
     * Setzt die Anzahl der Aktien auf ein int bestimmten Wert.
     *
     * @param anzahl zu setzende Anzahl
     */
    public void setAnzahl(int anzahl)
    {
        this.anzahl = anzahl;
    }

    /**
     * gibt den Preis der Aktie zurück.
     *
     * @return preis preis
     */
    public double getPreis()
    {
        return preis;
    }

    /**
     * gibt den Namen der Aktie zurück.
     *
     * @return name name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Setzt den Preis auf ein bestimmten double wert.
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

    /**
     * Fügt alle Objektvariablen in einen String zusammen und gibt diesen zurück
     * @return Alle Objektvariablen in einem String
     */
    @Override
    public String toString()
    {
        return String.format("%-15s %-18d %.2f%s",this.name, this.anzahl, this.preis,"\u20ac");
    }
}
