package GPP_Layer;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public interface PpInterface 
{
	void startConnection();
	void stopConnection();
	void sendMessage(MyMqttMessage message);
}
