package infraLayer;

import java.util.List;

import dataLayer.DeviceMeasurement;

public class CsvConvertion 
{
	public String convertToCSV(List<DeviceMeasurement> lstDevMea)
	{
		return null;
	}
	
	private String csv(List<DeviceMeasurement> lstDevMea)
	{
		StringBuilder sb = new StringBuilder();
		
		try
		{
			for(DeviceMeasurement devM:lstDevMea) 
			{
				
			}
			
			return sb.toString();
		}
		catch(Exception e) 
		{
			return null;
		}
	}
}
