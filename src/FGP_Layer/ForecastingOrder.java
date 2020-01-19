package FGP_Layer;

import java.util.List;

import dataLayer.DeviceMeasurement;
import dataLayer.FpDataBaseI;
import dataLayer.PpMySQL;
import infraLayer.CsvConvertion;

public class ForecastingOrder implements FoInterface
{
	private int idleMinutes;
	private String id;
	private int days;
	private int period;
	private Order order;
	
	private FpDataBaseI db;
	
	public ForecastingOrder()
	{
		this.db = PpMySQL.getInstance();
	}
	
	private Boolean queryMeasures()
	{
		try 
		{
			List<DeviceMeasurement> lstdevMea = db.getAllDevMeasurement(id, days);
			
			CsvConvertion csvObj = new CsvConvertion();
			String path = csvObj.convertToCSV(lstdevMea);
			
			order.setId(this.id);
			order.setPeriod(this.period);
			order.setTrainingCSV(path);
			
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	
	@Override
	public void setIddleMinutes(int idleMinutes) { this.idleMinutes = idleMinutes; }
	@Override
	public void setId(String id) { this.id = id; }
	@Override
	public void setDays(int days) { this.days = days; }
	@Override
	public void setPeriod(int period) {this.period = period; }
	@Override
	public Order getOrder() { return this.order; }

}
