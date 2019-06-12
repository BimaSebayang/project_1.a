package id.co.roxas.app.web.languange.controller.dictionary;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import id.co.roxas.app.web.languange.controller.BaseWebController;

@Controller
public class DictionaryCtl extends BaseWebController{
	@RequestMapping(value = "/dictionary", method = RequestMethod.GET)
	public String login() {
		return "dictionary";
	}

}
