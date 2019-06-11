package id.co.roxas.data.transfer.object.languangeIdentifierCore.core;

import java.io.Serializable;
import java.util.Date;

public class TblUsingCharacterDetailDto implements Serializable {

	private static final long serialVersionUID = -4330847308866329959L;

	private static final String dtoTicketing = "-4330847308866329959L";
	private String charId;
	private String usedChar;
	private String roleChar;
	private Date createdDate;
	private String createdBy;
	private int isActive;
	private TblCombinationWordRepositoryDto combId;
	private TblLangRepositoryTempDto langId;
	private TblUsedLangRepositoryDto usedLangId;
	public String getCharId() {
		return charId;
	}
	public void setCharId(String charId) {
		this.charId = charId;
	}
	public String getUsedChar() {
		return usedChar;
	}
	public void setUsedChar(String usedChar) {
		this.usedChar = usedChar;
	}
	public String getRoleChar() {
		return roleChar;
	}
	public void setRoleChar(String roleChar) {
		this.roleChar = roleChar;
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
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public TblCombinationWordRepositoryDto getCombId() {
		return combId;
	}
	public void setCombId(TblCombinationWordRepositoryDto combId) {
		this.combId = combId;
	}
	public TblLangRepositoryTempDto getLangId() {
		return langId;
	}
	public void setLangId(TblLangRepositoryTempDto langId) {
		this.langId = langId;
	}
	public TblUsedLangRepositoryDto getUsedLangId() {
		return usedLangId;
	}
	public void setUsedLangId(TblUsedLangRepositoryDto usedLangId) {
		this.usedLangId = usedLangId;
	}
	public static String getDtoticketing() {
		return dtoTicketing;
	}

	
}
