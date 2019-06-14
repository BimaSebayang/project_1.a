package id.co.roxas.app.web.languange.controller.dictionary;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import id.co.roxas.app.web.languange.controller.BaseWebController;
import id.co.roxas.app.web.languange.controller.config.HttpSecurityService;
import id.co.roxas.app.web.languange.controller.lib.ParamQueryCustomLib;
import id.co.roxas.data.transfer.object.UserDataActivation.core.TblRoleDto;
import id.co.roxas.data.transfer.object.UserDataActivation.custom.PageRequestCustom;
import id.co.roxas.data.transfer.object.UserDataActivation.model.PageClassResponse;
import id.co.roxas.data.transfer.object.UserDataActivation.model.PageRevolver;
import id.co.roxas.data.transfer.object.UserDataActivation.response.PageResponse;
import id.co.roxas.data.transfer.object.languangeIdentifierCore.core.TblLangRepositoryTempDtlDto;


@RestController
public class DictionaryWsCtl extends BaseWebController{

	@PostMapping("/all-search-query")
	public PageClassResponse<TblLangRepositoryTempDtlDto> allSearchQuery(@RequestBody PageRevolver revolver, HttpServletRequest request){
		PageClassResponse<TblLangRepositoryTempDtlDto> pageRequestCustom = new PageClassResponse<>();
		HttpSecurityService httpSecurityService = new HttpSecurityService(null, TblLangRepositoryTempDtlDto.getDtoticketing(), "I001");
		paramPagingNew(revolver.getPage(), 
				SIZE_LANG, revolver.getSearch(), 
				"langName,asc");
		PageResponse pageResponse = pageResultsWithSecurityAccess
				(LANG_END_POINT_URL + "/query-combination-word/temp/search-word", 
						revolver.getSearch(), HttpMethod.POST, null, getToken(request), 
						httpSecurityService, retrieveAllPagingNeeded());
		List<TblLangRepositoryTempDtlDto> tempDtlDtos = new ArrayList<>();
		try {
			tempDtlDtos = mapperJsonToListDto(pageResponse.getWsContent(), 
					TblLangRepositoryTempDtlDto.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageRequestCustom.setAllDatas(tempDtlDtos);
		pageRequestCustom.setPage(pageResponse.getPageNumber());
		pageRequestCustom.setTotalPage(pageResponse.getTotalPage());
		return pageRequestCustom;
	}
	
}
