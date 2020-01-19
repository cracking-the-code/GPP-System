package FGP_Layer;

public class Order 
{
	private String id;
	private int period;
	private String trainingPathCSV;
	
	public String getId() { return id; }
	public int getPeriod() { return period; }
	public String getTrainingCSV() { return trainingPathCSV; }
	
	public void setId(String id) { this.id = id; }
	public void setPeriod(int period) { this.period = period; }
	public void setTrainingCSV(String trainingCSV) { this.trainingPathCSV = trainingCSV; }
}
