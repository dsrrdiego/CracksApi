package com.cracks.api.repos.aux;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cracks.api.modelos.aux.StatusParticipants;

public interface RepoStatusParticipants extends JpaRepository<StatusParticipants,Long>{
    
}
