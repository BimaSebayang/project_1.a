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

//berisi fucntion-function unik untuk menyimpan transaksi dialog yang telah dilakukan oleh chatbot dan user
@Entity
@Table(name="Tbl_Chatbot_History_Chat_Dialogue")
public class TblChatbotHistoryChatDialogue {
	@Id
	@Column(name="dialogue_id", length=100, updatable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dialogue_id")
    @GenericGenerator(
        name = "dialogue_id", 
        strategy = "id.co.roxas.lang.identifier.core.config.StringPrefixedSequenceIdGenerator", 
        parameters = {@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "100"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "DIALID"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%094d")}
        )
	private String dialogueId;
	
	@Column(name = "group_dialogue")
	private String groupDialogue;
	
	//saat ini hanya untuk transaction(edit, add, and delete) dan respond (query and short answer)
	@Column(name = "dialogue_type")  
	private String dialogueType;
	
	@Column(name = "userChat")
	private String userChat;
	
	@Column(name = "is_chatbot_know")
	private Integer isChatbotKnow;
	
	@Column(name = "is_dialogue_continue")
	private Integer isDialogueContinue;
	
	@Column(name = "dialogue_position")
	private Integer dialoguePosition;
	
	@Column(name = "percentage_understand")
	private Integer percentageUnderstand;
	
	@Column(name = "chatbot_respond")
	private String chatbotRespond;
	
	@Column(name = "chatbot_knowing")
	private String chatbotKnowing;
	
	@Column(name = "user_question")
	private String userQuestion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="chat_sequence", nullable = false)
	private Integer chatSequence;

	
	
	public Integer getChatSequence() {
		return chatSequence;
	}

	public void setChatSequence(Integer chatSequence) {
		this.chatSequence = chatSequence;
	}

	public String getDialogueId() {
		return dialogueId;
	}

	public void setDialogueId(String dialogueId) {
		this.dialogueId = dialogueId;
	}

	public String getGroupDialogue() {
		return groupDialogue;
	}

	public void setGroupDialogue(String groupDialogue) {
		this.groupDialogue = groupDialogue;
	}

	public String getDialogueType() {
		return dialogueType;
	}

	public void setDialogueType(String dialogueType) {
		this.dialogueType = dialogueType;
	}

	public String getUserChat() {
		return userChat;
	}

	public void setUserChat(String userChat) {
		this.userChat = userChat;
	}

	public Integer getIsChatbotKnow() {
		return isChatbotKnow;
	}

	public void setIsChatbotKnow(Integer isChatbotKnow) {
		this.isChatbotKnow = isChatbotKnow;
	}

	public Integer getIsDialogueContinue() {
		return isDialogueContinue;
	}

	public void setIsDialogueContinue(Integer isDialogueContinue) {
		this.isDialogueContinue = isDialogueContinue;
	}

	public Integer getDialoguePosition() {
		return dialoguePosition;
	}

	public void setDialoguePosition(Integer dialoguePosition) {
		this.dialoguePosition = dialoguePosition;
	}

	public Integer getPercentageUnderstand() {
		return percentageUnderstand;
	}

	public void setPercentageUnderstand(Integer percentageUnderstand) {
		this.percentageUnderstand = percentageUnderstand;
	}

	public String getChatbotRespond() {
		return chatbotRespond;
	}

	public void setChatbotRespond(String chatbotRespond) {
		this.chatbotRespond = chatbotRespond;
	}

	public String getChatbotKnowing() {
		return chatbotKnowing;
	}

	public void setChatbotKnowing(String chatbotKnowing) {
		this.chatbotKnowing = chatbotKnowing;
	}

	public String getUserQuestion() {
		return userQuestion;
	}

	public void setUserQuestion(String userQuestion) {
		this.userQuestion = userQuestion;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	
	
}
