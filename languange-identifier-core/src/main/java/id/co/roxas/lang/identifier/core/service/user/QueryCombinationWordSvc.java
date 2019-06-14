package id.co.roxas.lang.identifier.core.service.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.co.roxas.data.transfer.object.UserDataActivation.custom.PageRequestCustom;
import id.co.roxas.data.transfer.object.languangeIdentifierCore.core.TblLangRepositoryTempDtlDto;
import id.co.roxas.data.transfer.object.languangeIdentifierCore.core.TblLangRepositoryTempDto;
import id.co.roxas.lang.identifier.core.dao.TblLangRepositoryTempDao;
import id.co.roxas.lang.identifier.core.dao.TblLangRepositoryTempDtlDao;
import id.co.roxas.lang.identifier.core.repository.TblLangRepositoryTempDtl;
import id.co.roxas.lang.identifier.core.service.BaseService;

@Service
public class QueryCombinationWordSvc extends BaseService{
    @Autowired
    private TblLangRepositoryTempDtlDao tblLangRepositoryTempDtlDao;
    
    public PageRequestCustom<TblLangRepositoryTempDtlDto> getAllMeaningOfSomeWords(String words, Pageable pageable){
    	List<TblLangRepositoryTempDtlDto> tblLangRepositoryTempDtlDtos = new ArrayList<>();	
    	Page<TblLangRepositoryTempDtl> page = tblLangRepositoryTempDtlDao.
    			getAllMeaningOfSomeWord(words, pageable);
    	for (TblLangRepositoryTempDtl tblLangRepositoryTempDtl : page.getContent()) {
			TblLangRepositoryTempDtlDto tempDtlDto = new TblLangRepositoryTempDtlDto();
			tempDtlDto = mapperFacade.map(tblLangRepositoryTempDtl, TblLangRepositoryTempDtlDto.class);
			TblLangRepositoryTempDto tblLangRepositoryTempDto = mapperFacade.
					map(tblLangRepositoryTempDtl.getTblId(), TblLangRepositoryTempDto.class);
			tblLangRepositoryTempDto.setTblUsingCharacterDetails(new ArrayList<>());
			tempDtlDto.setTblId(tblLangRepositoryTempDto);
			tblLangRepositoryTempDtlDtos.add(tempDtlDto);
		}
    	return new PageRequestCustom<>(tblLangRepositoryTempDtlDtos, page.getPageable().getPageSize(), page.getTotalPages(),
				page.getPageable().getPageNumber(),page.getTotalElements(), getSorter(pageable), null);
    }
}
