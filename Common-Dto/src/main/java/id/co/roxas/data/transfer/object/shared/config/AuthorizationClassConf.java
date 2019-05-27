package id.co.roxas.data.transfer.object.shared.config;

public class AuthorizationClassConf {
    private String[] requestUuid;
    private String[] realUuid;
    private String[] authorizationUser;
    private String module;
    private String userAccess;
    
    public AuthorizationClassConf(String[] requestUuid,String[] realUuid, String module, String userAccess) {
    	this.realUuid = realUuid;
    	this.requestUuid = requestUuid;
    	this.module = module;
    	this.userAccess = userAccess;
    }
    
    public AuthorizationClassConf(String[] requestUuid,String[] realUuid,String[] authorizationUser,
    		String module,String userAccess) {
    	this.realUuid = realUuid;
    	this.requestUuid = requestUuid;
    	this.authorizationUser = authorizationUser;
    	this.module = module;
    	this.userAccess = userAccess;
    }

    
    
	public String getUserAccess() {
		return userAccess;
	}

	public String getModule() {
		return module;
	}


	public String[] getRequestUuid() {
		return requestUuid;
	}

	public String[] getRealUuid() {
		return realUuid;
	}

	public String[] getAuthorizationUser() {
		return authorizationUser;
	}

    
    
}
