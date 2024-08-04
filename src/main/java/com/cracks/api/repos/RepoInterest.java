package com.cracks.api.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cracks.api.modelos.Interest;

public interface RepoInterest extends JpaRepository<Interest, Long> {
    @Query("SELECT i FROM Interest i WHERE i.owner.user.id=:userId")
    List<Interest> findByUserId(Long userId);
    // @Autowired
    // public static final EntityManager entityManager;
    // // @Query("SELECT i FROM Interest i WHERE i.owner.user.id=:idd")
    // String query="SELECT i FROM Interest i WHERE
    // TYPE(i.owner)=OwnerInterestUser";
    // TypedQuery<Interest> resp = entityManager.createQuery(query, Interest.class);

   

    // List<Interest> getGoals(@Param("idd") Long idd);

}
