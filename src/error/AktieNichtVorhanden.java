package error;

/**
 * Exception wenn eine Aktie nicht vorhanden ist
 */
public class AktieNichtVorhanden extends Exception
{
    /**
     * Konstruktor
     * @param message Fehlernachricht
     */
    public AktieNichtVorhanden(String message)
    {
        super(message);
    }
}
