package id.co.roxas.lang.identifier.core.dao.chatbot;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.roxas.lang.identifier.core.repository.chatbot.TblChatbotQuestionAnswerKnowledge;

@Repository
public interface TblChatbotQuestionKnowledgeDao extends JpaRepository<TblChatbotQuestionAnswerKnowledge, String>{
     
	@Query("select a from TblChatbotQuestionAnswerKnowledge a where a.isActive = 1 and a.userDelegate = ?1 and a.dialoguePosition = 1 ")
	public List<TblChatbotQuestionAnswerKnowledge> getAllQuestionKnowledgeAndResponse(String userId);
	
	@Query("select a from TblChatbotQuestionAnswerKnowledge a where a.isActive = 1 "
			+ " and a.userDelegate = ?1 "
			+ " and a.dialoguePosition = ?2 "
			+ " and (a.groupDialogue = ?3 or ?3 is null ) ")
	public List<TblChatbotQuestionAnswerKnowledge> getAllQuestionKnowledgeAndResponse(String userId,
			Integer dialoguePosition, String groupDialogue);
	
	@Query("select a.questionId from TblChatbotQuestionAnswerKnowledge a where a.isActive = 1 "
			+ " and a.userDelegate = ?1 "
			+ " and a.dialoguePosition = ?3 "
			+ " and a.groupDialogue = ?2 ")
	public String checkThereIsNextQuestion(String userId, String groupDialogue, Integer dialoguePosition);
	
	@Query("select a from TblChatbotQuestionAnswerKnowledge a where a.isActive = 1 "
			+ " and "
			+ " a.groupDialogue = ?1"
			+ " and "
			+ " a.userDelegate = ?2"
			+ " and "
			+ " a.dialoguePosition = ?3 ")
	public TblChatbotQuestionAnswerKnowledge getNextDialog(String groupDialog, String userDelegate, Integer dialoguePosition);
}
