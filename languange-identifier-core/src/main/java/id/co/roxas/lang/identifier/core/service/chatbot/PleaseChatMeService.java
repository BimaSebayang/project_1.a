package id.co.roxas.lang.identifier.core.service.chatbot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import id.co.roxas.lang.identifier.core.dao.chatbot.TblChatbotGeneralKnowledgeDao;
import id.co.roxas.lang.identifier.core.dao.chatbot.TblChatbotQuestionKnowledgeDao;
import id.co.roxas.lang.identifier.core.lib.LevensteinDistance;
import id.co.roxas.lang.identifier.core.lib.chatbot.MyChatbotDto;
import id.co.roxas.lang.identifier.core.lib.chatbot.MyComparerString;
import id.co.roxas.lang.identifier.core.repository.chatbot.TblChatbotQuestionAnswerKnowledge;
import id.co.roxas.lang.identifier.core.service.BaseService;

@Service
@Transactional
public class PleaseChatMeService extends BaseService {

	@Autowired
	private TblChatbotQuestionKnowledgeDao tblChatbotQuestionKnowledgeDao;

	@Autowired
	private TblChatbotGeneralKnowledgeDao tblChatbotGeneralKnowledgeDao;

	public List<TblChatbotQuestionAnswerKnowledge> getAllKnowledge(String userId) {
		List<TblChatbotQuestionAnswerKnowledge> knowledges = tblChatbotQuestionKnowledgeDao
				.getAllQuestionKnowledgeAndResponse(userId);

		return knowledges;
	}
	
	public List<TblChatbotQuestionAnswerKnowledge> getAllKnowledgeWithPosition(String userId, 
			Integer position, String groupDialogue) {
		System.out.println(userId + " " + position + " " + groupDialogue);
		List<TblChatbotQuestionAnswerKnowledge> knowledges = tblChatbotQuestionKnowledgeDao
				.getAllQuestionKnowledgeAndResponse(userId,position,groupDialogue);
		return knowledges;
	}

	public TblChatbotQuestionAnswerKnowledge getChatbotNearlyKnow(String userId, String chat) {
		List<TblChatbotQuestionAnswerKnowledge> knowledges = tblChatbotQuestionKnowledgeDao
				.getAllQuestionKnowledgeAndResponse(userId);
		TblChatbotQuestionAnswerKnowledge answerKnowledge = new TblChatbotQuestionAnswerKnowledge();

		return answerKnowledge;
	}

//	public String chatbotUnderstandABit(String trancId) {
//		
//	}

	public TblChatbotQuestionAnswerKnowledge getOnePossibleCondition(List<TblChatbotQuestionAnswerKnowledge> response,
			String userChatting) {
      System.err.println("resp : " + new Gson().toJson(response) + " userChatting : " + userChatting);
		TblChatbotQuestionAnswerKnowledge answerKnowledge = null;
		int maxValue = 0;
		for (TblChatbotQuestionAnswerKnowledge resp : response) {
			if(resp.getDialoguePosition().intValue()>1 && resp.getFocusWords().equalsIgnoreCase(FREE_TEXT)) {
				return resp;
			}
			
			List<String> matchingCondition = MyComparerString.stringComparePossible(resp.getQuestionUser(),
					userChatting);
			List<String> focus = new ArrayList<>();
			
			if (resp.getFocusWords() != null) {
				String[] arr = resp.getFocusWords().split("@@");
				focus.addAll(Arrays.asList(arr));
			}
			
			if (MyComparerString.listStringComparer(focus, matchingCondition)) {
				if(focus.size()>=maxValue)
				answerKnowledge = resp;
			}
		}

		return answerKnowledge;
	}

	public List<MyChatbotDto> prosesTransaction(TblChatbotQuestionAnswerKnowledge tblChatbotQuestionKnowledge) {
		List<MyChatbotDto> transs = new ArrayList<>();
			String responding = tblChatbotQuestionKnowledge.getChatbotRespond();
			String[] responds = responding.split("@@");
			for (String respond : responds) {
				System.err.println("knowledgeId : " + respond);
				String transFunc = tblChatbotGeneralKnowledgeDao.processTransaction(respond.trim());
				if(transFunc!=null) {
					transFunc = transFunc.trim();
				}
				else {
					transFunc = respond;
				}
				MyChatbotDto chatbotDto = new MyChatbotDto();
				chatbotDto.setTrancNo(transFunc);
				chatbotDto.setExpectedQuestionUser(tblChatbotQuestionKnowledge.getQuestionUser());
				transs.add(chatbotDto);
			}
		return transs;
	}

}
