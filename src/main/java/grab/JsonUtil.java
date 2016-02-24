package grab;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	public static ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}
	
	public static <T> T  jsonToBean(String jsonString,Class<T> valueType){
		T returnObj = null;
		try {
			returnObj = mapper.readValue(jsonString, valueType);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnObj;
	}
	
	public static String beanToJson(Object obj){
		String result =null;
		try {
			result = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}
		return result;
	}
	
}
