package id.co.roxas.lang.identifier.core.service.auth;

import java.util.HashMap;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblUserDto;
import id.co.roxas.data.transfer.object.UserDataActivation.response.WsResponse;
import id.co.roxas.lang.identifier.core.UltimateBase;
import id.co.roxas.lang.identifier.core.lib.ParamQueryCustomLib;

@Service
public class UserAuthenticationCustomLogin extends UltimateBase{

	
	public TblUserDto findByUserTicketOrUserEmailOrUserUserPhoneOrUserId(String userValidation) {
		WsResponse response = resultWsWithoutSecurity(UAA_END_POINT_URL + "/web-request/request-user", 
				userValidation, HttpMethod.POST, new HashMap<>(), new ParamQueryCustomLib[] {});
		TblUserDto tblUserDto = new TblUserDto();
		try {
			tblUserDto  = mapperJsonToSingleDto(response.getWsContent(), TblUserDto.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tblUserDto;
	}
}
