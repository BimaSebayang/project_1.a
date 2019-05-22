package id.co.roxas.data.transfer.object.UserDataActivation.core;

import java.io.Serializable;
import java.util.Date;

import id.co.roxas.data.transfer.object.UserDataActivation.BaseDto;

public class TblUaaHistoryDto extends BaseDto implements Serializable{

	private static final long serialVersionUID = 3701911261412132583L;
	private static final String dtoTicketing = "3701911261412132583L";
	private String historyId;
	private String dataBfr;
	private String dataNow;
	private String historyAction;
	private String trxId;
	private Date createdDate;
	private String createdBy;
	public String getHistoryId() {
		return historyId;
	}
	public void setHistoryId(String historyId) {
		this.historyId = historyId;
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
	public String getTrxId() {
		return trxId;
	}
	public void setTrxId(String trxId) {
		this.trxId = trxId;
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
	public static String getDtoticketing() {
		return dtoTicketing;
	}

	
	
	
}

