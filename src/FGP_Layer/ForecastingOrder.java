package FGP_Layer;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dataLayer.DeviceMeasurement;
import dataLayer.FpDataBaseI;
import dataLayer.PpMySQL;
import infraLayer.CsvConvertion;

public class ForecastingOrder implements FoInterface, Runnable
{
	private static Logger logger = LogManager.getLogger(ForecastingOrder.class);
	
	protected BlockingQueue<Order> queue;
	private long idleMinutes;
	private String id;
	private int days;
	private int period;
	private Order order;
	
	private FpDataBaseI db;
	
	public ForecastingOrder(BlockingQueue<Order> queue)
	{
		this.queue = queue;
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
			List<DeviceMeasurement> lstdevMea = db.getAllDevMeasurement(id, days);
			
			CsvConvertion csvObj = new CsvConvertion(id);
			String path = csvObj.convertToCSV(lstdevMea);
			
			order.setId(this.id);
			order.setPeriod(this.period);
			order.setTrainingCSV(path);
			
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	private void sleep()
	{
		try 
		{
			logger.info("This thread for Device: " + id + " Will be sleeping for: " + idleMinutes + " minutes");
			idleMinutes = idleMinutes * 60000;
			Thread.sleep(idleMinutes);
		} 
		catch (InterruptedException e) 
		{
			logger.error("Error in Sleeping Thread!!!");
			logger.error(e);
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void setIddleMinutes(long idleMinutes) { this.idleMinutes = idleMinutes; }
	@Override
	public void setId(String id) { this.id = id; }
	@Override
	public void setDays(int days) { this.days = days; }
	@Override
	public void setPeriod(int period) {this.period = period; }
	@Override
	public Order getOrder() { return this.order; }
}
