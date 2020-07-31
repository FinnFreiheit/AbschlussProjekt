package Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class HistorischeDatenListe
{
    public static void main(String[] args) throws IOException
    {
        HistorischeDatenListe daimler = new HistorischeDatenListe(file);
        daimler.ausgabeHistorischeDatenListeKonsole();
        SchreibenCsv.SchreibeCSV(daimler,schreibeFile);

    }
    //________________________________________________________________________________________________________________

    ArrayList<TagesInfo> historischeDatenListe = new ArrayList<TagesInfo>();

    // TODO: 31.07.20 File einlesen
    static File file = new File("/Users/Finn/IdeaProjects/Aktien/DAI.DE.csv");
    static File schreibeFile = new File("/Users/Finn/IdeaProjects/Aktien/src/Test/DaimlerTestSChreiben.csv");

    public HistorischeDatenListe(File file) throws IOException
    {
        this.historischeDatenListe = LesenCsv.lesenCSV(file);
    }

    public void ausgabeHistorischeDatenListeKonsole()
    {
        for (TagesInfo t : this.historischeDatenListe)
        {
            System.out.println(t.toString());
        }
    }

}
