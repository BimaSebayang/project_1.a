package id.co.roxas.lang.identifier.core.controller.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.roxas.data.transfer.object.UserDataActivation.core.TblRoleDto;
import id.co.roxas.data.transfer.object.UserDataActivation.custom.PageRequestCustom;
import id.co.roxas.data.transfer.object.UserDataActivation.response.PageResponse;
import id.co.roxas.data.transfer.object.UserDataActivation.response.WsResponse;
import id.co.roxas.data.transfer.object.languangeIdentifierCore.core.TblLangRepositoryTempDtlDto;
import id.co.roxas.data.transfer.object.languangeIdentifierCore.core.TblLangRepositoryTempDto;
import id.co.roxas.data.transfer.object.shared.config.AuthorizationClassConf;
import id.co.roxas.lang.identifier.core.controller.BaseController;
import id.co.roxas.lang.identifier.core.service.user.QueryCombinationWordSvc;

@RestController
@RequestMapping("/query-combination-word")
public class QueryCombinationWordCtl extends BaseController{

	@Autowired
	private QueryCombinationWordSvc queryCombinationWordSvc;
	
	@PostMapping("/temp/search-word")
	public PageResponse searchWordFromTempTable(@Valid @RequestBody String words,
			@RequestHeader(name = "uuid-connector-response", required = true) String uuid,
			@RequestHeader(name = "module", required = true) String module, 
			Authentication authentication, Pageable pageable) {
		AuthorizationClassConf authorizationClassConf = new AuthorizationClassConf(registUuid(uuid),
				registUuid(TblLangRepositoryTempDtlDto.getDtoticketing()), getAllAuthUser(authentication), module,
				authentication.getName());
		if (responsePusherInvalidatorAccessUser(authentication, authorizationClassConf, true)) {
			return getPageInvalid();
		} else {
			PageRequestCustom<TblLangRepositoryTempDtlDto> tempDtlDtos = 
					queryCombinationWordSvc.getAllMeaningOfSomeWords(words,authentication.getName(),pageable);
			return new PageResponse(tempDtlDtos, authorizationClassConf, SUCCESS_RETRIEVE);
		}
	}
	
	@PostMapping("/temp/suggest-word")
	public WsResponse suggestSomeWords(@Valid @RequestBody String words,Authentication authentication) {
		AuthorizationClassConf authorizationClassConf = new AuthorizationClassConf
				(new String[]{}, new String[]{},"I001", authentication.getName());
		List<String> leven = queryCombinationWordSvc.allSuggestWords(words, 
				authentication.getName());
		if (responsePusherInvalidatorAccessUser(authentication, authorizationClassConf, true)) {
			return getResponseInvalid();
		} else {
			return new WsResponse(leven, SUCCESS_RETRIEVE, authorizationClassConf);
		}
	}
}
