package id.co.roxas.user.data.activation.core.controller.init;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.roxas.user.data.activation.core.dao.TblRoleDao;
import id.co.roxas.user.data.activation.core.dao.TblRoleDtlDao;
import id.co.roxas.user.data.activation.core.dao.TblTicketDao;
import id.co.roxas.user.data.activation.core.dao.TblUserDao;
import id.co.roxas.user.data.activation.core.repository.TblRole;
import id.co.roxas.user.data.activation.core.repository.TblRoleDtl;
import id.co.roxas.user.data.activation.core.repository.TblTicket;
import id.co.roxas.user.data.activation.core.repository.TblUser;

@RestController
@Validated
@RequestMapping("/database-init")
public class InitializerDatabase {

	@GetMapping("/system-creator")
	public String systemCreator() {
	    DatabaseInitializer();
		return "DONE";
	}
	
	@Autowired
	private  TblUserDao tblUserDao;
	@Autowired
	private  TblRoleDao tblRoleDao;
	@Autowired
	private  TblRoleDtlDao tblRoleDtlDao;
	@Autowired
	private TblTicketDao tblTicketDao;
	
	public  void DatabaseInitializer() {
		Date date = new Date();
		TblRole tblRole =  new TblRole();
		tblRole.setCreatedDate(date);
		tblRole.setDateActive(date);
		tblRole.setIsActive(1);
		tblRole.setRoleName("SUPER_ADMIN");
		tblRoleDao.save(tblRole);		
		TblUser tblUser = new TblUser();
		tblUser.setUserName("SU");
		tblUser.setCreatedDate(date);
		tblUser.setDateActive(date);
		tblUser.setIsActive(1);
		tblUser.setRoleId(tblRole);
		tblUser.setUserEmail("SU@email.co.id");
		tblUser.setUserPassword("super_admin");
		tblUser.setUserPhone("00000000000");
		TblTicket tblTicket = new TblTicket();
		tblTicketDao.save(tblTicket);
		tblUser.setUserTicket(tblTicket);
		tblUserDao.save(tblUser);
		TblRoleDtl tblRoleDtl1 = new TblRoleDtl();
		tblRoleDtl1.setCreatedDate(date);
		tblRoleDtl1.setDateActive(date);
		tblRoleDtl1.setIsActive(1);
		tblRoleDtl1.setRoleDtlFunc("AUTHWS");
		tblRoleDtl1.setRoleDtlName("USER");
		tblRoleDtl1.setRoleId(tblRole);
		TblRoleDtl tblRoleDtl2 = new TblRoleDtl();
		tblRoleDtl2.setCreatedDate(date);
		tblRoleDtl2.setDateActive(date);
		tblRoleDtl2.setIsActive(1);
		tblRoleDtl2.setRoleDtlFunc("AUTHWS");
		tblRoleDtl2.setRoleDtlName("GUEST");
		tblRoleDtl2.setRoleId(tblRole);
		TblRoleDtl tblRoleDtl3 = new TblRoleDtl();
		tblRoleDtl3.setCreatedDate(date);
		tblRoleDtl3.setDateActive(date);
		tblRoleDtl3.setIsActive(1);
		tblRoleDtl3.setRoleDtlFunc("AUTHWS");
		tblRoleDtl3.setRoleDtlName("ADMIN");
		tblRoleDtl3.setRoleId(tblRole);
		List<TblRoleDtl> tblRoleDtls = new ArrayList<>();
		tblRoleDtls.add(tblRoleDtl3);
		tblRoleDtls.add(tblRoleDtl2);
		tblRoleDtls.add(tblRoleDtl1);
		tblRoleDtlDao.saveAll(tblRoleDtls);
	}
	
	
}
