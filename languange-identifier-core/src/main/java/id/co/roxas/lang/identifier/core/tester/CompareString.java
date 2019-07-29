package id.co.roxas.lang.identifier.core.tester;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import id.co.roxas.lang.identifier.core.lib.LevensteinDistance;

public class CompareString {
	public static void main(String[] args) {
		System.out.println(" --- >   " + new Gson().toJson(stringComparePossible("apakah ibu beli baju", "apakah beli baju")));
	}

	public static List<String> stringComparePossible(String compared, String comparing) {
		//String[] aCompared = compared.split(" ");
		String[] aComparing = comparing.split(" ");
		List<String> str = new ArrayList<>(); 
		for (String acomp : aComparing) {
			if(compared.contains(acomp)) {
				str.add(acomp);
			}
		}
		return str;
	}
}
