package id.co.roxas.data.transfer.object.chatbot.respond;

import java.io.Serializable;

public class ChatRespondDto implements Serializable{
	
	private static final long serialVersionUID = 2355684463803010656L;
	private static final String dtoTicketing = "2355684463803010656L";
	private String chatbotRespond;
	private String userAsking;
	private String chatbotKnowing;
	private int percentage;
	private long responseTime;
	public String getChatbotRespond() {
		return chatbotRespond;
	}
	public void setChatbotRespond(String chatbotRespond) {
		this.chatbotRespond = chatbotRespond;
	}
	public String getUserAsking() {
		return userAsking;
	}
	public void setUserAsking(String userAsking) {
		this.userAsking = userAsking;
	}
	public String getChatbotKnowing() {
		return chatbotKnowing;
	}
	public void setChatbotKnowing(String chatbotKnowing) {
		this.chatbotKnowing = chatbotKnowing;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	
	public long getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}
	public static String getDtoticketing() {
		return dtoTicketing;
	}
	
	

}
