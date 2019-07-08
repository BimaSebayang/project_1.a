package id.co.roxas.data.transfer.object.languangeIdentifierCore.custom;

import java.io.Serializable;

public class ExtractedResultDto implements Serializable{
	private static final long serialVersionUID = 1831705672948797661L;
	private static final String dtoTicketing = "1831705672948797661L";
	private String word;
	private String meaning;
	private int score;
	private int index;
	public String getString() {
		return meaning;
	}
	public void setString(String string) {
		this.meaning = string;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public static String getDtoticketing() {
		return dtoTicketing;
	}
	public String getString2() {
		return word;
	}
	public void setString2(String string2) {
		this.word = string2;
	}
	
	
}
