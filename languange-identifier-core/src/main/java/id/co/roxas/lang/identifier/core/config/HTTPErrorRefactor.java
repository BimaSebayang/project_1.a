package id.co.roxas.lang.identifier.core.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class HTTPErrorRefactor {
	@RequestMapping(value = "/HTTP404", method = RequestMethod.GET)
	public String HTTP404(HttpServletRequest httpServletRequest) {
		return "HTTP404";
	}
}
