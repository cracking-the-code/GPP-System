package businessLayer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import FGP_Layer.FGP_Manager;
import FGP_Layer.FGP_ManagerInterface;
import FGP_Layer.ForecastingOrder;
import FGP_Layer.Order;
import FGP_Layer.OrderDispatcher;
import dataLayer.DeviceGeneralInfo;

public class OrderManagement
{
	private static Logger logger = LogManager.getLogger(OrderManagement.class);
	
	BlockingQueue<Order> queue = new ArrayBlockingQueue<Order>(1024);
	Executor pool = Executors.newFixedThreadPool(15);
	
	private FGP_ManagerInterface fgp = new FGP_Manager();

	public void start()
	{
		if(fgp.isPossible())
		{
			logger.info("Startig the OrderManagement Process!!!");
			
			createProducers();
			createConsumer();
		}
	}
	
	private void createProducers()
	{
		logger.info("Creation of Order Producers!!!");
		
		for(DeviceGeneralInfo device : fgp.getForecastingDev()) 
		{
			Order order = new Order();
			order.setId(device.getIdDev());
			order.setTrainingPeriod(device.getTrainPeriod());
			order.setqueryPeriod(device.getQueryPeriod());
			ForecastingOrder forOrder = new ForecastingOrder(queue, order);
		
 			pool.execute(forOrder);
			logger.info("Creation of Order Producer ID: " + device.getIdDev() + " Successful");
		}
	}
	
	private void createConsumer()
	{
		logger.info("Creation of Order Consumer!!!");
		OrderDispatcher orderDispatch = new OrderDispatcher(queue);
		pool.execute(orderDispatch);
		logger.info("Creation of Order Consumer Successfully CREATE!!!");
	}
}
