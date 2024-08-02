package com.cracks.api.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cracks.api.modelos.OwnerInterestEvent;

public interface RepoOwnerInterestEvents extends JpaRepository<OwnerInterestEvent,Long>{
    
}
