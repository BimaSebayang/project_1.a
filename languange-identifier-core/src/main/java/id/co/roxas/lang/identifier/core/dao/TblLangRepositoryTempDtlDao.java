package id.co.roxas.lang.identifier.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.roxas.lang.identifier.core.repository.TblLangRepositoryTempDtl;

@Repository
public interface TblLangRepositoryTempDtlDao extends JpaRepository<TblLangRepositoryTempDtl, String>{

	@Query("select a from TblLangRepositoryTempDtl a where upper(a.langName) like upper(?1) "
			+ " and a.roleDetail = 'MEANING'")
	public List<TblLangRepositoryTempDtl> getAllMeaningOfSomeWord(String word);
}
