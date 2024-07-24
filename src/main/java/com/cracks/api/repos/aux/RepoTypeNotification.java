package com.cracks.api.repos.aux;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cracks.api.modelos.aux.TypeNotification;

public interface RepoTypeNotification extends JpaRepository<TypeNotification,Long>{
    
}
