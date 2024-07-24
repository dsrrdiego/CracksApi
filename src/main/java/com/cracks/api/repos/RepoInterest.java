package com.cracks.api.repos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cracks.api.modelos.Interest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public interface RepoInterest extends JpaRepository<Interest,Long>{
    // @Autowired
    // public static final EntityManager entityManager;
    // // @Query("SELECT i FROM Interest i WHERE i.owner.user.id=:idd")
    // String query="SELECT i FROM Interest i WHERE TYPE(i.owner)=InterestUser";
    // TypedQuery<Interest> resp = entityManager.createQuery(query, Interest.class);

    // List<Interest> getGoals(@Param("idd") Long idd);

   
    
}
