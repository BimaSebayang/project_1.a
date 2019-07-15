package id.co.roxas.lang.identifier.core.controller.chatbot;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.roxas.data.transfer.object.UserDataActivation.response.WsResponse;
import id.co.roxas.data.transfer.object.chatbot.respond.ChatRespondDto;
import id.co.roxas.lang.identifier.core.controller.BaseController;
import id.co.roxas.lang.identifier.core.lib.fuzzywuzzy.core.me.xdrops.diffutils.models.ExtractedResult;
import id.co.roxas.lang.identifier.core.service.chatbot.PleaseChatMeService;

@RestController
@RequestMapping("/please-chat-me")
@Transactional
public class PleaseChatMeCtl extends BaseController{

	@Autowired
	private PleaseChatMeService pleaseChatMeService;
	
	@PostMapping("/respond")
	private WsResponse responseChat(@RequestBody String chat, Authentication authentication) {
		long startTime = System.nanoTime();
		ExtractedResult extractedResult = pleaseChatMeService.getResponded(authentication.getName(), chat);
		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		ChatRespondDto chatRespondDto = new ChatRespondDto();
		chatRespondDto.setChatbotKnowing(extractedResult.getString());
		chatRespondDto.setChatbotRespond(extractedResult.getString2().split("@")[1]);
		chatRespondDto.setPercentage(extractedResult.getScore());
		chatRespondDto.setResponseTime(convertLongToSecond(totalTime));
		chatRespondDto.setUserAsking(chat);
		return new WsResponse(chatRespondDto, "FINISH");
	}
	
}
