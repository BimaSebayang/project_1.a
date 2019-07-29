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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import id.co.roxas.lang.identifier.core.config.StringPrefixedSequenceIdGenerator;


//berisi fucntion-function unik untuk melakukan transaksi (edit, add, and delete) dan query
@Entity
@Table(name="Tbl_Chatbot_General_Knowledge")
public class TblChatbotGeneralKnowledge {
	@Id
	@Column(name="knowledge_id", length=100, updatable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "knowledge_id")
    @GenericGenerator(
        name = "knowledge_id", 
        strategy = "id.co.roxas.lang.identifier.core.config.StringPrefixedSequenceIdGenerator", 
        parameters = {@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "100"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "#DIALID#"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%092d")}
        )
	private String knowledgeId;
	
	@Column(columnDefinition = "text", name = "transaction_func", nullable=false)
	private String transactionFunc;
	
	@Column(name="transaction_type", length=10)
	private String transactionType; //update, delete, query, insert
	

	@Column(name = "isActive", nullable = false)
	private Integer isActive;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", nullable = false)
	private Date createdDate;
	
	@Column(name = "created_by", nullable = false)
	private String createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date", nullable = true)
	private Date updatedDate;
	
	@Column(name = "updated_by", nullable = true)
	private String updatedBy;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "knowledgeId")
    private List<TblChatbotTransactionParamDtl> tblChatbotTransactionParamDtls;

	public String getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(String knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

	public String getTransactionFunc() {
		return transactionFunc;
	}

	public void setTransactionFunc(String transactionFunc) {
		this.transactionFunc = transactionFunc;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

    

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
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

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public List<TblChatbotTransactionParamDtl> getTblChatbotTransactionParamDtls() {
		return tblChatbotTransactionParamDtls;
	}

	public void setTblChatbotTransactionParamDtls(List<TblChatbotTransactionParamDtl> tblChatbotTransactionParamDtls) {
		this.tblChatbotTransactionParamDtls = tblChatbotTransactionParamDtls;
	}
	
	
	
}
