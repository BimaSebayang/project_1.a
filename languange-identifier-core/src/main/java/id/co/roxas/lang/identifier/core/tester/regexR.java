package id.co.roxas.lang.identifier.core.tester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

public class regexR {

	public static void main(String[] args) {
		String chat = "jika ditanya apa kabar kamu? maka jawab baik baik saja kakak";
	    Map<String, String> mss = QuestionAndAnswerFinder("Jika ditanya {question} maka jawab {answer}", 
	    		chat);
	    System.out.println(new Gson().toJson(mss));
		
		
		//System.out.println(chat.lastIndexOf("maka jawab"));
	}
	
	private static Map<String, String> QuestionAndAnswerFinder(String expectedChat,String chat){
		List<String> bussiness = findAllPatternBussiness("{", "}", expectedChat);
		expectedChat = expectedChat.toLowerCase();
		chat = chat.toLowerCase();
		 Map<String, String> map = new HashMap<>();
		List<String> references = new ArrayList<>();
		for (String buss : bussiness) {
			List<String> pattern = findAllPatternBussiness("", buss, expectedChat);
			String h = pattern.get(0);
			references.addAll(removeWordsInList(pattern, buss));
			expectedChat = expectedChat.replace(h, "");
		}
		for (int i = 0; i < references.size(); i++) {
			if(i!=references.size()-1) {
			String component = findAllPatternBussiness(references.get(i), references.get(i+1),
					chat).get(0).trim();	
			map.put(bussiness.get(i), removUnusedWords(component, references.get(i), references.get(i+1)));
			}
			else {
				map.put(bussiness.get(i), chat.substring(chat.lastIndexOf(references.get(i))+references.get(i).length()).trim());
			}
		}
		
		return map;
	}
	
	private static String removUnusedWords(String words, String... removalWords){
		for (String rw : removalWords) {
			words = words.replace(rw, "");
		}
		return words.trim();
	}
	
	private static List<String> removeWordsInList(List<String> list , String word) {
		List<String> temp = new ArrayList<>(list);
		list.clear();
		for (String t : temp) {
			list.add(t.replace(word, "").trim());
		}
		return list;
	}
	
	
	private static List<String> findAllPatternBussiness(String regexstart, String regexEnd, String word){
		List<String> patterns = new ArrayList<>();
		String patternStr = Pattern.quote(regexstart)+"(.*?)"+Pattern.quote(regexEnd);
		Pattern p = Pattern.compile(patternStr);
		Matcher m = p.matcher(word);
		while (m.find()) {
			patterns.add(m.group());
		}
		
		return patterns;
	}

}
