package grab;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Connection;
import org.jsoup.Connection.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;

import grab.dal.mapper.RegionMapper;
import grab.dal.model.CompanyInfoWithBLOBs;
import grab.dal.model.Region;
import grab.service.GrabCompanyService;

@Component("main")
public class Main {
	@Autowired
	RegionMapper rmapper;

	public static void main(String[] args) throws IOException, SAXException {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:application-context.xml");

		GrabCompanyService grab = (GrabCompanyService) ctx.getBean("grabCompanyService");
		grab.grabCompany();
		// main.getRegion3();

	}

	public void getRegion() throws IOException {
		Document doc = Jsoup.connect("http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/201504/t20150415_712722.html").get();
		doc.outputSettings().charset("Utf-8");
		// class TRS_PreAppend
		Elements eles = doc.getElementsByClass("MsoNormal");
		eles.forEach(ele -> {
			Region region = new Region();

			String regionCode = ele.getElementsByAttributeValue("lang", "EN-US").html()
					.replace("<span>&nbsp; </span>", "").replace("<span>&nbsp;&nbsp; </span>", "").trim();
			regionCode = regionCode
					.replace("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;", "")
					.trim();

			String regionName = ele.getElementsByAttributeValueContaining("style", "宋体").html().replace("　", "").trim();

			if (regionCode.contains("定州市")) {
				regionCode = "130682";
				regionName = "定州市";
			}
			if (regionCode.contains("辛集")) {
				regionCode = "130181";
				regionName = "辛集市";
			}
			region.setRegionId(Integer.parseInt(regionCode));
			region.setRegionCode(regionCode);
			region.setRegionName(regionName);
			System.out.println(regionCode + ":" + regionName);
			rmapper.insertSelective(region);
			System.out.println("inser");
		});
	}

	public void getRegion2() throws IOException, SAXException {
		final WebClient webClient = new WebClient();
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(true);
		CookieManager cookieManager = new CookieManager();
		Cookie k1 = new Cookie("www.qichacha.com", "CNZZDATA1254842228", "2145780953-1452911328-|1453207394");
		Cookie k2 = new Cookie("www.qichacha.com", "pspt",
				"{\"id\":\"627423\",\"pswd\":\"8835d2c1351d221b4ab016fbf9e8253f\",\"_code\":\"3bcb601f4ccde624febf5f5adcce4092\"}");
		Cookie k3 = new Cookie("www.qichacha.com", "PHPSESSID", "6boc3otstdittdubvbmt60jhf1");
		Cookie k4 = new Cookie("www.qichacha.com", "think_language", "zh-cn");
		Cookie k5 = new Cookie("www.qichacha.com", "SERVERID",
				"b7e4e7feacd29b9704e39cfdfe62aefc|1453211638|1453211472");
		URL url = new URL("http://www.qichacha.com/");

		cookieManager.buildCookieOrigin(url);
		cookieManager.addCookie(k1);
		cookieManager.addCookie(k2);
		cookieManager.addCookie(k3);
		cookieManager.addCookie(k4);
		cookieManager.addCookie(k5);
		final HtmlPage page = webClient.getPage("http://www.qichacha.com/firm_AH_bbe1ec15e8e69a62238c2b7d780081ba");
		System.out.println(page.asXml());
		webClient.closeAllWindows();
	}

	public void getRegion3() throws IOException, SAXException {

		CookieManager cookieManager = new CookieManager();
		Cookie k1 = new Cookie("www.qichacha.com", "CNZZDATA1254842228", "2145780953-1452911328-|1453207394");
		Cookie k2 = new Cookie("www.qichacha.com", "pspt",
				"{\"id\":\"627423\",\"pswd\":\"8835d2c1351d221b4ab016fbf9e8253f\",\"_code\":\"3bcb601f4ccde624febf5f5adcce4092\"}");
		Cookie k3 = new Cookie("www.qichacha.com", "PHPSESSID", "6boc3otstdittdubvbmt60jhf1");
		Cookie k4 = new Cookie("www.qichacha.com", "think_language", "zh-cn");
		Cookie k5 = new Cookie("www.qichacha.com", "SERVERID",
				"b7e4e7feacd29b9704e39cfdfe62aefc|1453211638|1453211472");

		Connection connection = Jsoup
				.connect("http://www.qichacha.com/company_base?unique=f6945da7fc4882ef7fc1c7209fe6ca1b");

		Request request = connection.request();
		request.cookie("CNZZDATA1254842228", "2145780953-1452911328-|1453207394");
		request.cookie("pspt",
				"{\"id\":\"627423\",\"pswd\":\"8835d2c1351d221b4ab016fbf9e8253f\",\"_code\":\"3bcb601f4ccde624febf5f5adcce4092\"}");
		request.cookie("PHPSESSID", "6boc3otstdittdubvbmt60jhf1");
		request.cookie("think_language", "zh-cn");
		request.cookie("SERVERID", "b7e4e7feacd29b9704e39cfdfe62aefc|1453211638|1453211472");
		Document doc = connection.get();
		System.out.println(doc.getElementsByClass("base_info").outerHtml());
	}

	public CompanyInfoWithBLOBs saveBasicInfo(Connection connection) {
		CompanyInfoWithBLOBs companyInfo = new CompanyInfoWithBLOBs();

		return companyInfo;
	}

}
