package id.co.roxas.user.data.activation.core.config;

import id.co.roxas.data.transfer.object.UserDataActivation.core.TblUserDto;
import id.co.roxas.user.data.activation.core.repository.TblUser;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class MapperPageClass{
	private MapperFacade mapperFacade = new DefaultMapperFactory.Builder().build().getMapperFacade();
	public TblUserDto mapperUserDto (TblUser tblUser) {
		TblUserDto tblUserDto = mapperFacade.map(tblUser, TblUserDto.class);
		return tblUserDto;
	}
}
