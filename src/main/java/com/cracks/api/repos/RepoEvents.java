package com.cracks.api.repos;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cracks.api.modelos.Events;

public interface RepoEvents extends JpaRepository<Events,Long>{
    // @Query(value = "SELECT e.* FROM events e WHERE e.date_Init > NOW() ORDER BY id ASC LIMIT :limit OFFSET :offset ", nativeQuery = true)
    // List<Events> findPage(
    //     @Param("limit") int limit, 
    //     @Param("offset") int offset
    // );
    @Query("SELECT e FROM Events e WHERE e.dateInit > NOW() ")
    List<Events> findPage(PageRequest page);

    @Query("SELECT e FROM Events e WHERE e.id=:id")
    Events fiXIde(@Param("id") Long id);

    // @Query("SELECT COUNT(e) > 3 FROM Event e")
    // boolean vencido();
}
