package id.co.roxas.lang.identifier.core;

import java.io.IOException;
import java.util.Collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.xmlpull.v1.XmlPullParserException;

import com.github.andrewoma.dexx.collection.ArrayList;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
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
