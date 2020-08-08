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


/**
 * Die Klasse Main beinhaltet die main Methode, über die das Programm gestartet wird.
 */
public class Main
{
    /**
     * Die CSV Datei, die informationen über die Transaktionen des Anwenders beinhaltet, muss vom Anwender in den
     * Ordner database gelegt werden.
     */
    static File kaufHistorieFile = new File("database/KaufHistorie.csv");

    /**
     * Startmethode des Programmes.
     *
     * @param args input Argumente
     * @throws IOException                      input, output Exceptions
     * @throws DatumFehler                      Exception bei einem Fehler mit einem Datum
     * @throws AktieNichtVorhanden              Exception wenn eine Aktie nicht vorhanden ist
     * @throws TagesInformationenNichtVorhanden Exception wenn Tagesinformationen nicht vorhanden sind
     * @throws FehlerCSVInhalt                 Exception wenn der inhalt einer CSV Datei fehlerhaft ist.
     */
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
        System.out.println("_____________");
    }


}
