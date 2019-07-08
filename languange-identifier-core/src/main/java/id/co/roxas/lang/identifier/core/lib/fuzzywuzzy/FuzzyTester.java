package id.co.roxas.lang.identifier.core.lib.fuzzywuzzy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import id.co.roxas.lang.identifier.core.lib.fuzzywuzzy.core.me.xdrops.diffutils.FuzzySearch;
import id.co.roxas.lang.identifier.core.lib.fuzzywuzzy.core.me.xdrops.diffutils.models.ExtractedResult;

public class FuzzyTester {

	public static void main(String[] args) {
//		String[] h =  new String[] {"Atlanta Falcons","cowboys junior","New York Jets", "Premier Cowboys", "Dallas Cowboys"};
//		
//		List<String> arrs = Arrays.asList(h);
//		Collection<String> coll = new ArrayList<>(arrs);
//		List<ExtractedResult> choices = 
//				FuzzySearch.extractSorted("cowboys jun",coll);
//		List<ExtractedResult> extractOne = FuzzySearch.extractTop
//				("cowboys jun", coll, Integer.MAX_VALUE);
//		 System.out.println(new Gson().toJson(extractOne));
//	    System.out.println(new Gson().toJson(choices));
		Map<String, String> mapper = new HashMap<>();
		mapper.put("bakh", "arkauntungbahagia");
		mapper.put("belalang", "bahagiasekali");
		mapper.put("bata-bata", "a");
		
		List<ExtractedResult> extractAll = FuzzySearch.extractAllHigherScore("bahagia", mapper);
		ExtractedResult topResult = extractAll.get(0);
		
		System.out.println(new Gson().toJson(extractAll));
		System.out.println("top : " + topResult.getScore());
	}

}
