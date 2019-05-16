package id.co.roxas.data.transfer.object.UserDataActivation.response;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import id.co.roxas.data.transfer.object.UserDataActivation.BaseDto;
import id.co.roxas.data.transfer.object.UserDataActivation.config.AuthorizationClassConf;

public class PageResponse extends BaseDto implements Serializable{
	private static final long serialVersionUID = -7189123300020116052L;
	private String wsContent;
	private String message;
	private int pageNumber;
	private int pageSize;
	private int totalPage;
	private static final Gson gson = new GsonBuilder()
			   .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();
	public PageResponse(Object wsContent, String message, int pageNumber, int pageSize, int totalPage,
			             AuthorizationClassConf authorizationClassConf) {
		super();
		super.setUserAccess(authorizationClassConf.getUserAccess());
		super.setUuidConnector(authorizationClassConf.getRequestUuid());
		super.setModuleLog(authorizationClassConf.getModule());
		if (wsContent != null) {
			this.wsContent = gson.toJson(wsContent);
		}
		this.message = message;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
	}
	public String getWsContent() {
		return wsContent;
	}
	public void setWsContent(String wsContent) {
		this.wsContent = wsContent;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	
	
}
