package id.co.roxas.lang.identifier.core.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import id.co.roxas.lang.identifier.core.config.StringPrefixedSequenceIdGenerator;

@Entity
@Table(name="Tbl_Combination_Word_Repository")
public class TblCombinationWordRepository {

	@Id
	@Column(name="comb_id", length=100, updatable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comb_id")
    @GenericGenerator(
        name = "comb_id", 
        strategy = "id.co.roxas.lang.identifier.core.config.StringPrefixedSequenceIdGenerator", 
        parameters = {@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "100"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "COMB"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%096d")}
        )
	private String combId;
	
	@Column(name="comb_word", length=100, nullable=false)
	private String combWord;
	
	@Column(name="count_word",nullable=false)
	private Integer countWord;
	
	@Column(name="created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(name = "created_by",length = 20)
	private String createdBy;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "combId")
	List<TblUsingCharacterDetail> tblUsingCharacterDetails;

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

	public List<TblUsingCharacterDetail> getTblUsingCharacterDetails() {
		return tblUsingCharacterDetails;
	}

	public void setTblUsingCharacterDetails(List<TblUsingCharacterDetail> tblUsingCharacterDetails) {
		this.tblUsingCharacterDetails = tblUsingCharacterDetails;
	}

	public Integer getCountWord() {
		return countWord;
	}

	public void setCountWord(Integer countWord) {
		this.countWord = countWord;
	}

	
	
}
