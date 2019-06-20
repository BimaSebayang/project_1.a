package id.co.roxas.lang.identifier.core.service.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import id.co.roxas.data.transfer.object.languangeIdentifierCore.custom.TwoSlidingClassDto;
import id.co.roxas.lang.identifier.core.dao.TblLangRepositoryTempDtlDao;
import id.co.roxas.lang.identifier.core.lib.LevensteinDistance;
import id.co.roxas.lang.identifier.core.lib.TwoSlidingClass;
import id.co.roxas.lang.identifier.core.repository.TblLangRepositoryTempDtl;
import id.co.roxas.lang.identifier.core.service.AsynchService;
import id.co.roxas.lang.identifier.core.service.BaseService;

@Service
public class QueryCombinationWordSvc extends BaseService {
	@Autowired
	private TblLangRepositoryTempDtlDao tblLangRepositoryTempDtlDao;

	@Autowired
	private AsynchService asynchService;

	public List<String> allSuggestWords(String words,String user){
		List<String> allWords = new ArrayList<>();
		
		List<String> temp = tblLangRepositoryTempDtlDao.getAllWords();
		
		allWords = LevensteinDistance.collectAllLevenstheinLevel
				(words, temp);
		
		return allWords;
	}
	
	public List<TwoSlidingClassDto> allSynonimsWord(String words,String user){
		List<TwoSlidingClassDto> allWords = new ArrayList<>();
		List<Object[]> temp = tblLangRepositoryTempDtlDao.getAllWordsAndItsMeaning();
		Map<String, String> obj = new HashMap<>();
		for (Object[] o : temp) {
			obj.put((String)o[0], (String)o[1]);
		}
		List<TwoSlidingClass> twss = LevensteinDistance.collectAllTwoSlidingValue(words, obj);
		allWords = mapperFacade.mapAsList(twss, TwoSlidingClassDto.class);
		return allWords;
	}
	
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
