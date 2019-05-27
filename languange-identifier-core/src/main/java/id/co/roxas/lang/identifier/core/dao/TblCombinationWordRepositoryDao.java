package id.co.roxas.lang.identifier.core.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.roxas.lang.identifier.core.repository.TblCombinationWordRepository;
@Repository
public interface TblCombinationWordRepositoryDao extends JpaRepository<TblCombinationWordRepository, String>{

	@Query("select a.combWord from TblCombinationWordRepository a where a.countWord = ?1")
	public List<String> getAllWords(int countWord);
	
	@Query("select a.combWord from TblCombinationWordRepository a where a.countWord = ?1")
	public Page<String> getAllWords(int countWord,Pageable pageable);
}
