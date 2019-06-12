package id.co.roxas.app.web.languange.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import id.co.roxas.app.web.languange.UltimateBase;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblUserDto;
import id.co.roxas.data.transfer.object.UserDataActivation.response.WsResponse;
import id.co.roxas.data.transfer.object.shared.ticket.TicketCc;

@Controller
public abstract class BaseWebController extends UltimateBase {
	
	private String message;
	
	protected void refreshAllSession(HttpServletRequest request) {
		TicketCc cc = new TicketCc();
		cc.setModule("web-languange");
		cc.setSessionId(request.getSession().getId());
		cc.setAccessIdentifier(getAccessDevice(request));
		TblUserDto tblUserDto = getUserDtoAccess(cc);
		cc.setSessionId(null);
		cc.setAccessIdentifier(getAccessDevice(request));
		cc.setUserIdentifier(tblUserDto.getUserId());
		cc.setIsLogOut(1);
		@SuppressWarnings("unused")
		WsResponse response2 = resultWsWithoutSecurity
				(UAA_END_POINT_URL+"/web-request/ticket/update-user", 
						cc, HttpMethod.PUT, null, new id.co.roxas.app.web.languange.controller.lib.ParamQueryCustomLib[] {});
		request.getSession().invalidate();
	}
	

    protected String onSecurityAccess(String url, HttpServletRequest request) {
    	TicketCc cc = new TicketCc();
		cc.setModule("web-languange");
		cc.setSessionId(request.getSession().getId());
		cc.setAccessIdentifier(getAccessDevice(request));
		TblUserDto tblUserDto = getUserDtoAccess(cc);
		if(tblUserDto==null) {
			return LOGIN_URL;
		}
		return url;
    }

	public String getMessage() {
		return message;
	}
    
    
    
    
    
}