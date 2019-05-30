package id.co.roxas.user.data.activation.core;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import id.co.roxas.user.data.activation.core.config.MapperPageClass;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class UltimateBase {
	protected static final int REPOSITORY_TRANSACTION_SUCCESS = 1;
	protected static final int UNRECOGNIZE_ID = 1;
	protected static final String RESPONSE_BAD_UUID = "Bad Uuid Class Connector";
	protected static final String RESPONSE_BAD_AUTHOR = "Bad Authorization User";
	protected static final String SUCCESS_RETRIEVE = "Data Retrieve Success";
	protected static final String SUCCESS_SAVE = "Save Success";
	protected static final String INSUCCESS_SAVE = "Save Insuccess";
	protected static final String SUCCESS_UPDATE = "Update Success";
	protected static final String INSUCCESS_UPDATE = "Update Insuccess";
	protected static final String SUCCESS_DELETE = "Delete success";
	protected static final String INSUCCESS_DELETE = "Delete Insuccess";
	protected MapperFacade mapperFacade = new DefaultMapperFactory.Builder().build().getMapperFacade();
	protected final MapperPageClass mapperPageClass = new MapperPageClass();
	protected final Date dateNow = new Date(); 
	
	protected String staplingWords(String word, String nvl) {
		return nvl+word+nvl;
	}
	
	protected <T> List<T> reloadUniqueValue(List<T> value) {
		Set<T> uniqueVal = new HashSet<T>(value);
		List<T> finalList = new ArrayList<>(uniqueVal);
		return finalList;
	}
}
