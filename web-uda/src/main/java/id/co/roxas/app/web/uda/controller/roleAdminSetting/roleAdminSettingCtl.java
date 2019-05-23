package id.co.roxas.app.web.uda.controller.roleAdminSetting;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import id.co.roxas.app.web.uda.controller.BaseWebController;

@Controller
public class roleAdminSettingCtl extends BaseWebController{
	@RequestMapping(value = "/role-admin-setting-index",  method = RequestMethod.GET)
	public String roleAdminSettingIndexCtl(HttpServletRequest request) {
		return onSecurityAccess("role-admin-setting-index", request);
	}
	
	@RequestMapping(value = "/role-admin-setting-add",  method = RequestMethod.GET)
	public String roleAdminSettingAddCtl(HttpServletRequest request) {
		return onSecurityAccess("role-admin-setting-add", request);
	}
	
	@RequestMapping(value = "/role-admin-setting-config",  method = RequestMethod.GET)
	public String roleAdminSettingConfigCtl(HttpServletRequest request) {
		return onSecurityAccess("role-admin-setting-config", request);
	}
}
