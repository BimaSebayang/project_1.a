package id.co.roxas.data.transfer.object.languangeIdentifierCore.core;

import java.io.Serializable;
import java.util.Date;

public class TblLangRepositoryTempDtlDto implements Serializable {

	private static final long serialVersionUID = -8419349046355885844L;
	private static final String dtoTicketing = "-8419349046355885844L";
	private String langIdDtl;

	private String langName;
	private String langDesc;
	private TblLangRepositoryTempDto tblId;
	private String langResource;
	private String roleDetail;
	private Date createdDate;
	private String createdBy;

	public String getLangIdDtl() {
		return langIdDtl;
	}

	public void setLangIdDtl(String langIdDtl) {
		this.langIdDtl = langIdDtl;
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

	public TblLangRepositoryTempDto getTblId() {
		return tblId;
	}

	public void setTblId(TblLangRepositoryTempDto tblId) {
		this.tblId = tblId;
	}

	public String getLangResource() {
		return langResource;
	}

	public void setLangResource(String langResource) {
		this.langResource = langResource;
	}

	public String getRoleDetail() {
		return roleDetail;
	}

	public void setRoleDetail(String roleDetail) {
		this.roleDetail = roleDetail;
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

	public static String getDtoticketing() {
		return dtoTicketing;
	}

}
