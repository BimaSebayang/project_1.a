package id.co.roxas.lang.identifier.core.lib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;

public class LevensteinDistance {

	private static int getMinValue(int... i) {
		int temp = Integer.MAX_VALUE;
		for (int j : i) {
			if (temp > j) {
				temp = j;
			}
		}
		return temp;
	}

	private static int getMaxValue(int... i) {
		int temp = 0;

		for (int j : i) {
			if (temp < j) {
				temp = j;
			}
		}
		return temp;
	}

	public static void main(String[] args) {
          Map<String, String> map = new HashMap<>();
          map.put("betul", "sudah benar banget");
          map.put("tepat", "pas kena banget");
          map.put("pas", "tepat banget");
          map.put("benar", "sudah betul banget");
          
          System.err.println(new Gson().toJson(collectAllTwoSlidingValue("yes banget", 
        		  map)));
          
    //      System.out.println(new Gson().toJson(TwoSlidingValue("pas kena", "sudah betul banget")));
	}

	public static List<String> cuttingString(String w) {
		Set<String> str = new HashSet<>();
		int lengW = w.toCharArray().length;

		for (int i = 1; i <= lengW; i++) {
			for (int j = 0; j < lengW - i + 1; j++) {
				str.add(w.substring(j, j + i));
			}
		}
		return new ArrayList<>(str);
	}

	public static List<String> collectAllLevenstheinLevel(String queryWord, List<String> allWords) {
		List<LevenstheinClass> levenstheinClasses = new ArrayList<>();
		List<Integer> allLevenValue = new ArrayList<>();
		for (String word : allWords) {
			int h = LevenstheinValue(word, queryWord);
			levenstheinClasses.add(new LevenstheinClass(queryWord, word, h));
			allLevenValue.add(h);
		}
		int minLevensthein = Collections.min(allLevenValue);
		System.err.println("min leven " + minLevensthein);
		Set<String> streams = new HashSet<>();
		for (LevenstheinClass levenstheinClass : levenstheinClasses) {
			if (levenstheinClass.getLevenstheinDistance().intValue() == minLevensthein) {
				streams.add(levenstheinClass.getWord());
			}
		}

		List<String> strs = new ArrayList<>(streams);
		return strs;
	}

	public static List<TwoSlidingClass> collectAllTwoSlidingValue(String queryWord, Map<String, String> wordsWithMeaning) {
		List<TwoSlidingClass> twoSlidingClassess = new ArrayList<>();
		List<Integer> allTwoSlidingValue = new ArrayList<>();
        for (Entry<String, String> wwm : wordsWithMeaning.entrySet()) {
        	TwoSlidingClass resultSliding = TwoSlidingValue(queryWord, wwm.getValue());
			int h = resultSliding.getLengSubQuery();
			twoSlidingClassess.add(new TwoSlidingClass
					(wwm.getKey(), wwm.getValue(),resultSliding.getSubquery(), 
							h));
			allTwoSlidingValue.add(h);
		}
        int maxSlidingValue = Collections.max(allTwoSlidingValue);
        System.err.println(maxSlidingValue);
        List<TwoSlidingClass> classes = new ArrayList<>();
        for (TwoSlidingClass tsc : twoSlidingClassess) {
			if(tsc.getLengSubQuery()==maxSlidingValue) {
				classes.add(tsc);
			}
		}
		return classes;
	}

	private static TwoSlidingClass TwoSlidingValue(String w1, String w2) {
		int lengW1 = w1.toCharArray().length;
		int lengW2 = w2.toCharArray().length;

		if (lengW2 >= lengW1) {
			return TwoSlidingValueMin(w1, w2);
		} else {
			return TwoSlidingValueMin(w2, w1);
		}
	}

	private static TwoSlidingClass TwoSlidingValueMin(String w1, String w2) {
		List<Integer> sorted = new ArrayList<>();
		List<String> ls = cuttingString(w2);
		TwoSlidingClass twoSlidingClass = new TwoSlidingClass();
		for (String l : ls) {
			
			if (w1.contains(l)) {
				
				int len = l.toCharArray().length;
				System.out.println(w1 + " " + l + " === length : " + len);
				if (twoSlidingClass.getLengSubQuery() < len) {
					twoSlidingClass = new TwoSlidingClass(l, len);
				}
			}
		}

		return twoSlidingClass;
	}

	private static int LevenstheinValue(String w1, String w2) {
		char[] chW1 = w1.toLowerCase().toCharArray();
		char[] chW2 = w2.toLowerCase().toCharArray();
		int[][] dist = new int[chW1.length + 1][chW2.length + 1];

		for (int i = 0; i < chW1.length + 1; i++) {
			for (int j = 0; j < chW2.length + 1; j++) {
				int val = 0;

				if (getMinValue(i, j) != 0) {
					int cost = 0;
					if (chW1[i - 1] != chW2[j - 1]) {
						cost = 1;
					}
					val = getMinValue(dist[i - 1][j - 1] + cost, dist[i - 1][j] + 1, dist[i][j - 1] + 1);
				} else {
					val = getMaxValue(i, j);
				}

				dist[i][j] = val;

			}
		}
//		
		for (int i = 0; i < chW1.length + 1; i++) {
			for (int j = 0; j < chW2.length + 1; j++) {
			}
		}
//		
		return dist[chW1.length][chW2.length];
	}

}
