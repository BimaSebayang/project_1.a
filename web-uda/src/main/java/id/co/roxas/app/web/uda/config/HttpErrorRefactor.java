package id.co.roxas.app.web.uda.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import id.co.roxas.app.web.uda.controller.BaseWebController;

@Controller
public class HttpErrorRefactor extends BaseWebController{
	@RequestMapping(value = "/HTTP404", method = RequestMethod.GET)
	public String HTTP404(HttpServletRequest httpServletRequest) {
		return "HTTP404";
	}
}
