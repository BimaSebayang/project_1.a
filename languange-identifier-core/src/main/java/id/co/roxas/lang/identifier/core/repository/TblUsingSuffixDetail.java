package id.co.roxas.lang.identifier.core.repository;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import id.co.roxas.lang.identifier.core.config.StringPrefixedSequenceIdGenerator;

@Entity
@Table(name="Tbl_Using_Suffix_Detail")
public class TblUsingSuffixDetail {
	@Id
	@Column(name="suffix_id", length=100, updatable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "suffix_id")
    @GenericGenerator(
        name = "suffix_id", 
        strategy = "id.co.roxas.lang.identifier.core.config.StringPrefixedSequenceIdGenerator", 
        parameters = {@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "100"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "SUFF"),
        		@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%096d")}
        )
	private String suffixId;
	
	@Column(name="used_suffix", length=100, updatable=false)
	private String usedSuffix;
	
	@Column(name="role_suffix", length=100,updatable=false)
	private String roleSuffix;
	
	@Column(name="created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(name = "isActive",length = 20)
	private int isActive;
	
	@Column(name = "created_by",length = 20)
	private String createdBy;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "lang_id", nullable=true,referencedColumnName = "lang_id")
	private TblLangRepositoryTemp langId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "used_lang_id", nullable=true,referencedColumnName = "lang_id")
	private TblUsedLangRepository usedLangId;
}
