package id.co.roxas.app.web.uda.controller.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import id.co.roxas.app.web.uda.controller.BaseWebController;

@Controller
public class LoginCtl extends BaseWebController {

	// Do not change return value of this request
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	// Do not change return value of this request
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		refreshAllSession(request);
		return "login";
	}
}
