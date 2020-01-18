package infraLayer;
import java.util.List;

public class JsonPubPrediction 
{
	private String Id;
	private int Periodo;
	private List<Prediccion> Predicciones;
		
	public String getId() { return Id; }
	public int getPeriodo() { return Periodo; }
	public List<Prediccion> getPredicciones() { return Predicciones; }
	
	public void setId(String id) { Id = id; }
	public void setPeriodo(int periodo) { Periodo = periodo; }
	public void setPredicciones(List<Prediccion> predicciones) { Predicciones = predicciones; }

	public class Prediccion
	{
		private String Date;
		private float Power;
        private float Temp;
        private float Humidity;
        private float Voltage;
        private float Current;
        private float Misc01;
        private float TotalPagar;
        
		public String getDate() { return Date; }
		public float getPower() { return Power; }
		public float getTemp() { return Temp; }
		public float getHumidity() { return Humidity; }
		public float getVoltage() { return Voltage; }
		public float getCurrent() { return Current; }
		public float getMisc01() { return Misc01; }
		public float getTotalPagar() { return TotalPagar; }
		
		public void setDate(String date) { Date = date; }
		public void setPower(float power) { Power = power; }
		public void setTemp(float temp) { Temp = temp; }
		public void setHumidity(float humidity) { Humidity = humidity; }
		public void setVoltage(float voltage) { Voltage = voltage; }
		public void setCurrent(float current) { Current = current; }
		public void setMisc01(float misc01) { Misc01 = misc01; }
		public void setTotalPagar(float totalPagar) { TotalPagar = totalPagar; }
	}
}
