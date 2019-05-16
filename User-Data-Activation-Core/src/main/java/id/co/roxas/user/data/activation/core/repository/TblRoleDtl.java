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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import id.co.roxas.user.data.activation.core.config.StringPrefixedSequenceIdGenerator;

@Entity
@Table(name="Tbl_Role_Dtl")
public class TblRoleDtl {
	@Id
	@Column(name="role_dtl_id", length=23, updatable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_dtl_id")
    @GenericGenerator(
        name = "role_dtl_id", 
        strategy = "id.co.roxas.user.data.activation.core.config.StringPrefixedSequenceIdGenerator", 
        parameters = {@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "23"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "ROLEDTL"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%016d")}
    )
	private String roleDtlId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", nullable = true)
	private TblRole roleId;
	
	@Column(name="role_dtl_name",length=20, updatable=false)
	private String roleDtlName;
	
	@Column(name="is_active")
	private int isActive;
	
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
	
	@Column(name = "role_dtl_func", nullable = false, length=20)
	private String roleDtlFunc;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "created_by", nullable = true)
	private TblUser createdBy;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "updated_by", nullable = true)
	private TblUser updatedBy;
	
	public String getRoleDtlId() {
		return roleDtlId;
	}

	public void setRoleDtlId(String roleDtlId) {
		this.roleDtlId = roleDtlId;
	}

	public TblRole getRoleId() {
		return roleId;
	}

	public void setRoleId(TblRole roleId) {
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
	
	
	
	
}
