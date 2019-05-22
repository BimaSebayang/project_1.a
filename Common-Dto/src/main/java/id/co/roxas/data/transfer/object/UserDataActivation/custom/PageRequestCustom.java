package id.co.roxas.data.transfer.object.UserDataActivation.custom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageRequestCustom<T> {
  
	private List<T> listResponse;
	private int pageSize;
	private int totalPages;
	private int pageNumbers;
	private long totalElements;
	private String sortBy;
	private Map<String,Object> filtering = new HashMap<String,Object>();
	public PageRequestCustom(List<T> listResponse, int pageSize, int totalPages, int pageNumbers, 
			long totalElements, String sortBy,Map<String,Object> filtering) {
		this.listResponse = listResponse;
		this.pageSize = pageSize;
		this.totalPages = totalPages;
		this.pageNumbers = pageNumbers;
		this.sortBy = sortBy;
		this.filtering = filtering;
	}
	public List<T> getListResponse() {
		return listResponse;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public int getPageNumbers() {
		return pageNumbers;
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
	public void setFiltering(Map<String, Object> filtering) {
		this.filtering = filtering;
	}
	
	
	
}
