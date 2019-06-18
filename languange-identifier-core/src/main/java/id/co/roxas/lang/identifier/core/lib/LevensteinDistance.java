package id.co.roxas.lang.identifier.core.lib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

public class LevensteinDistance {

	private static int getMinValue(int...i) {
		int temp = Integer.MAX_VALUE;
		for (int j : i) {
			if(temp>j) {
				temp = j;
			}
		}
		return temp;
	}
	
	private static int getMaxValue(int...i) {
		int temp = 0;
		
		for (int j : i) {
			if(temp<j) {
				temp = j;
			}
		}
		return temp;
	}
	
	public static void main(String[] args) {
		System.out.println(LevenstheinValue("gagaha", "gagah"));
	}
	
	public static List<String> collectAllLevenstheinLevel(String queryWord, List<String> allWords){

		    List<LevenstheinClass> levenstheinClasses = new ArrayList<>();
		    List<Integer> allLevenValue = new ArrayList<>();
		    for (String word : allWords) {
			  	int h = LevenstheinValue(word, queryWord);
			  	levenstheinClasses.add(new LevenstheinClass(queryWord, word, h));
			  	allLevenValue.add(h);
			}
		    int minLevensthein = Collections.min(allLevenValue);
		    System.err.println("min leven " + minLevensthein );
		    Set<String> streams = new HashSet<>();
		    for (LevenstheinClass levenstheinClass : levenstheinClasses) {
		        if(levenstheinClass.getLevenstheinDistance().intValue() == minLevensthein) {
		    		streams.add(levenstheinClass.getWord());
		    	}
			}
		    
		    List<String> strs = new ArrayList<>(streams);
		    return strs;
	}
	
	
	private static int LevenstheinValue(String w1, String w2) {
		char[] chW1 = w1.toLowerCase().toCharArray();
		char[] chW2 = w2.toLowerCase().toCharArray();
		int[][] dist = new int[chW1.length+1][chW2.length+1];
		
		for (int i = 0; i < chW1.length+1; i++) {
			for(int j=0; j < chW2.length+1;j++) {
				int val = 0;
				
				if(getMinValue(i,j)!=0) {
					  int cost = 0;
					  if(chW1[i-1]!=chW2[j-1]) {
						  cost = 1;
					  }
					 val  = getMinValue(dist[i-1][j-1]+cost,
							   dist[i-1][j]+1,dist[i][j-1]+1);
				}
				else {
					val = getMaxValue(i,j);
				}
				
				dist[i][j] = val;
				
			}
		}
//		
		for (int i = 0; i < chW1.length+1; i++) {
			for(int j=0; j < chW2.length+1;j++) {
			}
		}
//		
		return dist[chW1.length][chW2.length];
	}
	
}
