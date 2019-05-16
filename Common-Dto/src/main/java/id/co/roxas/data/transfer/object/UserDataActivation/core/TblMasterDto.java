package id.co.roxas.data.transfer.object.UserDataActivation.core;

import java.io.Serializable;

import id.co.roxas.data.transfer.object.UserDataActivation.BaseDto;

public class TblMasterDto extends BaseDto implements Serializable {
	private String masterId;
	private String masterCode;
	private String masterCodeDesc;
	private static final long serialVersionUID = -8620249951150392086L;
	private static final String dtoTicketing = "8620249951150392086L";
	public String getMasterId() {
		return masterId;
	}
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	public String getMasterCode() {
		return masterCode;
	}
	public void setMasterCode(String masterCode) {
		this.masterCode = masterCode;
	}
	public String getMasterCodeDesc() {
		return masterCodeDesc;
	}
	public void setMasterCodeDesc(String masterCodeDesc) {
		this.masterCodeDesc = masterCodeDesc;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static String getDtoticketing() {
		return dtoTicketing;
	}
	
	

}
