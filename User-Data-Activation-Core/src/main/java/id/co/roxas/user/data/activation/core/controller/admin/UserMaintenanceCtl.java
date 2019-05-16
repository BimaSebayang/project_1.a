package id.co.roxas.user.data.activation.core.controller.admin;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.co.roxas.data.transfer.object.UserDataActivation.config.AuthorizationClassConf;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblRoleDto;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblUserDto;
import id.co.roxas.data.transfer.object.UserDataActivation.custom.TransactionCUDDto;
import id.co.roxas.data.transfer.object.UserDataActivation.response.PageResponse;
import id.co.roxas.data.transfer.object.UserDataActivation.response.WsResponse;
import id.co.roxas.user.data.activation.core.controller.BaseController;
import id.co.roxas.user.data.activation.core.service.admin.UserMaintenanceSvc;

@RestController
@Validated
@RequestMapping("/admin-ws")
public class UserMaintenanceCtl extends BaseController {

	@Autowired
	private UserMaintenanceSvc userMaintenanceSvc;

	@GetMapping(value = "/role-transaction/select-all", params = {"isActive","roleId"})
	public PageResponse retrieveAllRole(@RequestHeader(name = "uuid-connector-response", required = true) String uuid,
			@RequestHeader(name = "module", required = true) String module, 
			@RequestParam("isActive") String isActive,
			@RequestParam("roleId") String roleId,
			Pageable pageable,
			Authentication authentication) {
		AuthorizationClassConf authorizationClassConf = new AuthorizationClassConf(registUuid(uuid),
				registUuid(TblRoleDto.getDtoticketing()),
				getAllAuthUser(authentication), module, authentication.getName());
		if (responsePusherInvalidatorAccess(authentication, authorizationClassConf, true)) {
			return getPageInvalid();
		} else {
			Page<TblUserDto> page = userMaintenanceSvc.getAllUser(isActive, roleId, pageable);
			return new PageResponse
					(page, SUCCESS_RETRIEVE, page.getPageable().getPageNumber(), 
							page.getPageable().getPageSize(), page.getTotalPages(), authorizationClassConf);
		}
	}
	
	@PostMapping("/user-transaction/save")
	public WsResponse adminSaveUser(
			@Valid @RequestBody TblUserDto tblUserDto,
			@RequestHeader(name = "uuid-connector-body", required = true) String uuid1,
			@RequestHeader(name = "uuid-connector-response", required = true) String uuid2,
			@RequestHeader(name = "module", required = true) String module, Authentication authentication) {
		AuthorizationClassConf authorizationClassConf = new AuthorizationClassConf(registUuid(uuid1, uuid2),
				registUuid(TransactionCUDDto.getDtoticketing(), TblUserDto.getDtoticketing()),
				getAllAuthUser(authentication), module, authentication.getName());
		if (responsePusherInvalidatorAccess(authentication, authorizationClassConf, true)) {
			return getResponseInvalid();
		} else {
			TransactionCUDDto customDto = new TransactionCUDDto();
			int result = userMaintenanceSvc.CrudUserSave(tblUserDto, authentication.getName());
				if (result == REPOSITORY_TRANSACTION_SUCCESS) {
					customDto.setSaveResult(result);
					return new WsResponse(customDto, "Save Success", authorizationClassConf);
				} else {
					return new WsResponse(null, "Save Insuccess", authorizationClassConf);
				}
		}
	}
	
	@PutMapping("/user-transaction/update")
	public WsResponse adminUpdateUser(
			@Valid @RequestBody TblUserDto tblUserDto,
			@RequestHeader(name = "uuid-connector-body", required = true) String uuid1,
			@RequestHeader(name = "uuid-connector-response", required = true) String uuid2,
			@RequestHeader(name = "module", required = true) String module, Authentication authentication) {
		AuthorizationClassConf authorizationClassConf = new AuthorizationClassConf(registUuid(uuid1, uuid2),
				registUuid(TransactionCUDDto.getDtoticketing(), TblUserDto.getDtoticketing()),
				getAllAuthUser(authentication), module, authentication.getName());
		if (responsePusherInvalidatorAccess(authentication, authorizationClassConf, true)) {
			return getResponseInvalid();
		} else {
			TransactionCUDDto customDto = new TransactionCUDDto();
			    int result = userMaintenanceSvc.crudUpdateUser(tblUserDto, authentication.getName());
				if (result == REPOSITORY_TRANSACTION_SUCCESS) {
					customDto.setSaveResult(result);
					return new WsResponse(customDto, "Update Success", authorizationClassConf);
				} else {
					return new WsResponse(null, "Update Insuccess", authorizationClassConf);
				}
		}
	}

}
