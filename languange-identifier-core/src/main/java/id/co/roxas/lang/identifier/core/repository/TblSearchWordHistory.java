package id.co.roxas.lang.identifier.core.repository;

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

import id.co.roxas.lang.identifier.core.config.StringPrefixedSequenceIdGenerator;

@Entity
@Table(name="Tbl_Search_Word_History")
public class TblSearchWordHistory {
	@Id
	@Column(name="history_id", length=100, updatable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "history_id")
    @GenericGenerator(
        name = "history_id", 
        strategy = "id.co.roxas.lang.identifier.core.config.StringPrefixedSequenceIdGenerator", 
        parameters = {@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "100"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "HIST"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%096d")}
        )
	private String historyId;
	
	@Column(name="search_word", length=50, nullable=false)
	private String searchWord;
	
	@Column(name="user_id", length=50, nullable=false)
	private String userId;
	
	@Column(name="existing_meaning", nullable=false)
	private int existingMeaning;
	
	@Column(name="needMeaning")
	private int needMeaning;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate; 
	
	@Column(name="meaning_searcher")
	private String meaningSearcher;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="meaning_find_date")
	private Date meaningFindDate;

	public String getHistoryId() {
		return historyId;
	}

	public void setHistoryId(String historyId) {
		this.historyId = historyId;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getExistingMeaning() {
		return existingMeaning;
	}

	public void setExistingMeaning(int existingMeaning) {
		this.existingMeaning = existingMeaning;
	}

	public int getNeedMeaning() {
		return needMeaning;
	}

	public void setNeedMeaning(int needMeaning) {
		this.needMeaning = needMeaning;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getMeaningSearcher() {
		return meaningSearcher;
	}

	public void setMeaningSearcher(String meaningSearcher) {
		this.meaningSearcher = meaningSearcher;
	}

	public Date getMeaningFindDate() {
		return meaningFindDate;
	}

	public void setMeaningFindDate(Date meaningFindDate) {
		this.meaningFindDate = meaningFindDate;
	} 
	
	
}
