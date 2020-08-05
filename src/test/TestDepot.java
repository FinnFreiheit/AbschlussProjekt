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
    static File file = new File("/Users/Finn/IdeaProjects/Aktien/DAI.DE.csv");
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
    void loeschen() throws IOException
    {

    }

    @Test
    void hinzufuegen() throws DatumFehler, TagesInformationenNichtVorhanden, AktieNichtVorhanden
    {
        meinDepot.kaufen("2019-08-06",10,daimler);
    }

    @Test
    void wertDepot() throws DatumFehler, TagesInformationenNichtVorhanden, AktieNichtVorhanden
    {
        meinDepot.kaufen("2019-08-06",10,daimler);
        assertEquals(437.6,meinDepot.wertDepot());

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
}