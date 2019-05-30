package id.co.roxas.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableZuulProxy
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableEurekaClient
@EnableEurekaServer
@EnableSwagger2
public class WebGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebGatewayApplication.class, args);
	}

}
