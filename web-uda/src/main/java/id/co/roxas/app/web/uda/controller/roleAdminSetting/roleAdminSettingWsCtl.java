package id.co.roxas.app.web.uda.controller.roleAdminSetting;

import java.text.ParseException;
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
import id.co.roxas.app.web.uda.lib.ParamQueryCustomLib;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblRoleDtlDto;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblRoleDto;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblUserDto;
import id.co.roxas.data.transfer.object.UserDataActivation.custom.TransactionCUDDto;
import id.co.roxas.data.transfer.object.UserDataActivation.model.PageClassResponse;
import id.co.roxas.data.transfer.object.UserDataActivation.model.PageRevolver;
import id.co.roxas.data.transfer.object.UserDataActivation.model.RoleActivationForm;
import id.co.roxas.data.transfer.object.UserDataActivation.model.RoleDtlActivationForm;
import id.co.roxas.data.transfer.object.UserDataActivation.model.UserActivationForm;
import id.co.roxas.data.transfer.object.UserDataActivation.response.PageResponse;
import id.co.roxas.data.transfer.object.UserDataActivation.response.WsResponse;
import id.co.roxas.data.transfer.object.shared.converter.DateConverter;

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
	

	@PostMapping("/update/role-admin-setting")
	public TransactionCUDDto userAdminSettingUpdate(@RequestBody TblRoleDto tblRoleDto, HttpServletRequest httpServletRequest) {
		HttpSecurityService httpSecurityService = new HttpSecurityService(TblRoleDto.getDtoticketing(),
				TransactionCUDDto.getDtoticketing(), "user-admin-setting");
		WsResponse response = resultWsWitSecurityAccess(END_POINT_URL + "/admin-ws/role-transaction/update", tblRoleDto,
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
	
	@PostMapping("/action-activation/role-admin-setting")
	public TransactionCUDDto userActionActiveDisactive(@RequestBody  RoleActivationForm raf, HttpServletRequest httpServletRequest) {
		HttpSecurityService httpSecurityService = new HttpSecurityService(TblRoleDto.getDtoticketing(),
				TransactionCUDDto.getDtoticketing(), "role-admin-setting");
		TblRoleDto dto = new TblRoleDto();
		dto.setRoleId(raf.getRoleId());
		dto.setIsActive(raf.getActionResult());
		WsResponse response = resultWsWitSecurityAccess(END_POINT_URL + "/admin-ws/role-transaction/action-activator", dto,
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
	
	@PostMapping("/action-activation/role-dtl-admin-setting")
	public TransactionCUDDto userActionActiveDisactive(@RequestBody  RoleDtlActivationForm raf, HttpServletRequest httpServletRequest) {
		HttpSecurityService httpSecurityService = new HttpSecurityService(TblRoleDtlDto.getDtoticketing(),
				TransactionCUDDto.getDtoticketing(), "role-admin-setting");
		TblRoleDtlDto dto = new TblRoleDtlDto();
		dto.setRoleDtlId(raf.getRoleDtlId());
		dto.setIsActive(raf.getActionResult());
		WsResponse response = resultWsWitSecurityAccess(END_POINT_URL + "/admin-ws/role-detail-transaction/action-activator", dto,
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
	
	@PostMapping("/select-all/role-admin-setting")
	public PageClassResponse<TblRoleDto> selectAllRole(@RequestBody PageRevolver pageRevolver, HttpServletRequest httpServletRequest) throws ParseException{
		PageClassResponse<TblRoleDto> pageRequestCustom = new PageClassResponse<>();
		List<TblRoleDto> tblRoleDtos = new ArrayList<>();
		HttpSecurityService httpSecurityService = new HttpSecurityService(null, TblRoleDto.getDtoticketing(), "I001");
		paramPaging(pageRevolver.getPage(), 
						pageRevolver.getSize(), pageRevolver.getSearch(), 
						"createdDate,desc", pageRevolver.getSort());
		PageResponse pageResponse = pageResultsWithSecurityAccess
				(END_POINT_URL + "/admin-ws/role-transaction/query-all", 
						null, HttpMethod.GET, null, getToken(httpServletRequest), 
						httpSecurityService, 
						retrieveAllPagingNeeded
						       ( new ParamQueryCustomLib("roleId", pageRevolver.getRoleId())
						        ,new ParamQueryCustomLib("roleDtlId", pageRevolver.getRoleDtlId())
								,new ParamQueryCustomLib("isActive", pageRevolver.getIsActive())
								,new ParamQueryCustomLib("startDate",DateConverter.parseDateToString(pageRevolver.getStartDate(), "ddMMyyyy") )
							    ,new ParamQueryCustomLib("endDate", DateConverter.parseDateToString(pageRevolver.getEndDate(), "ddMMyyyy") )));
		try {
			tblRoleDtos = mapperJsonToListDto(pageResponse.getWsContent(), TblRoleDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		pageRequestCustom.setAllDatas(tblRoleDtos);
		pageRequestCustom.setPage(pageResponse.getPageNumber());
		pageRequestCustom.setTotalPage(pageResponse.getTotalPage());
		pageRequestCustom.setFiltering(pageResponse.getFiltering());
		return pageRequestCustom;
	}
	
	@PostMapping("/select-all/role-detail-admin-setting")
	public PageClassResponse<TblRoleDtlDto> selectAllRoleDetail(@RequestBody PageRevolver pageRevolver, HttpServletRequest httpServletRequest) throws ParseException{
		PageClassResponse<TblRoleDtlDto> pageRequestCustom = new PageClassResponse<>();
		List<TblRoleDtlDto> tblRoleDtlDtos = new ArrayList<>();
		HttpSecurityService httpSecurityService = new HttpSecurityService(null, TblRoleDtlDto.getDtoticketing(), "I001");
		paramPaging(pageRevolver.getPage(), 
						pageRevolver.getSize(), pageRevolver.getSearch(), 
						"createdDate,desc", pageRevolver.getSort());
		PageResponse pageResponse = pageResultsWithSecurityAccess
				(END_POINT_URL + "/admin-ws/role-detail-transaction/query-all", 
						null, HttpMethod.GET, null, getToken(httpServletRequest), 
						httpSecurityService, 
						retrieveAllPagingNeeded
						       ( new ParamQueryCustomLib("roleId", pageRevolver.getRoleId())
						        ,new ParamQueryCustomLib("filterName", pageRevolver.getFilterName())
								,new ParamQueryCustomLib("isActive", pageRevolver.getIsActive())
								,new ParamQueryCustomLib("startDate",DateConverter.parseDateToString(pageRevolver.getStartDate(), "ddMMyyyy") )
							    ,new ParamQueryCustomLib("endDate", DateConverter.parseDateToString(pageRevolver.getEndDate(), "ddMMyyyy") )));
		try {
			tblRoleDtlDtos = mapperJsonToListDto(pageResponse.getWsContent(), TblRoleDtlDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		pageRequestCustom.setAllDatas(tblRoleDtlDtos);
		pageRequestCustom.setPage(pageResponse.getPageNumber());
		pageRequestCustom.setTotalPage(pageResponse.getTotalPage());
		pageRequestCustom.setFiltering(pageResponse.getFiltering());
		return pageRequestCustom;
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
