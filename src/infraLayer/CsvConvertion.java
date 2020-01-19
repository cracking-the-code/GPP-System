package infraLayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dataLayer.DeviceMeasurement;

public class CsvConvertion 
{
	private static Logger logger = LogManager.getLogger(CsvConvertion.class);
	
	private String path;
	private String id;
	
	public CsvConvertion(String id)
	{
		this.id = id;	
		this.path += "/" + this.id + ".csv";
	}
	
	public String convertToCSV(List<DeviceMeasurement> lstDevMea)
	{
		logger.info("Starting Creation of CSV file: " + path);
		try(PrintWriter writer = new PrintWriter(new File(this.path)))
		{
			String csv = csv(lstDevMea);
			writer.write(csv);
			
			logger.info("The CSV file: " + path + " Was created successfully!!!");
			return this.path;
		}
		catch (FileNotFoundException e) 
		{
			logger.error("Error in CSV File Creation for: " + path);
			logger.error("\nmsg " + e.getMessage() + 
						 "\nloc " + e.getLocalizedMessage() + 
						 "\ncause " + e.getCause() + 
						 "\nexcep " + e);
			return null;
		}
	}
	
	private String csv(List<DeviceMeasurement> lstDevMea)
	{
		StringBuilder sb = new StringBuilder();
		
		try
		{
			sb.append("TimeMeasure,Potency,Voltage,Charge,Temperature,Misc01");
			sb.append('\n');
			for(DeviceMeasurement devM:lstDevMea) 
			{
				sb.append(devM.getTimeMeasure());
				sb.append(",");
				sb.append(devM.getPotency());
				sb.append(",");
				sb.append(devM.getVoltage());
				sb.append(",");
				sb.append(devM.getCharge());
				sb.append(",");
				sb.append(devM.getTemperature());
				sb.append(",");
				sb.append(devM.getMisc01());
				sb.append('\n');
			}
			
			return sb.toString();
		}
		catch(Exception e) 
		{
			logger.error("Cannot create the StringBuilder CSV");
			logger.error("\nmsg " + e.getMessage() + 
						 "\nloc " + e.getLocalizedMessage() + 
						 "\ncause " + e.getCause() + 
						 "\nexcep " + e);
			return null;
		}
	}
}