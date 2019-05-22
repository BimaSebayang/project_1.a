package id.co.roxas.data.transfer.object.UserDataActivation.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageClassResponse<T> {
    private static final String PAGING_CODE = "Halaman ";
	private List<T> allDatas;
	private int page;
	private List<Map<String,Object>> totalPage;
	private Map<String,Object> filtering = new HashMap<String,Object>();
	
	public List<T> getAllDatas() {
		return allDatas;
	}
	public void setAllDatas(List<T> allDatas) {
		this.allDatas = allDatas;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public Map<String, Object> getFiltering() {
		return filtering;
	}
	public void setFiltering(Map<String, Object> filtering) {
		this.filtering = filtering;
	}
	public List<Map<String, Object>> getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		List<Map<String, Object>> mapPages = new ArrayList<Map<String, Object>>();
		for(int i = 1; i<= totalPage;i++) {
			Map<String, Object> mapPage = new HashMap<String, Object>();
			mapPage.put("Key", i-1);
			mapPage.put("Value", PAGING_CODE.concat(Integer.toString(i)));
			mapPages.add(mapPage);
		}
		this.totalPage = mapPages;
	}
	
    
	
	
}
