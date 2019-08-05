package id.co.roxas.data.transfer.object.chatbot.respond;

import java.io.Serializable;
import java.util.Date;

public class ChatbotDialogueUiDto implements Serializable{
    
	private static final long serialVersionUID = -8153617862461448549L;
	private static final String dtoTicketing = "-8153617862461448549L";
	private String text;
    private Boolean isOutgoing;
    private Boolean isIncoming;
    private String chatDate;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Boolean getIsOutgoing() {
		return isOutgoing;
	}
	public void setIsOutgoing(Boolean isOutgoing) {
		this.isOutgoing = isOutgoing;
	}
	public Boolean getIsIncoming() {
		return isIncoming;
	}
	public void setIsIncoming(Boolean isIncoming) {
		this.isIncoming = isIncoming;
	}
	
	public String getChatDate() {
		return chatDate;
	}
	public void setChatDate(String chatDate) {
		this.chatDate = chatDate;
	}
	public static String getDtoticketing() {
		return dtoTicketing;
	}
	
	
    
    
    
}
