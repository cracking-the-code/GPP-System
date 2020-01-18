package infraLayer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import com.google.gson.stream.JsonReader;

public class JsonUtil 
{
	private JsonReader reader;
	
	public JsonUtil() 
	{
		
	}
	
	public JsonReader readFile(String fileName)
	{
		try
		{
			reader = new JsonReader(new FileReader(fileName));
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reader;
	}
}
