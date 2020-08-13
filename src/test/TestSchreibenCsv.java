package test;

import io.csv.SchreibenCsv;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestSchreibenCsv
{

    @BeforeEach
    void setUp()
    {

    }

    @Ignore ("Schreiben und Kaufen verändert die Kaufhistorie CSV ")
    void schreibeCSVtest()
    {
        SchreibenCsv.schreibeKaufen("12-08-2020","DAI.DE","10");
        SchreibenCsv.schreibeVerkaufen("12-08-2020","DAI.DE","10");

    }
}