package grab.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.protocol.HTTP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import grab.CodeUtil;
import grab.JsonUtil;
import grab.bean.SearchResult;
import grab.dal.mapper.CompanyInfoMapper;
import grab.dal.mapper.RegionMapper;
import grab.dal.mapper.ResultMapper;
import grab.dal.model.CompanyInfoWithBLOBs;
import grab.dal.model.Region;
import grab.dal.model.Result;

@Component
public class GrabCompanyService2  implements InitializingBean{
	private static Logger logger = LogManager
			.getLogger(GrabCompanyService2.class);
	private static  List<Region> regionList;
	private static  int currentCount = -1;
	private static  Integer noResultCount = 0;
	private static  String currentRegisterId = "330000000000000";
	@Autowired
	RegionMapper regionMapper;
	@Autowired
	ResultMapper resultMapper;
	@Autowired
    CompanyInfoMapper companyInfoMapper;
	public void afterPropertiesSet(){
		loadCity();
		currentRegisterId = regionList.get(0).getRegionCode()+"000000000";
		System.out.println("init *****************************************");
	}
	GrabCompanyService2(){
//		regionList = new ArrayList<Region>();      //loadCity(regionList, 1);
//		Region r1= new Region();
//		r1.setRegionCode("123455");
//		Region r2= new Region();
//		r2.setRegionCode("333444");
//		regionList.add(r1);
//		regionList.add(r2);
		
	}
	
	@SuppressWarnings("static-access")
	public void grabCompany() {
		
		//connection.request().header("Accept", "application/json");

		
		while (true) {
			String currentRegisterId2 = getNextRegisterId();

			logger.info("noResultCount:{} currentRegisterId:{}", noResultCount,currentRegisterId);
            try {
				Thread.currentThread().sleep(2000);
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			if(getAllInfo(currentRegisterId2)){
				noResultCount = 0;
			}else{
				noResultCount ++;
			}
			
			
			if (noResultCount == 8000) {
				if (regionList.size() < 2) {
					break;
				}
				regionList.remove(0);
				noResultCount = 0;
				currentRegisterId = regionList.get(0).toString() + "000000000";
			}
		}
	}
	public synchronized String  getNextRegisterId(){
		currentRegisterId = String.valueOf(CodeUtil.getNextCode(currentRegisterId));
		return currentRegisterId;
	}

	public List<Region> loadCity() {
		if (regionList == null) {
			regionList = regionMapper.getAllRegion();
		}
		for (Region tmp : regionList) {
			System.out.println(tmp.getId() + ":" + tmp.getRegionName());
		}
		return regionList;
	}

	@SuppressWarnings("static-access")
	public Boolean getAllInfo(String registerId) {
		 SearchResult sr =null;
		URL url = null;
		try {
			url = new URL("http://www.tianyancha.com/search/" + registerId+ ".json");
			logger.info("url:{}", url.toString());
			Request rq= Request.Get(url.toString());
			rq.addHeader(HTTP.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0)");
			rq.addHeader(HTTP.CONN_DIRECTIVE,HTTP.CONN_KEEP_ALIVE);
			rq.addHeader(HTTP.CONTENT_TYPE, "application/json");
            rq.socketTimeout(100000);		
			Response rp = rq.execute();
            sr = JsonUtil.jsonToBean(rp.returnContent().toString(), SearchResult.class);
            System.out.println(sr.data);
			if ("ok".equals(sr.state)) {
				Thread.currentThread().sleep(2000);
				CompanyInfoWithBLOBs comInfo =  sr.data.get(0);
				System.out.println("http://www.tianyancha.com/company/" +comInfo.getId() + ".json");
				rq = Request.Get("http://www.tianyancha.com/company/" + comInfo.getId() + ".json");
				rq.addHeader(HTTP.USER_AGENT,
						"	Mozilla/5.0 (Windows NT 6.1; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0");
				rq.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
				rq.addHeader(HTTP.CONTENT_TYPE, "application/json");
				rq.addHeader("Accept", "application/json, text/plain, */*");
				rq.socketTimeout(100000);
				rq.socketTimeout(100000);
				rp = rq.execute();
				comInfo.setRegisterId(registerId);
				comInfo.setOrgId(regionList.get(0).getRegionCode());
				comInfo.setAllInfo(rp.returnContent().toString());
				comInfo.setUpdateTime(new Date());
			    companyInfoMapper.insertSelective(comInfo);
//				AllInfoResult af = JsonUtil.jsonToBean(rp.returnContent().toString(), AllInfoResult.class);
			//	System.out.println(af.data);
				return true;
            }else{
            	return false;
            }
			
		} catch (Exception e) {
			Result rr = new Result();
			rr.setStatus(0);
			rr.setPath(url.toString());
			rr.setCompanyId(registerId);
			rr.setException(e.toString());
			resultMapper.insertSelective(rr);

			logger.error(url.toString(), e);
		}
		return true;
	}

}
