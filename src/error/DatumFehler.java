package error;

/**
 * Exception für fehler mit einem Datum
 */
public class DatumFehler extends Exception
{
    /**
     * Konstruktor
     * @param message Fehlernachricht
     */
    public DatumFehler(String message)
    {
        super(message);
    }
}
