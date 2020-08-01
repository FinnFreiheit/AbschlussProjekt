package Model;

import java.io.File;
import java.io.IOException;

public class Main
{
    static File file = new File("/Users/Finn/IdeaProjects/Aktien/DAI.DE.csv");
    static File schreibeFile = new File("/Users/Finn/IdeaProjects/Aktien/src/Test/DaimlerTestSChreiben.csv");
    //__________________________________________________________________________________________________________________
    //Main Methode
    public static void main(String[] args) throws IOException
    {
        HistorischeDatenListe daimler = new HistorischeDatenListe(file);
        Depot meinDepot = new Depot();


        //daimler.ausgabeHistorischeDatenListeKonsole();
        //SchreibenCsv.SchreibeCSV(daimler,schreibeFile);
        //System.out.println(daimler.getTagesInformationen("2019-08-06").toString());

        meinDepot.hinzufuegen("2019-08-06",10,daimler);
        meinDepot.hinzufuegen("2019-08-07",10,daimler);

        System.out.println(meinDepot.wertDepot());
        meinDepot.ausgabeDepot();

        meinDepot.loeschen("2019-08-06", 5,"DAI.DE");

        System.out.println(meinDepot.wertDepot());
        meinDepot.ausgabeDepot();

        meinDepot.loeschen("2019-08-06", 5,"DAI.DE");

        System.out.println(meinDepot.wertDepot());
        meinDepot.ausgabeDepot();



    }
}
