package grab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import grab.service.GrabCompanyService;

@Controller
public class JobController {
	@Autowired
	GrabCompanyService  grab ;
	@RequestMapping("/StartJob")
	@ResponseBody
	public String startJob(int begin, String end) {
		System.out.println("start Job begin:"+begin);
		grab.grabCompany(begin);

		return "qeqwe";
	}

}
