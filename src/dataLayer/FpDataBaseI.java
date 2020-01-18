package dataLayer;
import java.util.List;

public interface FpDataBaseI 
{
	DeviceConfig gettDevConfig(String devConf);
	List<DeviceConfig> gettDevConfig();
	void closeDB();
}
