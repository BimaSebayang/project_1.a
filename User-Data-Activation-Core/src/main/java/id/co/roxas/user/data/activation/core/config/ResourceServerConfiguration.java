package id.co.roxas.user.data.activation.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/database-init/**", "/web-request/**", "/images/*", "/css/*", "/swagger-ui.js",
						"/swagger-ui.min.js", "/api-docs", "/swagger-ui.html", "/fonts/*", "/api-docs/*",
						"/api-docs/default/*", "/o2c.html", "index.html", "/webjars/**", "/hystrix/**",
						"**/swagger-resources/**", "/swagger-ui.html", "/v2/api-docs", "/webjars/**")
				.permitAll();
		http.authorizeRequests().anyRequest().authenticated();

	}

}
