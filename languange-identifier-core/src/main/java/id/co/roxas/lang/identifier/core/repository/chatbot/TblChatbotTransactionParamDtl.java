package id.co.roxas.lang.identifier.core.repository.chatbot;

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

import id.co.roxas.lang.identifier.core.config.StringPrefixedSequenceIdGenerator;

@Entity
@Table(name="Tbl_Chatbot_Transaction_Param_Dtl")
public class TblChatbotTransactionParamDtl {
	@Id
	@Column(name="param_id", length=100, updatable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "param_id")
    @GenericGenerator(
        name = "param_id", 
        strategy = "id.co.roxas.lang.identifier.core.config.StringPrefixedSequenceIdGenerator", 
        parameters = {@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "100"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "#TRANS#"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%093d")}
        )
	private String paramId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "knowledge_id", nullable = true)
	private TblChatbotGeneralKnowledge knowledgeId;
	
	@Column(name = "parameter", nullable = false)
	private String parameter;
	
	@Column(name = "parameter_reference", nullable = false)
	private String paramReference;
	
	@Column(name = "parameter_type", nullable = false)
	private String parameterType;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false)
	private Date createdDate;
	
	@Column(name = "created_by", nullable = false)
	private String createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "updated_by")
	private Date updatedBy;

	@Column(name = "hardcode_parameter")
	private String hardcodeParameter;
	
	
	
	public String getParameterType() {
		return parameterType;
	}

	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}


	public String getParamId() {
		return paramId;
	}

	public void setParamId(String paramId) {
		this.paramId = paramId;
	}

	public TblChatbotGeneralKnowledge getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(TblChatbotGeneralKnowledge knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getParamReference() {
		return paramReference;
	}

	public void setParamReference(String paramReference) {
		this.paramReference = paramReference;
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

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Date updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getHardcodeParameter() {
		return hardcodeParameter;
	}

	public void setHardcodeParameter(String hardcodeParameter) {
		this.hardcodeParameter = hardcodeParameter;
	}

	
	
	
}
