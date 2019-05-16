package id.co.roxas.user.data.activation.core.service.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.roxas.data.transfer.object.UserDataActivation.core.TblRoleDtlDto;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblRoleDto;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblUserDto;
import id.co.roxas.user.data.activation.core.dao.TblUserDao;
import id.co.roxas.user.data.activation.core.repository.TblUser;
import id.co.roxas.user.data.activation.core.service.BaseService;

@Service
public class UserAuthenticationCustomLogin extends BaseService{
      @Autowired
      TblUserDao tblUserDao;
      
      public List<TblUserDto> getAllUser(){
    	  List<TblUser> tblUsers = tblUserDao.findAll();
    	  List<TblUserDto> userDtos = new ArrayList<>();
    	  for (TblUser tblUser : tblUsers) {
			  TblUserDto userDto = new TblUserDto();
			  TblRoleDto roleDto = new TblRoleDto();
			  roleDto = mapperFacade.map(tblUser.getRoleId(), TblRoleDto.class);
			  roleDto.setTblRoleDtls(mapperFacade.mapAsList(tblUser.getRoleId().getTblRoleDtls(), TblRoleDtlDto.class));
			  userDto = mapperFacade.map(tblUser, TblUserDto.class);
			  userDto.setRoleId(roleDto);
			  userDtos.add(userDto);
		  }
    	  return userDtos;
      }
}
