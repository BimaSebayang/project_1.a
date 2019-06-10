package id.co.roxas.user.data.activation.core.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import id.co.roxas.user.data.activation.core.config.StringPrefixedSequenceIdGenerator;

@Entity
@Table(name="Tbl_Role")
public class TblRole {
	@Id
	@Column(name="role_id", length=20, updatable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id")
    @GenericGenerator(
        name = "role_id", 
        strategy = "id.co.roxas.user.data.activation.core.config.StringPrefixedSequenceIdGenerator", 
        parameters = {@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "20"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "ROLE"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%016d")}
        )
	private String roleId;
	
	@Column(name="role_name",length=20, unique=true)
	private String roleName;
	
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "roleId")
	List<TblRoleDtl> tblRoleDtls;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "created_by", nullable = true)
	private TblUser createdBy;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "updated_by", nullable = true)
	private TblUser updatedBy;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "roleId")
	List<TblUser> tblUsers;

	public TblRole() {
		// TODO Auto-generated constructor stub
	}
	
	public TblRole(String roleId) {
		this.roleId = roleId;
	}
	
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

	public List<TblRoleDtl> getTblRoleDtls() {
		return tblRoleDtls;
	}

	public void setTblRoleDtls(List<TblRoleDtl> tblRoleDtls) {
		this.tblRoleDtls = tblRoleDtls;
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

	public List<TblUser> getTblUsers() {
		return tblUsers;
	}

	public void setTblUsers(List<TblUser> tblUsers) {
		this.tblUsers = tblUsers;
	}
	
	
	
	
}
