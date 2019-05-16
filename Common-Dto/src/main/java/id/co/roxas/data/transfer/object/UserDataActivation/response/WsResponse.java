package id.co.roxas.data.transfer.object.UserDataActivation.response;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import id.co.roxas.data.transfer.object.UserDataActivation.BaseDto;
import id.co.roxas.data.transfer.object.UserDataActivation.config.AuthorizationClassConf;

public class WsResponse extends BaseDto implements Serializable {
	private String wsContent;
	private static final long serialVersionUID = -4409204758516116774L;
	private static final Gson gson = new GsonBuilder()
			   .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
	private String message;

	public WsResponse() {
		// TODO Auto-generated constructor stub
	}
	
	// Constructor with validation Authorization
	public WsResponse(Object wsContent, String message, AuthorizationClassConf authorizationClassConf) {
		super();
		super.setUserAccess(authorizationClassConf.getUserAccess());
		super.setUuidConnector(authorizationClassConf.getRequestUuid());
		super.setModuleLog(authorizationClassConf.getModule());
		if (wsContent != null) {
			this.wsContent = gson.toJson(wsContent);
		}
		this.message = message;
	}

	//'yyyy-MM-dd'T'HH:mm:ss.SSSZ'
	
	// Constructor without validation Authorization
	public WsResponse(Object wsContent, String message) {
		if (wsContent != null) {
			this.wsContent = gson.toJson(wsContent);
		}
		this.message = message;
	}

	public String getWsContent() {
		return wsContent;
	}

	public String getMessage() {
		return message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
