package id.co.roxas.lang.identifier.core.service.chatbot.knowledge;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import id.co.roxas.data.transfer.object.chatbot.TblChatbotHistoryChatDialogueDto;
import id.co.roxas.lang.identifier.core.dao.chatbot.TblChatbotQuestionKnowledgeDao;
import id.co.roxas.lang.identifier.core.lib.chatbot.CustomPattern;
import id.co.roxas.lang.identifier.core.repository.chatbot.TblChatbotGeneralKnowledge;
import id.co.roxas.lang.identifier.core.repository.chatbot.TblChatbotQuestionAnswerKnowledge;
import id.co.roxas.lang.identifier.core.service.BaseService;

@Service
@Transactional
public class ChatbotNeuronCenterService extends BaseService{
    
	@Autowired
	private ChatbotNeuronHistory chatbotNeuronHistory;
	
	@Autowired
	private ChatbotSaveService chatbotSaveService;
	
	@Autowired
    private TblChatbotQuestionKnowledgeDao tblChatbotQuestionKnowledgeDao;
	
	
	//for decide call what funtion to added.
	private void neuronDecisionMaker(String trancFunc, Map<String, Object> mapz ) throws Exception {
		  if(trancFunc.equals(TR0001)) {
			  TblChatbotQuestionAnswerKnowledge generalKnowledge = mapperJsonToSingleDto(new Gson().toJson(mapz), TblChatbotQuestionAnswerKnowledge.class);
			  System.err.println("dto : " + new Gson().toJson(generalKnowledge));
			  chatbotSaveService.addingNewKnowledge(generalKnowledge);
		  }
	}
	
	private Map<String,Object> neuronObjectMaker(String trancFunc,Map<String, Object> map, String user){
		if(trancFunc.equals(TR0001)) {
			map.put("isForDialogue", 0);
			map.put("groupDialogue", null);
			map.put("dialoguePosition", 1);
			map.put("isTransaction", 0);
			map.put("isActive", 1);
			map.put("userDelegate", user);
			map.put("createdDate", parseDateToString(new Date(), "yyyy-MM-dd"));
			map.put("createdBy", "SYSTEM");
		}
		
		return map;
	}
	
	private void myNeuronRunner(String trancFunc,Map<String,Object> map, String user) {
	    try {
			neuronDecisionMaker(trancFunc, neuronObjectMaker(trancFunc, map, user));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public String neuronRunner(String chatbotResp,
			                   String trancFunc, 
			                   String questionAnswerChatbotKnow, 
			                   String userChat,
			                   String groupDialog,
			                   Integer dialogPosition,
			                   String user,
			                   Integer chatSequence) {
		String responsive = trancFunc;
		Integer isChatbotKnowing = 1;
		Integer isDialogueCont = 0;
		if(trancFunc.equals(TR0001)) {
			TblChatbotHistoryChatDialogueDto history = 
					chatbotNeuronHistory.getNeededHistory(chatSequence-dialogPosition+1);
			Map<String, Object> patternInvolved = CustomPattern.QuestionAndAnswerFinder(history.getChatbotKnowing(), 
					history.getUserQuestion(),userChat);
			myNeuronRunner(trancFunc, patternInvolved, user);
			responsive = chatbotResp;
			return TRANSACTION_STAT;
		}
		else if(trancFunc.equals(NULL)) {
			responsive =  " Maaf Roxas Tidak paham dengan kalimat : ' " + userChat + " ', \n\n\n apakah kakak ingin mengajari roxas kalimat tersebut? ";
			isChatbotKnowing = 0;
		}
		
		if(dialogPosition==null) {
			dialogPosition = 0;
		}
		String questionId = tblChatbotQuestionKnowledgeDao.checkThereIsNextQuestion
				(user, groupDialog, Integer.sum(dialogPosition, 1));
		if(questionId!=null) {
			isDialogueCont = 1;
		}
		
		SimplifiedMyHistory(questionAnswerChatbotKnow, 
				responsive, 
				dialogPosition, 
				"TEST", 
				groupDialog, 
				isChatbotKnowing, 
				isDialogueCont, 
				0, 
				user, 
				userChat,
				chatSequence);
		return responsive;
	}
	
	private void SimplifiedMyHistory(String chatbotKnowing, String chatbotRespond, Integer dialoguePosition,
			String dialogueType, String groupDialogue, Integer isChatbotKnow, Integer isDialogueContinue,
			Integer percentageUnderstand, String userChat, String userQuestion, Integer chatSequence) {
		TblChatbotHistoryChatDialogueDto dto = new TblChatbotHistoryChatDialogueDto();
		dto.setChatbotKnowing(chatbotKnowing);
		dto.setChatbotRespond(chatbotRespond);
		dto.setCreatedDate(new Date());
		dto.setDialoguePosition(dialoguePosition);
		dto.setDialogueType(dialogueType);
		dto.setGroupDialogue(groupDialogue);
		dto.setIsChatbotKnow(isChatbotKnow);
		dto.setIsDialogueContinue(isDialogueContinue);
		dto.setPercentageUnderstand(percentageUnderstand);
		dto.setUserChat(userChat);
		dto.setUserQuestion(userQuestion);
		dto.setChatSequence(chatSequence);
		chatbotNeuronHistory.createHistoryMyChat(dto);
	}
	
}
