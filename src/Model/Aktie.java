package Model;

public class Aktie extends TagesInfo
{
    private int anzahl;
    private String name;

    public Aktie(TagesInfo tagesInfo, int anzahl, String name)
    {
        super(tagesInfo);
        this.anzahl = anzahl;
        this.name = name;
    }

    public int getAnzahl()
    {
        return anzahl;
    }

    public String getDatum()
    {
        return super.getDatum();
    }

    public void setAnzahl(int anzahl)
    {
        this.anzahl = anzahl;
    }

    @Override
    public String toString()
    {
        return name + " " + super.toString() + " " + anzahl;
    }


}
