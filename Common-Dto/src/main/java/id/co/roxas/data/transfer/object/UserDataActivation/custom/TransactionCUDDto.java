package id.co.roxas.data.transfer.object.UserDataActivation.custom;

import java.io.Serializable;


public class TransactionCUDDto implements Serializable{

	private static final long serialVersionUID = 5824781419406148517L;
	private static final String dtoTicketing = "5824781419406148517L";
	private int saveResult;
	private int updateResult;
	private int deleteResult;
	
	public int getSaveResult() {
		return saveResult;
	}

	public void setSaveResult(int saveResult) {
		this.saveResult = saveResult;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static String getDtoticketing() {
		return dtoTicketing;
	}

	public int getUpdateResult() {
		return updateResult;
	}

	public void setUpdateResult(int updateResult) {
		this.updateResult = updateResult;
	}

	public int getDeleteResult() {
		return deleteResult;
	}

	public void setDeleteResult(int deleteResult) {
		this.deleteResult = deleteResult;
	}
	
	
	
	    
}
