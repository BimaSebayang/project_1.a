package id.co.roxas.user.data.activation.core.controller.init;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.ws.rs.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.roxas.data.transfer.object.UserDataActivation.core.TblRoleDtlDto;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblRoleDto;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblUserDto;
import id.co.roxas.data.transfer.object.UserDataActivation.custom.TransactionCUDDto;
import id.co.roxas.data.transfer.object.UserDataActivation.response.WsResponse;
import id.co.roxas.data.transfer.object.shared.ticket.TicketCc;
import id.co.roxas.user.data.activation.core.controller.BaseController;
import id.co.roxas.user.data.activation.core.dao.TblTicketDao;
import id.co.roxas.user.data.activation.core.dao.TblUserDao;
import id.co.roxas.user.data.activation.core.repository.TblRoleDtl;
import id.co.roxas.user.data.activation.core.repository.TblUser;

@RestController
@Validated
@RequestMapping("/web-request")
public class InitUserInformationToWeb extends BaseController {

	@Autowired
	private TblUserDao tblUserDao;
    @Autowired
    private TblTicketDao tblTicketDao;
    
    @Autowired
    @Qualifier("transactionManager")
    protected PlatformTransactionManager txManager;
    
    @PostConstruct
    public void reNullAllTicket() {
    	   TransactionTemplate tmpl = new TransactionTemplate(txManager);
           tmpl.execute(new TransactionCallbackWithoutResult() {
               @Override
               protected void doInTransactionWithoutResult(TransactionStatus status) {
                   tblTicketDao.updateToNullAllTicket();
               }
           });
    }
    
	@PostMapping("/ticket/request-user")
	public WsResponse getRequestIdentifierTicket(@Valid @RequestBody TicketCc ticketCc) {
		TblUser tblUser = new TblUser();
		if (ticketCc.getModule().equals(WEB_UAA)) {
			if (ticketCc.getAccessIdentifier().equals(MOBILE)) {
                 //please fill this for uaasessionweb;
			} else if (ticketCc.getAccessIdentifier().equals(DESKTOP)) {
                 tblUser = tblUserDao.findUserByItsSessionUaaWeb(ticketCc.getSessionId());
			}
		} else if (ticketCc.getModule().equals(WEB_LANG)) {
			if (ticketCc.getAccessIdentifier().equals(MOBILE)) {
				 //please fill this for langsessionweb;
			} else if (ticketCc.getAccessIdentifier().equals(DESKTOP)) {
				  tblUser = tblUserDao.findUserByItsSessionLangWeb(ticketCc.getSessionId());
			}
		}
		return new WsResponse(getUserDto(tblUser), "DATA READY TO READ");
	}
	
	@PutMapping("/ticket/update-user")
	@Transactional
	public WsResponse updateTicketUser(@Valid @RequestBody TicketCc ticketCc) {
		TblUser tblUser = tblUserDao.findByUserTicketOrUserEmailOrUserUserPhoneOrUserId(ticketCc.getUserIdentifier());
		if (ticketCc.getModule().equals(WEB_UAA)) {
			if (ticketCc.getAccessIdentifier().equals(MOBILE)) {
                 //please fill this for uaasessionweb;
			} else if (ticketCc.getAccessIdentifier().equals(DESKTOP)) {
				System.err.println(tblUser.getUserTicket().getTicketId());
				tblTicketDao.updateSessionUaaWebByItsTicketId(tblUser.getUserTicket().getTicketId(), 
                		ticketCc.getSessionId());
			}
		} else if (ticketCc.getModule().equals(WEB_LANG)) {
			if (ticketCc.getAccessIdentifier().equals(MOBILE)) {
				 //please fill this for langsessionweb;
			} else if (ticketCc.getAccessIdentifier().equals(DESKTOP)) {
				tblTicketDao.updateSessionLangWebByItsTicketId(tblUser.getUserTicket().getTicketId(), 
                		ticketCc.getSessionId());
			}
		}
		TransactionCUDDto customDto = new TransactionCUDDto();
		customDto.setUpdateResult(REPOSITORY_TRANSACTION_SUCCESS);
		return new WsResponse(customDto, SUCCESS_UPDATE);
	}

	@PostMapping("/request-user")
	public WsResponse requestUser(@Valid @RequestBody String userValidation) {
		TblUser tblUser = tblUserDao.findByUserTicketOrUserEmailOrUserUserPhoneOrUserId(userValidation);
		return new WsResponse(getUserDto(tblUser), "DATA READY TO READ");
	}

	private TblUserDto getUserDto(TblUser tblUser) {
		if (tblUser == null) {
			return null;
		}
		TblUserDto tblUserDto = new TblUserDto();
		TblRoleDto tblRoleDto = new TblRoleDto();
		List<TblRoleDtl> tblRoleDtls = mapperFacade.mapAsList(tblUser.getRoleId().getTblRoleDtls(), TblRoleDtl.class);
		List<TblRoleDtlDto> tblRoleDtlDtos = new ArrayList<>();
		for (TblRoleDtl tblRoleDtl : tblRoleDtls) {
			TblRoleDtlDto tblRoleDtlDto = new TblRoleDtlDto();
			tblRoleDtlDto.setRoleDtlId(tblRoleDtl.getRoleDtlId());
			tblRoleDtlDto.setRoleDtlFunc(tblRoleDtl.getRoleDtlFunc());
			tblRoleDtlDto.setRoleDtlName(tblRoleDtl.getRoleDtlName());
			tblRoleDtlDto.setIsActive(tblRoleDtl.getIsActive());
			tblRoleDtlDtos.add(tblRoleDtlDto);
		}
		tblRoleDto.setRoleName(tblUser.getRoleId().getRoleName());
		tblRoleDto.setRoleId(tblUser.getRoleId().getRoleId());
		tblRoleDto.setIsActive(tblUser.getRoleId().getIsActive());
		tblRoleDto.setTblRoleDtlDtos(tblRoleDtlDtos);
		tblUserDto.setRoleId(tblRoleDto);
		tblUserDto.setIsActive(tblUser.getIsActive());
		tblUserDto.setUserId(tblUser.getUserId());
		tblUserDto.setUserPassword(tblUser.getUserPassword());
		return tblUserDto;
	}

}
