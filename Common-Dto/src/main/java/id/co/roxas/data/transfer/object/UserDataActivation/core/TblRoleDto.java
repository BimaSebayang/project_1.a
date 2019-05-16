package id.co.roxas.data.transfer.object.UserDataActivation.core;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TblRoleDto implements Serializable{
 
	private static final long serialVersionUID = 1785211619650952599L;
	private static final String dtoTicketing = "1785211619650952599L";

	private String roleId;
	
	private String roleName;
	
	private int isActive;
	
	private Date dateActive;
	
	private Date dateNonActive;
	
	private Date createdDate;
	
	private Date updatedDate;
	
	List<TblRoleDtlDto> tblRoleDtls;
	
	private TblUserDto createdBy;
	
	private TblUserDto updatedBy;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public Date getDateActive() {
		return dateActive;
	}

	public void setDateActive(Date dateActive) {
		this.dateActive = dateActive;
	}

	public Date getDateNonActive() {
		return dateNonActive;
	}

	public void setDateNonActive(Date dateNonActive) {
		this.dateNonActive = dateNonActive;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<TblRoleDtlDto> getTblRoleDtls() {
		return tblRoleDtls;
	}

	public void setTblRoleDtls(List<TblRoleDtlDto> tblRoleDtls) {
		this.tblRoleDtls = tblRoleDtls;
	}

	public TblUserDto getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(TblUserDto createdBy) {
		this.createdBy = createdBy;
	}

	public TblUserDto getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(TblUserDto updatedBy) {
		this.updatedBy = updatedBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static String getDtoticketing() {
		return dtoTicketing;
	}
	
	
	
	
}
