package id.co.roxas.data.transfer.object.UserDataActivation;

import java.util.Date;


public class BaseDto {
	
    private String userAccess;
    private Date timestamp = new Date();
    private String moduleLog;
    private String[] uuidConnector;
    
	public String[] getUuidConnector() {
		return uuidConnector;
	}

	public void setUuidConnector(String[] uuidConnector) {
		this.uuidConnector = uuidConnector;
	}

	public String getUserAccess() {
		return userAccess;
	}

	public void setUserAccess(String userAccess) {
		this.userAccess = userAccess;
	}

	public String getModuleLog() {
		return moduleLog;
	}

	public void setModuleLog(String moduleLog) {
		this.moduleLog = moduleLog;
	}

	public Date getTimestamp() {
		return timestamp;
	}
   
}
