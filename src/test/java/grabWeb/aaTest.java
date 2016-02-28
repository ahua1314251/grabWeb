package grabWeb;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import grab.CodeUtil;
import grab.service.ParserService;

@ContextConfiguration({"classpath*:/application-context.xml"})
public class aaTest extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	ParserService parserService;
	
	@Test
	public void aa(){
		parserService.parserCompanyInfo();
	}
	
	
	@Test
	public  void bb(){
		System.out.println(CodeUtil.getCheckCode("1877648"));
	}
	
}
