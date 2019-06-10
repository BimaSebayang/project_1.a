package id.co.roxas.user.data.activation.core.dao;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.roxas.user.data.activation.core.repository.TblRole;
import id.co.roxas.user.data.activation.core.repository.TblRoleDtl;
import id.co.roxas.user.data.activation.core.repository.TblUser;

@Repository
public interface TblRoleDtlDao extends JpaRepository<TblRoleDtl, String>{
	
	@Modifying
	@Query(" update TblRoleDtl set  "
		+  " roleId = "
		+  " case "
		+  " when roleDtlName not in ?2 then null "
		+  " when roleDtlName in ?2 then ?1 "
		+  " end, "
		+  " updatedDate= ?3, "
		+  " updatedBy = ?4 "
		+  " where "
		+  " roleId = ?1 ")
	public void updateRoleIdInRoleDetail(TblRole tblRole, List<String> list, Date updatedDate, TblUser updatedBy );
	
	@Modifying
	@Query(" update TblRoleDtl set  "
		+  " roleId = null, "
		+  " updatedDate= ?2, "
		+  " updatedBy = ?3 "
		+  " where "
		+  " roleId = ?1 ")
	public void removeAllDetailId(TblRole tblRole,Date updatedDate, TblUser updatedBy );
	
	@Modifying
	@Query(" update TblRoleDtl set  "
		+  " roleId = ?1, "
		+  " updatedDate= ?2, "
		+  " updatedBy = ?3 "
		+  " where "
		+  " roleDtlId = ?4 ")
	public void updateNullRoleIdBasetlId(TblRole tblRole,Date updatedDate, TblUser updatedBy,String dtlId );
	
	
	@Query("select a from TblRoleDtl a where a.roleDtlName = ?1 ")
	public List<TblRoleDtl> getDtilRoleId(String roleDtlName);
	
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
			                       @Param("updatedBy") TblUser updatedBy, 
			                       @Param("roleDtlId") String roleDtlId);
	
	@Query("select a from TblRoleDtl a left join TblUser tb on"
			+ " a.createdBy = tb "
			+ " left join TblRole tr on "
			+ " a.roleId = tr "
			+ " where "
			+ " ( upper(a.roleDtlName) like  upper(?1) "
			+ "   or "
			+ "   upper(tr.roleName) like upper(?1)"
			+ "   or "
			+ "   a.roleId in (select b.roleId from TblUser b where upper(b.userName) like upper(?1))"
			+ "   or "
			+ "   cast(a.isActive as text) like ?1  "
			+ "   or "
			+ "   upper(tb.userName) like upper(?1) "
			+ " ) "
			+ " and "
			+ " a.roleId not in (select b.roleId from TblUser b where upper(b.userName) = upper(?2))"
			+ " and "
			+ " ( upper( tr.roleName ) = upper(?3) or ?3='')"
			+ " and "
			+ " (  a.roleId in (select b.roleId from TblUser b where upper(b.userName) = upper(?4))  or ?4='' ) "
			+ " and "
			+ " ( ?5 = '' or cast(a.isActive as text) = ?5 ) "
			+ " and "
			+ " ( a.createdDate between ?6 and ?7 ) " )
	public Page<TblRoleDtl> findAllRoleDetailWithCondition(String search,String userName, String roleName,
			String userFilterName, String isActiveFilter,Date startDate, Date endDate, Pageable pageable);
	
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
