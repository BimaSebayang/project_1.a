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
    
    @Query(" select a from TblUser a left join"
    		+ " TblRole b"
    		+ " on "
    		+ " a.roleId = b "
    		+ " where (?1 = '' or cast(a.isActive as text) = ?1 )"
    		+ " and "
    		+ " ( ?2 = '' or a.roleId.roleId = ?2)"
    		+ " and "
    		+ " ( a.createdDate between ?3 and ?4 ) "
    		+ " and "
    		+ " a.userBatch != 'su' "
    		+ " and "
    		+ " a.userId != ?6 "
    		+ " and "
    		+ " ( upper(a.userName) like upper(?5)  or "
    		+ "   cast(a.isActive as text) like ?5 or "
    		+ "   upper(a.userEmail) like upper(?5) or"
    		+ "   upper(b.roleName) like upper(?5) or "
    		+ "   upper(a.userPhone) like upper(?5) or "
    		+ "   upper(a.createdBy.userName) like upper(?5)  ) ")
    Page<TblUser> findAllWithFilterAndSearch(String isActive,String roleId,Date startDate, Date endDate,
    		String search,String ownUser,Pageable pageable);
    
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
 			                       @Param("updatedBy") TblUser updatedBy, 
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
   			                       @Param("updatedBy") TblUser updatedBy, 
   			                       @Param("userId") List<String> userId);
}
