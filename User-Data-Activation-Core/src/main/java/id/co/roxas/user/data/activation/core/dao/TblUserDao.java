package id.co.roxas.user.data.activation.core.dao;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.roxas.user.data.activation.core.repository.TblUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Repository
public interface TblUserDao extends JpaRepository<TblUser, String>{
     public List<TblUser> findAll();
     
    @Query("select a from TblUser a "
     		+ " where ( a.userTicket.ticketId =:userValidation or a.userEmail =:userValidation "
     		+ " or a.userPhone =:userValidation or a.userId =:userValidation )  and a.roleId.isActive = 1 ")
    public TblUser findByUserTicketOrUserEmailOrUserUserPhoneOrUserId(@Param("userValidation")String userValidation);
    
    @Query(" select a from TblUser a where "
    		//startfiltering
    		+ " case "
    		+ " when :isActive is not null then CAST(a.isActive as integer) =:isActive "
    		+ " else a.isActive = 1 or a.isActive = 0 "
    		+ " end "
    		+ " and "
    		+ " case "
    		+ " when :roleId is not null then a.roleId =:roleId "
    		+ " else a.roleId like '%%' "
    		+ " end ")
    Page<TblUser> findAllWithFilterAndSearch(@Param("isActive") String isActive, @Param("roleId")String roleId, Pageable pageable);
    
    @Modifying
 	@Query("update TblUser set isActive =:isActive, "
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
 			+ " userId = :userId")
 	public void CrudActivationSwitcherUser(@Param("isActive") int isActive, 
 			                       @Param("dateNonActive") Date dateNonActive,
 			                       @Param("dateActive") Date dateActive,
 			                       @Param("updatedDate") Date updatedDate, 
 			                       @Param("updatedBy") String updatedBy, 
 			                       @Param("userId") String userId);
    
    @Modifying
   	@Query("update TblUser set isActive =:isActive, "
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
   			+ " userId in :userId")
   	public void CrudActivationSwitcherUsers(@Param("isActive") int isActive, 
   			                       @Param("dateNonActive") Date dateNonActive,
   			                       @Param("dateActive") Date dateActive,
   			                       @Param("updatedDate") Date updatedDate, 
   			                       @Param("updatedBy") String updatedBy, 
   			                       @Param("userId") List<String> userId);
}
