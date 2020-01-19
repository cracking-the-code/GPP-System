package dataLayer;
import java.util.List;

public interface FpDataBaseI 
{
	DeviceConfig gettDevConfig(String devConf);
	DeviceMqttInfo getDevMqttInfo(String idDev);
	DeviceMeasurement getDevMeasurement(String idDev);
	List<DeviceConfig> gettDevConfig();
	
	void closeDB();
}
