package GPP_Layer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import FGP_Layer.Order;

public class PpManager implements PpManagerInterface
{
	private static Logger logger = LogManager.getLogger(PpManager.class);
	private PpInterface publisher = new PublishProcess();
	
	@Override
	public void insertMessage(String message, String topic)
	{
		logger.info("PpManager Starts!!! The message is being prepared...");
		MyMqttMessage myMessage = new MyMqttMessage();
		MqttMessage mqttMessage = new MqttMessage();
		
		myMessage.setTopic(topic);
		myMessage.setMessage(mqttMessage);
		
		mqttMessage.setPayload(message.getBytes());
		mqttMessage.setQos(0);
		
		publisher.sendMessage(myMessage);
	}

	@Override
	public void startManager() 
	{
		logger.info("Starting the Connection to the MQTT Server...");
		publisher.startConnection();
	}
	
	@Override
	public void stopManager()
	{
		logger.info("Closing the Connection to the MQTT Server...");
		publisher.stopConnection();
	}
}
