package GPP_Layer;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MyMqttMessage
{
	private MqttMessage message;
	private String topic;
	
	public MqttMessage getMessage() { return message; }
	public String getTopic() { return topic; }
	
	public void setMessage(MqttMessage message) { this.message = message; }
	public void setTopic(String topic) { this.topic = topic; }
}
