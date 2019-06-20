package id.co.roxas.data.transfer.object.languangeIdentifierCore.custom;

import java.io.Serializable;

public class TwoSlidingClassDto implements Serializable{

	private static final long serialVersionUID = 8964044841724139494L;
    private static final String dtoTicketing = "8964044841724139494L";
    private String word = "";
	private String wordMeaning = "";
	private String subquery = "";
	private Integer lengSubQuery = 0;
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
	public static String getDtoticketing() {
		return dtoTicketing;
	}
	
	
}
