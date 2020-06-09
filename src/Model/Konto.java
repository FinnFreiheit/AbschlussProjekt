package Model;

public class Konto
{
    private double vermoegen;

    private void einzahlen(double geld)
    {
        this.vermoegen = this.vermoegen + geld;
    }

    private void bezahlen(double geld)
    {
        this.vermoegen = this.vermoegen - geld;
    }
}
