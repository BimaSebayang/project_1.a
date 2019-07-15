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

	 @Query(value = " select a.lang_Id from Tbl_Lang_Repository_Temp a "
	 		      + " order by a.lang_Id desc limit 1 ", 
	 		nativeQuery = true)
	 public String getLastId();
	
	 @Query("select a from TblLangRepositoryTemp a where upper(a.langName) = upper(?1)")
     public List<TblLangRepositoryTemp> getTempDataInfo(String words);
	 
	 @Query("select distinct(a) from TblLangRepositoryTemp a where a not in "
	 		+ " (select b.tblId from TblLangRepositoryTempDtl b)")
	 public List<TblLangRepositoryTemp> getAllTempPage();
	 
	 @Query("select a.langName from TblLangRepositoryTemp a ")
	 public List<String> getWords();
	 
	 @Query(value = "select exists(select 1 from TblLangRepositoryTemp a where a.langId=?1) "
			 , nativeQuery = true)
	 public boolean getExistingData(String id);
	
}
