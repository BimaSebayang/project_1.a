package id.co.roxas.app.web.languange.controller.login;

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

import id.co.roxas.app.web.languange.controller.BaseRestWebController;
import id.co.roxas.app.web.languange.lib.ParamQueryCustomLib;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblUserDto;
import id.co.roxas.data.transfer.object.UserDataActivation.custom.TransactionCUDDto;
import id.co.roxas.data.transfer.object.UserDataActivation.model.LoginForm;
import id.co.roxas.data.transfer.object.UserDataActivation.response.WsResponse;
import id.co.roxas.data.transfer.object.shared.ticket.TicketCc;

@RestController
public class LoginWsCtl extends BaseRestWebController {

	@GetMapping("/hello-ws")
	public String helloWs() {
		return "hello ws ";
	}

	@PostMapping("/request-login")
	public String requestLoginWs(@RequestBody LoginForm loginForm, HttpServletRequest request) {
		System.err.println("masuk kagak yahh");
		String token = restingToken(loginForm.getUserName(), loginForm.getPassword());
		if (token == null) {
			return LOGIN_URL;
		} else {
			TicketCc cc = new TicketCc();
			cc.setModule("web-languange");
			cc.setSessionId(request.getSession().getId());
			cc.setAccessIdentifier(getAccessDevice(request));
			cc.setUserIdentifier(loginForm.getUserName());

			@SuppressWarnings("unused")
			WsResponse response = resultWsWithoutSecurity(UAA_END_POINT_URL + "/web-request/ticket/update-user", cc,
					HttpMethod.PUT, null, new ParamQueryCustomLib[] {});
			TransactionCUDDto cudDto = new TransactionCUDDto();
			try {
				cudDto = mapperJsonToSingleDto(response.getWsContent(), TransactionCUDDto.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (cudDto.getUpdateResult() == 1)
				return lastUrl(request);
			else
				return LOGIN_URL;
		}
	}
}