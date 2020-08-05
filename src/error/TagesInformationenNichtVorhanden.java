package error;

public class TagesInformationenNichtVorhanden extends Exception
{
    /**
     * Exception wenn Tagesinformationen nicht vorhanden sind.
     * @param message Fehlernachricht
     */
    public TagesInformationenNichtVorhanden(String message)
    {
        super(message);
    }
}
