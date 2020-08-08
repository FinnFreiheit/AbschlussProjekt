package model;

import error.AktieNichtVorhanden;
import error.DatumFehler;
import error.FehlerCSVInhalt;
import error.TagesInformationenNichtVorhanden;
import io.csv.LesenCSVKaufHistorie;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Die Kaufhistorie beinhaltet alle Transaktionen, die der Nutzer getätigt hat.
 * Die Kaufhistorie besteht aus einer Liste aus Transaktionen.
 */
public class KaufHistorie
{
    //Objektvariablen
    public ArrayList<Transaktion> kaufHistorie;

    /**
     * Konstruktor
     *
     * @param file Die Transaktionen werden aus einer CSV Datei eingelesen.
     * @throws IOException the io exception bei fehler mit der Datei
     */
    public KaufHistorie(File file) throws IOException, FehlerCSVInhalt
    {
        this.kaufHistorie = LesenCSVKaufHistorie.lesenCSVtoTransaktionenListe(file);
    }

    /**
     * Erstellt ein Objekt der Klasse Depot anhand der Kaufhistorie
     *
     * @param datenbasis die Datenbasis
     * @return das Depot
     * @throws DatumFehler the io exception bei einem fehler mit dem Datum
     */
    public Depot depotErstellen(Database datenbasis)
            throws DatumFehler, AktieNichtVorhanden, TagesInformationenNichtVorhanden
    {
        final String RED = "\u001B[31m";
        final String RESET = "\u001B[0m";
        final String GREEN = "\u001B[32m";

        String euro = "\u20ac";


        Depot depot = new Depot();
        for (Transaktion t : this.kaufHistorie)
        {
            System.out.println("___________________________________");

            System.out.println("Datum " + t.datum);

            HistorischeDatenListe historischeDatenListe;
            historischeDatenListe = datenbasis.getHistorischeDatenListeAusDatenBasis(t.aktienName);
            if (t.handelsAktion)
            {
                depot.kaufen(t.datum, t.anzahl, historischeDatenListe);

                System.out.println("Die Aktie " + t.aktienName + " wurde für " + String.format("%.2f",
                                                                                               historischeDatenListe.getTagesInformationen(
                                                                                                       t.datum)
                                                                                                                    .durchschnittsTagesPreis()) +
                                           euro + " gekauft");

            }
            else
            {
                depot.verkaufen(t.datum, historischeDatenListe.getTagesInformationen(t.datum).durchschnittsTagesPreis(),
                                t.anzahl, historischeDatenListe.getName());

                System.out.println("Die Aktie " + t.aktienName + " wurde für " + String.format("%.2f",
                                                                                               historischeDatenListe.getTagesInformationen(
                                                                                                       t.datum)
                                                                                                                    .durchschnittsTagesPreis()) +
                                           euro + " verkauft");

            }


            depot.ausgabeDepot();
            System.out.println("Investiert: " + String.format("%.2f",depot.getInvestition()) + euro);
            if (depot.depotWertZumZeitpunkt(t.datum, datenbasis) >= 0)
            {
                System.out.println(
                        GREEN + "Gewinn: " + String.format("%.2f",depot.depotWertZumZeitpunkt(t.datum, datenbasis))  +
                                                                   euro + RESET);
            }
            else
            {
                System.out.println(RED + "Verlust: " + String.format("%.2f",depot.depotWertZumZeitpunkt(t.datum,
                                                                                                  datenbasis)) + euro + RESET);
            }
            System.out.println("___________________________________");

        }
        return depot;
    }
}
