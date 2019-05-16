package id.co.roxas.user.data.activation.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import id.co.roxas.user.data.activation.core.dao.TblUserDao;
import id.co.roxas.user.data.activation.core.service.auth.CustomUserService;

@SpringBootApplication
public class UserDataActivationCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserDataActivationCoreApplication.class, args);
	}
	
	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, TblUserDao tblUserDao) throws Exception {
	   builder.userDetailsService(new UserDetailsService() {
		
		@Override
		public UserDetails loadUserByUsername(String userValidation) throws UsernameNotFoundException {
			return new CustomUserService(tblUserDao.findByUserTicketOrUserEmailOrUserUserPhoneOrUserId(userValidation));
		}
	})	;
	}

}
