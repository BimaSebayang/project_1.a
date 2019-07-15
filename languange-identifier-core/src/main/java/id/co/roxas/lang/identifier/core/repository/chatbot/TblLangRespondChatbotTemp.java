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
@Table(name="Tbl_Lang_Respond_Chatbot")
public class TblLangRespondChatbotTemp {

	@Id
	@Column(name="respond_id", length=100, updatable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "respond_id")
    @GenericGenerator(
        name = "respond_id", 
        strategy = "id.co.roxas.lang.identifier.core.config.StringPrefixedSequenceIdGenerator", 
        parameters = {@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "100"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "RESPOND"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%093d")}
        )
    private String respondId;
	
	@Column(name="user_owner", length=100, updatable=false)
	private String userOwner;
	
	@Column(name="general_respond_id", length=100, updatable=false)
	private String generalRespondId;
	
	@Column(name="output_respond")
	private String outputRespond;
	
	@Column(name="is_active")
	private int isActive;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date", nullable = true)
	private Date updatedDate;

	public String getRespondId() {
		return respondId;
	}

	public void setRespondId(String respondId) {
		this.respondId = respondId;
	}

	public String getUserOwner() {
		return userOwner;
	}

	public void setUserOwner(String userOwner) {
		this.userOwner = userOwner;
	}

	public String getGeneralRespondId() {
		return generalRespondId;
	}

	public void setGeneralRespondId(String generalRespondId) {
		this.generalRespondId = generalRespondId;
	}

	public String getOutputRespond() {
		return outputRespond;
	}

	public void setOutputRespond(String outputRespond) {
		this.outputRespond = outputRespond;
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
	
	

	
}
