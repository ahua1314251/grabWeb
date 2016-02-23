package grab.service;

import grab.CodeUtil;
import grab.dal.mapper.RegionMapper;
import grab.dal.mapper.ResultMapper;
import grab.dal.model.Region;
import grab.dal.model.Result;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@Component
public class GrabCompanyService2 {
	private static Logger logger = LogManager
			.getLogger(GrabCompanyService2.class);
	private static List<Region> regionList;
	private static int currentCount = -1;
	private static Integer noResultCount = 0;
	private static String currentRegisterId = "330000000000000";
	@Autowired
	RegionMapper regionMapper;
	@Autowired
	ResultMapper resultMapper;

	public void grabCompany() {
		
		//connection.request().header("Accept", "application/json");

		regionList = new ArrayList<Region>();      //loadCity(regionList, 1);
		Region r1= new Region();
		r1.setRegionCode("123455");
		Region r2= new Region();
		r2.setRegionCode("333444");
		regionList.add(r1);
		regionList.add(r2);
		while (true) {
			logger.info("start");
			currentRegisterId = String.valueOf(CodeUtil.getNextCode(currentRegisterId));

			logger.info("noResultCount:{} currentRegisterId:{}", noResultCount,currentRegisterId);
            try {
				Thread.sleep(3000);
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			getAllInfo(currentRegisterId);
			
			
			if (noResultCount == 8000) {
				if (regionList.size() < 2) {
					break;
				}
				noResultCount = 0;
				currentRegisterId = regionList.get(0).toString() + "000000000";
			}
		}
	}

	public List<Region> loadCity(List<Region> regionList, int begin) {
		if (regionList == null) {
			regionList = regionMapper.getAllRegion();
		}
		for (Region tmp : regionList) {
			System.out.println(tmp.getId() + ":" + tmp.getRegionName());
		}
		return regionList;
	}

	public void getAllInfo(String registerId) {
		URL url = null;
		try {
			url = new URL("http://www.tianyancha.com/search/" + registerId+ ".json");
			logger.info("url:{}", url.toString());
			WebClient  webClient=new WebClient(BrowserVersion.CHROME);
			HtmlPage page=webClient.getPage(url.toString());
			System.out.println(page.);
//			logger.info(doc.text());
		} catch (Exception e) {
			Result rr = new Result();
			rr.setStatus(0);
			rr.setPath(url.toString());
			rr.setCompanyId(registerId);
			rr.setException(e.toString());
	//		resultMapper.insertSelective(rr);
			logger.error(url.toString(), e);
			return;
		}

	}

}
