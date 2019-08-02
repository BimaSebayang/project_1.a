package id.co.roxas.app.web.languange.controller.chatbot;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import id.co.roxas.app.web.languange.config.HttpSecurityService;
import id.co.roxas.app.web.languange.controller.BaseWebController;
import id.co.roxas.data.transfer.object.UserDataActivation.response.WsResponse;
import id.co.roxas.data.transfer.object.chatbot.respond.ChatbotDialogueUiDto;

@RestController
public class ChatbotWsCtl extends BaseWebController{
    private static final String	MY_CHATBOT_WS = "/i-want-chatbot-chatme-100-right";
	
	@GetMapping("/all-chat-history")
	public List<ChatbotDialogueUiDto> getAllHistoryPaging(HttpServletRequest request) {
		HttpSecurityService httpSecurityService = new HttpSecurityService(null, ChatbotDialogueUiDto.getDtoticketing(), "I001");
		WsResponse response = resultWsWitSecurityAccess(LANG_END_POINT_URL+"/please-chat-me/i-want-my-history-chat", 
				null, HttpMethod.GET,null,getToken(request), httpSecurityService);
		List<ChatbotDialogueUiDto> chatbotDialogueUiDtos = new ArrayList<>();
		try {
			chatbotDialogueUiDtos = mapperJsonToListDto(response.getWsContent(), ChatbotDialogueUiDto.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chatbotDialogueUiDtos;
	}
	
	@PostMapping("/chat-with-roxas")
	public ChatbotDialogueUiDto pleaseChatMeRoxas(@RequestBody String text, HttpServletRequest request) {
		HttpSecurityService httpSecurityService = new HttpSecurityService(null, ChatbotDialogueUiDto.getDtoticketing(), "I001");
		WsResponse response = resultWsWitSecurityAccess(LANG_END_POINT_URL+
				"/please-chat-me"+MY_CHATBOT_WS, 
				text, HttpMethod.POST,null,getToken(request), httpSecurityService);
		ChatbotDialogueUiDto chatbotDialogueUiDto = new ChatbotDialogueUiDto();
		try {
			chatbotDialogueUiDto = mapperJsonToSingleDto(response.getWsContent(), ChatbotDialogueUiDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chatbotDialogueUiDto;
	}
	
	@GetMapping("/manipulator")
	public ChatbotDialogueUiDto manipulatingChatDiv() {
		ChatbotDialogueUiDto chatbotDialogueUiDto = new ChatbotDialogueUiDto();
		return chatbotDialogueUiDto;
	}
	
}
