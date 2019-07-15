package id.co.roxas.lang.identifier.core.dao.chatbot;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.roxas.lang.identifier.core.repository.chatbot.TblLangRespondChatbotTemp;

@Repository
public interface TblLangRespondChatbotTempDao extends JpaRepository<TblLangRespondChatbotTemp, String>{

	@Query("select a.outputRespond, b.generalRespond "
			+ " from  TblLangRespondChatbotTemp a "
			+ " join TblLangRespondGeneralDtl b "
			+ " on "
			+ " a.generalRespondId = b.generalRespondId "
			+ " where "
			+ " a.userOwner = ?1 "
			+ " and "
			+ " a.isActive = 1 "
			+ " and "
			+ " b.isActive = 1 ")
	public List<Object[]> getRecommendedQueryCoding(String userId);
	
}
