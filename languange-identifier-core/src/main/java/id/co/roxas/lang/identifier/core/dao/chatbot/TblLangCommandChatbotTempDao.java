package id.co.roxas.lang.identifier.core.dao.chatbot;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.roxas.lang.identifier.core.repository.chatbot.TblLangCommandChatbotTemp;

@Repository
public interface TblLangCommandChatbotTempDao extends JpaRepository<TblLangCommandChatbotTemp, String>{

}
