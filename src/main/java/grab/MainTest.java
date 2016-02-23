package grab;

import grab.service.GrabCompanyService2;

public class MainTest {

	public static void main(String[] args) {
		System.out.println(CodeUtil.getNextCode("123456000000000"));
		GrabCompanyService2 xx =new GrabCompanyService2();
		xx.grabCompany();
	}

}
