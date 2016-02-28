package grab.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AllInfoResult {
	public  String state;
	public  String message;
	public  Object data;
}
