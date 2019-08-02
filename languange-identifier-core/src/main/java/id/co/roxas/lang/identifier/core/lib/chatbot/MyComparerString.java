package id.co.roxas.lang.identifier.core.lib.chatbot;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class MyComparerString {
	public static List<String> stringComparePossible(String compared, String comparing) {
		// String[] aCompared = compared.split(" ");
		String[] aComparing = comparing.split(" ");
		List<String> str = new ArrayList<>();
		if (compared != null) {
			compared = compared.trim().replaceAll("[^A-Za-z0-9]", "");
			for (String acomp : aComparing) {
				acomp = acomp.trim().replaceAll("[^A-Za-z0-9]", "");
				if (compared.contains(acomp)) {
					str.add(acomp);
				}
			}
		}
		return str;
	}

	public static boolean listStringComparer(List<String> compared, List<String> comparing) {
		if (compared.size() != 0 && comparing.size() != 0) {
			if (compared.size() > comparing.size()) {
				return compared.containsAll(comparing);
			} else {
				return comparing.containsAll(compared);
			}
		} else {
			return false;
		}

	}
}
