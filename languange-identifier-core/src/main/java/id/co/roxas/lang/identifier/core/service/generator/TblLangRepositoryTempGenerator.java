package id.co.roxas.lang.identifier.core.service.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import id.co.roxas.lang.identifier.core.dao.TblLangRepositoryTempDao;
import id.co.roxas.lang.identifier.core.service.BaseService;

@Service
public class TblLangRepositoryTempGenerator extends BaseService{
     
	    @Autowired
	    private TblLangRepositoryTempDao tblLangRepositoryTempDao;
	    public String checkEssentialId(String prefix, int numberFormat) {
	    	String lastNumberId  = tblLangRepositoryTempDao.getLastId().replaceAll("[^\\d.]", "");
	    	int number = Integer.parseInt(lastNumberId)+1;
	    	int lastFormat = numberFormat - prefix.length() - String.valueOf(number).length();
	    	String number0 = "";
	    	for (int i = 1; i < lastFormat; i++) {
				number0 += "0";
			}
	    	return  prefix+number0+String.valueOf(number);
	    }
	    
}
