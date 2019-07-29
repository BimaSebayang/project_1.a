package id.co.roxas.lang.identifier.core.lib.fuzzywuzzy.core.me.xdrops.diffutils.models;

public class ExtractedResult<T> implements Comparable<ExtractedResult> {
	private String word;
	private T ClassNext;
    private String meaning;
    private int score;
    private int index;
    private int dtlId;

    public ExtractedResult(String string, int score, int index) {
        this.meaning = string;
        this.score = score;
        this.index = index;
    }
    
    

    public ExtractedResult(String string2, String string, int score, int index) {
		super();
		this.word = string2;
		this.meaning = string;
		this.score = score;
		this.index = index;
	}
    
    public ExtractedResult(T string2, String string, int score, int index) {
		super();
		this.ClassNext = string2;
		this.meaning = string;
		this.score = score;
		this.index = index;
    }



	@Override
    public int compareTo(ExtractedResult o) {
        return Integer.compare(this.getScore(), o.getScore());
    }

    public String getString() {
        return meaning;
    }

    public void setString(String string) {
        this.meaning = string;
    }

    public int getScore() {
        return score;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "(string: " + meaning + ", score: " + score + ", index: " + index+ ")";
    }

	public String getString2() {
		return word;
	}

	public void setString2(String string2) {
		this.word = string2;
	}

	public int getDtlId() {
		return dtlId;
	}

	public void setDtlId(int dtlId) {
		this.dtlId = dtlId;
	}



	public T getClassNext() {
		return ClassNext;
	}



	public void setClassNext(T classNext) {
		ClassNext = classNext;
	}
    
	
    
}