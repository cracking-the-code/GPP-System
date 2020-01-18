package businessLayer;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface PpManagerInterface 
{
	void startManager();
	void stopManager();
	void insertMessage(MyMqttMessage message);
}
