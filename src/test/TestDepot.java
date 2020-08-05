package test;

import error.AktieNichtVorhanden;
import error.DatumFehler;
import error.FehlerCSVInhalt;
import error.TagesInformationenNichtVorhanden;
import model.Depot;
import model.HistorischeDatenListe;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TestDepot
{
    static File file = new File("/Users/Finn/IdeaProjects/Aktien/src/test/DAI.DE.csv");
    HistorischeDatenListe daimler;
    Depot meinDepot;

    @BeforeEach
    void setUp() throws IOException, FehlerCSVInhalt
    {
        daimler = new HistorischeDatenListe(file);
        meinDepot = new Depot();
    }

    @AfterEach
    void tearDown()
    {
    }

    @Test
    void kaufen_verkaufen() throws DatumFehler, TagesInformationenNichtVorhanden, AktieNichtVorhanden
    {
        //Kaufen
        meinDepot.kaufen("2019-08-06",10,daimler);
        assertEquals(meinDepot.getAnzahlAktien(),10);
        meinDepot.kaufen("2019-08-07",10,daimler);
        assertEquals(meinDepot.getAnzahlAktien(),20);

        //Verkaufen
        meinDepot.verkaufen(daimler.getTagesInformationen("2019-08-08").durchschnittsTagesPreis(),
                10,daimler.getName());
        assertEquals(meinDepot.getAnzahlAktien(),10);

        try
        {
            meinDepot.verkaufen(20,10,"SIE.DE");
        }
        catch (AktieNichtVorhanden e)
        {
            System.out.println(e.getMessage());
        }
    }



    @Test
    void wertDepot() throws DatumFehler, TagesInformationenNichtVorhanden, AktieNichtVorhanden
    {
        meinDepot.kaufen("2019-08-06",10,daimler);
       // assertEquals(437.6,meinDepot.wertDepot());

    }

    @Test
    void ausgabeDepot() throws DatumFehler, TagesInformationenNichtVorhanden, AktieNichtVorhanden
    {
        meinDepot.kaufen("2019-08-06",10,daimler);
        meinDepot.ausgabeDepot();
    }

    @Test
    void getAnzahlAktien() throws DatumFehler, TagesInformationenNichtVorhanden, AktieNichtVorhanden
    {
        meinDepot.kaufen("2019-08-06",10,daimler);
        assertEquals(10,meinDepot.getAnzahlAktien());
    }

    @Test
    void getAktie() throws AktieNichtVorhanden, TagesInformationenNichtVorhanden, DatumFehler
    {
        meinDepot.kaufen("2019-08-06",10,daimler);
        try
        {
            assertEquals("DAI.DE", meinDepot.getAktie("DAI.DE").getName());

            //Erwarte eine Exception bei SIE.DE
            meinDepot.getAktie("SIE.DE");
        } catch (AktieNichtVorhanden aktieNichtVorhanden)
        {

            System.out.println(aktieNichtVorhanden.getMessage());
        }
    }
}