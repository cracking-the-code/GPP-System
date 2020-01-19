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

public class SysManager
{
	private static Logger logger = LogManager.getLogger(SysManager.class);
	
	BlockingQueue<Order> queue = new ArrayBlockingQueue<Order>(1024);
	Executor pool = Executors.newFixedThreadPool(15);
	
	private FGP_ManagerInterface fgp = new FGP_Manager();

	public void start()
	{
		if(fgp.isPossible())
		{
			createProducers();
			createConsumer();
		}
	}
	
	private void createProducers()
	{
		for(DeviceGeneralInfo dev:fgp.getForecastingDev()) 
		{
			ForecastingOrder forOrder = new ForecastingOrder(queue);
			forOrder.setIddleMinutes(dev.getQoS());
			//Y asi sucesivamente y supongo que de aca tomare los valores ya no quiero pensar :(
			pool.execute(forOrder);
		}
	}
	
	private void createConsumer()
	{
		OrderDispatcher orderDispatch = new OrderDispatcher(queue);
		pool.execute(orderDispatch);
	}
}
