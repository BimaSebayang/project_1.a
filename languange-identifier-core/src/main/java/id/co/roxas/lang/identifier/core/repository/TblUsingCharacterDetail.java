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
@Table(name="Tbl_Using_Character_Detail")
public class TblUsingCharacterDetail {

	@Id
	@Column(name="char_id", length=100, updatable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "char_id")
    @GenericGenerator(
        name = "char_id", 
        strategy = "id.co.roxas.lang.identifier.core.config.StringPrefixedSequenceIdGenerator", 
        parameters = {@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "100"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "CHAR"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%096d")}
        )
	private String charId;
	
	@Column(name="used_char", length=100, updatable=false)
	private String usedChar;
	
	@Column(name="role_char", length=100,updatable=false)
	private String roleChar;
	
	@Column(name="created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(name = "created_by",length = 20)
	private String createdBy;
	
	@Column(name = "isActive",length = 20)
	private int isActive;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "comb_id",nullable=true,referencedColumnName = "comb_id")
	private TblCombinationWordRepository combId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "lang_id", nullable=true,referencedColumnName = "lang_id")
	private TblLangRepositoryTemp langId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "used_lang_id", nullable=true,referencedColumnName = "lang_id")
	private TblUsedLangRepository usedLangId;

	public String getCharId() {
		return charId;
	}

	public void setCharId(String charId) {
		this.charId = charId;
	}

	public String getUsedChar() {
		return usedChar;
	}

	public void setUsedChar(String usedChar) {
		this.usedChar = usedChar;
	}

	public TblCombinationWordRepository getCombId() {
		return combId;
	}

	public void setCombId(TblCombinationWordRepository combId) {
		this.combId = combId;
	}

	public String getRoleChar() {
		return roleChar;
	}

	public void setRoleChar(String roleChar) {
		this.roleChar = roleChar;
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

	public TblLangRepositoryTemp getLangId() {
		return langId;
	}

	public void setLangId(TblLangRepositoryTemp langId) {
		this.langId = langId;
	}

	public TblUsedLangRepository getUsedLangId() {
		return usedLangId;
	}

	public void setUsedLangId(TblUsedLangRepository usedLangId) {
		this.usedLangId = usedLangId;
	}


	
	
	
}
