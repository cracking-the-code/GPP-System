package FGP_Layer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class FGP_Manager implements FGP_ManagerInterface
{
	private static Logger logger = LogManager.getLogger(FGP_Manager.class);
	
	private ExecutorService pool;
	private BlockingQueue<Order> orderQueue = new ArrayBlockingQueue<Order>(1024);
	
	
	private int connections;
	
	public FGP_Manager(int connections)
	{
		this.connections = connections;
		pool = Executors.newFixedThreadPool(connections);
	}
	
	@Override
	public void startProcess() 
	{
	}

	@Override
	public void setConnections(int connections) { this.connections = connections; }

	@Override
	public int getConnections() { return this.connections; }

}
