package grab;

import java.io.IOException;
import java.util.Iterator;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.protocol.HTTP;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import grab.bean.SearchResult;
import grab.service.GrabCompanyService2;

public class MainTest {

	public static void main(String[] args) throws ClientProtocolException, IOException {
//		System.out.println(CodeUtil.getNextCode("123456000000000"));
//		GrabCompanyService2 xx =new GrabCompanyService2();
//		xx.grabCompany();
//				
		Request rq= Request.Get("http://www.tianyancha.com/search/330000000000097.json");
		rq.addHeader(HTTP.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0)");
		rq.addHeader(HTTP.CONN_DIRECTIVE,HTTP.CONN_KEEP_ALIVE);
		rq.addHeader(HTTP.CONTENT_TYPE, "application/json");
        rq.socketTimeout(100000);		
		Response rp = rq.execute();

		SearchResult sr = JsonUtil.jsonToBean(rp.returnContent().toString(), SearchResult.class);
	//	org.apache.http.client.HttpResponseException
		System.out.println(sr.state);
		
//		JsonNode jNode = JsonUtil.mapper.readTree(rp.returnContent().toString());
//		Iterator<String> it = jNode.fieldNames();
//		while(it.hasNext()){
//			System.out.println("public  String "+it.next()+";");
//		}
//	
//		Iterator<JsonNode> ite = jNode.elements();
//		while(ite.hasNext()){
//			JsonNode jd = ite.next();
//			if(jd.isArray()){
//				jd = jd.get(0);
//				
//			Iterator<String> it2 = jd.fieldNames();
//			while(it2.hasNext()){
//				System.out.println("public  String "+it2.next()+";");
//			}
//			}
//		}
	}


	
	
}
