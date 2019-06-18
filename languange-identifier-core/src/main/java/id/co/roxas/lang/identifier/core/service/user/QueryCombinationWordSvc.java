package id.co.roxas.lang.identifier.core.service.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.co.roxas.data.transfer.object.UserDataActivation.custom.PageRequestCustom;
import id.co.roxas.data.transfer.object.languangeIdentifierCore.core.TblLangRepositoryTempDtlDto;
import id.co.roxas.data.transfer.object.languangeIdentifierCore.core.TblLangRepositoryTempDto;
import id.co.roxas.data.transfer.object.languangeIdentifierCore.core.TblSearchWordHistoryDto;
import id.co.roxas.lang.identifier.core.dao.TblLangRepositoryTempDao;
import id.co.roxas.lang.identifier.core.dao.TblLangRepositoryTempDtlDao;
import id.co.roxas.lang.identifier.core.repository.TblLangRepositoryTempDtl;
import id.co.roxas.lang.identifier.core.service.AsynchService;
import id.co.roxas.lang.identifier.core.service.BaseService;

@Service
public class QueryCombinationWordSvc extends BaseService {
	@Autowired
	private TblLangRepositoryTempDtlDao tblLangRepositoryTempDtlDao;

	@Autowired
	private AsynchService asynchService;

	public PageRequestCustom<TblLangRepositoryTempDtlDto> getAllMeaningOfSomeWords(String words, String user,
			Pageable pageable) {
		List<TblLangRepositoryTempDtlDto> tblLangRepositoryTempDtlDtos = new ArrayList<>();
		Page<TblLangRepositoryTempDtl> page = tblLangRepositoryTempDtlDao.getAllMeaningOfSomeWord(words, pageable);
		for (TblLangRepositoryTempDtl tblLangRepositoryTempDtl : page.getContent()) {
			TblLangRepositoryTempDtlDto tempDtlDto = new TblLangRepositoryTempDtlDto();
			tempDtlDto = mapperFacade.map(tblLangRepositoryTempDtl, TblLangRepositoryTempDtlDto.class);
			TblLangRepositoryTempDto tblLangRepositoryTempDto = mapperFacade.map(tblLangRepositoryTempDtl.getTblId(),
					TblLangRepositoryTempDto.class);
			tblLangRepositoryTempDto.setTblUsingCharacterDetails(new ArrayList<>());
			tempDtlDto.setTblId(tblLangRepositoryTempDto);
			tblLangRepositoryTempDtlDtos.add(tempDtlDto);
		}
		asychHistoryQuery(words, tblLangRepositoryTempDtlDtos.size(), user);
		return new PageRequestCustom<>(tblLangRepositoryTempDtlDtos, page.getPageable().getPageSize(),
				page.getTotalPages(), page.getPageable().getPageNumber(), page.getTotalElements(), getSorter(pageable),
				null);
	}

	private void asychHistoryQuery(String usedWord, int count, String user) {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
				TblSearchWordHistoryDto wordHistoryDto = new TblSearchWordHistoryDto();
				if (count == 0) {
					wordHistoryDto.setExistingMeaning(0);
				} else {
					wordHistoryDto.setExistingMeaning(1);
				}
				wordHistoryDto.setCreatedDate(new Date());
				wordHistoryDto.setSearchWord(usedWord);
				wordHistoryDto.setUserId(user);
				return asynchService.asynchHistoryWordsSearcher(wordHistoryDto).get();
			}catch (InterruptedException | ExecutionException e) {
				throw new IllegalStateException(e);
			}
		});
		
	}
}
