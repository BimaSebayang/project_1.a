package id.co.roxas.user.data.activation.core.service.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblRoleDtlDto;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblRoleDto;
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

	public List<TblRoleDto> selectAllRoleIsActive(){
		List<TblRole> tblRoles = tblRoleDao.retrieveAllRoleIsActive();
		List<TblRoleDto> tblRoleDtos = new ArrayList<>();
		for (TblRole tblRole : tblRoles) {
			if(tblRole.getTblRoleDtls().size()>0) {
			TblRoleDto tblRoleDto = new TblRoleDto();
			tblRoleDto.setRoleName(tblRole.getRoleName());
			tblRoleDto.setRoleId(tblRole.getRoleId());
			tblRoleDtos.add(tblRoleDto);
			}
		}
		return tblRoleDtos;
	}
	
	public List<TblRoleDto> selectAllRole(){
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
			if(Strings.isBlank(roleDtl.getRoleDtlName())) {
			CrudDetailRoleSave(roleDtl,tblRole, user);
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
		 tblRoleDao.CrudActivationSwitcherRole
				(tblRoleDto.getIsActive(), dateNow, 
						dateNow, dateNow, 
						user, tblRoleDto.getRoleId());
		 return REPOSITORY_TRANSACTION_SUCCESS;
	}
	
	public int crudRoleActivatesSwitcher(List<TblRoleDto> tblRoleDtos, String user) {
		List<String> tblRoleDtosActive = new ArrayList<>();
		List<String> tblRoleDtosNonActive = new ArrayList<>();
		for (TblRoleDto tblRoleDto : tblRoleDtos) {
			if(tblRoleDto.getIsActive()==1) {
				tblRoleDtosActive.add(tblRoleDto.getRoleId());
			}
			else if(tblRoleDto.getIsActive()==0) {
				tblRoleDtosActive.add(tblRoleDto.getRoleId());
			}
		}
		tblRoleDao.CrudActivationSwitcherRoles
		       (1, null, dateNow, dateNow, user, tblRoleDtosActive);
		tblRoleDao.CrudActivationSwitcherRoles
	           (0, dateNow, null, dateNow, user, tblRoleDtosNonActive);
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
		if (tblRoleDtl!=null) {
			tblRoleDtl.setUpdatedBy(new TblUser(user));
			tblRoleDtl.setUpdatedDate(dateNow);
			
			if(tblRoleDtlDto.getRoleId()!=null) {
			tblRoleDtl.setRoleId(mapperFacade.map(tblRoleDtlDto.getRoleId(), TblRole.class));
			}
			else {
				tblRoleDtl.setRoleId(null);
			}
			tblRoleDtlDao.save(tblRoleDtl);	
			return REPOSITORY_TRANSACTION_SUCCESS;
		}else {
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
				if(!Strings.isBlank(tblRoleDtlDto.getRoleId().getRoleId())) {
				tblRoleDtl.setRoleId(mapperFacade.map(tblRoleDtlDto.getRoleId(), TblRole.class));
				}
				else {
					tblRoleDtl.setRoleId(null);
				}
			}else {
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
			if (tblRoleDtl!=null) {
				tblRoleDtl.setUpdatedBy(new TblUser(user));
				tblRoleDtl.setUpdatedDate(dateNow);
				if(tblRoleDtlDto.getRoleId()!=null) {
				tblRoleDtl.setRoleId(mapperFacade.map(tblRoleDtlDto.getRoleId(), TblRole.class));
				}
				else {
					tblRoleDtl.setRoleId(null);
				}
				tblRoleDtls.add(tblRoleDtl);
			}else {
				return UNRECOGNIZE_ID;
			}
		}
		tblRoleDtlDao.saveAll(tblRoleDtls);
		return REPOSITORY_TRANSACTION_SUCCESS;
	}
	

	public int crudRoleDtlActivateSwitcher(TblRoleDtlDto tblRoleDtlDto, String user) {
		 tblRoleDtlDao.CrudActivationSwitcherRoleDetail
		     (tblRoleDtlDto.getIsActive(), dateNow, dateNow, dateNow, user, tblRoleDtlDto.getRoleDtlId());
		 return REPOSITORY_TRANSACTION_SUCCESS;
	}
	
	public int crudRoleDetailActivatesSwitcher(List<TblRoleDtlDto> tblRoleDtlDtos, String user) {
		List<String> tblRoleDtlDtosActive = new ArrayList<>();
		List<String> tblRoleDtlDtosNonActive = new ArrayList<>();
		for (TblRoleDtlDto tblRoleDtlDto : tblRoleDtlDtos) {
			if(tblRoleDtlDto.getIsActive()==1) {
				tblRoleDtlDtosActive.add(tblRoleDtlDto.getRoleDtlId());
			}
			else if(tblRoleDtlDto.getIsActive()==0) {
				tblRoleDtlDtosNonActive.add(tblRoleDtlDto.getRoleDtlId());
			}
		}
		tblRoleDao.CrudActivationSwitcherRoles
		       (1, null, dateNow, dateNow, user, tblRoleDtlDtosActive);
		tblRoleDao.CrudActivationSwitcherRoles
	           (0, dateNow, null, dateNow, user, tblRoleDtlDtosNonActive);
		return REPOSITORY_TRANSACTION_SUCCESS;
	}
	
	

}
