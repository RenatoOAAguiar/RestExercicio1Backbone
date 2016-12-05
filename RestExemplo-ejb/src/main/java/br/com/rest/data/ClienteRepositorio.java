package br.com.rest.data;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

import br.com.rest.model.Cliente;

@ApplicationScoped
public class ClienteRepositorio {

    @Inject
    private EntityManager em;
    
    Logger log;
    
    @Resource
    private UserTransaction userTransaction;

    public Cliente findById(Long id) {
        return em.find(Cliente.class, id);
    }

    public Cliente findByEmail(String nome) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cliente> criteria = cb.createQuery(Cliente.class);
        Root<Cliente> cliente = criteria.from(Cliente.class);
        criteria.select(cliente).where(cb.equal(cliente.get("nome"), nome));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<Cliente> findAllOrderedByName() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cliente> criteria = cb.createQuery(Cliente.class);
        Root<Cliente> cliente = criteria.from(Cliente.class);
        criteria.select(cliente).orderBy(cb.asc(cliente.get("nome")));
        return em.createQuery(criteria).getResultList();
    }
    
    public void insereCliente(Cliente cliente){
    	try{
    	userTransaction.begin();
    	em.persist(cliente);
    	userTransaction.commit();
    	}
    	catch(Exception e){
    		System.out.println(e.getMessage());
    	}
    }
    
    public void removerCliente(Cliente cliente){
    	try{
    	userTransaction.begin();
    	em.remove(cliente);
    	userTransaction.commit();
    	}
    	catch(Exception e){
    		log.info(e.getMessage());
    	}
    }
}
