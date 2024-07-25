package com.cracks.api.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cracks.api.modelos.Session;

public interface RepoSession extends JpaRepository<Session,Long>{

    
    Session getPasswrdById(Long id);

    @Query("SELECT s FROM Session s WHERE s.users.id = :id")
    Session findByUserId(@Param("id") Long id);

    @Query("SELECT s.rol FROM Session s WHERE s.users.id=:id")
    String getRole(@Param("id")Long id);
    
}
