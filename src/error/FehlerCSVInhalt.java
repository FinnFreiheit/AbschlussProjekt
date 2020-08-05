package error;

public class FehlerCSVInhalt extends Exception
{
    /**
     *Exception bei einem Fehler mit dem Inhalt der CSV-Datei
     * @param message Fehlernachricht
     */
    public FehlerCSVInhalt(String message)
    {
        super(message);
    }
}
