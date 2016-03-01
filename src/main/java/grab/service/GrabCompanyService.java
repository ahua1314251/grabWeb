//package grab.service;
//
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.jsoup.Connection;
//import org.jsoup.Connection.Request;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.select.Elements;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import grab.CodeUtil;
//import grab.dal.mapper.CompanyInfoMapper;
//import grab.dal.mapper.ImmaterialAssetsMapper;
//import grab.dal.mapper.LawRecordMapper;
//import grab.dal.mapper.RecruitMapper;
//import grab.dal.mapper.RegionMapper;
//import grab.dal.mapper.ResultMapper;
//import grab.dal.mapper.YearReportMapper;
//import grab.dal.model.CompanyInfoWithBLOBs;
//import grab.dal.model.ImmaterialAssets;
//import grab.dal.model.LawRecord;
//import grab.dal.model.Recruit;
//import grab.dal.model.Region;
//import grab.dal.model.Result;
//import grab.dal.model.YearReport;
//@Component
//public class GrabCompanyService {
//	private static Logger logger = LogManager.getLogger(GrabCompanyService.class);
//	@Autowired
//	RegionMapper regionMapper;
//	@Autowired
//	ResultMapper resultMapper;
//
//	List<Region> regionList = new ArrayList<>();
//	 int noResultCount = 0;
//	 int timeOutCount = 0;
//	
//	public void grabCompany(){
//
//		 Connection connection = Jsoup.connect("http://www.qichacha.com");
//		 connection.timeout(15000);
//		 String currentRegisterId = "";
//		 loadCity();
//		 loadCookies(connection);
//		 for(int i=0;i<regionList.size();i++){
//			 logger.info("start");
//			 currentRegisterId = regionList.get(i).getRegionId()+"000000000";
//			 for(int j=0;j<100000000;j++){
//				 logger.info("noResultCount:{} currentRegisterId:{}",noResultCount,currentRegisterId);
//				 currentRegisterId =  String.valueOf(CodeUtil.getNextCode(currentRegisterId));
//				 CompanyInfoWithBLOBs comInfo = searchCompany(currentRegisterId,connection);
//				 if(noResultCount ==1000){
//					 noResultCount=0;
//					 break;
//				 }
//				 if(comInfo == null){
//					 noResultCount++;
//					 continue;
//				 }
//				 noResultCount = 0;
//				 saveCompanyBase(comInfo,connection);
//				 saveCompanySusong(comInfo,connection);
//				 saveTouzi(comInfo,connection);
//				 saveReport(comInfo,connection);
//				 saveCompanyAssets(comInfo,connection);
//			 }
//		 }
//		
//	}
//	public void grabCompany2(){
//		 Connection connection = Jsoup.connect("http://www.qichacha.com");
//		 loadCookies(connection);
//		 connection.timeout(15000);
//		 CompanyInfoWithBLOBs comInfo = searchCompany("120000000000069",connection);
//		 saveCompanyBase(comInfo,connection);
//		 saveCompanySusong(comInfo,connection);
//		 saveTouzi(comInfo,connection);
//		 saveReport(comInfo,connection);
//		 saveCompanyAssets(comInfo,connection);
//	}
//	
//	
//	
//	public void loadCookies(Connection connection){
//		Request request = connection.request();
//		request.cookie("CNZZDATA1254842228", "2145780953-1452911328-|1453207394");
//		request.cookie("pspt", "{\"id\":\"627423\",\"pswd\":\"8835d2c1351d221b4ab016fbf9e8253f\",\"_code\":\"3bcb601f4ccde624febf5f5adcce4092\"}");
//		request.cookie("PHPSESSID", "6boc3otstdittdubvbmt60jhf1");
//		request.cookie("think_language", "zh-cn");
//		request.cookie("SERVERID", "b7e4e7feacd29b9704e39cfdfe62aefc|1453211638|1453211472");
//	}
//	
//	public void loadCity(){
//	regionList = regionMapper.getAllRegion();
//	}
//	
//		
//	public CompanyInfoWithBLOBs searchCompany(String registerId ,Connection connection) {
//		CompanyInfoWithBLOBs companyInfo = new CompanyInfoWithBLOBs();
//		URL url = null;
//		try {
//			url = new URL("http://www.qichacha.com/search?key=" + registerId + "&sType=0");
//			logger.info("url:{}",url.toString());
//			connection.request().url(url);
//			Document doc = connection.get();
//			doc.outputSettings().charset("utf-8");
//			if(doc.getElementsByClass("pull-left").size()<1){
//				Result rr = new Result();
//				rr.setStatus(100);
//				rr.setPath(url.toString());
//				rr.setCompanyId(registerId);
//				resultMapper.insertSelective(rr);
//				logger.info("该企业不存在 registerId:{}",registerId);
//				return null;
//			}
//			String comPath = doc.getElementsByClass("pull-left").get(0).attr("href");
//			Elements comName = doc.select("H3 a");
//			comName.select(".searchbadge").remove();
//			  
//			companyInfo.setRegisterId(registerId);
//			if(comPath==null||comPath.trim().equals("")){
//				return null;
//			}
//			companyInfo.setPath(comPath);
//			companyInfo.setCompanyname(comName.get(0).text().replace("存续", "").trim());
//			companyInfo.setCompanyId(comPath.substring(9, comPath.length()));
//			logger.info("compath:{}  companyId:{}",comPath,companyInfo.getCompanyId());
//		} catch (Exception e) {
//			Result rr = new Result();
//			rr.setStatus(0);
//			rr.setPath(url.toString());
//			rr.setCompanyId(registerId);
//			rr.setException(e.toString());
//			resultMapper.insertSelective(rr);
//			logger.error(url.toString(),e);
//			return null;
//		} 
//		return companyInfo;
//	}
//	
//	@Autowired
//	CompanyInfoMapper companyInfoMapper;
//	public void saveCompanyBase(CompanyInfoWithBLOBs comInfo,Connection connection) {
//		URL url = null;
//		try {
//			url = new URL("http://www.qichacha.com/company_base?unique=" + comInfo.getCompanyId()+"&companyname="+comInfo.getCompanyname());
//			connection.request().url(url);
//			logger.info("baseUrl:{}",url.toString());
//			Document doc = connection.get();
//			Elements baseInfo = doc.getElementsByClass("base_info");
//			//System.out.println(baseInfo.outerHtml());
//			comInfo.setCompanyId(comInfo.getCompanyId());
//			comInfo.setRegisterId(comInfo.getRegisterId());
//			comInfo.setPath(url.toString());
//			comInfo.setAllInfo(baseInfo.outerHtml());
//			companyInfoMapper.insertSelective(comInfo);
//		} catch (Exception e) {
//			Result rr = new Result();
//			rr.setStatus(1);
//			rr.setPath(url.toString());
//			rr.setCompanyId(comInfo.getRegisterId());
//			rr.setException(e.toString());
//			resultMapper.insertSelective(rr);
//			logger.error(url.toString(),e);
//		} 
//	}
//	
//	@Autowired 
//	LawRecordMapper lawRecordMapper;
//	public void saveCompanySusong(CompanyInfoWithBLOBs comInfo,Connection connection) {
//		URL url = null;
//		try {
//			url = new URL("http://www.qichacha.com/company_susong?unique=" + comInfo.getCompanyId()+"&companyname="+comInfo.getCompanyname());
//			logger.info("Url:{}",url.toString());
//			connection.request().url(url);
//			Document doc = connection.get();
//			doc.outputSettings().charset("utf-8");
//			Elements susong = doc.getElementsByTag("body");
//			//System.out.println(susong.html());// susong
//			LawRecord obj = new LawRecord();
//			obj.setCompanyId(comInfo.getCompanyId());
//			obj.setRegisterId(comInfo.getRegisterId());
//			obj.setPath(url.toString());
//			obj.setAllInfo(susong.html());
//			lawRecordMapper.insertSelective(obj);
//		} catch (Exception e) {
//			Result rr = new Result();
//			rr.setStatus(2);
//			rr.setPath(url.toString());
//			rr.setCompanyId(comInfo.getRegisterId());
//			rr.setException(e.toString());
//			resultMapper.insertSelective(rr);
//			logger.error(url.toString(),e);
//		} 
//	}
//	
//	@Autowired
//	RecruitMapper recruitMapper ;
//	public void saveTouzi(CompanyInfoWithBLOBs comInfo,Connection connection) {
//		URL url = null;
//		try {
//			url = new URL("http://www.qichacha.com/company_touzi?unique=" + comInfo.getCompanyId()+"&companyname="+comInfo.getCompanyname());
//			logger.info("Url:{}",url.toString());
//			connection.request().url(url);
//			Document doc = connection.get();
//			Elements touzi = doc.getElementsByClass("touzi_info");
////			/System.out.println(touzi.outerHtml());
//			Recruit obj = new Recruit();
//			obj.setCompanyId(comInfo.getCompanyId());
//			obj.setRegisterId(comInfo.getRegisterId());
//			obj.setPath(url.toString());
//			obj.setAllInfo(touzi.outerHtml());
//			recruitMapper.insertSelective(obj);
//		} catch (Exception e) {
//			Result rr = new Result();
//			rr.setStatus(3);
//			rr.setPath(url.toString());
//			rr.setCompanyId(comInfo.getRegisterId());
//			rr.setException(e.toString());
//			resultMapper.insertSelective(rr);
//			logger.error(url.toString(),e);
//		} 
//	}
//	
//	@Autowired
//	YearReportMapper yearReportMapper;
//	public void saveReport(CompanyInfoWithBLOBs comInfo,Connection connection) {
//		URL url = null;
//		try {
//			url = new URL("http://www.qichacha.com/company_report?unique=" + comInfo.getCompanyId()+"&companyname="+comInfo.getCompanyname());
//			logger.info("Url:{}",url.toString());
//			connection.request().url(url);
//			Document doc = connection.get();
//			Elements report = doc.getElementsByClass("report_info");
//		//	System.out.println(report.outerHtml());
//			YearReport obj = new YearReport();
//			obj.setCompanyId(comInfo.getCompanyId());
//			obj.setRegisterId(comInfo.getRegisterId());
//			obj.setPath(url.toString());
//			obj.setAllInfo(report.outerHtml());
//			yearReportMapper.insertSelective(obj);
//		} catch (Exception e) {
//			Result rr = new Result();
//			rr.setStatus(4);
//			rr.setPath(url.toString());
//			rr.setCompanyId(comInfo.getRegisterId());
//			rr.setException(e.toString());
//			resultMapper.insertSelective(rr);
//			logger.error(url.toString(),e);
//		} 
//	}
//	
//	@Autowired
//	ImmaterialAssetsMapper immaterialAssetsMapper;
//	public void saveCompanyAssets(CompanyInfoWithBLOBs comInfo,Connection connection) {
//		URL url = null;
//		try {
//			url = new URL("http://www.qichacha.com/company_assets?unique=" + comInfo.getCompanyId()+"&companyname="+comInfo.getCompanyname());
//			logger.info("Url:{}",url.toString());
//			connection.request().url(url);
//			Document doc = connection.get();
//			Elements assets = doc.getElementsByTag("body");
//			//System.out.println(assets.html()); 
//			ImmaterialAssets obj = new ImmaterialAssets();
//			obj.setCompanyId(comInfo.getCompanyId());
//			obj.setRegisterId(comInfo.getRegisterId());
//			obj.setPath(url.toString());
//			obj.setAllInfo(assets.html());
//			immaterialAssetsMapper.insertSelective(obj);
//		} catch (Exception e) {
//			Result rr = new Result();
//			rr.setStatus(5);
//			rr.setPath(url.toString());
//			rr.setCompanyId(comInfo.getRegisterId());
//			rr.setException(e.toString());
//			resultMapper.insertSelective(rr);
//			logger.error(url.toString(),e);
//		} 
//	}
//	
//	
//	
//}
