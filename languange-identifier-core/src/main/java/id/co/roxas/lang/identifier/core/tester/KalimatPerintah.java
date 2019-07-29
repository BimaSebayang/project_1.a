package id.co.roxas.lang.identifier.core.tester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

public class KalimatPerintah {

	public static void main(String[] args) {
		List<Object> arr = new ArrayList<>();
		arr.add("Tanya");
		arr.add("jawab");
		arr.add("ada");
		
		//System.out.println("gson : " + new Gson().toJson(arr.subList(2, 3)));
		permutation(new ArrayList<>(),arr);
	}

	public static void permutation(List<Object> resultObjs, List<Object> objs) {
	
	
		int n = objs.size();
		if (n == 0) {
			System.out.println(new Gson().toJson(resultObjs));
			resultObjs = new ArrayList<>();
		} else {
			for (int h = 0; h < n; h++) {
				resultObjs.add(objs.get(h));
				List<Object> temps = new ArrayList<>();
				temps.addAll(objs.subList(0, h));
				temps.addAll(objs.subList(h+1, n));
				System.out.println("untuk h : " + h + " : " + new Gson().toJson(temps));
				permutation(resultObjs, temps);
			}

		}

	}

	private static List<String> findAllPatternBussiness(String regexstart, String regexEnd, String word) {
		List<String> patterns = new ArrayList<>();
		String patternStr = Pattern.quote(regexstart) + "(.*?)" + Pattern.quote(regexEnd);
		// System.err.println(word);
		Pattern p = Pattern.compile(patternStr);
		Matcher m = p.matcher(word);
		while (m.find()) {
			patterns.add(m.group());
		}

		return patterns;
	}
}
