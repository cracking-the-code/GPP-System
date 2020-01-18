package businessLayer;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import infraLayer.ConfigClass;

public class PublishProcess implements PpInterface
{
	private static Logger logger = LogManager.getLogger(PublishProcess.class);
	private ConfigClass conf = ConfigClass.getInstance();
	
	private MqttAsyncClient client;
	private MqttConnectOptions connOpts;
	private MemoryPersistence persistence;
		
	public PublishProcess()
	{
		configureMqtt();
	}
	
	private void configureMqtt() 
	{
		try 
		{
			logger.info("Starting the Mqtt Configuration...");
			
			String clientId = UUID.randomUUID().toString();
			client = new MqttAsyncClient(conf.getServerURI(), clientId, persistence);
			connOpts = new MqttConnectOptions();
			
			connOpts.setCleanSession(true);
			connOpts.setUserName(conf.getUserName());
			connOpts.setPassword(conf.getPassword().toCharArray());
			connOpts.setAutomaticReconnect(true);
			//Setting the callback to accept the messages as soon as the client is connected
			//client.setCallback(getCallback());
			
			logger.info("The Mqtt protocol has been configured successfully!!!");
		} 
		catch (Exception e) 
		{
			logger.error("An error has happened during Mqtt configuration");
			logger.error(e);
			e.printStackTrace();
		}
	}
	
	@Override
	public void sendMessage(MyMqttMessage message) 
	{
		try 
		{
			IMqttDeliveryToken deMessage = client.publish(message.getTopic(), message.getMessage(), null, new MqttActionHandler());
		} 
		catch (MqttException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void startConnection()
	{
		try
		{
			logger.info("Conecting to: " + conf.getServerURI() + " Mqtt Server");
			IMqttToken token = client.connect(connOpts);
			
			token.waitForCompletion();
			logger.info("Connected to the: " + conf.getServerURI() + " Mqtt Server");
						
		}
		catch(Exception me)
		{
			logger.error("An error has happened: " + me.toString());
			logger.error("\nmsg " + me.getMessage() + 
						"\nloc " + me.getLocalizedMessage() + 
						"\ncause " + me.getCause() + 
						"\nexcep " + me);
		}
	}

	@Override
	public void stopConnection() 
	{
		try 
		{
			client.close();
			logger.info("The connection has been closed successfully!!!");
		} 
		catch (MqttException e) 
		{
			logger.error("An Error ocurred related to closing connection");
			logger.error("Error: " + e.getMessage());
		}
	}
}
