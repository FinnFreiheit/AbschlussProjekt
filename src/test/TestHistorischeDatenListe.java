package test;

import error.AktieNichtVorhanden;
import error.DatumFehler;
import error.FehlerCSVInhalt;
import error.TagesInformationenNichtVorhanden;
import model.HistorischeDatenListe;
import model.TagesInfo;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TestHistorischeDatenListe
{
    HistorischeDatenListe daimler;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws IOException, FehlerCSVInhalt
    {
        daimler = new HistorischeDatenListe(new File("/Users/Finn/IdeaProjects/Aktien/src/test/DAI.DE.csv"));
    }

    @org.junit.jupiter.api.Test
    void ausgabeHistorischeDatenListeKonsole()
    {
        daimler.ausgabeHistorischeDatenListeKonsole();
    }

    @org.junit.jupiter.api.Test
    void getTagesInformationen() throws DatumFehler, TagesInformationenNichtVorhanden
    {
        TagesInfo referenzObj = new TagesInfo("2019-08-06",44.145000,43.279999,
                44.305000,43.215000,4018085);
        TagesInfo tagesInfo1;
        tagesInfo1 = daimler.getTagesInformationen("2019-08-06");
        assertEquals(tagesInfo1, referenzObj);
        //Erwarte DatumFehler Exception dar das Datum nicht das richtige Format besitzt
        try
        {
            daimler.getTagesInformationen("19-20-2020");
        }
        catch (DatumFehler e)
        {
            System.out.println(e.getMessage());
        }

        //Erwarte TagesInformationenNichtVorhanden Exception
        try
        {
            daimler.getTagesInformationen("2021-01-01");
        }
        catch (TagesInformationenNichtVorhanden e)
        {
            System.out.println(e.getMessage());
        }




    }
    //todo Funktioniert nicht mehr da Private
    @org.junit.jupiter.api.AfterEach
    void tearDown()
    {
    }
}