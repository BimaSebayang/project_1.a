package id.co.roxas.lang.identifier.core.lib.fuzzywuzzy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;

import id.co.roxas.lang.identifier.core.lib.fuzzywuzzy.core.me.xdrops.diffutils.FuzzySearch;
import id.co.roxas.lang.identifier.core.lib.fuzzywuzzy.core.me.xdrops.diffutils.models.ExtractedResult;

public class FuzzyTester {

	public static void main(String[] args) {
		String[] h =  new String[] {"Atlanta Falcons","cowboys junior","New York Jets", "Premier Cowboys", "Dallas Cowboys"};
		List<String> arrs = Arrays.asList(h);
		Collection<String> coll = new ArrayList<>(arrs);
		List<ExtractedResult> choices = 
				FuzzySearch.extractAll("cowboys",coll);
		ExtractedResult extractOne = FuzzySearch.extractOne("cowboys", coll);
	    System.out.println(new Gson().toJson(choices));
	}

}
