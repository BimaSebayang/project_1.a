package id.co.roxas.lang.identifier.core.lib;

public class LevenstheinClass implements Comparable<LevenstheinClass>{
	private String queryWords;
	private String word;
	private Integer levenstheinDistance;
	
	
	public LevenstheinClass(String queryWords, String word, Integer levenstheinDistance) {
		this.queryWords = queryWords;
		this.word = word;
		this.levenstheinDistance = levenstheinDistance;
	}

	public String getQueryWords() {
		return queryWords;
	}

	public String getWord() {
		return word;
	}

	public Integer getLevenstheinDistance() {
		return levenstheinDistance;
	}

	@Override
	public int compareTo(LevenstheinClass o) {
		
			return 0 - o.getLevenstheinDistance();
		
	}
	

}
