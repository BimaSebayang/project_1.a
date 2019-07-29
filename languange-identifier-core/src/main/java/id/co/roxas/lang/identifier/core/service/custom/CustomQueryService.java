package id.co.roxas.lang.identifier.core.service.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import id.co.roxas.lang.identifier.core.service.BaseService;

@Service
@Repository
@Transactional
public class CustomQueryService extends BaseService{
	@PersistenceContext
	private EntityManager em;
	

	public void insertQueryStandard(String question, String answer, String userId) {
		
		 JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		
//		String[] ques = queries.split(" ");
//		for (String sp : ques) {
//			if(sp)
//		}
	}
	
	
//	public void insertQuery(List<String> queries, List<String> questionExample, String focusWords, String ) {
//		for (String query : queries) {
//			String 
//			Query query = em.createNativeQuery(sqlString)
//		}
//	}
}
