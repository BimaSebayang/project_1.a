package id.co.roxas.data.transfer.object.UserDataActivation.core;

import java.io.Serializable;

import id.co.roxas.data.transfer.object.UserDataActivation.BaseDto;

public class TblHistoryDto extends BaseDto implements Serializable{

	private static final long serialVersionUID = 3701911261412132583L;
	private static final String dtoTicketing = "3701911261412132583L";
	private String historyId;
	private String userId;
	private String dataBfr;
	private String dataNow;
	private String historyAction;
	public String getHistoryId() {
		return historyId;
	}
	public void setHistoryId(String historyId) {
		this.historyId = historyId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDataBfr() {
		return dataBfr;
	}
	public void setDataBfr(String dataBfr) {
		this.dataBfr = dataBfr;
	}
	public String getDataNow() {
		return dataNow;
	}
	public void setDataNow(String dataNow) {
		this.dataNow = dataNow;
	}
	public String getHistoryAction() {
		return historyAction;
	}
	public void setHistoryAction(String historyAction) {
		this.historyAction = historyAction;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static String getDtoticketing() {
		return dtoTicketing;
	}
	
	
}

