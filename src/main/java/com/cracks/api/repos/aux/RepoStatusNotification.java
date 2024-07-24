package com.cracks.api.repos.aux;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cracks.api.modelos.aux.StatusNotification;

public interface RepoStatusNotification extends JpaRepository<StatusNotification,Long>{
    
}
