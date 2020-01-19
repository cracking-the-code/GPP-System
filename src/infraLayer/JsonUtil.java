package infraLayer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class JsonUtil 
{
	private static Logger logger = LogManager.getLogger(JsonUtil.class);
	
	private Gson gson = new Gson();
	
	public JsonUtil() 
	{
		
	}
	
	public String readJson(String path)
	{
		String jsonString = "";
		
	    try
	    {
	        BufferedReader buffer = new BufferedReader(new FileReader(path));
	        JsonPubPrediction obj = gson.fromJson(buffer, JsonPubPrediction.class);
	        
	        jsonString = gson.toJson(obj);
	        
	        System.out.println(jsonString);

	    }
	    catch (IOException e)
	    {
	        e.printStackTrace();
	        logger.error(e);
	    }
	    
	    return jsonString;
	}
}
