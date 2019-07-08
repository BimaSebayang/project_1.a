package id.co.roxas.lang.identifier.core.lib;

public class TwoSlidingClass {
	private String word = "";
	private String wordMeaning = "";
	private String subquery = "";
	private Integer lengSubQuery = 0;
	
	public TwoSlidingClass() {
		
	}

	public TwoSlidingClass(String word, String wordMeaning, String subquery, Integer lengSubQuery)
	{
		this.word = word;
		this.wordMeaning = wordMeaning;
		this.subquery = subquery;
		this.lengSubQuery = lengSubQuery;
	}

	public TwoSlidingClass(String subquery, Integer lengSubQuery) {
		this.subquery = subquery;
		this.lengSubQuery = lengSubQuery;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getWordMeaning() {
		return wordMeaning;
	}

	public void setWordMeaning(String wordMeaning) {
		this.wordMeaning = wordMeaning;
	}

	public String getSubquery() {
		return subquery;
	}

	public void setSubquery(String subquery) {
		this.subquery = subquery;
	}

	public Integer getLengSubQuery() {
		return lengSubQuery;
	}

	public void setLengSubQuery(Integer lengSubQuery) {
		this.lengSubQuery = lengSubQuery;
	}
	
	
	
    
}