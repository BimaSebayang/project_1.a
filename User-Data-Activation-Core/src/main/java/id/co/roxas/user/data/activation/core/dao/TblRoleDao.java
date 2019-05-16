package id.co.roxas.user.data.activation.core.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.roxas.user.data.activation.core.repository.TblRole;

@Repository
public interface TblRoleDao extends JpaRepository<TblRole,String>{
     
	@Query("select a from TblRole a where a.isActive = 1 ")
	public List<TblRole> retrieveAllRoleIsActive();
	
	@Modifying
	@Query("update TblRole set isActive =:isActive, "
			+ " dateNonActive = "
			+ " case "
			+ " when :isActive = 0 then :dateNonActive "
			+ " when :isActive = 1 then dateNonActive "
			+ " end, "
			+ " dateActive = "
			+ " case "
			+ " when :isActive = 0 then dateActive "
			+ " when :isActive = 1 then :dateActive "
			+ " end, "
			+ " updatedDate=:updatedDate, updatedBy = :updatedBy where "
			+ " roleId = :roleId")
	public void CrudActivationSwitcherRole(@Param("isActive") int isActive, 
			                       @Param("dateNonActive") Date dateNonActive,
			                       @Param("dateActive") Date dateActive,
			                       @Param("updatedDate") Date updatedDate, 
			                       @Param("updatedBy") String updatedBy, 
			                       @Param("roleId") String roleId);
	
	@Modifying
	@Query("update TblRole set isActive =:isActive, "
			+ " dateNonActive = "
			+ " case "
			+ " when :isActive = 0 then :dateNonActive "
			+ " when :isActive = 1 then dateNonActive "
			+ " end, "
			+ " dateActive = "
			+ " case "
			+ " when :isActive = 0 then dateActive "
			+ " when :isActive = 1 then :dateActive "
			+ " end, "
			+ " updatedDate=:updatedDate, updatedBy = :updatedBy where "
			+ " roleId in :roleIds")
	public void CrudActivationSwitcherRoles(@Param("isActive") int isActive, 
			                       @Param("dateNonActive") Date dateNonActive,
			                       @Param("dateActive") Date dateActive,
			                       @Param("updatedDate") Date updatedDate, 
			                       @Param("updatedBy") String updatedBy, 
			                       @Param("roleIds") List<String> roleIds);
}
