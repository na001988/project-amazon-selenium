package app;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.github.cliftonlabs.json_simple.*;

public class ReadFile {
	
	String val="";
	String base="\\src\\app\\";
	
	public String getString(String x) {
		try {
			Reader reader =  Files.newBufferedReader(Paths.get(System.getProperty("user.dir")+base+"Constants.json"));
			JsonObject parser = (JsonObject) Jsoner.deserialize(reader);
	
			val = (String) parser.get(x);
			
			reader.close();
  
		}catch(Exception e) {
			e.printStackTrace();
		}
		return val;
	}
	
}
