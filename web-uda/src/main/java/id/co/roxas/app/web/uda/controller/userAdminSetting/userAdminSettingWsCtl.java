package id.co.roxas.app.web.uda.controller.userAdminSetting;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import id.co.roxas.app.web.uda.config.HttpSecurityService;
import id.co.roxas.app.web.uda.controller.BaseRestWebController;
import id.co.roxas.app.web.uda.lib.ParamQueryCustomLib;
import id.co.roxas.data.transfer.object.UserDataActivation.converter.DateConverter;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblRoleDto;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblUserDto;
import id.co.roxas.data.transfer.object.UserDataActivation.custom.TransactionCUDDto;
import id.co.roxas.data.transfer.object.UserDataActivation.model.PageClassResponse;
import id.co.roxas.data.transfer.object.UserDataActivation.model.PageRevolver;
import id.co.roxas.data.transfer.object.UserDataActivation.model.UserActivationForm;
import id.co.roxas.data.transfer.object.UserDataActivation.response.PageResponse;
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
	public TransactionCUDDto userAdminSettingSave(@RequestBody TblUserDto tblUserDto, HttpServletRequest httpServletRequest) {
		HttpSecurityService httpSecurityService = new HttpSecurityService(TblUserDto.getDtoticketing(),
				TransactionCUDDto.getDtoticketing(), "user-admin-setting");
		WsResponse response = resultWsWitSecurityAccess(END_POINT_URL + "/admin-ws/user-transaction/save", tblUserDto,
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
	
	@PostMapping("/update/user-admin-setting")
	public TransactionCUDDto userAdminSettingUpdate(@RequestBody TblUserDto tblUserDto, HttpServletRequest httpServletRequest) {
		HttpSecurityService httpSecurityService = new HttpSecurityService(TblUserDto.getDtoticketing(),
				TransactionCUDDto.getDtoticketing(), "user-admin-setting");
		WsResponse response = resultWsWitSecurityAccess(END_POINT_URL + "/admin-ws/user-transaction/update", tblUserDto,
				HttpMethod.PUT, null, getToken(httpServletRequest), httpSecurityService);
		TransactionCUDDto cudDto = new TransactionCUDDto();
		try {
			cudDto = mapperJsonToSingleDto(response.getWsContent(), TransactionCUDDto.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cudDto;
	}
	
	@PostMapping("/action-activation/user-admin-setting")
	public TransactionCUDDto userActionActiveDisactive(@RequestBody  UserActivationForm uaf, HttpServletRequest httpServletRequest) {
		HttpSecurityService httpSecurityService = new HttpSecurityService(TblUserDto.getDtoticketing(),
				TransactionCUDDto.getDtoticketing(), "user-admin-setting");
		TblUserDto dto = new TblUserDto();
		dto.setUserId(uaf.getUserId());
		dto.setIsActive(uaf.getActionResult());
		WsResponse response = resultWsWitSecurityAccess(END_POINT_URL + "/admin-ws/user-transaction/action-activator", dto,
				HttpMethod.PUT, null, getToken(httpServletRequest), httpSecurityService);
		TransactionCUDDto cudDto = new TransactionCUDDto();
		try {
			cudDto = mapperJsonToSingleDto(response.getWsContent(), TransactionCUDDto.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cudDto;
	}
	
	@PostMapping("/select-all/user-admin-setting")
	public PageClassResponse<TblUserDto> selectAllUser(@RequestBody PageRevolver pageRevolver, HttpServletRequest httpServletRequest) throws ParseException{
		PageClassResponse<TblUserDto> pageRequestCustom = new PageClassResponse<>();
		List<TblUserDto> tblUserDtos = new ArrayList<>();
		HttpSecurityService httpSecurityService = new HttpSecurityService(null, TblUserDto.getDtoticketing(), "I001");
		paramPaging(pageRevolver.getPage(), 
						pageRevolver.getSize(), pageRevolver.getSearch(), 
						"createdDate,desc", pageRevolver.getSort());
		PageResponse pageResponse = pageResultsWithSecurityAccess
				(END_POINT_URL + "/admin-ws/user-transaction/select-all", 
						null, HttpMethod.GET, null, getToken(httpServletRequest), 
						httpSecurityService, 
						retrieveAllPagingNeeded
						       ( new ParamQueryCustomLib("roleId", pageRevolver.getRoleId())
								,new ParamQueryCustomLib("isActive", pageRevolver.getIsActive())
								,new ParamQueryCustomLib("startDate",DateConverter.parseDateToString(pageRevolver.getStartDate(), "ddMMyyyy") )
							    ,new ParamQueryCustomLib("endDate", DateConverter.parseDateToString(pageRevolver.getEndDate(), "ddMMyyyy") )));
		try {
			tblUserDtos = mapperJsonToListDto(pageResponse.getWsContent(), TblUserDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		pageRequestCustom.setAllDatas(tblUserDtos);
		pageRequestCustom.setPage(pageResponse.getPageNumber());
		pageRequestCustom.setTotalPage(pageResponse.getTotalPage());
		pageRequestCustom.setFiltering(pageResponse.getFiltering());
		return pageRequestCustom;
	}
	
	protected <T> List<T> reloadUniqueValue(List<T> value) {
		Set<T> uniqueVal = new HashSet<T>(value);
		List<T> finalList = new ArrayList<>(uniqueVal);
		return finalList;
	}
	
	
}
