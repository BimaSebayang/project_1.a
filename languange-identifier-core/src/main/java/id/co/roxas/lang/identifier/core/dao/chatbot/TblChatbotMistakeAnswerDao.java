package id.co.roxas.lang.identifier.core.dao.chatbot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.roxas.lang.identifier.core.repository.chatbot.TblChatbotMistakeAnswer;

@Repository
public interface TblChatbotMistakeAnswerDao extends JpaRepository<TblChatbotMistakeAnswer, String> {

}