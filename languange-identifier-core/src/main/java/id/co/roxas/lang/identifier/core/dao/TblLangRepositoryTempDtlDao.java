package id.co.roxas.lang.identifier.core.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.roxas.lang.identifier.core.repository.TblLangRepositoryTempDtl;

@Repository
public interface TblLangRepositoryTempDtlDao extends JpaRepository<TblLangRepositoryTempDtl, String>{

	@Query("select a.langName from TblLangRepositoryTempDtl a where a.roleDetail = 'MEANING' ")
	public List<String> getAllWords();
	
	@Query("select a from TblLangRepositoryTempDtl a"
			+ " where "
			+ " upper(a.langName) like upper(concat('%',?1,'%'))"
			+ " and"
			+ " a.tblId in (select b from TblLangRepositoryTemp b where "
			+ " upper(?1) like upper(concat('%',b.langName,'%')) ) "
			+ " and "
			+ " a.roleDetail = 'MEANING'")
	public Page<TblLangRepositoryTempDtl> getAllMeaningOfSomeWord(String word, Pageable pageable);
}
