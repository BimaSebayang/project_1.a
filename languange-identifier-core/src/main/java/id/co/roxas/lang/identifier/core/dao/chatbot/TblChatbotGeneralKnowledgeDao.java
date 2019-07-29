package id.co.roxas.lang.identifier.core.dao.chatbot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.roxas.lang.identifier.core.repository.chatbot.TblChatbotGeneralKnowledge;

@Repository
public interface TblChatbotGeneralKnowledgeDao extends JpaRepository<TblChatbotGeneralKnowledge, String>{
              
	@Query(" select a.transactionFunc from TblChatbotGeneralKnowledge a where a.knowledgeId = ?1 ")
	public String processTransaction(String knowId);
	
}
