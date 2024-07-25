package com.cracks.api.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class ConsultaGeneral {
    @PersistenceContext
    private EntityManager entityManager;
    @SuppressWarnings("unchecked")
    public List<Object> consultar(String con){
        Query query = entityManager.createNativeQuery(con);
        
        return query.getResultList();
    }

    
}
