package id.co.roxas.lang.identifier.core.service.chatbot.knowledge;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import id.co.roxas.data.transfer.object.chatbot.TblChatbotHistoryChatDialogueDto;
import id.co.roxas.data.transfer.object.chatbot.respond.ChatbotDialogueUiDto;
import id.co.roxas.lang.identifier.core.dao.chatbot.TblChatbotHistoryChatDialogueDao;
import id.co.roxas.lang.identifier.core.repository.chatbot.TblChatbotHistoryChatDialogue;
import id.co.roxas.lang.identifier.core.service.BaseService;

@Service
@Transactional
public class ChatbotNeuronHistory extends BaseService {
	@Autowired
	private TblChatbotHistoryChatDialogueDao tblChatbotHistoryChatDialogueDao;

	public void createHistoryMyChat(TblChatbotHistoryChatDialogueDto chatbotHistoryChatDialogueDto) {
		TblChatbotHistoryChatDialogue tblChatbotHistoryChatDialogue = mapperFacade.map(chatbotHistoryChatDialogueDto,
				TblChatbotHistoryChatDialogue.class);
		tblChatbotHistoryChatDialogueDao.save(tblChatbotHistoryChatDialogue);
	}
	
	public List<TblChatbotHistoryChatDialogueDto> getAllHistory(String userId){
		List<TblChatbotHistoryChatDialogue> chatDialogues = tblChatbotHistoryChatDialogueDao.getAllHistory(userId);
		if(chatDialogues!=null) {
			List<TblChatbotHistoryChatDialogueDto> dialogueDtos = mapperFacade.mapAsList(chatDialogues,
					TblChatbotHistoryChatDialogueDto.class);
			return dialogueDtos;
		}
		return null;
	}
	
	public List<ChatbotDialogueUiDto> getAllMyHistoryChatWithYou(String userId){
		List<TblChatbotHistoryChatDialogueDto> historyTable = getAllHistory(userId);
		if(historyTable == null) {
			return null;
		}
		List<ChatbotDialogueUiDto> chatbotDialogueUiDtos = new ArrayList<>();
		for (TblChatbotHistoryChatDialogueDto tbl : historyTable) {
			ChatbotDialogueUiDto uiDtoUser = new ChatbotDialogueUiDto();
			ChatbotDialogueUiDto uiDtoChat = new ChatbotDialogueUiDto();
			uiDtoUser.setChatDate("Saya| "+timerDecision(tbl.getCreatedDate()));
			uiDtoUser.setIsIncoming(false);
			uiDtoUser.setIsOutgoing(true);
			uiDtoUser.setText(tbl.getUserQuestion());
			chatbotDialogueUiDtos.add(uiDtoUser);

			uiDtoChat.setChatDate("Roxas| " + timerDecision(tbl.getCreatedDate()));
			uiDtoChat.setIsIncoming(true);
			uiDtoChat.setIsOutgoing(false);
			uiDtoChat.setText(tbl.getChatbotRespond());
		    chatbotDialogueUiDtos.add(uiDtoChat);
		}
		
		
		return chatbotDialogueUiDtos;
	}

	public TblChatbotHistoryChatDialogueDto getLastHistory(String userId) {
		List<Object[]> sequence = tblChatbotHistoryChatDialogueDao.sequenceDialogue(userId);
		TblChatbotHistoryChatDialogueDto historyChatDialogueDto = new TblChatbotHistoryChatDialogueDto();
        System.err.println("sequence : " + new Gson().toJson(sequence));
		if (sequence.size() == 0) {
			historyChatDialogueDto.setChatSequence(1); // ini untuk semua user baru
			historyChatDialogueDto.setDialoguePosition(0);
		} else {
			for (Object[] objects : sequence) {
				historyChatDialogueDto.setGroupDialogue((String) objects[0]);
				historyChatDialogueDto.setChatSequence((Integer) objects[1]);
				historyChatDialogueDto.setDialoguePosition((Integer) objects[2]);
				break;
			}
		}
		return historyChatDialogueDto;
	}
	
	public TblChatbotHistoryChatDialogueDto getNeededHistory(Integer sequence) {
		TblChatbotHistoryChatDialogueDto chatDialogueDto = mapperFacade.map
				(tblChatbotHistoryChatDialogueDao.getDialogueHistoryChat(sequence), TblChatbotHistoryChatDialogueDto.class);
		return chatDialogueDto;
	}
}
