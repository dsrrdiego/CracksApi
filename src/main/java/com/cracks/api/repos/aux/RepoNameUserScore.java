package com.cracks.api.repos.aux;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cracks.api.modelos.aux.NameUserScore;

public interface RepoNameUserScore extends JpaRepository<NameUserScore,Long>{
    
}
