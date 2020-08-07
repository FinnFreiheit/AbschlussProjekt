package helper;

import java.io.*;
import java.net.*;
import java.nio.file.StandardCopyOption;
import java.time.Instant;

public class GenerateCSV
{

	public static void loadCSV(String key)
	{
		// Zugriff auf finance.yahoo vorbereiten
		long currentTime = Instant.now().getEpochSecond(); 
		long time6MonthsAgo = currentTime - 15552000;
		long time1YearAgo = currentTime - (2*time6MonthsAgo);
				String daily = "1d";
		// Beispiel: "https://query1.finance.yahoo.com/v7/finance/download/SIE.DE?period1=1583280000&period2=1596499200&interval=1d&events=history";
		String url = "https://query1.finance.yahoo.com/v7/finance/download/" + key +
					"?period1=" + Long.toString(time1YearAgo) +
					"&period2=" + Long.toString(currentTime) + 
					"&interval=" + daily + 
					"&events=history";
		System.out.println(url);
		URL u;
		InputStream inputstream = null;

		// Datei, in die geschrieben werden soll
		String targetFileName = "./database/"+key+".csv";
		File targetFile = null;
		try 
		{
			targetFile = new File(targetFileName);
			// nur für DEBUG Zwecke - kann ausgeschaltet werden
			if (targetFile.createNewFile()) 
			{
		        System.out.println("File created: " + targetFile.getName());
		    } 
			else 
			{
		        System.out.println("File already exists.");
		    }
		} 
		catch (IOException e) {
		      System.err.println("An error occurred while creating/accessing the file.");
		      e.printStackTrace();
		}
		
		// den InputStream von yahoo in die csv-Datei schreiben
		try
		{
			u = new URL(url);
			inputstream = u.openStream();
		    java.nio.file.Files.copy(
		    	      inputstream, 
		    	      targetFile.toPath(), 
		    	      StandardCopyOption.REPLACE_EXISTING);
		}
		catch (MalformedURLException mue)
		{
			System.err.println("MalformedURLException happened.");
		}
		catch (IOException ioe)
		{
			System.err.println("IOException happened.");
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
			}
		}

	}

}
