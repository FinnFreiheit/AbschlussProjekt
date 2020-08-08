package error;

/**
 * Exception bei einem Fehler mit dem Inhalt der CSV-Datei
 */
public class FehlerCSVInhalt extends Exception
{
    /**
     * Konstruktor
     * @param message Fehlernachricht
     */
    public FehlerCSVInhalt(String message)
    {
        super(message);
    }
}
