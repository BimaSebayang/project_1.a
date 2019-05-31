package id.co.roxas.data.transfer.object.UserDataActivation.model;

import java.util.Date;

//please register all your needed param/ filter/ search/ or informational addition from *.index file here.
public class PageRevolver {
     private String roleId;
     private String isActive;
     private Date startDate;
     private Date endDate;
     private String page;
     private String size;
     private String[] sort;
     private String search;
     private String roleDtlId;
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String[] getSort() {
		return sort;
	}
	public void setSort(String[] sort) {
		this.sort = sort;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getRoleDtlId() {
		return roleDtlId;
	}
	public void setRoleDtlId(String roleDtlId) {
		this.roleDtlId = roleDtlId;
	}
    
}
