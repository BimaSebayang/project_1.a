package id.co.roxas.app.web.uda.controller.login;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import id.co.roxas.app.web.uda.config.HttpSecurityService;
import id.co.roxas.app.web.uda.controller.BaseRestWebController;
import id.co.roxas.app.web.uda.lib.ParamQueryCustomLib;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblUserDto;
import id.co.roxas.data.transfer.object.UserDataActivation.custom.TransactionCUDDto;
import id.co.roxas.data.transfer.object.UserDataActivation.model.LoginForm;
import id.co.roxas.data.transfer.object.UserDataActivation.response.WsResponse;

@RestController
public class LoginWsCtl extends BaseRestWebController{
      
	@GetMapping("/hello-ws")
	public String helloWs() {
		return "hello ws ";
	}
	
    
	
	@PostMapping("/request-login")
	public String requestLoginWs(@RequestBody LoginForm loginForm, HttpServletRequest request) {
		String token = restingToken(loginForm.getUserName(), loginForm.getPassword());
		if(token==null) {
		   return LOGIN_URL;
		}
		else {
			request.getSession().setAttribute("token",token);
	    	request.getSession().setAttribute("age-token", new Date());
	    	request.getSession().setAttribute("user-name", loginForm.getUserName());
	    	request.getSession().setAttribute("user-password", loginForm.getPassword());
	    	RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	    	ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	    	System.err.println("login : " + attributes.getRequest().getSession().getAttribute(token));
		     return lastUrl(request);
		}
	}
}
