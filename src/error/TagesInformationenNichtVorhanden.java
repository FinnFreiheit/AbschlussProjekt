package error;

/**
 * Exception wenn Tagesinformationen nicht vorhanden sind.
 */
public class TagesInformationenNichtVorhanden extends Exception
{
    /**
     * Konstruktor
     * @param message Fehlernachricht
     */
    public TagesInformationenNichtVorhanden(String message)
    {
        super(message);
    }
}
