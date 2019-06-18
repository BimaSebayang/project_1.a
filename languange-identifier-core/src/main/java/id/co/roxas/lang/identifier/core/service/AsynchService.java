package id.co.roxas.lang.identifier.core.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import id.co.roxas.data.transfer.object.languangeIdentifierCore.core.TblSearchWordHistoryDto;
import id.co.roxas.lang.identifier.core.dao.TblSearchWordHistoryDao;
import id.co.roxas.lang.identifier.core.repository.TblSearchWordHistory;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;


@Service
public class AsynchService{

	@Autowired
	private TblSearchWordHistoryDao tblSearchWordHistoryDao;
	
	protected MapperFacade mapperFacade = new DefaultMapperFactory.Builder().build().getMapperFacade();

	
	@Async("asyncExecutor")
	public CompletableFuture<String> asynchHistoryWordsSearcher(TblSearchWordHistoryDto tblSearchWordHistoryDto ){
		TblSearchWordHistory tblSearchWordHistory =  mapperFacade.map
					(tblSearchWordHistoryDto, TblSearchWordHistory.class);
			tblSearchWordHistoryDao.save(tblSearchWordHistory);
		return CompletableFuture.completedFuture("DONE");
	}
}
