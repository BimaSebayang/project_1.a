package id.co.roxas.user.data.activation.core.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Tbl_Master")
public class TblMaster {

	@Id
	@Column(name="master_id", length=20, updatable=false)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String masterId;
	
	@Column(name="master_code", length = 20, unique=true, updatable=false)
	private String masterCode;
	
	@Column(name="master_code_desc", length = 225, unique=true)
	private String masterCodeDesc;

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getMasterCode() {
		return masterCode;
	}

	public void setMasterCode(String masterCode) {
		this.masterCode = masterCode;
	}

	public String getMasterCodeDesc() {
		return masterCodeDesc;
	}

	public void setMasterCodeDesc(String masterCodeDesc) {
		this.masterCodeDesc = masterCodeDesc;
	}
	
	
}
