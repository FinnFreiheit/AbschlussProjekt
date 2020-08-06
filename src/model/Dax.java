package model;

import java.util.HashMap;
import java.util.Map;

public class Dax
{
	static Map<String, String> dax = new HashMap<>();
	
	public static void fill()
	{
		dax.put("ADS.DE", "Adidas");
		dax.put("ALV.DE", "Allianz");
		dax.put("BAS.DE", "BASF"); 
		dax.put("BAYN.DE", "Bayer");  
		dax.put("BMW.DE", "Bayerische Motoren-Werke (BMW)");  
		dax.put("BEI.DE", "Beiersdorf");  
		dax.put("CBK.DE", "Commerzbank");  
		dax.put("CON.DE", "Continental");  
		dax.put("DAI.DE", "Daimler");  
		dax.put("DBK.DE", "Deutsche Bank");  
		dax.put("DB1.DE", "Deutsche Börse");  
		dax.put("LHA.DE", "Deutsche Lufthansa"); 
		dax.put("DPW.DE", "Deutsche Post");  
		dax.put("DTE.DE", "Deutsche Telekom");  
		dax.put("EOAN.DE", "E.On");  
		dax.put("FME.DE", "Fresenius Medical Care");  
		dax.put("FRE.DE", "Fresenius");  
		dax.put("HEI.DE", "HeidelbergCement");  
		dax.put("HEN3.DE", "Henkel (Vorzugsaktie)"); 
		dax.put("IFX.DE", "Infineon Technologies");  
		dax.put("SDF.DE", "K+S");  
		dax.put("LXS.DE", "Lanxess");  
		dax.put("LIN.DE", "Linde");  
		dax.put("MRK.DE", "Merck");  
		dax.put("MUV2.DE", "Munich Re");  
		dax.put("RWE.DE", "RWE");  
		dax.put("SAP.DE", "SAP");  
		dax.put("SIE.DE", "Siemens");  
		dax.put("TKA.DE", "ThyssenKrupp");  
		dax.put("VOW3.DE", "Volkswagen (Vorzugsaktie)");  
	}
	
	public static Map<String, String> getDax()
	{
		Dax.fill();
		return dax;
	}
}
