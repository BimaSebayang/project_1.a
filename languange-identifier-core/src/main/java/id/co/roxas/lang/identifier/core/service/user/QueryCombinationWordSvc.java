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
import id.co.roxas.data.transfer.object.languangeIdentifierCore.custom.ExtractedResultDto;
import id.co.roxas.data.transfer.object.languangeIdentifierCore.custom.TwoSlidingClassDto;
import id.co.roxas.lang.identifier.core.dao.TblLangRepositoryTempDtlDao;
import id.co.roxas.lang.identifier.core.lib.LevensteinDistance;
import id.co.roxas.lang.identifier.core.lib.TwoSlidingClass;
import id.co.roxas.lang.identifier.core.lib.fuzzywuzzy.core.me.xdrops.diffutils.FuzzySearch;
import id.co.roxas.lang.identifier.core.lib.fuzzywuzzy.core.me.xdrops.diffutils.models.ExtractedResult;
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
	
	public Map<String,List<ExtractedResultDto>> allSynonimsWord(String words,String user){
		List<ExtractedResultDto> allWords = new ArrayList<>();
		
		List<String> meaning = tblLangRepositoryTempDtlDao.getWordsMeaning(words);
		if(meaning==null) {
			return new HashMap<>();
		}
		List<Object[]> temp = tblLangRepositoryTempDtlDao.getAllWordsAndItsMeaning(words);
		Map<String, String> obj = new HashMap<>();
		for (Object[] o : temp) {
			String categorizeChar = LevensteinDistance.replaceUnalphabeticChar((String)o[1]);
			if(categorizeChar.trim().toCharArray().length>=3) {
			obj.put((String)o[0], categorizeChar.trim());
			}
		}
		
		Map<String,List<ExtractedResultDto>> mapResults = new HashMap<>();
		
		for(String str : meaning) {
		List<ExtractedResult> extractedResults = FuzzySearch.extractAllHigherScore
				(str, obj);
		allWords = mapperFacade.mapAsList(extractedResults, ExtractedResultDto.class);
		mapResults.put(LevensteinDistance.replaceUnalphabeticChar(str), allWords);
		}
		
		return mapResults;
	}
	
	public Map<String,List<TwoSlidingClassDto>> allSynonimsWordTwoSlidingDto(String words,String user){
		List<TwoSlidingClassDto> allWords = new ArrayList<>();
	
		List<String> meaning = tblLangRepositoryTempDtlDao.getWordsMeaning(words);
		if(meaning==null) {
			return new HashMap<>();
		}
		List<Object[]> temp = tblLangRepositoryTempDtlDao.getAllWordsAndItsMeaning(words);
		Map<String, String> obj = new HashMap<>();
		for (Object[] o : temp) {
			String categorizeChar = LevensteinDistance.replaceUnalphabeticChar((String)o[1]);
			if(categorizeChar.trim().toCharArray().length>=3) {
			obj.put((String)o[0], categorizeChar.trim());
			}
		}
		
		Map<String,List<TwoSlidingClassDto>> mapResults = new HashMap<>();
		
		for(String str : meaning) {
        List<TwoSlidingClass> twoSlidingClasses =
        		LevensteinDistance.collectAllTwoSlidingValue(LevensteinDistance.replaceUnalphabeticChar(str)
        				, obj);
		allWords = mapperFacade.mapAsList(twoSlidingClasses, TwoSlidingClassDto.class);
		mapResults.put(LevensteinDistance.replaceUnalphabeticChar(str), allWords);
		}
		
		return mapResults;
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
