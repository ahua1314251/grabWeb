package grab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import grab.service.GrabCompanyService2;

@Controller
public class JobController {
	@Autowired
	GrabCompanyService2  grab ;
	@RequestMapping("/StartJob")
	@ResponseBody
	public String startJob(Integer begin, String end) {
//	/	System.out.println("start Job begin:"+begin);
		grab.grabCompany();
		return "qeqwe";
	}

}
