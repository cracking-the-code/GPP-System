package dataLayer;
import java.util.List;

public interface FpDataBaseI 
{
	DeviceConfig gettDevConfig(String devConf);
	List<DeviceMeasurement> getAllDevMeasurement(String idDev, int days);
	List<DeviceGeneralInfo> getAllDevGeneralInfo();
	
	void closeDB();
}
