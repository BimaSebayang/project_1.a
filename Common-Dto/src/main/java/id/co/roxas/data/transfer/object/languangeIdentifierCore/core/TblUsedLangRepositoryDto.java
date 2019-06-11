package id.co.roxas.data.transfer.object.languangeIdentifierCore.core;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TblUsedLangRepositoryDto implements Serializable {

	private static final long serialVersionUID = -865477617683498060L;
	private static final String dtoTicketing = "-865477617683498060L";

	private String langId;

	private int isActive;
	private String langName;

	private String langDesc;

	private String langResource;

	private int countAlphabet;
	private String indexAlphabet;

	private Date createdDate;

	private String createdBy;

	private String tblNameResources; // nama table yang diambil dari database/table mana

	private String langIdResc; // id Data yang diambil dari database/table mana

	List<TblUsingCharacterDetailDto> tblUsingCharacterDetailDtos;

	public String getLangId() {
		return langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getLangName() {
		return langName;
	}

	public void setLangName(String langName) {
		this.langName = langName;
	}

	public String getLangDesc() {
		return langDesc;
	}

	public void setLangDesc(String langDesc) {
		this.langDesc = langDesc;
	}

	public String getLangResource() {
		return langResource;
	}

	public void setLangResource(String langResource) {
		this.langResource = langResource;
	}

	public int getCountAlphabet() {
		return countAlphabet;
	}

	public void setCountAlphabet(int countAlphabet) {
		this.countAlphabet = countAlphabet;
	}

	public String getIndexAlphabet() {
		return indexAlphabet;
	}

	public void setIndexAlphabet(String indexAlphabet) {
		this.indexAlphabet = indexAlphabet;
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

	public String getTblNameResources() {
		return tblNameResources;
	}

	public void setTblNameResources(String tblNameResources) {
		this.tblNameResources = tblNameResources;
	}

	public String getLangIdResc() {
		return langIdResc;
	}

	public void setLangIdResc(String langIdResc) {
		this.langIdResc = langIdResc;
	}

	public List<TblUsingCharacterDetailDto> getTblUsingCharacterDetailDtos() {
		return tblUsingCharacterDetailDtos;
	}

	public void setTblUsingCharacterDetailDtos(List<TblUsingCharacterDetailDto> tblUsingCharacterDetailDtos) {
		this.tblUsingCharacterDetailDtos = tblUsingCharacterDetailDtos;
	}

	public static String getDtoticketing() {
		return dtoTicketing;
	}
	
	
}
