package id.co.roxas.data.transfer.object.UserDataActivation.core;

import java.io.Serializable;


//this class is not implementing DtoTicketing, which mean this class never be called as wsContent in wsResponse
public class TblTicketDto implements Serializable {

	private static final long serialVersionUID = 2292971943111365220L;
	private static final String dtoTicketing = "2292971943111365220L";
	private String ticketId;
	private String uaaSessionIdWeb;
	private String langSessionIdWeb;
	private String uaaSessionIdAndroid;
	private String langSessionIdAndroid;

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getUaaSessionIdWeb() {
		return uaaSessionIdWeb;
	}

	public void setUaaSessionIdWeb(String uaaSessionIdWeb) {
		this.uaaSessionIdWeb = uaaSessionIdWeb;
	}

	public String getLangSessionIdWeb() {
		return langSessionIdWeb;
	}

	public void setLangSessionIdWeb(String langSessionIdWeb) {
		this.langSessionIdWeb = langSessionIdWeb;
	}

	public String getUaaSessionIdAndroid() {
		return uaaSessionIdAndroid;
	}

	public void setUaaSessionIdAndroid(String uaaSessionIdAndroid) {
		this.uaaSessionIdAndroid = uaaSessionIdAndroid;
	}

	public String getLangSessionIdAndroid() {
		return langSessionIdAndroid;
	}

	public void setLangSessionIdAndroid(String langSessionIdAndroid) {
		this.langSessionIdAndroid = langSessionIdAndroid;
	}

	public static String getDtoticketing() {
		return dtoTicketing;
	}
	
	
	
}
