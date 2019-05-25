package id.co.roxas.user.data.activation.core.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.roxas.user.data.activation.core.repository.TblRole;

@Repository
public interface TblRoleDao extends JpaRepository<TblRole,String>{
     
	@Query("select a from TblRole a  "
			+ " where a.isActive = 1 ")
	public List<TblRole> retrieveAllRoleIsActive();
	
	
	@Query("select a from TblRole a left join TblUser tb on"
			+ "  a.createdBy = tb where "
			+ "  (upper(a.roleName) like upper(?1) "
			+ "  or "
			+ "  a in (select b.roleId from TblRoleDtl b where upper(b.roleDtlName) like upper(?1)) "
			+ "  or "
			+ "  upper(tb.userName) like upper(?1)"
			+ "  or "
			+ "  cast(a.isActive as text) like ?1 ) "
			+ "  and  "
			+ "  ( a.createdDate between ?2 and ?3 ) "
			+ "  and "
			+ "  ( ?4 in (select e.roleDtlName from TblRoleDtl e where e.roleId = a)  or ?4 ='' ) "
			+ "  and "
			+ "  ( ?5 = '' or cast(a.isActive as text) = ?5 ) ")
	public Page<TblRole> findAllRoleWithCondition(String search,Date startDate, Date endDate, 
			String roleDtlId, String isActive, Pageable pageable);
	
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
