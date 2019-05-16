package id.co.roxas.app.web.uda.config;

public class HttpSecurityService {
    private String uuidConnectorBody;
    private String uuidConnectorResponse;
    private String module;
    private final String contentType = "application/json";
    
	public HttpSecurityService(String uuidConnectorBody, String uuidConnectorResponse, String module) {
		this.uuidConnectorBody = uuidConnectorBody;
		this.uuidConnectorResponse = uuidConnectorResponse;
		this.module = module;
	}

	public String getUuidConnectorBody() {
		return uuidConnectorBody;
	}

	public String getUuidConnectorResponse() {
		return uuidConnectorResponse;
	}

	public String getModule() {
		return module;
	}

	public String getContentType() {
		return contentType;
	}
    
    
    
    
}
