package helper;

import model.Dax;

import java.io.*;
import java.net.*;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.Map;

public class GenerateCSV
{
	final static boolean DEBUG = false;  // Zum Ein- und Ausschalten von Ausgaben
	final static String TARGETPATH = "database/";

	/**
	 * laedt alle Aktioninformationen aller Dax-Werte aus finance.yahoo und
	 * speichert als csv-Datein im database-Ordner
	 */
	public static void loadCSV()
	{
		Map<String, String> dax = Dax.getDax();
		for(String key: dax.keySet())
		{
			System.out.printf("    csv für %-7s ... ", key);
			loadCSV(key);
			System.out.println("erzeugt (" + TARGETPATH + key + ".csv)");
		}
	}

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
