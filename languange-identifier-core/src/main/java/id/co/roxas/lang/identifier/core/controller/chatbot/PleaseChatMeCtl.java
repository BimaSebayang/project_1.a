package id.co.roxas.lang.identifier.core.controller.chatbot;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.roxas.data.transfer.object.UserDataActivation.response.WsResponse;
import id.co.roxas.data.transfer.object.chatbot.TblChatbotHistoryChatDialogueDto;
import id.co.roxas.data.transfer.object.chatbot.respond.ChatbotDialogueUiDto;
import id.co.roxas.lang.identifier.core.controller.BaseController;
import id.co.roxas.lang.identifier.core.lib.chatbot.MyChatbotDto;
import id.co.roxas.lang.identifier.core.repository.chatbot.TblChatbotQuestionAnswerKnowledge;
import id.co.roxas.lang.identifier.core.service.chatbot.PleaseChatMeService;
import id.co.roxas.lang.identifier.core.service.chatbot.knowledge.ChatbotNeuronCenterService;
import id.co.roxas.lang.identifier.core.service.chatbot.knowledge.ChatbotNeuronHistory;

@RestController
@RequestMapping("/please-chat-me")
@Transactional
public class PleaseChatMeCtl extends BaseController {
	@Autowired
	private PleaseChatMeService pleaseChatMeService;

	@Autowired
	private ChatbotNeuronCenterService chatbotNeuronCenterService;
	
	@Autowired
	private ChatbotNeuronHistory chatbotNeuronHistory;

	@PostMapping("/call-my-knowledge")
	private WsResponse getMeKnowledge(@RequestBody String textChat, Authentication authentication) {
		List<TblChatbotQuestionAnswerKnowledge> response = pleaseChatMeService
				.getAllKnowledge(authentication.getName());
		List<MyChatbotDto> allRespond = pleaseChatMeService.prosesTransaction(response.get(0));

		return new WsResponse(allRespond, "DONE");
	}
//
//	@PostMapping("/i-want-chatbot-do-me-favor")
//	private WsResponse chattingWithChatbot(@RequestBody String textChat, Authentication authentication) {
//		List<TblChatbotQuestionAnswerKnowledge> response = pleaseChatMeService
//				.getAllKnowledge(authentication.getName());
//		List<MyChatbotDto> allRespond = pleaseChatMeService.prosesTransaction(response.get(0));
//		for (MyChatbotDto respond : allRespond) {
//			chatbotNeuronCenterService.neuronRunner(respond.getTrancNo(), respond.getExpectedQuestionUser(), textChat,
//					authentication.getName());
//		}
//		return new WsResponse(null, "DONE");
//	}

	@GetMapping("/i-want-my-history-chat")
	private WsResponse historyChat(Authentication authentication) {
		List<ChatbotDialogueUiDto> uiDtos = chatbotNeuronHistory.getAllMyHistoryChatWithYou(authentication.getName());
		return new WsResponse(uiDtos, SUCCESS_RETRIEVE);
	}
	
	@PostMapping("/i-want-chatbot-chatme-100-right")
	private WsResponse chatting100Right(@RequestBody String textChat, Authentication authentication) {
		TblChatbotHistoryChatDialogueDto history = chatbotNeuronHistory.getLastHistory(authentication.getName());
		int charSequence = history.getChatSequence().intValue();
		int dialoguePosition = history.getDialoguePosition().intValue();
		List<TblChatbotQuestionAnswerKnowledge> response = pleaseChatMeService
				.getAllKnowledgeWithPosition(authentication.getName(), dialoguePosition+1, history.getGroupDialogue());
		TblChatbotQuestionAnswerKnowledge answerKnowledge = pleaseChatMeService.
				getOnePossibleCondition(response,
				textChat);
		String chatAnswer = "";
		System.err.println("answer : " + answerKnowledge);
		if (answerKnowledge != null) {
			List<MyChatbotDto> allRespond = pleaseChatMeService.prosesTransaction(answerKnowledge);
			int seq = charSequence+1;
			for (MyChatbotDto respond : allRespond) {
				chatAnswer = chatbotNeuronCenterService.neuronRunner(answerKnowledge.getChatbotRespond(),respond.getTrancNo(), respond.getExpectedQuestionUser(),
						textChat, answerKnowledge.getGroupDialogue(),
						answerKnowledge.getDialoguePosition(),authentication.getName(),seq);
				if(!chatAnswer.equalsIgnoreCase(TRANSACTION_STAT))
				{
					seq++;
				}
			}
		} else {
			    chatAnswer = chatbotNeuronCenterService.neuronRunner(null,NULL,null,
					textChat, null,null,authentication.getName(),charSequence+1);
		}
         
		
		ChatbotDialogueUiDto chatbotDialogueUiDto = new ChatbotDialogueUiDto();
		chatbotDialogueUiDto.setChatDate(timerDecision(new Date()));
		chatbotDialogueUiDto.setIsIncoming(true);
		chatbotDialogueUiDto.setIsOutgoing(false);
		chatbotDialogueUiDto.setText(chatAnswer);
		return new WsResponse(chatbotDialogueUiDto, "DONE");
	}
}
