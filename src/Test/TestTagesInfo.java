package Test;

import Model.TagesInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestTagesInfo
{

    // temp = temp2 != temp3
    TagesInfo temp = new TagesInfo("2020-12-01",40.000,
            41.000,41.500,39.500,100);
    TagesInfo temp2 = new TagesInfo("2020-12-01",40.000,
            41.000,41.500,39.500,100);
    TagesInfo temp3 = new TagesInfo("2020-12-01",41.000,
            41.000,41.500,39.500,100);

    @BeforeEach
    void setUp()
    {

    }

    @AfterEach
    void tearDown()
    {
    }

   @Test
   void testdurchschnittsTagesPreis()
   {
       assertEquals(40.5,temp.durchschnittsTagesPreis());
   }

    @Test
    void getDatum()
    {
    }

    @Test
    void testToString()
    {
    }

    @Test
    void toCSV()
    {
    }

    @Test
    void testEquals()
    {

        assertTrue(temp.equals(temp2));
        assertFalse(temp.equals(temp3));

    }

    @Test
    void ausgabeConsoleTagesListe()
    {
    }
}