package id.co.roxas.user.data.activation.core.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import id.co.roxas.user.data.activation.core.repository.TblRoleDtl;

public interface TblRoleDtlDao extends JpaRepository<TblRoleDtl, String>{
	@Modifying
	@Query("update TblRoleDtl set isActive =:isActive, "
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
			+ " roleDtlId = :roleDtlId")
	public void CrudActivationSwitcherRoleDetail(@Param("isActive") int isActive, 
			                       @Param("dateNonActive") Date dateNonActive,
			                       @Param("dateActive") Date dateActive,
			                       @Param("updatedDate") Date updatedDate, 
			                       @Param("updatedBy") String updatedBy, 
			                       @Param("roleDtlId") String roleDtlId);
	
	@Modifying
	@Query("update TblRoleDtl set isActive =:isActive, "
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
			+ " roleDtlId in :roleDtlId")
	public void CrudActivationSwitcherRoleDetails(@Param("isActive") int isActive, 
			                       @Param("dateNonActive") Date dateNonActive,
			                       @Param("dateActive") Date dateActive,
			                       @Param("updatedDate") Date updatedDate, 
			                       @Param("updatedBy") String updatedBy, 
			                       @Param("roleDtlId") List<String> roleDtlId);
}
