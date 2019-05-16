package id.co.roxas.user.data.activation.core.service.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import id.co.roxas.user.data.activation.core.config.PasswordRefactor;
import id.co.roxas.user.data.activation.core.repository.TblRoleDtl;
import id.co.roxas.user.data.activation.core.repository.TblUser;

public class CustomUserService implements UserDetails {

	private static final long serialVersionUID = -5446817407722834700L;
    private String userName;
    private String password;
    private boolean isActive = false;
    Collection<? extends GrantedAuthority> authorities;
	
	public CustomUserService(TblUser tblUser) {
		  this.userName = tblUser.getUserId();
		  this.password = "{noop}"+PasswordRefactor.refactorChar(tblUser.getUserPassword());
		  if(tblUser.getIsActive()==1) {
			  isActive = true;
		  }
		  List<GrantedAuthority> authorities = new ArrayList<>();
		  for(TblRoleDtl role : tblUser.getRoleId().getTblRoleDtls()) {
			  if(role.getIsActive() == 1 && role.getRoleDtlFunc().equalsIgnoreCase("AUTHWS")) {
			  authorities.add(new SimpleGrantedAuthority(role.getRoleDtlName().toUpperCase()));
			  }
		  }
		  this.authorities = authorities;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return isActive;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
