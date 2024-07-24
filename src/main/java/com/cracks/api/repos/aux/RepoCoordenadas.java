package com.cracks.api.repos.aux;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cracks.api.modelos.aux.Coordenadas;

public interface RepoCoordenadas extends JpaRepository<Coordenadas,Long>{
    
}
