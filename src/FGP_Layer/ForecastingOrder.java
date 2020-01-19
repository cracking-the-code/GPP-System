package FGP_Layer;

public class ForecastingOrder implements FoInterface
{
	private int minutes;
	private String id;
	
	@Override
	public void setIddleMinutes(int minutes) 
	{
		this.minutes = minutes;
	}

	@Override
	public void setId(String id) 
	{
		this.id = id;
	}

	@Override
	public Order getOrder() 
	{
		return null;
	}

}
