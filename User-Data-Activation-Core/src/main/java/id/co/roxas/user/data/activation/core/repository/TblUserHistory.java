package id.co.roxas.user.data.activation.core.repository;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Tbl_User_History")
public class TblUserHistory {
	@Id
	@Column(name = "history_id", length = 20,updatable=false)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String historyId;

	@Column(name = "user_id", length = 20)
	private String userId;

	@Column(columnDefinition = "text", name = "data_bfr")
	private String dataBfr;
	
	@Column(columnDefinition = "text", name = "data_now")
	private String dataNow;

	@Column(name = "history_action", length = 20)
	private String historyAction;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	public String getHistoryId() {
		return historyId;
	}

	public void setHistoryId(String historyId) {
		this.historyId = historyId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDataBfr() {
		return dataBfr;
	}

	public void setDataBfr(String dataBfr) {
		this.dataBfr = dataBfr;
	}

	public String getDataNow() {
		return dataNow;
	}

	public void setDataNow(String dataNow) {
		this.dataNow = dataNow;
	}

	public String getHistoryAction() {
		return historyAction;
	}

	public void setHistoryAction(String historyAction) {
		this.historyAction = historyAction;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	

}
