package id.co.roxas.user.data.activation.core.repository;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import id.co.roxas.user.data.activation.core.config.StringPrefixedSequenceIdGenerator;

@Entity
@Table(name = "Tbl_User")
public class TblUser {

	@Id
	@Column(name="user_id", length=20, updatable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id")
    @GenericGenerator(
        name = "user_id", 
        strategy = "id.co.roxas.user.data.activation.core.config.StringPrefixedSequenceIdGenerator", 
        parameters = {@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "20"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "USER"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%016d")}
        )
	private String userId;
	
	@Column(name="user_name", length=220, nullable=false)
	private String userName;
	
	@Column(name="user_batch", length=220, nullable=false, unique = true)
	private String userBatch;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", nullable = true)
	private TblRole roleId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_ticket", nullable = true,  referencedColumnName = "ticket_id")
	private TblTicket userTicket;
	
	@Column(name="is_active",nullable=false)
	private int isActive;
	
	@Column(name="user_email", length=100,nullable=false, unique=true)
	private String userEmail;
	
	@Column(name="user_phone", length=24,nullable=false, unique=true)
	@Size(max=24, min=10, message="user phone exceeded between 10 to 24")
	private String userPhone;
	
	@Column(name="user_password", length=100,nullable=false)
	private String userPassword;
	
	@Column(name = "date_active")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateActive;
	
	@Column(name = "date_non_active")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateNonActive;
	
	@Column(name = "created_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(name = "updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "created_by", nullable = true,  referencedColumnName = "user_id")
	private TblUser createdBy;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "updated_by", nullable = true, referencedColumnName = "user_id")
	private TblUser updatedBy;

	public TblUser(String userId) {
		this.userId = userId;
	}
	
	public TblUser() {
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

	public TblRole getRoleId() {
		return roleId;
	}

	public void setRoleId(TblRole roleId) {
		this.roleId = roleId;
	}

	public TblTicket getUserTicket() {
		return userTicket;
	}

	public void setUserTicket(TblTicket userTicket) {
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

	public TblUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(TblUser createdBy) {
		this.createdBy = createdBy;
	}

	public TblUser getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(TblUser updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUserBatch() {
		return userBatch;
	}

	public void setUserBatch(String userBatch) {
		this.userBatch = userBatch;
	}

	
	
	
	
}
