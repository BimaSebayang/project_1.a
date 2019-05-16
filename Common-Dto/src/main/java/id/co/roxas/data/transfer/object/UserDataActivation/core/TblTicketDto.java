package id.co.roxas.data.transfer.object.UserDataActivation.core;

import java.io.Serializable;

//this class is not implementing DtoTicketing, which mean this class never be called as wsContent in wsResponse
public class TblTicketDto implements Serializable{

	private static final long serialVersionUID = 2292971943111365220L;
	private String ticketId;

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}
}
