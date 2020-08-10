package model;

/**
 * Eine Transaktion besteht aus einem Namen der Aktie, einer Anzahl der gehandelten Aktien, dem Handelstag,
 * und der Handelsaktion (Kaufen, Verkaufen)
 */
public class Transaktion
{
    String datum;
    int anzahl;
    String aktienName;
    boolean handelsAktion;

    /**
     * Konstruktor
     *
     * @param datum         Handelsdatum
     * @param aktienName    Aktienname
     * @param anzahl        anzahl der gehandelten Aktien
     * @param handelsAktion Handelsaktion
     */
    public Transaktion(String datum, String aktienName, int anzahl, String handelsAktion)
    {
        this.datum = datum;
        this.aktienName = aktienName;
        this.anzahl = anzahl;
        //true fuer Kaufen
        this.handelsAktion = handelsAktion.equals("k");
    }
}
