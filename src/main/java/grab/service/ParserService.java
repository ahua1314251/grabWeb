package grab.service;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import grab.dal.mapper.CompanyInfoMapper;
import grab.dal.model.CompanyInfo;
import grab.dal.model.CompanyInfoWithBLOBs;
@Component
public class ParserService {
	@Autowired
	CompanyInfoMapper companyInfoMapper;
	public static int  number = 0;
	
	public static void main(String[] args) {
		

	}

	public synchronized int addNumber(){
		number--;
		return number;
	}
	
	public CompanyInfo parserCompanyInfo(){
		CompanyInfoWithBLOBs info = companyInfoMapper.selectByPrimaryKey(1);
	    Document document = Jsoup.parse(info.getAllInfo());
	    System.out.println(document.html());
	    Elements eles = document.getElementsByClass("company-info");
	    System.out.println(eles.html());
		
		return info;
		
	}

}
