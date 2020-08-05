package model;

import error.AktieNichtVorhanden;
import error.DatumFehler;
import error.FehlerCSVInhalt;
import error.TagesInformationenNichtVorhanden;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

// TODO: 05.08.20 Format ausgabe auf zwei Nachkommastellen
public class Main
{
    static File daimlerFile = new File("/Users/Finn/IdeaProjects/Aktien/src/Database/DAI.DE.csv");
    static File siemensFile = new File("/Users/Finn/IdeaProjects/Aktien/src/Database/SIE.DE.csv");
    static File infineonFile = new File("/Users/Finn/IdeaProjects/Aktien/src/Database/IFX.DE.csv");
    static File kaufHistorieFile = new File("/Users/Finn/IdeaProjects/Aktien/src/Database/KaufHistorie.csv");
    static File schreibeFile = new File("/Users/Finn/IdeaProjects/Aktien/src/Test/DaimlerTestSChreiben.csv");
    //__________________________________________________________________________________________________________________
    //Main Methode
    public static void main(String[] args) throws IOException, DatumFehler, AktieNichtVorhanden, TagesInformationenNichtVorhanden, FehlerCSVInhalt
    {
        HistorischeDatenListe daimler = new HistorischeDatenListe(daimlerFile);
        HistorischeDatenListe siemens = new HistorischeDatenListe(siemensFile);
        HistorischeDatenListe infineon = new HistorischeDatenListe(infineonFile);

        Database datenbasis = new Database();
        datenbasis.addData(daimler);
        datenbasis.addData(siemens);
        datenbasis.addData(infineon);

        KaufHistorie kaufHistorie = new KaufHistorie(kaufHistorieFile);

        Depot meinDepot = kaufHistorie.depotErstellen(datenbasis);

        System.out.println("______________________________");
        meinDepot.ausgabeDepot();
        System.out.println(meinDepot.getInvestition());





        //daimler.ausgabeHistorischeDatenListeKonsole();
        //SchreibenCsv.SchreibeCSV(daimler,schreibeFile);
        //System.out.println(daimler.getTagesInformationen("2019-08-06").toString());


        System.out.println("_____________");

       /* meinDepot.hinzufuegen("2019-08-06",10,daimler);
        System.out.println(meinDepot.getAnzahlAktien());
        meinDepot.ausgabeDepot();
        meinDepot.hinzufuegen("2019-08-06",10,daimler);
        System.out.println(meinDepot.getAnzahlAktien());
        meinDepot.ausgabeDepot();
        meinDepot.loeschen("2019-08-06", 5,"DAI.DE");
        System.out.println(meinDepot.getAnzahlAktien());
        meinDepot.ausgabeDepot();
        meinDepot.hinzufuegen("2019-08-07",1,daimler);
        meinDepot.loeschen("2019-08-06", 15,"DAI.DE");
        meinDepot.ausgabeDepot();
        */



    }


}
