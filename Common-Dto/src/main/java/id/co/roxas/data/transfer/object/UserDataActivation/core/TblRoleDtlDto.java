package id.co.roxas.data.transfer.object.UserDataActivation.core;

import java.io.Serializable;
import java.util.Date;


public class TblRoleDtlDto implements Serializable{

	private static final long serialVersionUID = 4938568452596411235L;
	private static final String dtoTicketing = "4938568452596411235L";
	
	private String roleDtlId;
	
	private TblRoleDto roleId;
	
	private String roleDtlName;
	
	private int isActive;
	
	private Date dateActive;
	
	private Date dateNonActive;
	
	private Date createdDate;
	
	private Date updatedDate;
	
	private String roleDtlFunc;

	private TblUserDto createdBy;
	
	private TblUserDto updatedBy;

	public String getRoleDtlId() {
		return roleDtlId;
	}

	public void setRoleDtlId(String roleDtlId) {
		this.roleDtlId = roleDtlId;
	}

	

	public TblRoleDto getRoleId() {
		return roleId;
	}

	public void setRoleId(TblRoleDto roleId) {
		this.roleId = roleId;
	}

	public String getRoleDtlName() {
		return roleDtlName;
	}

	public void setRoleDtlName(String roleDtlName) {
		this.roleDtlName = roleDtlName;
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

	public String getRoleDtlFunc() {
		return roleDtlFunc;
	}

	public void setRoleDtlFunc(String roleDtlFunc) {
		this.roleDtlFunc = roleDtlFunc;
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
