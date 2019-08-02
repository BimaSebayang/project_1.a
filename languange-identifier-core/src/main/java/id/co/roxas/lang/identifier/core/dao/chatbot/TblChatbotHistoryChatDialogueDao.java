package id.co.roxas.lang.identifier.core.dao.chatbot;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.roxas.lang.identifier.core.repository.chatbot.TblChatbotHistoryChatDialogue;

@Repository
public interface TblChatbotHistoryChatDialogueDao extends JpaRepository<TblChatbotHistoryChatDialogue, String> {

	@Query(value = "select a.dialogue_position, a.dialogue_session, a.is_dialogue_continue "
			+ " from Tbl_Chatbot_History_Chat_Dialogue a "
			+ " where a.user_chat = :userId "
			+ " order by a.dialogue_session desc,a.dialogue_position desc limit 1 ", nativeQuery = true)
	public List<Object[]> objectDialogue(@Param("userId")String userId);
	
	@Query("select a from TblChatbotHistoryChatDialogue a where a.chatSequence = ?1")
	public TblChatbotHistoryChatDialogue getDialogueHistoryChat(Integer chatSeq);
	
	@Query(value = " select "
			+ " case "
			+ " when "
			+ " is_dialogue_continue = 0 or is_dialogue_continue is null "
			+ " then null "
			+ " else a.group_dialogue "
			+ " end "
			+ " , a.chat_sequence "
			+ " , case "
			+ " when "
			+ " is_dialogue_continue = 0 or is_dialogue_continue is null "
			+ " then 0 "
			+ " else "
			+ " a.dialogue_position  "
			+ " end "
			+ " from Tbl_Chatbot_History_Chat_Dialogue a "
			+ " where a.user_chat = :userId "
			+ " order by a.chat_sequence desc limit 1 ", nativeQuery = true)
	public List<Object[]> sequenceDialogue(@Param("userId") String userId);
	
	@Query("select a from TblChatbotHistoryChatDialogue a where a.userChat = ?1 order by a.createdDate asc")
	public List<TblChatbotHistoryChatDialogue> getAllHistory(String userName);
}
