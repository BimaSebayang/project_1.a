package id.co.roxas.user.data.activation.core;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.xmlpull.v1.XmlPullParserException;

import id.co.roxas.user.data.activation.core.dao.TblUserDao;
import id.co.roxas.user.data.activation.core.service.auth.CustomUserService;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
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
	
	 @Bean
	    public Docket api() throws IOException, XmlPullParserException {
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .select() 
	          .apis(RequestHandlerSelectors.basePackage("id.co.roxas.user.data.activation.core.controller"))
	          .paths(PathSelectors.regex("/.*"))                         
	          .build().apiInfo(new ApiInfo("Account Service Api Documentation", 
	        		  "Documentation automatically generated",
	        		  "1.1.1",
	        		  null, new Contact("Bima Satrya Sebayang", 
	        				    "bimasebayang11@gmail.com", "bimasebayang11@gmail.com"),
	        		  "no license needed", "no license needed"));                                           
	    }

}
