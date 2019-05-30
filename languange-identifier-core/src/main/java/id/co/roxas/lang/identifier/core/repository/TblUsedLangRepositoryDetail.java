package id.co.roxas.lang.identifier.core.repository;

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

import id.co.roxas.lang.identifier.core.config.StringPrefixedSequenceIdGenerator;


@Entity
@Table(name="Tbl_used_Lang_Repository_Dtl")
public class TblUsedLangRepositoryDetail {

	@Id
	@Column(name="lang_id_dtl", length=100, updatable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lang_id_dtl")
    @GenericGenerator(
        name = "lang_id_dtl", 
        strategy = "id.co.roxas.lang.identifier.core.config.StringPrefixedSequenceIdGenerator", 
        parameters = {@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "100"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "LDTL"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%096d")}
        )
	private String langIdDtl;
	
	@Column(name="lang_name", length=50, nullable=false)
	private String langName;
	
	@Column(columnDefinition = "text", name = "lang_desc", nullable=false)
	private String langDesc;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tbl_id", nullable=true,referencedColumnName = "lang_id")
	private TblLangRepositoryTemp tblId;
	
	@Column(name="lang_resource", length=100, nullable=true)
	private String langResource;
	
	@Column(name="role_detail", length=100,updatable=false)
	private String roleDetail;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(name = "created_by",length = 20)
	private String createdBy;

	public String getLangIdDtl() {
		return langIdDtl;
	}

	public void setLangIdDtl(String langIdDtl) {
		this.langIdDtl = langIdDtl;
	}

	public String getLangName() {
		return langName;
	}

	public void setLangName(String langName) {
		this.langName = langName;
	}

	public String getLangDesc() {
		return langDesc;
	}

	public void setLangDesc(String langDesc) {
		this.langDesc = langDesc;
	}

	public TblLangRepositoryTemp getTblId() {
		return tblId;
	}

	public void setTblId(TblLangRepositoryTemp tblId) {
		this.tblId = tblId;
	}

	public String getLangResource() {
		return langResource;
	}

	public void setLangResource(String langResource) {
		this.langResource = langResource;
	}

	public String getRoleDetail() {
		return roleDetail;
	}

	public void setRoleDetail(String roleDetail) {
		this.roleDetail = roleDetail;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	
	
}
