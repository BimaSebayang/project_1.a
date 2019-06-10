package id.co.roxas.user.data.activation.core.service.admin;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import id.co.roxas.data.transfer.object.UserDataActivation.core.TblRoleDto;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblTicketDto;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblUserDto;
import id.co.roxas.data.transfer.object.UserDataActivation.custom.PageRequestCustom;
import id.co.roxas.data.transfer.object.shared.ticket.PasswordDefactor;
import id.co.roxas.user.data.activation.core.dao.TblTicketDao;
import id.co.roxas.user.data.activation.core.dao.TblUserDao;
import id.co.roxas.user.data.activation.core.repository.TblRole;
import id.co.roxas.user.data.activation.core.repository.TblTicket;
import id.co.roxas.user.data.activation.core.repository.TblUser;
import id.co.roxas.user.data.activation.core.service.BaseService;

@Service
@Transactional
public class UserMaintenanceSvc extends BaseService {

	@Autowired
	private TblUserDao tblUserDao;
	@Autowired
	private TblTicketDao tblTicketDao;

	public PageRequestCustom<TblUserDto> getAllUser(String isActive, String roleId, String startDate,
			String endDate,String search, String ownUser,Pageable pageable){
		
		if(Strings.isBlank(isActive)) {
			System.err.println("is active null");
			isActive = "";
		}
		
		if(Strings.isBlank(roleId)) {
			System.err.println("role id null");
			roleId = "";
		} 
		
		if(Strings.isBlank(search)) {
			search = "";
		}else {
			if(search.equalsIgnoreCase("Active")) {
				search = "1";
			}else if(search.equalsIgnoreCase("Inactive")) {
				search = "0";
			}
		}
		
		
		
		getStartDateEndDate(startDate, endDate, "ddMMyyyy");
		Page<TblUser> page = tblUserDao.findAllWithFilterAndSearch(isActive,roleId,startDat, endDat,
				staplingWords(search, "%"),ownUser,pageable);
		
		
		Page<TblUser> filter = tblUserDao.findAllWithFilterAndSearch(isActive,roleId,startDat, endDat,
				staplingWords(search, "%"),ownUser,PageRequest.of(0, Integer.MAX_VALUE));
		
		List<Map<String, String>> flagActivator = new ArrayList<>();
		List<Map<String, String>> tblRoleDtos = new ArrayList<>();
		
		for (TblUser filt : filter.getContent()) {
			Map<String, String> activator = new HashMap<>();
			Map<String, String> role = new HashMap<>();
			if(filt.getIsActive() == 1) {
			activator.put("value","Activate");
			activator.put("key", "1");
			}
			else if(filt.getIsActive() == 0) {
				activator.put("value","Disactivate");
				activator.put("key", "0");
			}
			flagActivator.add(activator);
			if(filt.getRoleId()!=null) {
			role.put("roleName", filt.getRoleId().getRoleName());
			role.put("roleId", filt.getRoleId().getRoleId());
			}
			tblRoleDtos.add(role);
		}
		
		Map<String, Object> filtering = new HashMap<>();
		filtering.put("flagName", reloadUniqueValue(flagActivator));
		filtering.put("roleName", reloadUniqueValue(tblRoleDtos));
		
		
		
		List<TblUserDto> tblUserDtos = new ArrayList<>();
		//System.out.println(page.getSize());
		for (TblUser tblUser : page.getContent()) {
			TblUserDto tblUserDto = new TblUserDto();
			tblUserDto = mapperFacade.map(tblUser, TblUserDto.class);
			TblUserDto userDto = new TblUserDto();
			if(tblUser.getCreatedBy()!=null) {
			userDto.setUserName(tblUser.getCreatedBy().getUserName());
			}
			TblRoleDto roleDto = new TblRoleDto();
			if(tblUser.getRoleId()!=null) {
			roleDto.setRoleId(tblUser.getRoleId().getRoleId());
			roleDto.setRoleName(tblUser.getRoleId().getRoleName());
			}
			TblUserDto userUpdate = new TblUserDto();
			if(tblUser.getUpdatedBy() !=null) {
			userUpdate.setUserName(tblUser.getUpdatedBy().getUserName());	
			}
			TblTicketDto tblTicketDto = new TblTicketDto();
			if(tblUser.getUserTicket()!=null) {
				tblTicketDto.setTicketId(tblUser.getUserTicket().getTicketId());
			}
			tblUserDto.setCreatedBy(userDto);
			tblUserDto.setUserPassword(null);
			tblUserDto.setRoleId(roleDto);
			tblUserDto.setUpdatedBy(userUpdate);
			tblUserDto.setUserTicket(tblTicketDto);
			tblUserDtos.add(tblUserDto);
		}
		System.err.println("banyaknya dtos : " + tblUserDtos.size());
		return new PageRequestCustom<>(tblUserDtos, 
				page.getPageable().getPageSize(), 
				page.getTotalPages(), page.getPageable().getPageNumber(), tblUserDtos.size(), 
				getSorter(pageable),filtering);
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
		     (tblUserDto.getIsActive(), dateNow, dateNow, dateNow, new TblUser(user), tblUserDto.getUserId());
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
		       (1, null, dateNow, dateNow, new TblUser(user), tblUserDtosActive);
		 tblUserDao.CrudActivationSwitcherUsers
	           (0, dateNow, null, dateNow, new TblUser(user), tblUserDtosNonActive);
		return REPOSITORY_TRANSACTION_SUCCESS;
	}

}
