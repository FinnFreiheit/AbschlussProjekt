package Model;

public class Aktie
{
    private int anzahl;
    private String name;
    private double preis;

    public Aktie(String name, int anzahl, double preis)
    {
        this.name = name;
        this.anzahl = anzahl;
        this.preis = preis;
    }

    public Aktie(TagesInfo tagesInfo, String name, int anzahl)
    {
        this.name = name;
        this.anzahl = anzahl;
        this.preis = tagesInfo.durchschnittsTagesPreis();
    }

    public int getAnzahl()
    {
        return anzahl;
    }

    public void setAnzahl(int anzahl)
    {
        this.anzahl = anzahl;
    }

    public double getPreis()
    {
        return preis;
    }

    public String getName()
    {
        return name;
    }

    public void setPreis(double preis, int anz)
    {
        double alterWert = this.preis * this.anzahl;
        double neuerWert = alterWert + (preis * anz);

        this.preis = (neuerWert / (anz + this.anzahl));
    }

    @Override
    public String toString()
    {
        return this.name + " " + this.anzahl + " " + this.preis;
    }


}
