package id.co.roxas.app.web.uda.controller.userSetting;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import id.co.roxas.app.web.uda.config.HttpSecurityService;
import id.co.roxas.app.web.uda.controller.BaseRestWebController;
import id.co.roxas.app.web.uda.lib.ParamQueryCustomLib;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblRoleDto;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblUserDto;
import id.co.roxas.data.transfer.object.UserDataActivation.custom.TransactionCUDDto;
import id.co.roxas.data.transfer.object.UserDataActivation.response.WsResponse;

@RestController
public class userAdminSettingWsCtl extends BaseRestWebController {

	@GetMapping("/role/select-all")
	public List<TblRoleDto> retrieveAllRole(HttpServletRequest httpServletRequest) {
		HttpSecurityService httpSecurityService = new HttpSecurityService(null, TblRoleDto.getDtoticketing(),
				"user-admin-setting");
		WsResponse response = resultWsWitSecurityAccess(END_POINT_URL + "/admin-ws/role-transaction/select-all", null,
				HttpMethod.GET, null, getToken(httpServletRequest), httpSecurityService);
		List<TblRoleDto> tblRoleDtos = new ArrayList<>();
		try {
			tblRoleDtos = mapperJsonToListDto(response.getWsContent(), TblRoleDto.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tblRoleDtos;
	}

	@PostMapping("/save/user-admin-setting")
	public String userAdminSettingSave(@RequestBody TblUserDto tblUserDto, HttpServletRequest httpServletRequest) {
		HttpSecurityService httpSecurityService = new HttpSecurityService(TblUserDto.getDtoticketing(),
				TransactionCUDDto.getDtoticketing(), "user-admin-setting");
		WsResponse response = resultWsWitSecurityAccess(END_POINT_URL + "/admin-ws/user-transaction/save", tblUserDto,
				HttpMethod.POST, null, getToken(httpServletRequest), httpSecurityService, new ParamQueryCustomLib[] {});
		return response.getMessage();
	}
}
