package id.co.roxas.user.data.activation.core.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import id.co.roxas.data.transfer.object.UserDataActivation.config.AuthorizationClassConf;
import id.co.roxas.data.transfer.object.UserDataActivation.response.PageResponse;
import id.co.roxas.data.transfer.object.UserDataActivation.response.WsResponse;
import id.co.roxas.user.data.activation.core.UltimateBase;

public class BaseController extends UltimateBase {
	public static final String TRANS_DELETE = "delete";
	public static final String TRANS_UPDATE = "update";
	public static final String TRANS_SAVE = "save";
	public static final String[] AUTH_ADMIN = new String[] { "ADMIN" };
	public static final String[] AUTH_USER = new String[] { "USER" };
	public static final String[] AUTH_GUEST = new String[] { "GUEST" };
	private WsResponse responseInvalid;
	private PageResponse pageInvalid;

	protected String[] registUuid(String... uuids) {
		return uuids;
	}
	
	protected String[] getAllAuthUser(Authentication authentication) {
		Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
		String[] authorities = new String[grantedAuthorities.size()];
		int i = 0;
		for (GrantedAuthority grantedAuthority : grantedAuthorities) {
			authorities[i] = grantedAuthority.getAuthority();
			i++;
		}
		return authorities;
	}

	protected boolean responsePusherInvalidatorAccess(Authentication authentication, AuthorizationClassConf authorizationClassConf, boolean isNeedAccessAuthor) {
		if (isNeedAccessAuthor) {
			if (isAuthorizationCantAccess(AUTH_ADMIN, getAllAuthUser(authentication), true)) {
				this.responseInvalid = new WsResponse(null, RESPONSE_BAD_AUTHOR, authorizationClassConf);
				this.pageInvalid = new PageResponse(null, RESPONSE_BAD_AUTHOR, 0, 0, 0, authorizationClassConf);
				return true;
			}
		}
		if (isUuidNotValid(authorizationClassConf.getRequestUuid(), authorizationClassConf.getRealUuid())) {
			this.responseInvalid = new WsResponse(null, RESPONSE_BAD_UUID, authorizationClassConf);
			this.pageInvalid = new PageResponse(null, RESPONSE_BAD_UUID, 0, 0, 0, authorizationClassConf);
			return true;
		}
		return false;
	}

	protected boolean isUuidNotValid(String[] requestUuid, String[] realUuid) {
		List<String> l1 = Arrays.asList(requestUuid);
		List<String> l2 = Arrays.asList(realUuid);
		if (!l1.containsAll(l2) || !l2.containsAll(l1)) {
			return true;
		}
		return false;
	}

	protected boolean isAuthorizationCantAccess(String[] author, String[] authorNeeded, boolean isNeedAccessAuthor) {
		if (isNeedAccessAuthor) {
			if (Collections.disjoint(Arrays.asList(author), Arrays.asList(authorNeeded))) {
				return true;
			}
		}
		return false;
	}

	public WsResponse getResponseInvalid() {
		return responseInvalid;
	}

	public PageResponse getPageInvalid() {
		return pageInvalid;
	}

}
