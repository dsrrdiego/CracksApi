package com.cracks.api.repos.estadisticas;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cracks.api.modelos.estadisticas.EstadisticScreen;

public interface RepoEstadisticScreen extends JpaRepository<EstadisticScreen,Long>{
    
}
