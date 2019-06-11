package id.co.roxas.lang.identifier.core.controller.user;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.roxas.data.transfer.object.UserDataActivation.core.TblRoleDto;
import id.co.roxas.data.transfer.object.UserDataActivation.response.WsResponse;
import id.co.roxas.data.transfer.object.languangeIdentifierCore.core.TblLangRepositoryTempDto;
import id.co.roxas.data.transfer.object.shared.config.AuthorizationClassConf;
import id.co.roxas.lang.identifier.core.controller.BaseController;
import id.co.roxas.lang.identifier.core.repository.TblLangRepositoryTemp;
import id.co.roxas.lang.identifier.core.repository.TblLangRepositoryTempDtl;
import lombok.val;

@RestController
@RequestMapping("/query-combination-word")
public class QueryCombinationWord extends BaseController{

	@PostMapping("/temp/search-word")
	public WsResponse searchWordFromTempTable(@Valid @RequestBody String words,
			@RequestHeader(name = "uuid-connector-response", required = true) String uuid,
			@RequestHeader(name = "module", required = true) String module, Authentication authentication) {
		AuthorizationClassConf authorizationClassConf = new AuthorizationClassConf(registUuid(uuid),
				registUuid(TblLangRepositoryTempDto.getDtoticketing()), getAllAuthUser(authentication), module,
				authentication.getName());
		return null;
	}
}
