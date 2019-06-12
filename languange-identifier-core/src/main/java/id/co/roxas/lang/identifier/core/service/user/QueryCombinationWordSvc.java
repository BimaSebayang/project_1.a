package id.co.roxas.lang.identifier.core.service.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
    public List<TblLangRepositoryTempDtlDto> getAllMeaningOfSomeWords(String words){
    	List<TblLangRepositoryTempDtlDto> tblLangRepositoryTempDtlDtos = new ArrayList<>();
    	
    	List<TblLangRepositoryTempDtl> tblLangRepositoryTempDtls = tblLangRepositoryTempDtlDao.
    			getAllMeaningOfSomeWord(staplingWords(words, "%"));
    	
//    	List<TblLangRepositoryTempDtl> tblLangRepositoryTempDtls = tblLangRepositoryTempDtlDao.findAll();
//    	System.err.println("word : " + staplingWords(words, "%"));
    	for (TblLangRepositoryTempDtl tblLangRepositoryTempDtl : tblLangRepositoryTempDtls) {
			TblLangRepositoryTempDtlDto tempDtlDto = new TblLangRepositoryTempDtlDto();
			tempDtlDto = mapperFacade.map(tblLangRepositoryTempDtl, TblLangRepositoryTempDtlDto.class);
			tempDtlDto.setTblId(new TblLangRepositoryTempDto());
			tblLangRepositoryTempDtlDtos.add(tempDtlDto);
		}
    	
    	return tblLangRepositoryTempDtlDtos;
    }
}
