package id.co.roxas.lang.identifier.core.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.roxas.lang.identifier.core.repository.TblLangRepositoryTemp;

@Repository
public interface TblLangRepositoryTempDao extends JpaRepository<TblLangRepositoryTemp, String>{

	 @Query("select a from TblLangRepositoryTemp a where upper(a.langName) = upper(?1)")
     public List<TblLangRepositoryTemp> getTempDataInfo(String words);
	 
	 @Query("select distinct(a) from TblLangRepositoryTemp a where a not in "
	 		+ " (select b.tblId from TblLangRepositoryTempDtl b)")
	 public List<TblLangRepositoryTemp> getAllTempPage();
	
}
