package GPP_Layer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import infraLayer.JsonUtil;

public class PpManager implements PpManagerInterface, Runnable
{
	private static Logger logger = LogManager.getLogger(PpManager.class);
	
	private PpInterface publisher = new PublishProcess();
	private BlockingQueue<MyMqttMessage> order = new ArrayBlockingQueue<MyMqttMessage>(1024);
	
	
	@Override
	public void insertMessage(MyMqttMessage message)
	{
		try
		{
			order.put(message);
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run()
	{
		try 
		{
			MyMqttMessage message = order.take();
			publisher.sendMessage(message);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void startManager() 
	{
		publisher.startConnection();
	}
	
	@Override
	public void stopManager()
	{
		publisher.stopConnection();
	}
}
