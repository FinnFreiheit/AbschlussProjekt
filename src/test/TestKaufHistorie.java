package test;

import error.AktieNichtVorhanden;
import error.DatumFehler;
import error.FehlerCSVInhalt;
import error.TagesInformationenNichtVorhanden;
import model.Database;
import model.Depot;
import model.HistorischeDatenListe;
import model.KaufHistorie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TestKaufHistorie
{
    KaufHistorie testKaufHistorie;
    Database data = Database.generateDatabase();
    //Database dummyData = new Database();
    HistorischeDatenListe daimler;

    @BeforeEach
    void setUp() throws IOException, FehlerCSVInhalt
    {
        testKaufHistorie = new KaufHistorie(new File("./src/test/KaufHistorie.csv"));
        daimler = new HistorischeDatenListe(new File("./src/test/DAI.DE.csv"));
        data.addData(daimler);

    }

    @Test
    void depotErstellen()
    {
        Depot testDepot = new Depot();
        try
        {
            testDepot = testKaufHistorie.depotErstellen(data);

            // TODO: 05.08.20 DataException
            //testDepot = testKaufHistorie.depotErstellen(dummyData);
        } catch (DatumFehler | AktieNichtVorhanden | TagesInformationenNichtVorhanden datumFehler)
        {
            datumFehler.printStackTrace();
        }
    }
}
