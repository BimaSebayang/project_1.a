package id.co.roxas.app.web.languange.controller.master;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import id.co.roxas.app.web.languange.controller.BaseWebController;

@Controller
public class MasterIndexCtl extends BaseWebController{
	@RequestMapping(value = "/master-web-uda-index", method = RequestMethod.GET)
	public String sideBarMenu(HttpServletRequest request) {
		return onSecurityAccess("master-web-uda-index", request);
	}

	@RequestMapping(value = "/list-modal", method = RequestMethod.GET)
	public String callModal(HttpServletRequest request) {
		return onSecurityAccess("list-modal", request);
	}
}
