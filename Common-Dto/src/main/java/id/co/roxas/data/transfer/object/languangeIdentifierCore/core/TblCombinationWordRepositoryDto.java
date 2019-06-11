package id.co.roxas.data.transfer.object.languangeIdentifierCore.core;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TblCombinationWordRepositoryDto implements Serializable {

	private static final long serialVersionUID = 957635293342837576L;
	private static final String dtoTicketing = "957635293342837576L";
	private String combId;
	private String combWord;

	private Integer countWord;

	private Date createdDate;

	private String createdBy;

	private List<TblUsingCharacterDetailDto> tblUsingCharacterDetails;

	public String getCombId() {
		return combId;
	}

	public void setCombId(String combId) {
		this.combId = combId;
	}

	public String getCombWord() {
		return combWord;
	}

	public void setCombWord(String combWord) {
		this.combWord = combWord;
	}

	public Integer getCountWord() {
		return countWord;
	}

	public void setCountWord(Integer countWord) {
		this.countWord = countWord;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public List<TblUsingCharacterDetailDto> getTblUsingCharacterDetails() {
		return tblUsingCharacterDetails;
	}

	public void setTblUsingCharacterDetails(List<TblUsingCharacterDetailDto> tblUsingCharacterDetails) {
		this.tblUsingCharacterDetails = tblUsingCharacterDetails;
	}

	public static String getDtoticketing() {
		return dtoTicketing;
	}

	
	
}
