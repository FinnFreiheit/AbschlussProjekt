package error;

public class AktieNichtVorhanden extends Exception
{
    /**
     * Exception wenn eine Aktie nicht vorhanden ist
     * @param message Fehlernachricht
     */
    public AktieNichtVorhanden(String message)
    {
        super(message);
    }
}
