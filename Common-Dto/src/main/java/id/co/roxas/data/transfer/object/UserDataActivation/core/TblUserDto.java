package id.co.roxas.data.transfer.object.UserDataActivation.core;

import java.io.Serializable;
import java.util.Date;

import id.co.roxas.data.transfer.object.UserDataActivation.BaseDto;

public class TblUserDto extends BaseDto implements Serializable {

	private static final long serialVersionUID = -6589020775819364118L;
	private static final String dtoTicketing = "6589020775819364118L";

	private String userId;
	private String userName;
	private TblRoleDto roleId;
	private String userBatch;
	private TblTicketDto userTicket;
	private int isActive;
	private String userEmail;
	private String userPhone;
	private String userPassword;
	private Date dateActive;
	private Date dateNonActive;
	private Date createdDate;
	private Date updatedDate;
	private TblUserDto createdBy;
	private TblUserDto updatedBy;
	
	public TblUserDto(String userId,String userName) {
		this.userId = userId;
		this.userName = userName;
	}
	
	public TblUserDto() {
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public TblRoleDto getRoleId() {
		return roleId;
	}
	public void setRoleId(TblRoleDto roleId) {
		this.roleId = roleId;
	}
	
	public TblTicketDto getUserTicket() {
		return userTicket;
	}
	public void setUserTicket(TblTicketDto userTicket) {
		this.userTicket = userTicket;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
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
	public String getUserBatch() {
		return userBatch;
	}
	public void setUserBatch(String userBatch) {
		this.userBatch = userBatch;
	}
	
	
	
	
	
}
