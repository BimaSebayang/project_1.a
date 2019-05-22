package id.co.roxas.user.data.activation.core.repository;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import id.co.roxas.user.data.activation.core.config.StringPrefixedSequenceIdGenerator;

@Entity
@Table(name = "Tbl_Uaa_History")
public class TblUaaHistory {

	@Id
	@Column(name="history_id", length=20, updatable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "history_id")
    @GenericGenerator(
        name = "history_id", 
        strategy = "id.co.roxas.user.data.activation.core.config.StringPrefixedSequenceIdGenerator", 
        parameters = {@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "20"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "HIST"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%016d")}
        )
	private String historyId;

	@Column(columnDefinition = "text", name = "data_bfr")
	private String dataBfr;
	
	@Column(columnDefinition = "text", name = "data_now")
	private String dataNow;

	@Column(name = "history_action", length = 20)
	private String historyAction;
	
	@Column(name = "trx_id", length = 20)
	private String trxId;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(name = "created_by",length = 20)
	private String createdBy;

	public String getHistoryId() {
		return historyId;
	}

	public void setHistoryId(String historyId) {
		this.historyId = historyId;
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

	public String getTrxId() {
		return trxId;
	}

	public void setTrxId(String trxId) {
		this.trxId = trxId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	
	
	

}
