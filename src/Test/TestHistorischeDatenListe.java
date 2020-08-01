package Test;

import Model.HistorischeDatenListe;
import Model.TagesInfo;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TestHistorischeDatenListe
{
    HistorischeDatenListe daimler;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws IOException
    {
        daimler = new HistorischeDatenListe(new File("/Users/Finn/IdeaProjects/Aktien/DAI.DE.csv"));
    }

    @org.junit.jupiter.api.Test
    void ausgabeHistorischeDatenListeKonsole()
    {
    }

    @org.junit.jupiter.api.Test
    void getTagesInformationen() throws IOException
    {
        TagesInfo referenzObj = new TagesInfo("2019-08-06",44.145000,43.279999,
                44.305000,43.215000,4018085);
        TagesInfo tagesInfo1;

        tagesInfo1 = daimler.getTagesInformationen("2019-08-06");
        assertEquals(tagesInfo1, referenzObj);


    }

    @org.junit.jupiter.api.Test
    void eingabeDatumUeberpruefen()
    {
        assertTrue(HistorischeDatenListe.eingabeDatumUeberpruefen("2020-12-12"));
        assertFalse(HistorischeDatenListe.eingabeDatumUeberpruefen("12-12-20"));
        assertFalse(HistorischeDatenListe.eingabeDatumUeberpruefen("12-2020-12"));
        assertFalse(HistorischeDatenListe.eingabeDatumUeberpruefen("12-12"));
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown()
    {
    }
}