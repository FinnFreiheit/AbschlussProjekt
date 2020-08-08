package controller;

import error.AktieNichtVorhanden;
import error.DatumFehler;
import error.FehlerCSVInhalt;
import error.TagesInformationenNichtVorhanden;
import helper.GenerateCSV;
import model.Database;
import model.Depot;
import model.HistorischeDatenListe;
import model.KaufHistorie;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

// TODO: 05.08.20 Format ausgabe auf zwei Nachkommastellen
public class Main
{
    static File kaufHistorieFile = new File("database/KaufHistorie.csv");

    //__________________________________________________________________________________________________________________
    //Main Methode
    public static void main(String[] args)
            throws IOException, DatumFehler, AktieNichtVorhanden, TagesInformationenNichtVorhanden, FehlerCSVInhalt
    {
        System.out.printf("%n%n------ 1. alle csv-Dateien laden ------%n%n");
        GenerateCSV.loadCSV();

        System.out.printf("%n%n------ 2. Datenbasis erstellen --------%n%n");
        Database datenbasis = Database.generateDatabase();

        System.out.printf("%n%n------ 3. Kaufhistorie einlesen -------%n%n");
        KaufHistorie kaufHistorie = new KaufHistorie(kaufHistorieFile);

        System.out.printf("%n%n------ 4. Depot erstellen -------------%n%n");
        Depot meinDepot = kaufHistorie.depotErstellen(datenbasis);

        System.out.printf("%n%n------ 5. GUI starten -----------------%n%n");
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new view.MainWindow(meinDepot);
            }
        });

        System.out.println("______________________________");
        //meinDepot.ausgabeDepot();
        //System.out.println(meinDepot.getInvestition());


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
        *//*

*/
    }


}
