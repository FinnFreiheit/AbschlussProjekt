package Test;

import Model.Depot;
import Model.HistorischeDatenListe;
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
    void setUp() throws IOException
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
    void hinzufuegen() throws IOException
    {
        meinDepot.kaufen("2019-08-06",10,daimler);
    }

    @Test
    void wertDepot() throws IOException
    {
        meinDepot.kaufen("2019-08-06",10,daimler);
        assertEquals(437.6,meinDepot.wertDepot());

    }

    @Test
    void ausgabeDepot() throws IOException
    {
        meinDepot.kaufen("2019-08-06",10,daimler);
        meinDepot.ausgabeDepot();
    }

    @Test
    void getAnzahlAktien() throws IOException
    {
        meinDepot.kaufen("2019-08-06",10,daimler);
        assertEquals(10,meinDepot.getAnzahlAktien());
    }
}