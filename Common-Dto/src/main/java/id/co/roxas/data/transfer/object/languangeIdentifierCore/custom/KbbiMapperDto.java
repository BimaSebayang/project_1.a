package id.co.roxas.data.transfer.object.languangeIdentifierCore.custom;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KbbiMapperDto implements Serializable{

	
	private static final long serialVersionUID = -7227342938988057138L;
	private static final String dtoTicketing = Long.toString(serialVersionUID);
	private String headLabel = new String();
	private String actualRegex = new String();
	private Map<String, String> exampleDetailHeadLabel = new HashMap<String, String>();
	public String getHeadLabel() {
		return headLabel;
	}
	public void setHeadLabel(String headLabel) {
		this.headLabel = headLabel;
	}

	public Map<String, String> getExampleDetailHeadLabel() {
		return exampleDetailHeadLabel;
	}
	public void setExampleDetailHeadLabel(Map<String, String> exampleDetailHeadLabel) {
		this.exampleDetailHeadLabel = exampleDetailHeadLabel;
	}
	public static String getDtoticketing() {
		return dtoTicketing;
	}
	public String getActualRegex() {
		return actualRegex;
	}
	public void setActualRegex(String actualRegex) {
		this.actualRegex = actualRegex;
	}

	
	
	
}
