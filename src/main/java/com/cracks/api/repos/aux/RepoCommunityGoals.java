package com.cracks.api.repos.aux;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cracks.api.modelos.aux.CommunityGoals;

public interface RepoCommunityGoals extends JpaRepository<CommunityGoals,Long>{
    
}
