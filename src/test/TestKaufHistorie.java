package test;

import error.AktieNichtVorhanden;
import error.DatumFehler;
import error.FehlerCSVInhalt;
import error.TagesInformationenNichtVorhanden;
import io.csv.LesenCSVKaufHistorie;
import model.Database;
import model.Depot;
import model.HistorischeDatenListe;
import model.KaufHistorie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

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
        InputStream in = LesenCSVKaufHistorie.class.getClassLoader().getResourceAsStream("io/csv/database/KaufHistorie.csv");
        testKaufHistorie = new KaufHistorie(in);
        //daimler = new HistorischeDatenListe(new File("./src/test/DAI.DE.csv"));
        data.addData(daimler);

    }

    @Test
    void depotErstellen()
    {
        Depot testDepot = new Depot();
        try
        {
            testDepot = testKaufHistorie.depotErstellen(data);
        } catch (DatumFehler | AktieNichtVorhanden | TagesInformationenNichtVorhanden datumFehler)
        {
            datumFehler.printStackTrace();
        }
    }
}
