package id.co.roxas.user.data.activation.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("my-trusted-website")
		.authorizedGrantTypes("password")
		.authorities("ROLE_CLIENT","ROLE_TRUSTED_CLIENT")
		.scopes("read","write","trust")
		.resourceIds("oauth2-resource")
		.accessTokenValiditySeconds(5000)
		.secret("{noop}lacking0309WebSite").refreshTokenValiditySeconds(5000)
		.and().withClient("my-trusted-service-neighbourhood")
		.authorizedGrantTypes("password")
		.authorities("ROLE_CLIENT","ROLE_TRUSTED_CLIENT")
		.scopes("read","write","trust")
		.resourceIds("oauth2-resource")
		.accessTokenValiditySeconds(5000)
		.secret("{noop}lacking0309Neighbour").refreshTokenValiditySeconds(5000)
		.and().withClient("my-trusted-client")
		.authorizedGrantTypes("password")
		.authorities("ROLE_CLIENT","ROLE_TRUSTED_CLIENT")
		.scopes("read","write","trust")
		.resourceIds("oauth2-resource")
		.accessTokenValiditySeconds(5000)
		.secret("{noop}secretClientsIsExist").refreshTokenValiditySeconds(5000);	
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
	}

	
	
}
