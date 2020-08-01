package Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class HistorischeDatenListe
{
    //__________________________________________________________________________________________________________________
    //Objekt Variablen

    public ArrayList<TagesInfo> historischeDatenListe = new ArrayList<TagesInfo>();
    private String name;

    //__________________________________________________________________________________________________________________
    //Konstruktoren

    public HistorischeDatenListe(File file) throws IOException
    {
        this.historischeDatenListe = LesenCsv.lesenCSV(file);
        this.name = getNameAusFile(file);
    }

    //__________________________________________________________________________________________________________________
    //Methoden

    public void ausgabeHistorischeDatenListeKonsole()
    {
        for (TagesInfo t : this.historischeDatenListe)
        {
            System.out.println(t.toString());
        }
    }

    public  TagesInfo getTagesInformationen(String datum) throws IOException
    {
        if(!eingabeDatumUeberpruefen(datum))
        {
            throw new IOException();
        }
        for (TagesInfo t : this.historischeDatenListe)
        {
            if(t.getDatum().equals(datum)) return t;
        }
        return null;
    }

    /**
     * Ueberprueft, ob das Datum dem vormart JJJJ-MM-DD entspricht.
     * @param datum zu ueberpruefendes Datum
     * @return true wenn es dem Format entspricht, false wenn nicht
     */
    public static boolean eingabeDatumUeberpruefen(String datum)
    {
        String[] datumSplit = datum.split("-");
        return datumSplit.length == 3 && datumSplit[0].length() == 4 && datumSplit[1].length() == 2 && datumSplit[2].length() == 2;

    }

    public static String getNameAusFile(File file)
    {
        String stringFile = file.toString();
        String[] strings = stringFile.split("/");
        String nameMitDateiEndung = strings[strings.length -1];
        String[] nameMitDateiEndungListe = nameMitDateiEndung.split("\\.");
        return nameMitDateiEndungListe[0] + "." + nameMitDateiEndungListe[1];
    }

    public String getName()
    {
        return this.name;
    }

}
