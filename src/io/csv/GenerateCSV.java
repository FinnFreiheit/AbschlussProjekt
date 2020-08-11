package io.csv;

import model.Dax;

import java.io.*;
import java.net.*;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.Map;

/**
 * Die historischen Kursdaten vom letztem Jahr, aller im Deutschen Leitindex (DAX) enthaltenden Unternehmen werden aus
 * dem internet geladen und in CSV Dateien Geschrieben. Die CSV Datein werden im Ordner database gespeichert.
 * Der Name der CSV Datei entspricht dem Kürzel der Aktie mit der Endung .csv BSP.: DAI.csv oder SIE.csv
 */
public class GenerateCSV
{
	final static boolean DEBUG = false;  // Zum Ein- und Ausschalten von Ausgaben
	//Hier wird der Ordner angegeben in dem die CSV Dateien gespeichert werden sollen
	final static String TARGETPATH = "database/";

	/**
	 * Ruft für jede Aktie im DAX die methode loadCSV(Key) auf und  erstellt somit die csv Dateien.
	 * todo Vorbedingung Internet zugriff
	 * todo nachbedingung vorhandene CSV dateien
	 */
	public static void loadCSV()
	{
		// Map<Kürzel, Name>
		Map<String, String> dax = Dax.getDax();
		for(String key: dax.keySet())
		{
			System.out.printf("    csv für %-7s ... ", key);
			loadCSV(key);
			System.out.println("erzeugt (" + TARGETPATH + key + ".csv)");
		}
	}

	/**
	 * Anhand vom key (Aktienkürzel) wird eine URL erstellt, mit der man die historischen Kursdaten vom letzten Jahr
	 * aus dem Internet laden kann. Die Kursdaten werden dann in einer CSV Datei gespeichert und im Ordner database
	 * abgelegt.
	 * @param key Aktienkürzel mit dem die URL erstellt wird
	 */
	private static void loadCSV(String key)
	{
		// Zugriff auf finance.yahoo vorbereiten
		long currentTime = Instant.now().getEpochSecond();
		long time6MonthsAgo = currentTime - 15552000;  // als period1 verwenden, wenn 6M
		long time1YearAgo = currentTime - 31622400;
				String daily = "1d";
		// Beispiel: "https://query1.finance.yahoo.com/v7/finance/download/SIE.DE?period1=1583280000&period2=1596499200&interval=1d&events=history";
		// https://query1.finance.yahoo.com/v7/finance/download/IFX.DE?period1=1565222400&period2=1596844800&interval=1d&events=history
		String url = "https://query1.finance.yahoo.com/v7/finance/download/" + key +
					"?period1=" + Long.toString(time1YearAgo) +
					"&period2=" + Long.toString(currentTime) + 
					"&interval=" + daily + 
					"&events=history";
		if(DEBUG) System.out.println(url);
		URL u;
		InputStream inputstream = null;

		// Datei, in die geschrieben werden soll
		String targetFileName = TARGETPATH +key+".csv";
		File targetFile = null;
		try 
		{
			targetFile = new File(targetFileName);
			if (targetFile.createNewFile())
			{
		        if(DEBUG) System.out.println("File created: " + targetFile.getName());
		    }
			else
			{
				if(DEBUG) System.out.println("File already exists.");
		    }
		} 
		catch (IOException e) {
			if(DEBUG)
			{
				System.err.println("An error occurred while creating/accessing the file.");
				e.printStackTrace();
			}

		}
		
		// den InputStream von yahoo in die csv-Datei schreiben
		try
		{
			u = new URL(url);
			inputstream = u.openStream();
			if(DEBUG) System.out.println("Stream opened");
		    java.nio.file.Files.copy(
		    	      inputstream, 
		    	      targetFile.toPath(), 
		    	      StandardCopyOption.REPLACE_EXISTING);
			if(DEBUG) System.out.println("copied");
		}
		catch (MalformedURLException mue)
		{
			if(DEBUG) System.err.println("MalformedURLException happened.");
		}
		catch (IOException ioe)
		{
			if(DEBUG) System.err.println("copy --> IOException happened.");
		}
		// InputStream schliessen
		finally
		{

			try
			{
				inputstream.close();
			}
			catch (IOException ioe)
			{
				if(DEBUG) System.out.println("Error closing stream");
			}
		}
	}

}
