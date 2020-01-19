package FGP_Layer;

public class Order 
{
	private String id;
	private int trainingPeriod;		//Minutes
	private int queryPeriod;		//Days
	private String trainingPathCSV;
	
	public String getId() { return id; }
	public int getTrainingPeriod() { return trainingPeriod; }
	public int getqueryPeriod() { return queryPeriod; }
	public String getTrainingCSV() { return trainingPathCSV; }
	
	public void setId(String id) { this.id = id; }
	public void setTrainingPeriod(int trainingPeriod) { this.trainingPeriod = trainingPeriod; }
	public void setqueryPeriod(int queryPeriod) { this.queryPeriod = queryPeriod; }
	public void setTrainingCSV(String trainingCSV) { this.trainingPathCSV = trainingCSV; }
}
