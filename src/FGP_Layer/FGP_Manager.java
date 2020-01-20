package FGP_Layer;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dataLayer.DeviceGeneralInfo;
import dataLayer.FpDataBaseI;
import dataLayer.PpMySQL;


public class FGP_Manager implements FGP_ManagerInterface
{
	private static Logger logger = LogManager.getLogger(FGP_Manager.class);

	private Boolean isPossible = false;
	private FpDataBaseI db;
	private List<DeviceGeneralInfo> lstDev = null;
	
	public FGP_Manager() 
	{
		db = PpMySQL.getInstance();
		logger.info("The FGP_Manager Process starts!!!!!!");
		lstDev = db.getAllDevGeneralInfo();
	}
	
	@Override
	public List<DeviceGeneralInfo> getForecastingDev() 
	{
		return lstDev;
	}

	@Override
	public Boolean isPossible() 
	{
		if(lstDev == null)
		{
			logger.info("There are no devices. The process cannot continue :(");
			return false;
		}
		else
		{
			logger.info("There are: " + lstDev.size() + " Active Devices !!!");
			return true;
		}
	}
}
