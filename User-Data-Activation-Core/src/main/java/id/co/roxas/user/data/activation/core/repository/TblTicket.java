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

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
	
	
}
