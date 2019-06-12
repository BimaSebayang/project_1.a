package id.co.roxas.app.web.languange.controller.master;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.roxas.app.web.languange.controller.BaseRestWebController;

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
