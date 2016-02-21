package grab.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import grab.CodeUtil;
import grab.dal.mapper.RegionMapper;
import grab.dal.mapper.ResultMapper;
import grab.dal.model.CompanyInfoWithBLOBs;
import grab.dal.model.Region;
import grab.dal.model.Result;
@Component
public class GrabCompanyService2 {
	private static Logger logger = LogManager.getLogger(GrabCompanyService2.class);
	private static  List<Region> regionList;
	private static Integer noResultCount = 0;
	@Autowired
	RegionMapper regionMapper;
	@Autowired
	ResultMapper resultMapper;

	public void grabCompany(){
		
		 Connection connection = Jsoup.connect("http://www.qichacha.com");
		 connection.timeout(15000);
		 String currentRegisterId = "";
		 regionList = loadCity(regionList,1);
		
		 for(int i=0;i<regionList.size();i++){
			 logger.info("start");
			 currentRegisterId = regionList.get(i).getRegionId()+"000000000";
			 for(int j=0;j<100000000;j++){
				 logger.info("noResultCount:{} currentRegisterId:{}",noResultCount,currentRegisterId);
				 currentRegisterId =  String.valueOf(CodeUtil.getNextCode(currentRegisterId));
				 if(noResultCount ==3000){
					 noResultCount=0;
					 break;
				 }
				 noResultCount = 0;

			 }
		 }}
		
	

	
	public List<Region> loadCity(List<Region> regionList,int begin){
		if(regionList==null){
	regionList = regionMapper.getAllRegion(begin);
		}
	for(Region tmp: regionList){
		System.out.println(tmp.getId()+":"+tmp.getRegionName());
	}
	return regionList;
	}
	
	public void getAllInfo(String registerId ,Connection connection) {
		URL url = null;
		try {
			url = new URL("http://www.qichacha.com/search?key=" + registerId + "&sType=0");
			logger.info("url:{}",url.toString());
			connection.request().url(url);
			Document doc = connection.get();
			logger.info("compath:{}  companyId:{}");
		} catch (Exception e) {
			Result rr = new Result();
			rr.setStatus(0);
			rr.setPath(url.toString());
			rr.setCompanyId(registerId);
			rr.setException(e.toString());
			resultMapper.insertSelective(rr);
			logger.error(url.toString(),e);
			return ;
		} 

	}
	
	
	
}
