package com.cracks.api.repos.aux;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cracks.api.modelos.Goals;

public interface RepoGoals extends JpaRepository<Goals,Long>{
    
}
