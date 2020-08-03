package Model;

public class Transaktion
{
    String datum;
    int anzahl;
    String aktienName;
    boolean handelsAktion;

    public Transaktion(String datum, String aktienName, int anzahl, String handelsAktion)
    {
        this.datum = datum;
        this.aktienName = aktienName;
        this.anzahl = anzahl;
        //true fuer Kaufen
        this.handelsAktion = handelsAktion.equals("k");
    }


}
