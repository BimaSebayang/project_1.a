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

/*
 *  berisi question-question yang akan diberikan user tapi chatbot salah memberikan respond,
 */
@Entity
@Table(name="Tbl_Chatbot_Mistake_Answer")
public class TblChatbotMistakeAnswer {
	@Id
	@Column(name="mistake_id", length=100, updatable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mistake_id")
    @GenericGenerator(
        name = "mistake_id", 
        strategy = "id.co.roxas.lang.identifier.core.config.StringPrefixedSequenceIdGenerator", 
        parameters = {@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "100"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "MISSID"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%096d")}
        )
	private String mistake_id;
	
	@Column(name="user_question", updatable=false)
	private String userQuestion;
	
	@Column(name="chatbot_respond", updatable=false) //diambil dari table TBL_CHATBOT_RESPOND_KNOWLEDGE
	private String chatbotRespond;
	
	@Column(name="respond_id", updatable=false) //diambil dari table TBL_CHATBOT_RESPOND_KNOWLEDGE
	private String respondId;
	
	@Column(name="chatbot_question", updatable=false)//diambil dari table TBL_CHATBOT_QUESTION_KNOWLEDGE
	private String chatbotQuestion;
	
	@Column(name="question_id", updatable=false) //diambil dari table TBL_CHATBOT_QUESTION_KNOWLEDGE
	private String questionId;
	
	@Column(name="dialogue_id", updatable=false)  //diambil dari table TBL_CHAT_HISTORY_CHAT_DIALOGUE
	private String dialogueId;
	
	@Column(name="sugestion_answer", updatable=false)
	private String sugestionAnswer;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date", nullable = false, updatable = false)
	private Date createdDate;
	
	@Column(name="created_by",  nullable = false, updatable=false)
	private String createdBy; //aka user yang memberikan sugestion baik dari chat atau pun unek-unek

	public String getMistake_id() {
		return mistake_id;
	}

	public void setMistake_id(String mistake_id) {
		this.mistake_id = mistake_id;
	}

	public String getUserQuestion() {
		return userQuestion;
	}

	public void setUserQuestion(String userQuestion) {
		this.userQuestion = userQuestion;
	}

	public String getChatbotRespond() {
		return chatbotRespond;
	}

	public void setChatbotRespond(String chatbotRespond) {
		this.chatbotRespond = chatbotRespond;
	}

	public String getRespondId() {
		return respondId;
	}

	public void setRespondId(String respondId) {
		this.respondId = respondId;
	}

	public String getChatbotQuestion() {
		return chatbotQuestion;
	}

	public void setChatbotQuestion(String chatbotQuestion) {
		this.chatbotQuestion = chatbotQuestion;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getDialogueId() {
		return dialogueId;
	}

	public void setDialogueId(String dialogueId) {
		this.dialogueId = dialogueId;
	}

	public String getSugestionAnswer() {
		return sugestionAnswer;
	}

	public void setSugestionAnswer(String sugestionAnswer) {
		this.sugestionAnswer = sugestionAnswer;
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
