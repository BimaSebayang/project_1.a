package id.co.roxas.data.transfer.object.UserDataActivation.response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import id.co.roxas.data.transfer.object.UserDataActivation.BaseDto;
import id.co.roxas.data.transfer.object.UserDataActivation.custom.PageRequestCustom;
import id.co.roxas.data.transfer.object.shared.config.AuthorizationClassConf;

public class PageResponse extends BaseDto implements Serializable {
	private static final long serialVersionUID = -7189123300020116052L;
	private String wsContent;
	private String message;
	private int pageNumber;
	private int pageSize;
	private int totalPage;
	private long totalElements;
	private String sortBy;
	private Map<String, Object> filtering = new HashMap<String, Object>();
	private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

	public PageResponse() {

	}

	public PageResponse(Object wsContent, String message, int pageNumber, int pageSize, int totalPage,
			long totalElements, String sortBy, AuthorizationClassConf authorizationClassConf,
			Map<String, Object> filtering) {
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
		this.totalElements = totalElements;
		this.sortBy = sortBy;
		this.filtering = filtering;
	}

	public <T> PageResponse(PageRequestCustom<T> pageRequestCustom, 
			    AuthorizationClassConf authorizationClassConf,String message) {
		super();
		super.setUserAccess(authorizationClassConf.getUserAccess());
		super.setUuidConnector(authorizationClassConf.getRequestUuid());
		super.setModuleLog(authorizationClassConf.getModule());
		if (pageRequestCustom.getListResponse() != null) {
			this.wsContent = gson.toJson(pageRequestCustom.getListResponse());
		}
		this.message = message;
		this.pageNumber = pageRequestCustom.getPageNumbers();
		this.pageSize = pageRequestCustom.getPageSize();
		this.totalPage = pageRequestCustom.getTotalPages();
		this.totalElements = pageRequestCustom.getTotalElements();
		this.sortBy = pageRequestCustom.getSortBy();
		this.filtering = pageRequestCustom.getFiltering();
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

	public long getTotalElements() {
		return totalElements;
	}

	public String getSortBy() {
		return sortBy;
	}

	public Map<String, Object> getFiltering() {
		return filtering;
	}

}
