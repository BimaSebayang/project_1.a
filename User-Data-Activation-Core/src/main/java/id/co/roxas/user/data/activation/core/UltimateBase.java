package id.co.roxas.user.data.activation.core;

import java.util.Date;

import org.springframework.stereotype.Component;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class UltimateBase {
	protected static final int REPOSITORY_TRANSACTION_SUCCESS = 1;
	protected static final int UNRECOGNIZE_ID = 1;
	protected static final String RESPONSE_BAD_UUID = "Bad Uuid Class Connector";
	protected static final String RESPONSE_BAD_AUTHOR = "Bad Authorization User";
	protected static final String SUCCESS_RETRIEVE = "Data Retrieve Success";
	protected MapperFacade mapperFacade = new DefaultMapperFactory.Builder().build().getMapperFacade();
	protected final Date dateNow = new Date(); 
}
