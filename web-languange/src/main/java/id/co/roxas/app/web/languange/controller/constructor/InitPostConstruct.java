package id.co.roxas.app.web.languange.controller.constructor;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RestController;

import id.co.roxas.app.web.languange.controller.BaseRestWebController;
import id.co.roxas.app.web.languange.lib.ParamQueryCustomLib;
import id.co.roxas.data.transfer.object.UserDataActivation.response.WsResponse;

@RestController
public class InitPostConstruct extends BaseRestWebController{
  
	@PostConstruct
	public void nullAllUaaSession() {
		WsResponse response = resultWsWithoutSecurity(UAA_END_POINT_URL+"/database-init/refreshAllSession", null,
				HttpMethod.GET, null, new ParamQueryCustomLib[] {});
		System.out.println(response.getMessage());
	}
}
