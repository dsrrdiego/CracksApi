package com.cracks.api.repos;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cracks.api.dtos.UserActivitiesDto;
import com.cracks.api.modelos.Interest;

public interface RepoInterest extends JpaRepository<Interest,Long>{

    @Query("SELECT new com.cracks.api.dtos.UserActivitiesDto(i.id,i.goals.title ) FROM Interest i WHERE i.usuario.id=:id")
    List<UserActivitiesDto> getGoalsFromUser(Long id);
    
    @Query("SELECT new com.cracks.api.dtos.UserActivitiesDto(i.id,i.sports.title ) FROM Interest i WHERE i.usuario.id=:id")
    List<UserActivitiesDto> getSportsFromUser(Long id);

    @Query("SELECT new com.cracks.api.dtos.UserActivitiesDto(i.id,i.goals.title ) FROM Interest i WHERE i.evento.id=:id")
    List<UserActivitiesDto> getGoalsFromEvent(Long id);

    @Query("SELECT new com.cracks.api.dtos.UserActivitiesDto(i.id,i.sports.title ) FROM Interest i WHERE i.evento.id=:id")
    List<UserActivitiesDto> getSportsFromEvent(Long id);
}
