package FGP_Layer;

import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import GPP_Layer.MyMqttMessage;
import GPP_Layer.PpManagerInterface;
import GPP_Layer.PpManager;
import infraLayer.ConfigClass;
import infraLayer.JsonUtil;

public class OrderDispatcher implements Runnable
{
	private static Logger logger = LogManager.getLogger(OrderDispatcher.class);
	
	private ConfigClass config = ConfigClass.getInstance();
	private PpManagerInterface publisher;
	JsonUtil jsonFile = new JsonUtil();
	private BlockingQueue<Order> queue;
	
	public OrderDispatcher(BlockingQueue<Order> queue) 
	{
		this.queue = queue;
		publisher = new PpManager();
		publisher.startManager();
	}

	@Override
	public void run()
	{
		try 
		{
			while(true)
			{
				Order order = queue.take();
				
				if(!order.equals(null))
				{
					String topic = config.getPredictionTopic() + order.getId();
					String path = processOrder(order);
					String message = readJson(path);
					
					publisher.insertMessage(message, topic);
				}
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
			logger.info("PROCESSING INCOMMIG ORDER FOR: " + order.getId() + " Device, File: " + order.getTrainingCSV());
			
			MachineLearning machineL = new MachineLearning();
			
			machineL.train(order.getTrainingCSV(), order.getTrainingPeriod());
			machineL.predict();
			String pathTrained = machineL.persistJson();
			
			return pathTrained;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	private String readJson(String path)
	{	
		return jsonFile.readJson(path);
	}
}
