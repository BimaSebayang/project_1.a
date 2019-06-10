package id.co.roxas.user.data.activation.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.roxas.user.data.activation.core.repository.TblTicket;

@Repository
public interface TblTicketDao extends JpaRepository<TblTicket, String> {
	@Modifying
	@Query(" update TblTicket " + " set uaaSessionIdWeb = ?2 " + " where " + " ticketId = ?1 ")
	public void updateSessionUaaWebByItsTicketId(String ticketId, String sessionId);

	@Modifying
	@Query(" update TblTicket " + " set langSessionIdWeb = ?2 " + " where " + " ticketId = ?1 ")
	public void updateSessionLangWebByItsTicketId(String ticketId, String sessionId);
	
	@Modifying
	@Query(" update TblTicket " + " set langSessionIdWeb = null, "
			+ " uaaSessionIdWeb = null, "
			+ " uaaSessionIdAndroid = null, "
			+ " langSessionIdAndroid = null ")
	public void updateToNullAllTicket();

}
