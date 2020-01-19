package FGP_Layer;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dataLayer.DeviceMeasurement;
import dataLayer.FpDataBaseI;
import dataLayer.PpMySQL;
import infraLayer.CsvConvertion;

public class ForecastingOrder implements Runnable
{
	private static Logger logger = LogManager.getLogger(ForecastingOrder.class);
	
	protected BlockingQueue<Order> queue;
	
	private FpDataBaseI db;
	private Order order;
	private int idleMinutes;
	
	public ForecastingOrder(BlockingQueue<Order> queue, Order order)
	{
		this.queue = queue;
		this.order = order;
		this.db = PpMySQL.getInstance();
	}
	
	@Override
	public void run() 
	{
		while(true)
		{
			try 
			{
				if(queryMeasures())
					queue.put(order);
				else
					break;
			} 
			catch (InterruptedException e) 
			{
				logger.error("Error by adding new Order to Queue");
				logger.error("Error Order: " + order.getId());
				logger.error("Error Order: " + e.getStackTrace());
				break;
			}
			sleep();
		}
	}
	
	private Boolean queryMeasures()
	{
		try 
		{
			logger.info("Starting the query for creation of CSV File :D");
			
			List<DeviceMeasurement> lstdevMea = db.getAllDevMeasurement(order.getId(), order.getqueryPeriod());
			
			CsvConvertion csvObj = new CsvConvertion(order.getId());
			String path = csvObj.convertToCSV(lstdevMea);		
			order.setTrainingCSV(path);
			
			logger.info("Creation of CSV Successfully created >:D");
			return true;
		}
		catch(Exception e)
		{
			logger.error("Cannot Query or Create the CSV file for: " + order.getId() + " Device :(");
			logger.error("\nmsg " + e.getMessage() + 
						 "\nloc " + e.getLocalizedMessage() + 
						 "\ncause " + e.getCause() + 
						 "\nexcep " + e);
			
			return false;
		}
	}
	
	private void sleep()
	{
		try 
		{
			logger.info("This thread for Device: " + order.getId() + " Will be sleeping for: " + order.getTrainingPeriod() + " minutes");
			idleMinutes = order.getTrainingPeriod() * 60000;
			Thread.sleep(idleMinutes);
		} 
		catch (InterruptedException e) 
		{
			logger.error("Error in Sleeping Thread!!!");
			logger.error(e);
			throw new RuntimeException(e);
		}
	}
}
