package dataLayer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PpMySQL implements FpDataBaseI
{
	private static PpMySQL uniqueInstance;
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY;
	private static Logger logger = LogManager.getLogger(PpMySQL.class);

	public static synchronized PpMySQL getInstance() {
		if(uniqueInstance == null)
		{
			try 
			{
				uniqueInstance = new PpMySQL();
			} 
			catch (Exception e) 
			{
				logger.error(e.getMessage());
			}
		}
		return uniqueInstance;
	}
	
	private PpMySQL()
	{
		logger.info("initializing Data Base Connection...");
		
		ENTITY_MANAGER_FACTORY = Persistence
	            .createEntityManagerFactory("GPP_System");
	}
	
	@Override
	public DeviceConfig gettDevConfig(String devConf)
	{		
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		DeviceConfig devConfig = null;
		
		LocalDateTime minDate = LocalDateTime.now();
		minDate.minusDays(10);
		
		try 
		{
			logger.info("The list of Devices will be queried from Tbl_DeviceConfig");
			
			Query query = em.createQuery("SELECT * FROM Tbl_DeviceConfig WHERE IdDev = :idDevice");
			
			query.setParameter("idDevice", devConf);
			
			devConfig = (DeviceConfig) query.getSingleResult();
		}
		catch(Exception e)
		{
			logger.error("Cannot query the table Tbl_DeviceConfig");
			logger.error("\nmsg " + e.getMessage() + 
						 "\nloc " + e.getLocalizedMessage() + 
						 "\ncause " + e.getCause() + 
						 "\nexcep " + e);
		}
		finally
		{
			em.close();
		}
		
		return devConfig;
	}

	@Override
	public List<DeviceMeasurement> getAllDevMeasurement(String idDev, int days) 
	{
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		List<DeviceMeasurement> lstDevMeasurement = null;
		
		LocalDateTime minDate = LocalDateTime.now();
		minDate = minDate.minusDays(days);
		
		try 
		{
			logger.info("The list of Devices will be queried from Tbl_DeviceMeasurement");
			
			Query query = em.createNativeQuery("SELECT * FROM Tbl_DeviceMeasurement WHERE IdDev = :idDevice "
									   + "AND TimeMeasure >= :minDate", DeviceMeasurement.class);
			
			query.setParameter("idDevice", idDev);
			query.setParameter("minDate", minDate.toString());
			
			lstDevMeasurement = query.getResultList();
		}
		catch(Exception e)
		{
			logger.error("Cannot query the table Tbl_DeviceMeasurement");
			logger.error("\nmsg " + e.getMessage() + 
						 "\nloc " + e.getLocalizedMessage() + 
						 "\ncause " + e.getCause() + 
						 "\nexcep " + e);
		}
		finally
		{
			em.close();
		}
		
		return lstDevMeasurement;
	}

	@Override
	public List<DeviceGeneralInfo> getAllDevGeneralInfo() 
	{
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		List<DeviceGeneralInfo> lstDevGeneralInfo = new ArrayList<DeviceGeneralInfo>();
		
		try 
		{
			logger.info("The list of Devices will be queried from Tbl_DeviceGeneralInfo");
			
			Query query = em.createNativeQuery("SELECT * FROM Tbl_DeviceGeneralInfo WHERE IsActive = 1", DeviceGeneralInfo.class);
			lstDevGeneralInfo = query.getResultList();
		}
		catch(Exception e)
		{
			logger.error("Cannot query the table Tbl_DeviceGeneralInfo");
			logger.error("\nmsg " + e.getMessage() + 
						 "\nloc " + e.getLocalizedMessage() + 
						 "\ncause " + e.getCause() + 
						 "\nexcep " + e);
		}
		finally
		{
			em.close();
		}
		
		return lstDevGeneralInfo;
	}

	@Override
	public void closeDB() 
	{
		logger.info("Closing Data Base Connection...");
		ENTITY_MANAGER_FACTORY.close();
	}

}
