package FGP_Layer;

import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import GPP_Layer.MyMqttMessage;

public class OrderDispatcher implements Runnable
{
	private static Logger logger = LogManager.getLogger(OrderDispatcher.class);
	
	private BlockingQueue<Order> queue;
	
	public OrderDispatcher(BlockingQueue<Order> queue) 
	{
		this.queue = queue;
	}

	@Override
	public void run()
	{
		try 
		{
			while(true)
			{
				Order order = queue.take();
				String pathResults = processOrder(order);
				
				
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
	private String processOrder(Order order)
	{
		try
		{
			MachineLearning machineL = new MachineLearning();
			
			machineL.train(order.getTrainingCSV(), order.getPeriod());
			machineL.predict();
			String pathTrained = machineL.persistJson();
			
			return pathTrained;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	private MyMqttMessage createMqttMessage()
	{
		
		return null;
	}
}
