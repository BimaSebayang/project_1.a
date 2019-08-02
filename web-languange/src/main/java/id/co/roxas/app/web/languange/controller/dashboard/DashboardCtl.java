package id.co.roxas.app.web.languange.controller.dashboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import id.co.roxas.app.web.languange.controller.BaseWebController;

@Controller
public class DashboardCtl extends BaseWebController{

	@RequestMapping(value = "/dashboard-admin",  method = RequestMethod.GET)
	public String dashboardCtl(HttpServletRequest request, HttpSession session) {
		return onSecurityAccess("dashboard-admin", request);
	}
	
	
}
