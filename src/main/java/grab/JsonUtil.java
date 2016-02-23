package grab;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	public static ObjectMapper mapper = new ObjectMapper();
	
	
	public <T> T  jsonToBean(String jsonString,Class<T> valueType){
		T returnObj = null;
		try {
			returnObj = mapper.readValue(jsonString, valueType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnObj;
	}
}
