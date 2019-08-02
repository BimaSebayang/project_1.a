package id.co.roxas.lang.identifier.core.lib.chatbot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomPattern {
	
	public static Map<String, Object> QuestionAndAnswerFinder(String expectedChat,String chat,String focusWords){
		List<String> bussiness = findAllPatternBussiness("{", "}", expectedChat);
		 Map<String, Object> map = new HashMap<>();
		List<String> references = new ArrayList<>();
		for (String buss : bussiness) {
			List<String> pattern = findAllPatternBussiness("", buss, expectedChat);
			String h = pattern.get(0);
			references.addAll(removeWordsInList(pattern, buss));
			expectedChat = expectedChat.replace(h, "");
		}
		
		List<String> ruinWords = findAllPatternBussiness("\"", "\"", chat);
		
		for (int i = 0; i < references.size(); i++) {
			map.put(removUnusedWords(bussiness.get(i), "{","}"), removUnusedWords(ruinWords.get(i), "\"", "\""));
		}
		map.put("focusWords", focusWords);
		return map;
	}
	
	public static Map<String, Object> QuestionAndAnswerFinder2(String expectedChat,String chat,String focusWords){
		List<String> bussiness = findAllPatternBussiness("{", "}", expectedChat);
		 Map<String, Object> map = new HashMap<>();
		List<String> references = new ArrayList<>();
		for (String buss : bussiness) {
			List<String> pattern = findAllPatternBussiness("", buss, expectedChat);
			String h = pattern.get(0);
			references.addAll(removeWordsInList(pattern, buss));
			expectedChat = expectedChat.replace(h, "");
		}
		for (int i = 0; i < references.size(); i++) {
			if(i!=references.size()-1) {
			String component = findAllPatternBussiness(references.get(i).toLowerCase(), references.get(i+1).toLowerCase(),
					chat.toLowerCase()).get(0).trim();	
			map.put(removUnusedWords(bussiness.get(i), "{","}"), removUnusedWords(component, references.get(i).toLowerCase(), references.get(i+1).toLowerCase()));
			}
			else {
				map.put(removUnusedWords(bussiness.get(i), "{","}"), chat.substring(chat.lastIndexOf(references.get(i))+references.get(i).length()).trim());
			}
		}
		map.put("focusWords", focusWords);
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
