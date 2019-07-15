package id.co.roxas.lang.identifier.core.service.chatbot;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import id.co.roxas.lang.identifier.core.dao.chatbot.TblChatbotHistoryChatDao;
import id.co.roxas.lang.identifier.core.dao.chatbot.TblLangRespondChatbotTempDao;
import id.co.roxas.lang.identifier.core.lib.fuzzywuzzy.core.me.xdrops.diffutils.FuzzySearch;
import id.co.roxas.lang.identifier.core.lib.fuzzywuzzy.core.me.xdrops.diffutils.models.ExtractedResult;
import id.co.roxas.lang.identifier.core.repository.chatbot.TblChatbotHistoryChat;
import id.co.roxas.lang.identifier.core.service.BaseService;

@Service
@Transactional
public class PleaseChatMeService extends BaseService{
      @Autowired
      private TblLangRespondChatbotTempDao tblLangRespondChatbotTempDao;
      
      public ExtractedResult getResponded (String userId, String question) throws NullPointerException{
    	  List<Object[]> respondObject = tblLangRespondChatbotTempDao.getRecommendedQueryCoding(userId);
    	  Map<String, String> map = new HashMap<>();
    	  
    	  int i = 0;
    	  
    	  for (Object[] rob : respondObject) {
			 map.put( i + "@" + (String) rob[0], (String) rob[1]);
			 i++;
		  }
    	  List<ExtractedResult> extractAll = FuzzySearch.extractAllHigherScore(question, map);
    	  System.err.println(new Gson().toJson(extractAll));
    	  ExtractedResult extractedResult = extractAll.get(0);
    	  chatHistory(extractedResult.getString2().split("@")[1],extractedResult.getString(),question, extractedResult.getScore(), userId);
    	  return extractedResult;
      } 
}
