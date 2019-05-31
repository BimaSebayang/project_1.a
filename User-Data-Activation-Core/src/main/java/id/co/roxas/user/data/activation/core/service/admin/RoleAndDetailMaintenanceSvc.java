package id.co.roxas.user.data.activation.core.service.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import id.co.roxas.data.transfer.object.UserDataActivation.core.TblRoleDtlDto;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblRoleDto;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblTicketDto;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblUserDto;
import id.co.roxas.data.transfer.object.UserDataActivation.custom.PageRequestCustom;
import id.co.roxas.user.data.activation.core.dao.TblRoleDao;
import id.co.roxas.user.data.activation.core.dao.TblRoleDtlDao;
import id.co.roxas.user.data.activation.core.repository.TblRole;
import id.co.roxas.user.data.activation.core.repository.TblRoleDtl;
import id.co.roxas.user.data.activation.core.repository.TblUser;
import id.co.roxas.user.data.activation.core.service.BaseService;

@Service
public class RoleAndDetailMaintenanceSvc extends BaseService {

	@Autowired
	private TblRoleDao tblRoleDao;
	@Autowired
	private TblRoleDtlDao tblRoleDtlDao;

	public PageRequestCustom<TblRoleDto> getAllRole(String isActive, String roleDtlId, String startDate, String endDate,
			String search, String names,Pageable pageable) {

		if (Strings.isBlank(isActive)) {
			System.err.println("is active null");
			isActive = "";
		}

		if (Strings.isBlank(roleDtlId)) {
			System.err.println("roleDtlId id null");
			roleDtlId = "";
		}

		if (Strings.isBlank(search)) {
			search = "";
		} else {
			if (search.equalsIgnoreCase("Active")) {
				search = "1";
			} else if (search.equalsIgnoreCase("Inactive")) {
				search = "0";
			}
		}

		getStartDateEndDate(startDate, endDate, "ddMMyyyy");
		Page<TblRole> page = tblRoleDao.findAllRoleWithCondition(staplingWords(search, "%"), startDat, endDat,
				roleDtlId, isActive,names, pageable);

		Page<TblRole> filter = tblRoleDao.findAllRoleWithCondition(staplingWords(search, "%"), startDat, endDat,
				roleDtlId, isActive,names, PageRequest.of(0, Integer.MAX_VALUE));

		List<Map<String, String>> flagActivator = new ArrayList<>();
		List<Map<String, String>> tblRoleDtlDtos = new ArrayList<>();

		for (TblRole filt : filter.getContent()) {
			Map<String, String> activator = new HashMap<>();
			if (filt.getIsActive() == 1) {
				activator.put("value", "Activate");
				activator.put("key", "1");
			} else if (filt.getIsActive() == 0) {
				activator.put("value", "Disactivate");
				activator.put("key", "0");
			}
			flagActivator.add(activator);
			for (TblRoleDtl tbl : filt.getTblRoleDtls()) {
				Map<String, String> role = new HashMap<>();
				role.put("roleName", tbl.getRoleDtlName());
				tblRoleDtlDtos.add(role);
			}

		}

		Map<String, Object> filtering = new HashMap<>();
		filtering.put("flagName", reloadUniqueValue(flagActivator));
		filtering.put("roleDtlName", reloadUniqueValue(tblRoleDtlDtos));

		List<TblRoleDto> tblRoleDtos = new ArrayList<>();
		// System.out.println(page.getSize());
		for (TblRole tblRole : page.getContent()) {
			TblRoleDto tblRoleDto = new TblRoleDto();
			tblRoleDto = mapperFacade.map(tblRole, TblRoleDto.class);
			List<TblRoleDtlDto> detail = new ArrayList<>();
			List<TblUserDto> userDtos = new ArrayList<>();
			if (tblRole.getTblRoleDtls() != null) {
				System.out.println("banyaknya detail " + tblRole.getTblRoleDtls().size());
				for (TblRoleDtl dtldto : tblRole.getTblRoleDtls()) {
					TblRoleDtlDto roleDtlDto = new TblRoleDtlDto();
					roleDtlDto.setRoleDtlId(dtldto.getRoleDtlId());
					roleDtlDto.setRoleDtlName(dtldto.getRoleDtlName());
					roleDtlDto.setRoleDtlFunc(dtldto.getRoleDtlFunc());
					detail.add(roleDtlDto);
				}
			}
			
			if (tblRole.getTblUsers() != null) {
				System.out.println("banyaknya detail user" + tblRole.getTblUsers().size());
				for (TblUser dtldto : tblRole.getTblUsers()) {
					TblUserDto userDto = new TblUserDto();
					userDto.setUserName(dtldto.getUserName());
					userDtos.add(userDto);
				}
			}

			tblRoleDto.setTblUserDtos(userDtos);
			tblRoleDto.setTblRoleDtlDtos(detail);
			if (tblRole.getCreatedBy() != null) {
				tblRoleDto.setCreatedBy(
						new TblUserDto(tblRole.getCreatedBy().getUserId(), tblRole.getCreatedBy().getUserName()));
				tblRoleDto.setUpdatedBy(
						new TblUserDto(tblRole.getCreatedBy().getUserId(), tblRole.getCreatedBy().getUserName()));
			}
			tblRoleDtos.add(tblRoleDto);
		}
		return new PageRequestCustom<>(tblRoleDtos, page.getPageable().getPageSize(), page.getTotalPages(),
				page.getPageable().getPageNumber(), tblRoleDtos.size(), getSorter(pageable), filtering);
	}

	public List<TblRoleDto> selectAllRoleIsActive() {
		List<TblRole> tblRoles = tblRoleDao.retrieveAllRoleIsActive();
		List<TblRoleDto> tblRoleDtos = new ArrayList<>();
		for (TblRole tblRole : tblRoles) {
			if (tblRole.getTblRoleDtls().size() > 0) {
				TblRoleDto tblRoleDto = new TblRoleDto();
				tblRoleDto.setRoleName(tblRole.getRoleName());
				tblRoleDto.setRoleId(tblRole.getRoleId());
				tblRoleDtos.add(tblRoleDto);
			}
		}
		return tblRoleDtos;
	}

	public List<TblRoleDto> selectAllRole() {
		List<TblRole> tblRoles = tblRoleDao.findAll();
		List<TblRoleDto> tblRoleDtos = new ArrayList<>();
		for (TblRole tblRole : tblRoles) {
			TblRoleDto tblRoleDto = new TblRoleDto();
			tblRoleDto.setRoleName(tblRole.getRoleName());
			tblRoleDto.setRoleId(tblRole.getRoleId());
			tblRoleDtos.add(tblRoleDto);
		}
		return tblRoleDtos;
	}

	public int CrudRoleSave(TblRoleDto tblRoleDto, String user) {
		TblRole tblRole = new TblRole();
		tblRole = mapperFacade.map(tblRoleDto, TblRole.class);
		tblRole.setCreatedDate(dateNow);
		tblRole.setDateActive(dateNow);
		tblRole.setCreatedBy(new TblUser(user));
		tblRole.setIsActive(1);
		tblRoleDao.save(tblRole);
		for (TblRoleDtlDto roleDtl : tblRoleDto.getTblRoleDtlDtos()) {
			if (Strings.isBlank(roleDtl.getRoleDtlName())) {
				CrudDetailRoleSave(roleDtl, tblRole, user);
			}
		}
		return REPOSITORY_TRANSACTION_SUCCESS;
	}

	public int CrudRolesSave(List<TblRoleDto> tblRoleDtos, String user) {
		List<TblRole> tblRoles = new ArrayList<>();
		for (TblRoleDto tblRoleDto : tblRoleDtos) {
			TblRole tblRole = new TblRole();
			tblRole = mapperFacade.map(tblRoleDto, TblRole.class);
			tblRole.setIsActive(1);
			tblRole.setCreatedDate(dateNow);
			tblRole.setDateActive(dateNow);
			tblRole.setCreatedBy(new TblUser(user));
			tblRoles.add(tblRole);
		}
		tblRoleDao.saveAll(tblRoles);
		return REPOSITORY_TRANSACTION_SUCCESS;
	}

	public int crudRoleActivateSwitcher(TblRoleDto tblRoleDto, String user) {
		tblRoleDao.CrudActivationSwitcherRole(tblRoleDto.getIsActive(), dateNow, dateNow, dateNow, user,
				tblRoleDto.getRoleId());
		return REPOSITORY_TRANSACTION_SUCCESS;
	}

	public int crudRoleActivatesSwitcher(List<TblRoleDto> tblRoleDtos, String user) {
		List<String> tblRoleDtosActive = new ArrayList<>();
		List<String> tblRoleDtosNonActive = new ArrayList<>();
		for (TblRoleDto tblRoleDto : tblRoleDtos) {
			if (tblRoleDto.getIsActive() == 1) {
				tblRoleDtosActive.add(tblRoleDto.getRoleId());
			} else if (tblRoleDto.getIsActive() == 0) {
				tblRoleDtosActive.add(tblRoleDto.getRoleId());
			}
		}
		tblRoleDao.CrudActivationSwitcherRoles(1, null, dateNow, dateNow, user, tblRoleDtosActive);
		tblRoleDao.CrudActivationSwitcherRoles(0, dateNow, null, dateNow, user, tblRoleDtosNonActive);
		return REPOSITORY_TRANSACTION_SUCCESS;
	}

	public int CrudDetailRoleSave(TblRoleDtlDto tblRoleDtlDto, String user) {
		TblRoleDtl tblRoleDtl = new TblRoleDtl();
		tblRoleDtl = mapperFacade.map(tblRoleDtlDto, TblRoleDtl.class);
		tblRoleDtl.setCreatedDate(dateNow);
		tblRoleDtl.setIsActive(1);
		tblRoleDtl.setCreatedBy(new TblUser(user));
		tblRoleDtl.setDateActive(dateNow);
		if (tblRoleDtlDto.getRoleId() != null) {
			tblRoleDtl.setRoleId(mapperFacade.map(tblRoleDtlDto.getRoleId(), TblRole.class));
		}
		tblRoleDtlDao.save(tblRoleDtl);
		return REPOSITORY_TRANSACTION_SUCCESS;
	}

	public int CrudDetailRoleSave(TblRoleDtlDto tblRoleDtlDto, TblRole tblRole, String user) {
		TblRoleDtl tblRoleDtl = new TblRoleDtl();
		tblRoleDtl = mapperFacade.map(tblRoleDtlDto, TblRoleDtl.class);
		tblRoleDtl.setCreatedDate(dateNow);
		tblRoleDtl.setIsActive(1);
		tblRoleDtl.setCreatedBy(new TblUser(user));
		tblRoleDtl.setDateActive(dateNow);
		tblRoleDtl.setRoleId(tblRole);
		tblRoleDtlDao.save(tblRoleDtl);
		return REPOSITORY_TRANSACTION_SUCCESS;
	}

	public int CrudDetailRoleUpdateInformation(TblRoleDtlDto tblRoleDtlDto, String user) {
		TblRoleDtl tblRoleDtl = tblRoleDtlDao.getOne(tblRoleDtlDto.getRoleDtlId());
		if (tblRoleDtl != null) {
			tblRoleDtl.setUpdatedBy(new TblUser(user));
			tblRoleDtl.setUpdatedDate(dateNow);

			if (tblRoleDtlDto.getRoleId() != null) {
				tblRoleDtl.setRoleId(mapperFacade.map(tblRoleDtlDto.getRoleId(), TblRole.class));
			} else {
				tblRoleDtl.setRoleId(null);
			}
			tblRoleDtlDao.save(tblRoleDtl);
			return REPOSITORY_TRANSACTION_SUCCESS;
		} else {
			return UNRECOGNIZE_ID;
		}
	}

	public int CrudDetailRolesSave(List<TblRoleDtlDto> tblRoleDtlDtos, String user) {
		List<TblRoleDtl> tblRoleDtls = new ArrayList<>();
		for (TblRoleDtlDto tblRoleDtlDto : tblRoleDtlDtos) {
			TblRoleDtl tblRoleDtl = new TblRoleDtl();
			tblRoleDtl = mapperFacade.map(tblRoleDtlDto, TblRoleDtl.class);
			tblRoleDtl.setIsActive(1);
			tblRoleDtl.setCreatedDate(dateNow);
			tblRoleDtl.setCreatedBy(new TblUser(user));
			tblRoleDtl.setDateActive(dateNow);
			if (tblRoleDtlDto.getRoleId() != null) {
				if (!Strings.isBlank(tblRoleDtlDto.getRoleId().getRoleId())) {
					tblRoleDtl.setRoleId(mapperFacade.map(tblRoleDtlDto.getRoleId(), TblRole.class));
				} else {
					tblRoleDtl.setRoleId(null);
				}
			} else {
				tblRoleDtl.setRoleId(null);
			}
			tblRoleDtls.add(tblRoleDtl);
		}
		tblRoleDtlDao.saveAll(tblRoleDtls);
		return REPOSITORY_TRANSACTION_SUCCESS;
	}

	public int CrudDetailRolesUpdateInformation(List<TblRoleDtlDto> tblRoleDtlDtos, String user) {
		List<TblRoleDtl> tblRoleDtls = new ArrayList<>();
		for (TblRoleDtlDto tblRoleDtlDto : tblRoleDtlDtos) {
			TblRoleDtl tblRoleDtl = tblRoleDtlDao.getOne(tblRoleDtlDto.getRoleDtlId());
			if (tblRoleDtl != null) {
				tblRoleDtl.setUpdatedBy(new TblUser(user));
				tblRoleDtl.setUpdatedDate(dateNow);
				if (tblRoleDtlDto.getRoleId() != null) {
					tblRoleDtl.setRoleId(mapperFacade.map(tblRoleDtlDto.getRoleId(), TblRole.class));
				} else {
					tblRoleDtl.setRoleId(null);
				}
				tblRoleDtls.add(tblRoleDtl);
			} else {
				return UNRECOGNIZE_ID;
			}
		}
		tblRoleDtlDao.saveAll(tblRoleDtls);
		return REPOSITORY_TRANSACTION_SUCCESS;
	}

	public int crudRoleDtlActivateSwitcher(TblRoleDtlDto tblRoleDtlDto, String user) {
		tblRoleDtlDao.CrudActivationSwitcherRoleDetail(tblRoleDtlDto.getIsActive(), dateNow, dateNow, dateNow, user,
				tblRoleDtlDto.getRoleDtlId());
		return REPOSITORY_TRANSACTION_SUCCESS;
	}

	public int crudRoleDetailActivatesSwitcher(List<TblRoleDtlDto> tblRoleDtlDtos, String user) {
		List<String> tblRoleDtlDtosActive = new ArrayList<>();
		List<String> tblRoleDtlDtosNonActive = new ArrayList<>();
		for (TblRoleDtlDto tblRoleDtlDto : tblRoleDtlDtos) {
			if (tblRoleDtlDto.getIsActive() == 1) {
				tblRoleDtlDtosActive.add(tblRoleDtlDto.getRoleDtlId());
			} else if (tblRoleDtlDto.getIsActive() == 0) {
				tblRoleDtlDtosNonActive.add(tblRoleDtlDto.getRoleDtlId());
			}
		}
		tblRoleDao.CrudActivationSwitcherRoles(1, null, dateNow, dateNow, user, tblRoleDtlDtosActive);
		tblRoleDao.CrudActivationSwitcherRoles(0, dateNow, null, dateNow, user, tblRoleDtlDtosNonActive);
		return REPOSITORY_TRANSACTION_SUCCESS;
	}

}
