package FGP_Layer;

public interface FoInterface
{
	void setIddleMinutes(long minutes);
	void setId(String id);
	void setDays(int days);
	void setPeriod(int period);
	Order getOrder();
}
