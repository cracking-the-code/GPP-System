package FGP_Layer;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dataLayer.DeviceGeneralInfo;


public class FGP_Manager implements FGP_ManagerInterface
{
	private static Logger logger = LogManager.getLogger(FGP_Manager.class);

	@Override
	public List<DeviceGeneralInfo> getForecastingDev() 
	{
		return null;
	}

	@Override
	public Boolean isPossible() 
	{
		return null;
	}
}
