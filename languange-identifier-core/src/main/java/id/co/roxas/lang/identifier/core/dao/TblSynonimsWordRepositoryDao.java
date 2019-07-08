package id.co.roxas.lang.identifier.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.roxas.lang.identifier.core.repository.TblSynonimsWordRepositoryTemp;

public interface TblSynonimsWordRepositoryDao extends JpaRepository<TblSynonimsWordRepositoryTemp, String>{

	
}
