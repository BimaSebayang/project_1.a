package id.co.roxas.app.web.languange.controller.spellChecker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import id.co.roxas.app.web.languange.controller.BaseWebController;

@Controller
public class SpellCheckerWsCtl extends BaseWebController{
	@RequestMapping(value = "/spell-checker", method = RequestMethod.GET)
	public String login() {
		return "spell-checker";
	}

}
