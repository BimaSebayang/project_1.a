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
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class RoleAndDetailMaintenanceSvc extends BaseService {

	@Autowired
	private TblRoleDao tblRoleDao;
	@Autowired
	private TblRoleDtlDao tblRoleDtlDao;

	public List<TblRoleDto> releasedAllRoleThatNotInUsed(String dtlName){
		List<TblRoleDto> tblRoleDtos = new ArrayList<>();
		List<TblRole> tblRoles = tblRoleDao.retrieveAllRoleIsActiveButNotInUsedInOtherRole(dtlName);
		for (TblRole tblRole : tblRoles) {
			TblRoleDto tblRoleDto = new TblRoleDto();
			tblRoleDto.setRoleId(tblRole.getRoleId());
			tblRoleDto.setRoleName(tblRole.getRoleName());
			tblRoleDtos.add(tblRoleDto);
		}
		return tblRoleDtos;
	}
	
	public PageRequestCustom<TblRoleDto> getAllRole(String isActive, String roleDtlId, String startDate, String endDate,
			String search, String names, Pageable pageable) {

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
				roleDtlId, isActive, names, pageable);

		Page<TblRole> filter = tblRoleDao.findAllRoleWithCondition(staplingWords(search, "%"), startDat, endDat,
				roleDtlId, isActive, names, PageRequest.of(0, Integer.MAX_VALUE));

		List<Map<String, String>> flagActivator = new ArrayList<>();
		List<Map<String, String>> tblRoleDtlDtos = new ArrayList<>();
		// List<Map<String, String>> tblUserDtos = new ArrayList<>();
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
//			for (TblUser tbl : filt.getTblUsers()) {
//				Map<String, String> user = new HashMap<>();
//				user.put("userName", tbl.getUserName());
//				tblUserDtos.add(user);
//			}

		}

		Map<String, Object> filtering = new HashMap<>();
		filtering.put("flagName", reloadUniqueValue(flagActivator));
		filtering.put("roleDtlName", reloadUniqueValue(tblRoleDtlDtos));
		// filtering.put("userDtl", reloadUniqueValue(tblUserDtos));

		List<TblRoleDto> tblRoleDtos = new ArrayList<>();
		// System.out.println(page.getSize());
		for (TblRole tblRole : page.getContent()) {
			TblRoleDto tblRoleDto = new TblRoleDto();
			tblRoleDto = mapperFacade.map(tblRole, TblRoleDto.class);
			List<TblRoleDtlDto> detail = new ArrayList<>();
			List<TblUserDto> userDtos = new ArrayList<>();
			if (tblRole.getTblRoleDtls() != null) {
				for (TblRoleDtl dtldto : tblRole.getTblRoleDtls()) {
					TblRoleDtlDto roleDtlDto = new TblRoleDtlDto();
					roleDtlDto.setRoleDtlId(dtldto.getRoleDtlId());
					roleDtlDto.setRoleDtlName(dtldto.getRoleDtlName());
					roleDtlDto.setRoleDtlFunc(dtldto.getRoleDtlFunc());
					detail.add(roleDtlDto);
				}
			}

			if (tblRole.getTblUsers() != null) {
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
			}
			if (tblRole.getUpdatedBy() != null) {
				tblRoleDto.setUpdatedBy(
						new TblUserDto(tblRole.getUpdatedBy().getUserId(), tblRole.getUpdatedBy().getUserName()));

			}
			tblRoleDtos.add(tblRoleDto);
		}
		return new PageRequestCustom<>(tblRoleDtos, page.getPageable().getPageSize(), page.getTotalPages(),
				page.getPageable().getPageNumber(), tblRoleDtos.size(), getSorter(pageable), filtering);
	}

	public PageRequestCustom<TblRoleDtlDto> getAllRoleDtl(String isActive, String roleId, String filterUserName,
			String startDate, String endDate, String search, String names, Pageable pageable) {

		if (Strings.isBlank(isActive)) {
			isActive = "";
		}

		if (Strings.isBlank(roleId)) {
			roleId = "";
		}

		if (Strings.isBlank(filterUserName)) {
			filterUserName = "";
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
		Page<TblRoleDtl> page = tblRoleDtlDao.findAllRoleDetailWithCondition(staplingWords(search, "%"), names, roleId,
				filterUserName, isActive, startDat, endDat, pageable);

		Page<TblRoleDtl> filter = tblRoleDtlDao.findAllRoleDetailWithCondition(staplingWords(search, "%"), names,
				roleId, filterUserName, isActive, startDat, endDat, PageRequest.of(0, Integer.MAX_VALUE));

		List<Map<String, String>> flagActivator = new ArrayList<>();
		List<Map<String, String>> tblRoleDtos = new ArrayList<>();
		List<Map<String, String>> tblUserDtos = new ArrayList<>();
		for (TblRoleDtl filt : filter.getContent()) {
			Map<String, String> activator = new HashMap<>();
			if (filt.getIsActive() == 1) {
				activator.put("value", "Activate");
				activator.put("key", "1");
			} else if (filt.getIsActive() == 0) {
				activator.put("value", "Disactivate");
				activator.put("key", "0");
			}
			flagActivator.add(activator);
			Map<String, String> role = new HashMap<>();
			if (filt.getRoleId() != null) {
				role.put("roleName", filt.getRoleId().getRoleName());
				tblRoleDtos.add(role);
				for (TblUser map : filt.getRoleId().getTblUsers()) {
					Map<String, String> user = new HashMap<>();
					user.put("userName", map.getUserName());
					tblUserDtos.add(user);
				}
			}
		}

		Map<String, Object> filtering = new HashMap<>();
		filtering.put("flagName", reloadUniqueValue(flagActivator));
		filtering.put("roleName", reloadUniqueValue(tblRoleDtos));
		filtering.put("userDtl", reloadUniqueValue(tblUserDtos));

		List<TblRoleDtlDto> tblRoleDtlDtos = new ArrayList<>();
		// System.out.println(page.getSize());
		for (TblRoleDtl tblRoleDtl : page.getContent()) {
			TblRoleDtlDto tblRoleDtlDto = new TblRoleDtlDto();
			tblRoleDtlDto = mapperFacade.map(tblRoleDtl, TblRoleDtlDto.class);
			if (tblRoleDtl.getRoleId() != null) {
				tblRoleDtlDto.setRoleId(
						new TblRoleDto(tblRoleDtl.getRoleId().getRoleId(), tblRoleDtl.getRoleId().getRoleName()));
			}
			if (tblRoleDtl.getCreatedBy() != null) {
				tblRoleDtlDto.setCreatedBy(
						new TblUserDto(tblRoleDtl.getCreatedBy().getUserId(), tblRoleDtl.getCreatedBy().getUserName()));
			}

			if (tblRoleDtl.getUpdatedBy() != null) {
				tblRoleDtlDto.setUpdatedBy(
						new TblUserDto(tblRoleDtl.getUpdatedBy().getUserId(), tblRoleDtl.getUpdatedBy().getUserName()));
			}
			tblRoleDtlDtos.add(tblRoleDtlDto);
		}
		return new PageRequestCustom<>(tblRoleDtlDtos, page.getPageable().getPageSize(), page.getTotalPages(),
				page.getPageable().getPageNumber(), tblRoleDtlDtos.size(), getSorter(pageable), filtering);
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
		tblRole.setTblRoleDtls(null);
		tblRoleDao.save(tblRole);
		for (TblRoleDtlDto roleDtl : tblRoleDto.getTblRoleDtlDtos()) {
			CrudDetailRoleSave(roleDtl, tblRole, user);
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
		tblRoleDao.CrudActivationSwitcherRole(tblRoleDto.getIsActive(), dateNow, dateNow, dateNow, new TblUser(user),
				tblRoleDto.getRoleId());
		return REPOSITORY_TRANSACTION_SUCCESS;
	}
	
	public int crudRoleDetailActivateSwitcher(TblRoleDtlDto tblRoleDtlDto, String user) {
		tblRoleDtlDao.CrudActivationSwitcherRoleDetail(tblRoleDtlDto.getIsActive(), dateNow, dateNow, dateNow, new TblUser(user),
				tblRoleDtlDto.getRoleDtlId());
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
		tblRoleDao.CrudActivationSwitcherRoles(1, null, dateNow, dateNow, new TblUser(user), tblRoleDtosActive);
		tblRoleDao.CrudActivationSwitcherRoles(0, dateNow, null, dateNow, new TblUser(user), tblRoleDtosNonActive);
		return REPOSITORY_TRANSACTION_SUCCESS;
	}

	public int crudRoleUpdate(TblRoleDto tblRoleDto, String user) {
		TblRole role = new TblRole();
		role = tblRoleDao.getRoleById(tblRoleDto.getRoleId());
		role.setUpdatedBy(new TblUser(user));
		role.setUpdatedDate(dateNow);
		role.setTblRoleDtls(null);
		if (!Strings.isBlank(tblRoleDto.getRoleName())) {
			role.setRoleName(tblRoleDto.getRoleName());
		}
		tblRoleDao.save(role);
		List<TblRoleDtlDto> tempDetail = new ArrayList<>(tblRoleDto.getTblRoleDtlDtos());
		List<String> tempDetailRecognize = new ArrayList<>();
		List<TblRoleDtlDto> saveTempDetail = new ArrayList<>();

		   for (TblRoleDtlDto dtl : tempDetail) {
			List<TblRoleDtl> tblRoleDtls = tblRoleDtlDao.getDtilRoleId(dtl.getRoleDtlName());

			if(tblRoleDtls == null ||tblRoleDtls.size()==0) {
				saveTempDetail.add(dtl);
			}
			else if (tblRoleDtls.size() == 1) {
				TblRoleDtl tblRoleDtl = tblRoleDtls.get(0);
				if (Strings.isBlank(tblRoleDtl.getRoleDtlId())) {
					saveTempDetail.add(dtl);
				} else {
					if (tblRoleDtl.getRoleId() == null) {
						tblRoleDtlDao.updateNullRoleIdBasetlId(new TblRole(tblRoleDto.getRoleId()), dateNow,
								new TblUser(user), tblRoleDtl.getRoleDtlId());
					}
					tempDetailRecognize.add(dtl.getRoleDtlName());
				}
			}
			else if(tblRoleDtls.size() > 1) {
				boolean surpass = true;
				for (TblRoleDtl roleDetail : tblRoleDtls) {
					if (roleDetail.getRoleId() == null) {
						tblRoleDtlDao.updateNullRoleIdBasetlId(new TblRole(tblRoleDto.getRoleId()), dateNow,
								new TblUser(user), roleDetail.getRoleDtlId());
						tempDetailRecognize.add(dtl.getRoleDtlName());
						surpass = false;
						break;
					}
				}
				
				if(surpass) {
					saveTempDetail.add(dtl);
				}
			}
		}

		if (tempDetailRecognize.size() > 0) {
			tblRoleDtlDao.updateRoleIdInRoleDetail(new TblRole(tblRoleDto.getRoleId()), tempDetailRecognize, dateNow,
					new TblUser(user));
		} else {
			tblRoleDtlDao.removeAllDetailId(new TblRole(tblRoleDto.getRoleId()), dateNow, new TblUser(user));
		}

		for (TblRoleDtlDto tblRoleDtlDto : saveTempDetail) {
			CrudDetailRoleSave(tblRoleDtlDto, role, user);
		}

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
		tblRoleDao.CrudActivationSwitcherRoles(1, null, dateNow, dateNow, new TblUser(user), tblRoleDtlDtosActive);
		tblRoleDao.CrudActivationSwitcherRoles(0, dateNow, null, dateNow, new TblUser(user), tblRoleDtlDtosNonActive);
		return REPOSITORY_TRANSACTION_SUCCESS;
	}

}
