package id.co.roxas.app.web.uda.controller.roleAdminSetting;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import id.co.roxas.app.web.uda.config.HttpSecurityService;
import id.co.roxas.app.web.uda.controller.BaseRestWebController;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblRoleDtlDto;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblRoleDto;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblUserDto;
import id.co.roxas.data.transfer.object.UserDataActivation.custom.TransactionCUDDto;
import id.co.roxas.data.transfer.object.UserDataActivation.response.WsResponse;

@RestController
public class roleAdminSettingWsCtl extends BaseRestWebController{
	@PostMapping("/save/role-admin-setting")
	public TransactionCUDDto roleAdminSettingSave(@RequestBody TblRoleDto tblRoleDto, HttpServletRequest httpServletRequest) {
		HttpSecurityService httpSecurityService = new HttpSecurityService(TblRoleDto.getDtoticketing(),
				TransactionCUDDto.getDtoticketing(), "role-admin-setting");
		WsResponse response = resultWsWitSecurityAccess(END_POINT_URL + "/admin-ws/role-transaction/save", tblRoleDto,
				HttpMethod.POST, null, getToken(httpServletRequest), httpSecurityService);
		TransactionCUDDto cudDto = new TransactionCUDDto();
		try {
			cudDto = mapperJsonToSingleDto(response.getWsContent(), TransactionCUDDto.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cudDto;
	}
	
	
	@GetMapping("/role/select-all/no-condition")
	public List<TblRoleDto> retrieveAllRole(HttpServletRequest httpServletRequest) {
		HttpSecurityService httpSecurityService = new HttpSecurityService(null, TblRoleDto.getDtoticketing(),
				"user-admin-setting");
		WsResponse response = resultWsWitSecurityAccess(END_POINT_URL + "/admin-ws/role-transaction/select-all/no-condition", null,
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
	
	@PostMapping("/save/detail/role-admin-setting")
	public TransactionCUDDto roleAdminSettingSaves(@RequestBody List<TblRoleDtlDto> tblRoleDtlDtos,
			HttpServletRequest httpServletRequest) {
		System.out.println(new Gson().toJson(tblRoleDtlDtos));
		HttpSecurityService httpSecurityService = new HttpSecurityService(TblRoleDtlDto.getDtoticketing(),
				TransactionCUDDto.getDtoticketing(), "role-admin-setting");
		WsResponse response = resultWsWitSecurityAccess(END_POINT_URL + "/admin-ws/role-detail-transaction/save-all", tblRoleDtlDtos,
				HttpMethod.POST, null, getToken(httpServletRequest), httpSecurityService);
		TransactionCUDDto cudDto = new TransactionCUDDto();
		try {
			cudDto = mapperJsonToSingleDto(response.getWsContent(), TransactionCUDDto.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cudDto;
	}
}
