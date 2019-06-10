package id.co.roxas.user.data.activation.core.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import id.co.roxas.user.data.activation.core.config.StringPrefixedSequenceIdGenerator;

@Entity
@Table(name="Tbl_Ticket")
public class TblTicket {

	@Id
	@Column(name="ticket_id", length=23, updatable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_id")
    @GenericGenerator(
        name = "ticket_id", 
        strategy = "id.co.roxas.user.data.activation.core.config.StringPrefixedSequenceIdGenerator", 
        parameters = {@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "23"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "TICKET"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%017d")}
    )
	private String ticketId;

	@Column(name="uaa_session_id_web",length=100, unique=true)
	private String uaaSessionIdWeb;
	
	@Column(name="lang_session_id_web",length=100, unique=true)
	private String langSessionIdWeb;
	
	@Column(name="uaa_session_id_android",length=100, unique=true)
	private String uaaSessionIdAndroid;
	
	@Column(name="lang_session_id_android",length=100, unique=true)
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


     
	
	
}
