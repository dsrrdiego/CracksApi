package com.cracks.api.repos.estadisticas;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cracks.api.modelos.estadisticas.EstadisticUser;

public interface RepoEstadisticUser extends JpaRepository<EstadisticUser,Long> {
    
}
