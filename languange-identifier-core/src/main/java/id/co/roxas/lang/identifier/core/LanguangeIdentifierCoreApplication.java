package id.co.roxas.lang.identifier.core;

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

import id.co.roxas.lang.identifier.core.service.auth.CustomUserService;
import id.co.roxas.lang.identifier.core.service.auth.UserAuthenticationCustomLogin;
import springfox.documentation.builders.ApiInfoBuilder;
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
public class LanguangeIdentifierCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(LanguangeIdentifierCoreApplication.class, args);
	}
	
	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserAuthenticationCustomLogin userAuthenticationCustomLogin) throws Exception {
	   builder.userDetailsService(new UserDetailsService() {
		@Override
		public UserDetails loadUserByUsername(String userValidation) throws UsernameNotFoundException {
			return new CustomUserService(userAuthenticationCustomLogin.findByUserTicketOrUserEmailOrUserUserPhoneOrUserId(userValidation));
		}
	})	;
	}
	
    @Bean
    public Docket api() throws IOException, XmlPullParserException {
  
        return new Docket(DocumentationType.SWAGGER_2)  
          .select() 
          .apis(RequestHandlerSelectors.basePackage("id.co.roxas.lang.identifier.core.controller"))
          .paths(PathSelectors.regex("/.*"))                         
          .build().apiInfo(apiEndPointsInfo());                                           
    }
    
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Languange Identifier Api Documentation")
            .description("Languange for chat-bot, synonims, etc REST API")
            .contact(new Contact("Bima Satrya Sebayang", 
				    "bimasebayang11@gmail.com", "bimasebayang11@gmail.com"))
            .license(null)
            .licenseUrl(null)
            .version("1.1.1")
            .build();
    }

}
