package com.cracks.api.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cracks.api.modelos.Interest;

public interface RepoInterest extends JpaRepository<Interest,Long>{
    // @Autowired
    // public static final EntityManager entityManager;
    // // @Query("SELECT i FROM Interest i WHERE i.owner.user.id=:idd")
    // String query="SELECT i FROM Interest i WHERE TYPE(i.owner)=InterestUser";
    // TypedQuery<Interest> resp = entityManager.createQuery(query, Interest.class);

    // List<Interest> getGoals(@Param("idd") Long idd);

   
    
}
