package id.co.roxas.app.web.uda.controller.master;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.roxas.app.web.uda.controller.BaseRestWebController;

@RestController
public class MasterIndexWsCtl extends BaseRestWebController {

	@RequestMapping("/last-url")
	public String lastUrlCtl(HttpServletRequest httpServletRequest) {
		String data = lastUrl(httpServletRequest);
		if(data.equalsIgnoreCase("master-web-uda-index")) {
			data = "dashboard-admin";
		}
		return data;
	}
}
