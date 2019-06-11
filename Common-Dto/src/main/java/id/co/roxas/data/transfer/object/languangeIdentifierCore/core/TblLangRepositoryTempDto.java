package id.co.roxas.data.transfer.object.languangeIdentifierCore.core;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TblLangRepositoryTempDto implements Serializable{

	private static final long serialVersionUID = -1049072435522239274L;
	private static final String dtoTicketing = "-1049072435522239274L";
	private String langId;
	private String langName;
	private String langDesc;
	private String langResource;
	private Date createdDate;
	private String createdBy;
	List<TblUsingCharacterDetailDto> tblUsingCharacterDetails;
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
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
