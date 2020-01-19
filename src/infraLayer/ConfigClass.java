package infraLayer;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ConfigClass 
{
	private static ConfigClass uniqueInstance;
	private Properties prop;
	private Path configPath = Paths.get("resources","config.properties");
	private static Logger logger = LogManager.getLogger(ConfigClass.class);
	
	private String serverURI;
	private String clientID;
	private String userName;
	private String password;

	private String PredictionTopic;
	
	private String csvPath;
	private String jsonPath;
	
	private int spConnections;
	
	private ConfigClass() throws Exception
	{
		logger.info("Starting the General Configuration...");
		try(InputStream input = new FileInputStream(configPath.toAbsolutePath().toString())) 
		{
			prop = new Properties();
			
			if(input == null)
				throw new Exception("Missing Configuration File for: " + configPath.toAbsolutePath().toString());
			
			prop.load(input);
			
			this.serverURI = prop.getProperty("serverURI");
			this.clientID = prop.getProperty("clientID");
			this.userName = prop.getProperty("userName");
			this.password = prop.getProperty("password");
			
			this.PredictionTopic = prop.getProperty("PredictionTopic");
			
			this.csvPath = prop.getProperty("csvPath");
			this.jsonPath = prop.getProperty("jsonPath");
			this.spConnections = Integer.parseInt(prop.getProperty("spConnections"));
			
			logger.info("Successful Configuration!!!");
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public static synchronized ConfigClass getInstance() {
		if(uniqueInstance == null)
		{
			try 
			{
				uniqueInstance = new ConfigClass();
			} 
			catch (Exception e) 
			{
				logger.error(e.getMessage());
			}
		}
		return uniqueInstance;
	}

	public Properties getProp() {
		return prop;
	}

	public String getServerURI() {
		return serverURI;
	}

	public String getClientID() {
		return clientID;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	
	public String getPredictionTopic() {
		return PredictionTopic;
	}
	
	public String getCsvPath() {
		return csvPath;
	}
	
	public String getJsonPath() {
		return jsonPath;
	}

	public int getSpConnections() {
		return spConnections;
	}
}