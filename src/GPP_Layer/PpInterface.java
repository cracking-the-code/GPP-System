package GPP_Layer;

public interface PpInterface 
{
	void startConnection();
	void stopConnection();
	void sendMessage(MyMqttMessage message);
}
