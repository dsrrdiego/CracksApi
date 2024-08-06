package com.cracks.api.repos;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cracks.api.dtos.UserActivitiesDto;
import com.cracks.api.modelos.Goals;
import com.cracks.api.modelos.Interest;
import com.cracks.api.modelos.Sports;

public interface RepoInterest extends JpaRepository<Interest,Long>{

    @Query("SELECT new com.cracks.api.dtos.UserActivitiesDto(i.id,i.goals.title ) FROM Interest i WHERE i.usuario.id=:id")
    List<UserActivitiesDto> getGoalsDtoFromUser(Long id);
    
    @Query("SELECT new com.cracks.api.dtos.UserActivitiesDto(i.id,i.sports.title ) FROM Interest i WHERE i.usuario.id=:id")
    List<UserActivitiesDto> getSportsDtoFromUser(Long id);

    @Query("SELECT new com.cracks.api.dtos.UserActivitiesDto(i.id,i.goals.title ) FROM Interest i WHERE i.evento.id=:id")
    List<UserActivitiesDto> getGoalsDtoFromEvent(Long id);

    @Query("SELECT new com.cracks.api.dtos.UserActivitiesDto(i.id,i.sports.title ) FROM Interest i WHERE i.evento.id=:id")
    List<UserActivitiesDto> getSportsDtoFromEvent(Long id);

    @Query("SELECT i.goals FROM Interest i WHERE i.usuario.id=:id")
    List<Goals> getGoalsFromUser(Long id);

    @Query("SELECT i.sports FROM Interest i WHERE i.usuario.id=:id")
    List<Sports> getSportsFromUser(Long id);

    @Query("SELECT i FROM Interest i WHERE i.usuario.id=:id")
    List<Interest> findByUserId(Long userId);
}
