package id.co.roxas.lang.identifier.core.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import id.co.roxas.lang.identifier.core.config.StringPrefixedSequenceIdGenerator;

@Entity
@Table(name="Tbl_Lang_Repository")
public class TblLangRepositoryTemp {

	@Id
	@Column(name="lang_id", length=100, updatable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lang_id")
    @GenericGenerator(
        name = "lang_id", 
        strategy = "id.co.roxas.lang.identifier.core.config.StringPrefixedSequenceIdGenerator", 
        parameters = {@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "100"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "LANG"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%096d")}
        )
	private String langId;
	
	@Column(name="lang_name", length=50, nullable=false)
	private String langName;
	
	@Column(columnDefinition = "text", name = "lang_desc", nullable=false)
	private String langDesc;
	
	@Column(name="lang_resource", length=100, nullable=true)
	private String langResource;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(name = "created_by",length = 20)
	private String createdBy;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "langId")
	List<TblUsingCharacterDetail> tblUsingCharacterDetails;
	
	
	
	public String getLangId() {
		return langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
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

	public String getLangResource() {
		return langResource;
	}

	public void setLangResource(String langResource) {
		this.langResource = langResource;
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

	public List<TblUsingCharacterDetail> getTblUsingCharacterDetails() {
		return tblUsingCharacterDetails;
	}

	public void setTblUsingCharacterDetails(List<TblUsingCharacterDetail> tblUsingCharacterDetails) {
		this.tblUsingCharacterDetails = tblUsingCharacterDetails;
	}

	
	
	
	
}
