package com.cracks.api.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cracks.api.modelos.InterestEvent;

public interface RepoInterestEvents extends JpaRepository<InterestEvent,Long>{
    
}
