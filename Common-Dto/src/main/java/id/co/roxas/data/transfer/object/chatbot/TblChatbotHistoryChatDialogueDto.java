package id.co.roxas.data.transfer.object.chatbot;

import java.util.Date;

public class TblChatbotHistoryChatDialogueDto {
    private String dialogueId;
	private String groupDialogue;
	private String dialogueType;
	private String userChat;
	private Integer isChatbotKnow;
	private Integer isDialogueContinue;
	private Integer dialoguePosition;
	private Integer percentageUnderstand;
	private String chatbotRespond;
	private String chatbotKnowing;
	private String userQuestion;
	private Date createdDate;
	private Integer chatSequence;
	
	
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
	public Integer getChatSequence() {
		return chatSequence;
	}
	public void setChatSequence(Integer chatSequence) {
		this.chatSequence = chatSequence;
	}
	
	
	
}
