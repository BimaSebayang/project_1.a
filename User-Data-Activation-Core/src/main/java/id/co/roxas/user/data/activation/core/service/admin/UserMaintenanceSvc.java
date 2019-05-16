package id.co.roxas.user.data.activation.core.service.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblUserDto;
import id.co.roxas.user.data.activation.core.config.PasswordDefactor;
import id.co.roxas.user.data.activation.core.dao.TblTicketDao;
import id.co.roxas.user.data.activation.core.dao.TblUserDao;
import id.co.roxas.user.data.activation.core.repository.TblRole;
import id.co.roxas.user.data.activation.core.repository.TblTicket;
import id.co.roxas.user.data.activation.core.repository.TblUser;
import id.co.roxas.user.data.activation.core.service.BaseService;

@Service
public class UserMaintenanceSvc extends BaseService {

	@Autowired
	private TblUserDao tblUserDao;
	@Autowired
	private TblTicketDao tblTicketDao;

	private final UserMaintenanceSvc userMaintenanceSvc = new UserMaintenanceSvc();
	
	private TblUserDto mapperUserDto (TblUser tblUser) {
		TblUserDto tblUserDto = mapperFacade.map(tblUser, TblUserDto.class);
		return tblUserDto;
	}
	
	
	public Page<TblUserDto> getAllUser(String isActive, String roleId, Pageable pageable){
		return tblUserDao.findAllWithFilterAndSearch(isActive, roleId, pageable)
				.map(userMaintenanceSvc::mapperUserDto);
	}
	
	public TblTicket addAndRetrieveNewTicket(TblTicket ticket) {
		tblTicketDao.save(ticket);
		return ticket;
	}

	public int CrudUserSave(TblUserDto tblUserDto, String user) {
		TblUser tblUser = new TblUser();
		tblUser = mapperFacade.map(tblUserDto, TblUser.class);
		if (tblUserDto.getRoleId() != null) {
			tblUser.setRoleId(mapperFacade.map(tblUserDto.getRoleId(), TblRole.class));
		}
		tblUser.setUserPassword(PasswordDefactor.defactorChar(tblUserDto.getUserPassword()));
		tblUser.setIsActive(1);
		tblUser.setCreatedDate(dateNow);
		tblUser.setDateActive(dateNow);
		tblUser.setCreatedBy(new TblUser(user));
		tblUser.setUserTicket(addAndRetrieveNewTicket(new TblTicket()));
		tblUserDao.save(tblUser);
		return REPOSITORY_TRANSACTION_SUCCESS;
	}

	public int CrudUsersSave(List<TblUserDto> tblUserDtos, String user) {
		List<TblUser> tblUsers = new ArrayList<>();
		for (TblUserDto tblUserDto : tblUserDtos) {
			TblUser tblUser = new TblUser();
			tblUser = mapperFacade.map(tblUserDto, TblUser.class);
			if (tblUserDto.getRoleId() != null) {
				tblUser.setRoleId(mapperFacade.map(tblUserDto.getRoleId(), TblRole.class));
			}
			tblUser.setUserPassword(PasswordDefactor.defactorChar(tblUserDto.getUserPassword()));
			tblUser.setIsActive(1);
			tblUser.setCreatedDate(dateNow);
			tblUser.setDateActive(dateNow);
			tblUser.setCreatedBy(new TblUser(user));
			tblUser.setUserTicket(addAndRetrieveNewTicket(new TblTicket()));
			tblUsers.add(tblUser);
		}
		tblUserDao.saveAll(tblUsers);
		return REPOSITORY_TRANSACTION_SUCCESS;
	}
	
    public int crudUpdateUser(TblUserDto tblUserDto, String user) {
    	TblUser tblUser = tblUserDao.getOne(tblUserDto.getUserId());
    	tblUser.setUpdatedDate(dateNow);
    	tblUser.setUpdatedBy(new TblUser(user));
    	if(!Strings.isBlank(tblUserDto.getUserEmail())) {
    	  tblUser.setUserEmail(tblUserDto.getUserEmail());
    	}
    	if(!Strings.isBlank(tblUserDto.getUserName())) {
    		tblUser.setUserName(tblUserDto.getUserName());
    	}
    	if(!Strings.isBlank(tblUserDto.getUserPassword())) {
    		tblUser.setUserPassword(tblUserDto.getUserPassword());
    	}
    	if(!Strings.isBlank(tblUserDto.getUserPhone())) {
    		tblUser.setUserPhone(tblUserDto.getUserPhone());
    	}
    	if(tblUserDto.getUserTicket() != null) {
    		tblUser.setUserTicket(mapperFacade.map(tblUserDto.getUserTicket(), TblTicket.class));
    	}else {
    		TblTicket tblTicket = new TblTicket();
    		tblTicketDao.save(tblTicket);
    		tblUser.setUserTicket(tblTicket);
    	}
    	if(tblUserDto.getRoleId()!=null) {
    		tblUser.setRoleId(mapperFacade.map(tblUserDto.getRoleId(), TblRole.class));
    	}
    	tblUserDao.save(tblUser);
    	return REPOSITORY_TRANSACTION_SUCCESS;
    }
    
    public int crudUpdateUsers(List<TblUserDto> tblUserDtos, String user) {
    	List<TblUser> tblUsers = new ArrayList<>();
    	for (TblUserDto tblUserDto : tblUserDtos) {
    		TblUser tblUser = tblUserDao.getOne(tblUserDto.getUserId());
        	tblUser.setUpdatedDate(dateNow);
        	tblUser.setUpdatedBy(new TblUser(user));
        	if(!Strings.isBlank(tblUserDto.getUserEmail())) {
        	  tblUser.setUserEmail(tblUserDto.getUserEmail());
        	}
        	if(!Strings.isBlank(tblUserDto.getUserName())) {
        		tblUser.setUserName(tblUserDto.getUserName());
        	}
        	if(!Strings.isBlank(tblUserDto.getUserPassword())) {
        		tblUser.setUserPassword(tblUserDto.getUserPassword());
        	}
        	if(!Strings.isBlank(tblUserDto.getUserPhone())) {
        		tblUser.setUserPhone(tblUserDto.getUserPhone());
        	}
        	if(tblUserDto.getUserTicket() != null) {
        		tblUser.setUserTicket(mapperFacade.map(tblUserDto.getUserTicket(), TblTicket.class));
        	}else {
        		TblTicket tblTicket = new TblTicket();
        		tblTicketDao.save(tblTicket);
        		tblUser.setUserTicket(tblTicket);
        	}
        	if(tblUserDto.getRoleId()!=null) {
        		tblUser.setRoleId(mapperFacade.map(tblUserDto.getRoleId(), TblRole.class));
        	}
        	tblUsers.add(tblUser);
		}
    	
    	tblUserDao.saveAll(tblUsers);
    	return REPOSITORY_TRANSACTION_SUCCESS;
    }
	
	public int crudUserActivateSwitcher(TblUserDto tblUserDto, String user) {
		 tblUserDao.CrudActivationSwitcherUser
		     (tblUserDto.getIsActive(), dateNow, dateNow, dateNow, user, tblUserDto.getUserId());
		 return REPOSITORY_TRANSACTION_SUCCESS;
	}
	
	public int crudUserActivatesSwitcher(List<TblUserDto> tblUserDtos, String user) {
		List<String> tblUserDtosActive = new ArrayList<>();
		List<String> tblUserDtosNonActive = new ArrayList<>();
		for (TblUserDto tblUserDto : tblUserDtos) {
			if(tblUserDto.getIsActive()==1) {
				tblUserDtosActive.add(tblUserDto.getUserId());
			}
			else if(tblUserDto.getIsActive()==0) {
				tblUserDtosNonActive.add(tblUserDto.getUserId());
			}
		}
		 tblUserDao.CrudActivationSwitcherUsers
		       (1, null, dateNow, dateNow, user, tblUserDtosActive);
		 tblUserDao.CrudActivationSwitcherUsers
	           (0, dateNow, null, dateNow, user, tblUserDtosNonActive);
		return REPOSITORY_TRANSACTION_SUCCESS;
	}

}
