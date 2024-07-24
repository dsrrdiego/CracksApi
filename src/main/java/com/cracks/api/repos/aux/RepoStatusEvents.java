package com.cracks.api.repos.aux;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cracks.api.modelos.aux.StatusEvents;

public interface RepoStatusEvents extends JpaRepository<StatusEvents,Long>{
    
}
