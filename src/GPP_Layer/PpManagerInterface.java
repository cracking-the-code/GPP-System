package GPP_Layer;
import FGP_Layer.Order;

public interface PpManagerInterface 
{
	void startManager();
	void stopManager();
	void insertMessage(String message, String topic);
}
