package id.co.roxas.app.web.uda.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;

import id.co.roxas.app.web.uda.UltimateBase;
import id.co.roxas.app.web.uda.staticVar.InformationStatus;

@Controller
@RequestMapping("/web-uda")
public abstract class BaseWebController extends UltimateBase {
	
	private String message;
	
	protected void refreshAllSession(HttpServletRequest request) {
		request.getSession().invalidate();
	}
	

    protected String onSecurityAccess(String url, HttpServletRequest request) {
    	
//    	String token = (String) request.getSession().getAttribute("token");
//    	Date ageToken = (Date) request.getSession().getAttribute("age-token");
//    	String userName = (String) request.getSession().getAttribute("user-name");
//    	String userPassword = (String) request.getSession().getAttribute("user-password");
    	
    	String token = (String) request.getSession().getAttribute("token");
    	Date ageToken = (Date) request.getSession().getAttribute("age-token");
    	String userName = (String) request.getSession().getAttribute("user-name");
    	String userPassword = (String) request.getSession().getAttribute("user-password");
    	
    	if(token == null) {
    		
    		this.message = InformationStatus.EMPTY_TOKEN;
    		System.err.println(message);
    		lastWantedUrlRequest(url, request);
    		return "login";
    	}
    	else {
    		if(ageToken.before(new Date())){
    			token = restingToken(userName, userPassword);
    			if(token!=null) {
    				this.message = InformationStatus.PERFECT_COG;
    				System.err.println(message);
    				request.getSession().setAttribute("age-token", new Date());
    				request.getSession().setAttribute("token", token);
    				return url;
    			}
    			else {
    				
    				this.message = InformationStatus.AGE_TOKEN_OLD;
    				System.err.println(message);
    				lastWantedUrlRequest(url, request);
    				return "login";
    			}
    		}
    		else {
    			this.message = InformationStatus.TOKEN_CANT_RETRIEVE;
    			System.err.println(message);
    			request.getSession().setAttribute("token", null);
    			lastWantedUrlRequest(url, request);
    			return "login";
    		}
    	}
    }

	public String getMessage() {
		return message;
	}
    
    
    
    
    
}
