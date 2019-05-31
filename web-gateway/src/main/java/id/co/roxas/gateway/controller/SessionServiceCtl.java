package id.co.roxas.gateway.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import id.co.roxas.data.transfer.object.UserDataActivation.response.WsResponse;

@RequestMapping("/session")
@RestController
public class SessionServiceCtl {

	
	    @PostMapping("/save")
		public WsResponse saveSession(@RequestBody Map<String, Object> sessionNeeded, 
				HttpSession session) {
	    	if(sessionNeeded.get("token")!=null)
	    	session.setAttribute("token",sessionNeeded.get("token"));
	    	if(sessionNeeded.get("age-token")!=null)
	    	session.setAttribute("age-token", sessionNeeded.get("age-token"));
	    	if(sessionNeeded.get("user-name")!=null)
	    	session.setAttribute("user-name", sessionNeeded.get("user-name"));
	    	if(sessionNeeded.get("user-password")!=null)
	    	session.setAttribute("user-password", sessionNeeded.get("user-password"));
	    	return new WsResponse("Save Success", "I001");
		}
	
	    @GetMapping("/retrieve")
	    public WsResponse retrieveSession(HttpSession session){
	    	System.err.println("will get session : " +  session.getAttribute("user-name"));
	    	Map<String, Object> map = new HashMap<>();
	    	map.put("token", session.getAttribute("token"));
	    	map.put("age-token", session.getAttribute("age-token"));
	    	map.put("user-name", session.getAttribute("user-name"));
	    	map.put("user-password", session.getAttribute("user-password"));
	    	return new WsResponse(map,"I001");
	    }
	    
}
