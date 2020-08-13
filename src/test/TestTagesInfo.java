package test;

import model.TagesInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestTagesInfo
{

    // temp = temp2 != temp3
    TagesInfo temp = new TagesInfo("2020-12-01",40.000,
            41.000,41.500,39.500,100);
    TagesInfo temp2 = new TagesInfo(temp);
    TagesInfo temp3 = new TagesInfo("2020-12-01",41.000,
            41.000,41.500,39.500,100);

   @Test
   void testdurchschnittsTagesPreis()
   {
       assertEquals(40.5,temp.durchschnittsTagesPreis());
   }

    @Test
    void getDatum()
    {
        assertTrue(temp.getDatum().equals("2020-12-01"));
    }

    @Test
    void testEquals()
    {

        assertTrue(temp.equals(temp2));
        assertFalse(temp.equals(temp3));

    }

    @Test
    void ausgabeConsoleTagesInformationen()
    {
        temp.ausgabeConsoleTagesInformation();

    }
}