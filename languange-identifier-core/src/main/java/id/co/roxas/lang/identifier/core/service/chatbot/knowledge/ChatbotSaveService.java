package id.co.roxas.lang.identifier.core.service.chatbot.knowledge;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.roxas.lang.identifier.core.dao.TblChatbotQuestionAnswerKnowledgeDao;
import id.co.roxas.lang.identifier.core.dao.chatbot.TblChatbotGeneralKnowledgeDao;
import id.co.roxas.lang.identifier.core.repository.chatbot.TblChatbotGeneralKnowledge;
import id.co.roxas.lang.identifier.core.repository.chatbot.TblChatbotQuestionAnswerKnowledge;
import id.co.roxas.lang.identifier.core.service.BaseService;

@Service
@Transactional
public class ChatbotSaveService extends BaseService{
	
	  @Autowired
	  private TblChatbotGeneralKnowledgeDao tblChatbotGeneralKnowledgeDao;
	  
	  @Autowired
	  private TblChatbotQuestionAnswerKnowledgeDao tblChatbotQuestionAnswerKnowledgeDao;
	
      public void addingNewKnowledge(TblChatbotQuestionAnswerKnowledge tblChatbotQuestionAnswerKnowledge) {
    	  tblChatbotQuestionAnswerKnowledgeDao.save(tblChatbotQuestionAnswerKnowledge);
      }
}
