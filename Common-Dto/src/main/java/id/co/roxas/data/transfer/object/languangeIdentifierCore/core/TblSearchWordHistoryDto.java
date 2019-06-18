package id.co.roxas.data.transfer.object.languangeIdentifierCore.core;

import java.io.Serializable;
import java.util.Date;


public class TblSearchWordHistoryDto implements Serializable{

	private static final long serialVersionUID = -5477194471802851161L;
	private static final String dtoTicketing = "-5477194471802851161L";
	private String historyId;
	private String searchWord;
	private String userId;
	private int existingMeaning;
	private int needMeaning;
	private Date createdDate; 
	private String meaningSearcher;
	private Date meaningFindDate;
	public String getHistoryId() {
		return historyId;
	}
	public void setHistoryId(String historyId) {
		this.historyId = historyId;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getExistingMeaning() {
		return existingMeaning;
	}
	public void setExistingMeaning(int existingMeaning) {
		this.existingMeaning = existingMeaning;
	}
	public int getNeedMeaning() {
		return needMeaning;
	}
	public void setNeedMeaning(int needMeaning) {
		this.needMeaning = needMeaning;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getMeaningSearcher() {
		return meaningSearcher;
	}
	public void setMeaningSearcher(String meaningSearcher) {
		this.meaningSearcher = meaningSearcher;
	}
	public Date getMeaningFindDate() {
		return meaningFindDate;
	}
	public void setMeaningFindDate(Date meaningFindDate) {
		this.meaningFindDate = meaningFindDate;
	}
	public static String getDtoticketing() {
		return dtoTicketing;
	}
	
	
}
