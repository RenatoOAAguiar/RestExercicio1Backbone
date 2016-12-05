/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.rest.data;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.rest.model.Cliente;

@ApplicationScoped
public class ClienteRepositorio {

    @Inject
    private EntityManager em;
    
    Logger log;

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
    	em.persist(cliente);
    	}
    	catch(Exception e){
    		log.info(e.getMessage());
    	}
    }
    
    public void removerCliente(Cliente cliente){
    	try{
    	em.remove(cliente);
    	}
    	catch(Exception e){
    		log.info(e.getMessage());
    	}
    }
}
