package id.co.roxas.lang.identifier.core.service.custom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
@Transactional
public class CustomQueryService {
	@PersistenceContext
	private EntityManager em;
	
	
}
