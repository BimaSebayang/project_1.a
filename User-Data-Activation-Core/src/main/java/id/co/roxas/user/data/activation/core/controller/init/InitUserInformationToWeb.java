	package id.co.roxas.user.data.activation.core.controller.init;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.roxas.data.transfer.object.UserDataActivation.core.TblRoleDtlDto;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblRoleDto;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblUserDto;
import id.co.roxas.data.transfer.object.UserDataActivation.response.WsResponse;
import id.co.roxas.user.data.activation.core.controller.BaseController;
import id.co.roxas.user.data.activation.core.dao.TblUserDao;
import id.co.roxas.user.data.activation.core.repository.TblRoleDtl;
import id.co.roxas.user.data.activation.core.repository.TblUser;

@RestController
@Validated
@RequestMapping("/web-request")
public class InitUserInformationToWeb extends BaseController{

	@Autowired
	private TblUserDao tblUserDao;
	
	@PostMapping("/request-user")
	public WsResponse requestUser(@Valid @RequestBody String userValidation) {
		TblUser tblUser = tblUserDao.findByUserTicketOrUserEmailOrUserUserPhoneOrUserId(userValidation);
		if(tblUser == null) {
			return null;
		}
		TblUserDto tblUserDto = new TblUserDto();
		TblRoleDto tblRoleDto = new TblRoleDto();
		List<TblRoleDtl> tblRoleDtls = mapperFacade.mapAsList(tblUser.getRoleId().getTblRoleDtls(),
				TblRoleDtl.class);
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
		tblRoleDto.setTblRoleDtls(tblRoleDtlDtos);
		tblUserDto.setRoleId(tblRoleDto);
		tblUserDto.setIsActive(tblUser.getIsActive());
		tblUserDto.setUserId(tblUser.getUserId());
		tblUserDto.setUserPassword(tblUser.getUserPassword());
		
		return new WsResponse(tblUserDto, "DATA READY TO READ");
	}
}