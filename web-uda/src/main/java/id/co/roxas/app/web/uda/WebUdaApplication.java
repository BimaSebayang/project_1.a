package id.co.roxas.app.web.uda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@SpringBootApplication
@EnableDiscoveryClient
public class WebUdaApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(WebUdaApplication.class, args);
	}
}
