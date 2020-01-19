package dataLayer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tbl_DeviceGeneralInfo")
public class DeviceGeneralInfo 
{
	@Id
	@Column(name = "IdDev", unique = true)
	private String IdDev;
	
	@Column(name = "DevName", nullable = true)
	private String DevName;
	
	@Column(name = "DevUser", nullable = true)
	private String DevUser;
	
	@Column(name = "DevPass", nullable = true)
	private String DevPass;
	
	@Column(name = "DevLocation", nullable = false)
	private String DevLocation;
	
	@Column(name = "DevIpAddress", nullable = false)
	private String DevIpAddress;
	
	@Column(name = "queryPeriod", nullable = true)
	private int queryPeriod;
	
	@Column(name = "trainPeriod", nullable = true)
	private int trainPeriod;
	
	@Column(name = "IsActive", nullable = true)
	private Boolean IsActive;

	public String getIdDev() { return IdDev; }
	public String getDevName() { return DevName; }
	public String getDevUser() { return DevUser; }
	public String getDevPass() { return DevPass; }
	public String getDevLocation() { return DevLocation; }
	public String getDevIpAddress() { return DevIpAddress; }
	public int getQueryPeriod() { return queryPeriod; }
	public int getTrainPeriod() { return trainPeriod; }
	public Boolean getIsActive() { return IsActive; }
	
	public void setIdDev(String idDev) { IdDev = idDev; }
	public void setDevName(String devName) { DevName = devName; }
	public void setDevUser(String devUser) { DevUser = devUser; }
	public void setDevPass(String devPass) { DevPass = devPass; }
	public void setDevLocation(String devLocation) { DevLocation = devLocation; }
	public void setDevIpAddress(String devIpAddress) { DevIpAddress = devIpAddress; }
	public void setQueryPeriod(int queryPeriod) { this.queryPeriod = queryPeriod; }
	public void setTrainPeriod(int trainPeriod) { this.trainPeriod = trainPeriod; }
	public void setIsActive(Boolean isActive) { IsActive = isActive; }
}
