package grab.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SearchResult {
	public  String state;
	public  String message;
	public  int totalPage;
	public  String humanCount;
	public  String companyCount;
//	public  String total;
	public  List<SimpleInfo> data;
}
