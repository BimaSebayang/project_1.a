package id.co.roxas.lang.identifier.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.roxas.lang.identifier.core.repository.chatbot.TblChatbotQuestionAnswerKnowledge;

public interface TblChatbotQuestionAnswerKnowledgeDao extends JpaRepository<TblChatbotQuestionAnswerKnowledge, String>{

}
