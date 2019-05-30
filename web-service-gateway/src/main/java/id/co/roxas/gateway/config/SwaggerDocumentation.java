package id.co.roxas.gateway.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Component
@Primary
@EnableAutoConfiguration
public class SwaggerDocumentation implements SwaggerResourcesProvider{

    @Override
    public List<SwaggerResource> get() {
    	List<SwaggerResource> resources = new ArrayList<>();
        resources.add(swaggerResource("languange-service", "/languange/v2/api-docs", "1.1.1"));
        resources.add(swaggerResource("uaa-service", "/uaa/v2/api-docs", "1.1.1"));
        return resources;
    }
 
    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}
