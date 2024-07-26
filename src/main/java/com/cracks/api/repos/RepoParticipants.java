package com.cracks.api.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cracks.api.modelos.Participants;
import com.cracks.api.modelos.User;

public interface RepoParticipants extends JpaRepository<Participants,Long>{
    // @Query("SELECT p FROM Participants p WHERE p.user.id=:id")
    @Query("SELECT p FROM Participants p WHERE p.user.id=:id AND p.event.dateInit < NOW()")
    List<Participants> findPasadosById(@Param("id") Long id);

    @Query("SELECT p FROM Participants p WHERE p.user.id=:id")
    List<Participants> findByUserId(Long id);

    @Query("SELECT p FROM Participants p WHERE p.user.id=:userId AND p.event.id=:eventId")
    Participants findByUserAndEvent(Long userId, Long eventId);

    @Query("SELECT p.user FROM Participants p WHERE p.event.id=:id")
    List<User> usersByEventId(@Param("id") Long id);
    
}
