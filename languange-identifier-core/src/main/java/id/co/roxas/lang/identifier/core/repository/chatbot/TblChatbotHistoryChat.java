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
@Table(name="Tbl_Chatbot_History_Chat")
public class TblChatbotHistoryChat {

	@Id
	@Column(name="chat_id", length=100, updatable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chat_id")
    @GenericGenerator(
        name = "chat_id", 
        strategy = "id.co.roxas.lang.identifier.core.config.StringPrefixedSequenceIdGenerator", 
        parameters = {@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "100"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "CHATID"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%094d")}
        )
	private String chatId;
	
	@Column(name = "userChat")
	private String userChat;
	
	@Column(name = "is_chatbot_know")
	private int isChatbotKnow;
	
	@Column(name = "percentage_understand")
	private int percentageUnderstand;
	
	@Column(name = "chatbot_respond")
	private String chatbotRespond;
	
	@Column(name = "chatbot_knowing")
	private String chatbotKnowing;
	
	@Column(name = "user_question")
	private String userQuestion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	public String getChatId() {
		return chatId;
	}

	public void setChatId(String chatId) {
		this.chatId = chatId;
	}

	public String getUserChat() {
		return userChat;
	}

	public void setUserChat(String userChat) {
		this.userChat = userChat;
	}

	public int getIsChatbotKnow() {
		return isChatbotKnow;
	}

	public void setIsChatbotKnow(int isChatbotKnow) {
		this.isChatbotKnow = isChatbotKnow;
	}

	public String getChatbotRespond() {
		return chatbotRespond;
	}

	public void setChatbotRespond(String chatbotRespond) {
		this.chatbotRespond = chatbotRespond;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getPercentageUnderstand() {
		return percentageUnderstand;
	}

	public void setPercentageUnderstand(int percentageUnderstand) {
		this.percentageUnderstand = percentageUnderstand;
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
	
	
	
	
}
