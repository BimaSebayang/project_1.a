package id.co.roxas.user.data.activation.core.controller.admin;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.co.roxas.data.transfer.object.UserDataActivation.core.TblRoleDtlDto;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblRoleDto;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblUserDto;
import id.co.roxas.data.transfer.object.UserDataActivation.custom.PageRequestCustom;
import id.co.roxas.data.transfer.object.UserDataActivation.custom.TransactionCUDDto;
import id.co.roxas.data.transfer.object.UserDataActivation.response.PageResponse;
import id.co.roxas.data.transfer.object.UserDataActivation.response.WsResponse;
import id.co.roxas.data.transfer.object.shared.config.AuthorizationClassConf;
import id.co.roxas.data.transfer.object.shared.converter.PathVariableConverter;
import id.co.roxas.user.data.activation.core.controller.BaseController;
import id.co.roxas.user.data.activation.core.service.admin.RoleAndDetailMaintenanceSvc;

@RestController
@Validated
@RequestMapping("/admin-ws")
public class RoleAndDetailMaintenanceCtl extends BaseController {

	@Autowired
	private RoleAndDetailMaintenanceSvc roleAndDetailMaintenanceSvc;

	@GetMapping(value = "/role-transaction/query-specific/{dtlName}")
	public WsResponse retrieveSpecificRoleWithDtlCondition(@PathVariable("dtlName") String dtlName,
			@RequestHeader(name = "uuid-connector-response", required = true) String uuid,
			@RequestHeader(name = "module", required = true) String module, Authentication authentication) {
		AuthorizationClassConf authorizationClassConf = new AuthorizationClassConf(registUuid(uuid),
				registUuid(TblRoleDto.getDtoticketing()), getAllAuthUser(authentication), module,
				authentication.getName());
		if (responsePusherInvalidatorAccess(authentication, authorizationClassConf, true)) {
			return getResponseInvalid();
		} else {
            List<TblRoleDto> tblRoleDtos = roleAndDetailMaintenanceSvc.
            		releasedAllRoleThatNotInUsed(PathVariableConverter.getVariablePathWithCod(dtlName));
			return new WsResponse(tblRoleDtos, SUCCESS_RETRIEVE, authorizationClassConf);

		}
	}

	@GetMapping(value = "/role-transaction/query-all")
	public PageResponse retrieveAllRole(@RequestHeader(name = "uuid-connector-response", required = true) String uuid,
			@RequestHeader(name = "module", required = true) String module,
			@RequestParam(name = "isActive") String isActive, @RequestParam(name = "startDate") String startDate,
			@RequestParam(name = "endDate") String endDate, @RequestParam(name = "search") String search,
			@RequestParam(name = "roleDtlId") String roleDtlId, Pageable pageable, Authentication authentication) {
		AuthorizationClassConf authorizationClassConf = new AuthorizationClassConf(registUuid(uuid),
				registUuid(TblRoleDto.getDtoticketing()), getAllAuthUser(authentication), module,
				authentication.getName());
		if (responsePusherInvalidatorAccess(authentication, authorizationClassConf, true)) {
			return getPageInvalid();
		} else {
			PageRequestCustom<TblRoleDto> page = roleAndDetailMaintenanceSvc.getAllRole(isActive, roleDtlId, startDate,
					endDate, search, authentication.getName(), pageable);
			return new PageResponse(page.getListResponse(), SUCCESS_RETRIEVE, page.getPageNumbers(), page.getPageSize(),
					page.getTotalPages(), page.getTotalElements(), page.getSortBy(), authorizationClassConf,
					page.getFiltering());
		}
	}

	@PutMapping("/role-transaction/update")
	public WsResponse adminUpdateUser(@Valid @RequestBody TblRoleDto tblRoleDto,
			@RequestHeader(name = "uuid-connector-body", required = true) String uuid1,
			@RequestHeader(name = "uuid-connector-response", required = true) String uuid2,
			@RequestHeader(name = "module", required = true) String module, Authentication authentication) {
		AuthorizationClassConf authorizationClassConf = new AuthorizationClassConf(registUuid(uuid1, uuid2),
				registUuid(TransactionCUDDto.getDtoticketing(), TblRoleDto.getDtoticketing()),
				getAllAuthUser(authentication), module, authentication.getName());
		if (responsePusherInvalidatorAccess(authentication, authorizationClassConf, true)) {
			return getResponseInvalid();
		} else {
			TransactionCUDDto customDto = new TransactionCUDDto();
			int result = roleAndDetailMaintenanceSvc.crudRoleUpdate(tblRoleDto, authentication.getName());
			if (result == REPOSITORY_TRANSACTION_SUCCESS) {
				customDto.setUpdateResult(result);
				return new WsResponse(customDto, SUCCESS_UPDATE, authorizationClassConf);
			} else {
				return new WsResponse(null, INSUCCESS_UPDATE, authorizationClassConf);
			}
		}
	}

	@PutMapping("/role-transaction/action-activator")
	public WsResponse roleActivator(@Valid @RequestBody TblRoleDto tblRoleDto,
			@RequestHeader(name = "uuid-connector-body", required = true) String uuid1,
			@RequestHeader(name = "uuid-connector-response", required = true) String uuid2,
			@RequestHeader(name = "module", required = true) String module, Authentication authentication) {
		AuthorizationClassConf authorizationClassConf = new AuthorizationClassConf(registUuid(uuid1, uuid2),
				registUuid(TransactionCUDDto.getDtoticketing(), TblRoleDto.getDtoticketing()),
				getAllAuthUser(authentication), module, authentication.getName());
		if (responsePusherInvalidatorAccess(authentication, authorizationClassConf, true)) {
			return getResponseInvalid();
		} else {
			TransactionCUDDto customDto = new TransactionCUDDto();
			int result = roleAndDetailMaintenanceSvc.crudRoleActivateSwitcher(tblRoleDto, authentication.getName());
			if (result == REPOSITORY_TRANSACTION_SUCCESS) {
				customDto.setUpdateResult(result);
				return new WsResponse(customDto, SUCCESS_UPDATE, authorizationClassConf);
			} else {
				return new WsResponse(null, INSUCCESS_UPDATE, authorizationClassConf);
			}
		}
	}

	@PutMapping("/role-detail-transaction/action-activator")
	public WsResponse roleDetailActivator(@Valid @RequestBody TblRoleDtlDto tblRoleDtlDto,
			@RequestHeader(name = "uuid-connector-body", required = true) String uuid1,
			@RequestHeader(name = "uuid-connector-response", required = true) String uuid2,
			@RequestHeader(name = "module", required = true) String module, Authentication authentication) {
		AuthorizationClassConf authorizationClassConf = new AuthorizationClassConf(registUuid(uuid1, uuid2),
				registUuid(TransactionCUDDto.getDtoticketing(), TblRoleDtlDto.getDtoticketing()),
				getAllAuthUser(authentication), module, authentication.getName());
		if (responsePusherInvalidatorAccess(authentication, authorizationClassConf, true)) {
			return getResponseInvalid();
		} else {
			TransactionCUDDto customDto = new TransactionCUDDto();
			int result = roleAndDetailMaintenanceSvc.crudRoleDetailActivateSwitcher(tblRoleDtlDto,
					authentication.getName());
			if (result == REPOSITORY_TRANSACTION_SUCCESS) {
				customDto.setUpdateResult(result);
				return new WsResponse(customDto, SUCCESS_UPDATE, authorizationClassConf);
			} else {
				return new WsResponse(null, INSUCCESS_UPDATE, authorizationClassConf);
			}
		}
	}

	@GetMapping(value = "/role-detail-transaction/query-all")
	public PageResponse retrieveAllRoleDtl(
			@RequestHeader(name = "uuid-connector-response", required = true) String uuid,
			@RequestHeader(name = "module", required = true) String module,
			@RequestParam(name = "isActive") String isActive, @RequestParam(name = "startDate") String startDate,
			@RequestParam(name = "endDate") String endDate, @RequestParam(name = "search") String search,
			@RequestParam(name = "roleId") String role, @RequestParam(name = "filterName") String filterName,
			Pageable pageable, Authentication authentication) {
		AuthorizationClassConf authorizationClassConf = new AuthorizationClassConf(registUuid(uuid),
				registUuid(TblRoleDtlDto.getDtoticketing()), getAllAuthUser(authentication), module,
				authentication.getName());
		if (responsePusherInvalidatorAccess(authentication, authorizationClassConf, true)) {
			return getPageInvalid();
		} else {
			PageRequestCustom<TblRoleDtlDto> page = roleAndDetailMaintenanceSvc.getAllRoleDtl(isActive, role,
					filterName, startDate, endDate, search, authentication.getName(), pageable);
			return new PageResponse(page.getListResponse(), SUCCESS_RETRIEVE, page.getPageNumbers(), page.getPageSize(),
					page.getTotalPages(), page.getTotalElements(), page.getSortBy(), authorizationClassConf,
					page.getFiltering());
		}
	}

	@GetMapping("/role-transaction/select-all")
	public WsResponse retrieveAllRole(@RequestHeader(name = "uuid-connector-response", required = true) String uuid,
			@RequestHeader(name = "module", required = true) String module, Authentication authentication) {
		AuthorizationClassConf authorizationClassConf = new AuthorizationClassConf(registUuid(uuid),
				registUuid(TblRoleDto.getDtoticketing()), getAllAuthUser(authentication), module,
				authentication.getName());
		if (responsePusherInvalidatorAccess(authentication, authorizationClassConf, true)) {
			return getResponseInvalid();
		} else {
			List<TblRoleDto> tblRoleDtos = roleAndDetailMaintenanceSvc.selectAllRoleIsActive();
			return new WsResponse(tblRoleDtos, SUCCESS_RETRIEVE, authorizationClassConf);
		}
	}

	@GetMapping("/role-transaction/select-all/no-condition")
	public WsResponse retrieveAllRoleWithNoCondition(
			@RequestHeader(name = "uuid-connector-response", required = true) String uuid,
			@RequestHeader(name = "module", required = true) String module, Authentication authentication) {
		AuthorizationClassConf authorizationClassConf = new AuthorizationClassConf(registUuid(uuid),
				registUuid(TblRoleDto.getDtoticketing()), getAllAuthUser(authentication), module,
				authentication.getName());
		if (responsePusherInvalidatorAccess(authentication, authorizationClassConf, true)) {
			return getResponseInvalid();
		} else {
			List<TblRoleDto> tblRoleDtos = roleAndDetailMaintenanceSvc.selectAllRole();
			return new WsResponse(tblRoleDtos, SUCCESS_RETRIEVE, authorizationClassConf);
		}
	}

	@PostMapping("/role-transaction/save")
	public WsResponse saveRole(@RequestBody TblRoleDto tblRoleDto,
			@RequestHeader(name = "uuid-connector-body", required = true) String uuid1,
			@RequestHeader(name = "uuid-connector-response", required = true) String uuid2,
			@RequestHeader(name = "module", required = true) String module, Authentication authentication) {
		AuthorizationClassConf authorizationClassConf = new AuthorizationClassConf(registUuid(uuid1, uuid2),
				registUuid(TransactionCUDDto.getDtoticketing(), TblRoleDto.getDtoticketing()),
				getAllAuthUser(authentication), module, authentication.getName());
		if (responsePusherInvalidatorAccess(authentication, authorizationClassConf, true)) {
			return getResponseInvalid();
		} else {
			TransactionCUDDto customDto = new TransactionCUDDto();
			int result = roleAndDetailMaintenanceSvc.CrudRoleSave(tblRoleDto, authentication.getName());
			if (result == REPOSITORY_TRANSACTION_SUCCESS) {
				customDto.setSaveResult(result);
				return new WsResponse(customDto, "Save Success", authorizationClassConf);
			} else {
				return new WsResponse(null, "Save Insuccess", authorizationClassConf);
			}
		}
	}

	@PostMapping("/role-detail-transaction/save")
	public WsResponse saveRoleDetail(@RequestBody TblRoleDtlDto tblRoleDtlDto,
			@RequestHeader(name = "uuid-connector-body", required = true) String uuid1,
			@RequestHeader(name = "uuid-connector-response", required = true) String uuid2,
			@RequestHeader(name = "module", required = true) String module, Authentication authentication) {
		AuthorizationClassConf authorizationClassConf = new AuthorizationClassConf(registUuid(uuid1, uuid2),
				registUuid(TransactionCUDDto.getDtoticketing(), TblRoleDtlDto.getDtoticketing()),
				getAllAuthUser(authentication), module, authentication.getName());
		if (responsePusherInvalidatorAccess(authentication, authorizationClassConf, true)) {
			return getResponseInvalid();
		} else {
			TransactionCUDDto customDto = new TransactionCUDDto();
			int result = roleAndDetailMaintenanceSvc.CrudDetailRoleSave(tblRoleDtlDto, authentication.getName());
			if (result == REPOSITORY_TRANSACTION_SUCCESS) {
				customDto.setSaveResult(result);
				return new WsResponse(customDto, "Save Success", authorizationClassConf);
			} else {
				return new WsResponse(null, "Save Insuccess", authorizationClassConf);
			}
		}
	}

	@PostMapping("/role-detail-transaction/save-all")
	public WsResponse saveRoleDetailAll(@RequestBody List<TblRoleDtlDto> tblRoleDtlDtos,
			@RequestHeader(name = "uuid-connector-body", required = true) String uuid1,
			@RequestHeader(name = "uuid-connector-response", required = true) String uuid2,
			@RequestHeader(name = "module", required = true) String module, Authentication authentication) {
		AuthorizationClassConf authorizationClassConf = new AuthorizationClassConf(registUuid(uuid1, uuid2),
				registUuid(TransactionCUDDto.getDtoticketing(), TblRoleDtlDto.getDtoticketing()),
				getAllAuthUser(authentication), module, authentication.getName());
		if (responsePusherInvalidatorAccess(authentication, authorizationClassConf, true)) {
			return getResponseInvalid();
		} else {
			TransactionCUDDto customDto = new TransactionCUDDto();
			int result = roleAndDetailMaintenanceSvc.CrudDetailRolesSave(tblRoleDtlDtos, authentication.getName());
			if (result == REPOSITORY_TRANSACTION_SUCCESS) {
				customDto.setSaveResult(result);
				return new WsResponse(customDto, "Save Success", authorizationClassConf);
			} else {
				return new WsResponse(null, "Save Insuccess", authorizationClassConf);
			}
		}
	}

	@PutMapping("/role-detail-transaction/update")
	public WsResponse updateRoleDetail(@RequestBody TblRoleDtlDto tblRoleDtlDto,
			@RequestHeader(name = "uuid-connector-body", required = true) String uuid1,
			@RequestHeader(name = "uuid-connector-response", required = true) String uuid2,
			@RequestHeader(name = "module", required = true) String module, Authentication authentication) {
		AuthorizationClassConf authorizationClassConf = new AuthorizationClassConf(registUuid(uuid1, uuid2),
				registUuid(TransactionCUDDto.getDtoticketing(), TblRoleDtlDto.getDtoticketing()),
				getAllAuthUser(authentication), module, authentication.getName());
		if (responsePusherInvalidatorAccess(authentication, authorizationClassConf, true)) {
			return getResponseInvalid();
		} else {
			TransactionCUDDto customDto = new TransactionCUDDto();
			int result = roleAndDetailMaintenanceSvc.CrudDetailRoleUpdateInformation(tblRoleDtlDto,
					authentication.getName());
			if (result == REPOSITORY_TRANSACTION_SUCCESS) {
				customDto.setUpdateResult(result);
				return new WsResponse(customDto, "Update Success", authorizationClassConf);
			} else {
				return new WsResponse(null, "Update Insuccess", authorizationClassConf);
			}
		}
	}
}
