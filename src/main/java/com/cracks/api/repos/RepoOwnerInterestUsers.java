package com.cracks.api.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cracks.api.modelos.OwnerInterestUser;

public interface RepoOwnerInterestUsers extends JpaRepository<OwnerInterestUser,Long>{
    
}
