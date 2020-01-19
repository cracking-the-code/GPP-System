package FGP_Layer;

import java.util.List;

import dataLayer.DeviceGeneralInfo;

public interface FGP_ManagerInterface
{
	List<DeviceGeneralInfo> getForecastingDev();
	Boolean isPossible(); //If there aren´t any data this variable will shutdown everything
}
