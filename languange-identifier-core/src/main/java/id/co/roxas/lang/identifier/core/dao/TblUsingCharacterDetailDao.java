package id.co.roxas.lang.identifier.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.roxas.lang.identifier.core.repository.TblUsingCharacterDetail;

@Repository
public interface TblUsingCharacterDetailDao extends JpaRepository<TblUsingCharacterDetail, String>{

}
