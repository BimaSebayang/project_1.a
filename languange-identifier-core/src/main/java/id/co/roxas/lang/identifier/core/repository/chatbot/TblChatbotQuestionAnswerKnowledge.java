package id.co.roxas.lang.identifier.core.repository.chatbot;

import java.util.Date;

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

/*
 *  berisi question-question yang akan diberikan user 
 *  untuk memanggil respond pada TblChatbotRespondKnowledge
 *  untuk user tertentu
 */
@Entity
@Table(name="Tbl_Chatbot_Question_Answer_Knowledge")
public class TblChatbotQuestionAnswerKnowledge {
	@Id
	@Column(name="question_id", length=100, updatable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_id")
    @GenericGenerator(
        name = "question_id", 
        strategy = "id.co.roxas.lang.identifier.core.config.StringPrefixedSequenceIdGenerator", 
        parameters = {@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "100"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "QUESID"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%094d")}
        )
	private String questionId;
	
	
	@Column(name="question_user")
	private String questionUser;
	
	@Column(name="focus_words")
	private String focusWords;
	
	@Column(name = "is_for_dialogue")  //0 jika tidak, 1 jika iya
	private Integer isForDialogue;
	
	@Column(name = "group_dialogue") // null jika isForDialogue 0
	private String groupDialogue;
	
	@Column(name = "dialogue_position")
	private Integer dialoguePosition;
	
	@Column(name="is_transaction")
	private Integer isTransaction;
	
	@Column(name="chatbot_respond", nullable = false)
	private String chatbotRespond;  
	
	@Column(name = "is_active")
	private Integer isActive;
	
	@Column(name="user_delegate")
	private String userDelegate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="updated_by")
	private String updatedBy;

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestionUser() {
		return questionUser;
	}

	public void setQuestionUser(String questionUser) {
		this.questionUser = questionUser;
	}

	public String getFocusWords() {
		return focusWords;
	}

	public void setFocusWords(String focusWords) {
		this.focusWords = focusWords;
	}

	public Integer getIsForDialogue() {
		return isForDialogue;
	}

	public void setIsForDialogue(Integer isForDialogue) {
		this.isForDialogue = isForDialogue;
	}

	public String getGroupDialogue() {
		return groupDialogue;
	}

	public void setGroupDialogue(String groupDialogue) {
		this.groupDialogue = groupDialogue;
	}

	public Integer getDialoguePosition() {
		return dialoguePosition;
	}

	public void setDialoguePosition(Integer dialoguePosition) {
		this.dialoguePosition = dialoguePosition;
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

	public String getUserDelegate() {
		return userDelegate;
	}

	public void setUserDelegate(String userDelegate) {
		this.userDelegate = userDelegate;
	}

	public Integer getIsTransaction() {
		return isTransaction;
	}

	public void setIsTransaction(Integer isTransaction) {
		this.isTransaction = isTransaction;
	}

	public String getChatbotRespond() {
		return chatbotRespond;
	}

	public void setChatbotRespond(String chatbotRespond) {
		this.chatbotRespond = chatbotRespond;
	}
	
	
	
}
