package id.co.roxas.lang.identifier.core.repository.chatbot;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import id.co.roxas.lang.identifier.core.config.StringPrefixedSequenceIdGenerator;

@Entity
@Table(name="Tbl_Lang_Respond_General_Dtl")
public class TblLangRespondGeneralDtl {

	@Id
	@Column(name="dtl_id", length=100, updatable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dtl_id")
    @GenericGenerator(
        name = "dtl_id", 
        strategy = "id.co.roxas.lang.identifier.core.config.StringPrefixedSequenceIdGenerator", 
        parameters = {@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "100"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "DTLID"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%095d")}
        )
	private String dtlId;
	
	@Column(name="general_respond_id", length=100, updatable=false)
	private String generalRespondId;
	
	@Column(name="general_respond", length=100, updatable=false)
	private String generalRespond;
	
	@Column(name="is_active")
	private int isActive;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date", nullable = true)
	private Date updatedDate;
	
	@Column(name="focus_words", nullable = true)
	private String focusWords;

	public String getDtlId() {
		return dtlId;
	}

	public void setDtlId(String dtlId) {
		this.dtlId = dtlId;
	}

	public String getGeneralRespondId() {
		return generalRespondId;
	}

	public void setGeneralRespondId(String generalRespondId) {
		this.generalRespondId = generalRespondId;
	}

	public String getGeneralRespond() {
		return generalRespond;
	}

	public void setGeneralRespond(String generalRespond) {
		this.generalRespond = generalRespond;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
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

	public String getFocusWords() {
		return focusWords;
	}

	public void setFocusWords(String focusWords) {
		this.focusWords = focusWords;
	}
	
	
	
	
}
