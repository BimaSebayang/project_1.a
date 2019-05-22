package id.co.roxas.data.transfer.object.UserDataActivation.model;

public class UserActivationForm{
	private String userId;
	private int actionResult;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getActionResult() {
		return actionResult;
	}
	public void setActionResult(int actionResult) {
		this.actionResult = actionResult;
	}

}
