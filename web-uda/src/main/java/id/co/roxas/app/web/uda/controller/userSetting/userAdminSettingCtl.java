package id.co.roxas.app.web.uda.controller.userSetting;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import id.co.roxas.app.web.uda.controller.BaseWebController;

@Controller
public class userAdminSettingCtl extends BaseWebController{

	@RequestMapping(value = "/user-admin-setting-index",  method = RequestMethod.GET)
	public String userAdminSettingIndexCtl(HttpServletRequest request) {
		return onSecurityAccess("user-admin-setting-index", request);
	}
	
	@RequestMapping(value = "/user-admin-setting-add",  method = RequestMethod.GET)
	public String userAdminSettingAddCtl(HttpServletRequest request) {
		return onSecurityAccess("user-admin-setting-add", request);
	}
	
	@RequestMapping(value = "/user-admin-setting-config",  method = RequestMethod.GET)
	public String userAdminSettingConfigCtl(HttpServletRequest request) {
		return onSecurityAccess("user-admin-setting-config", request);
	}
}
