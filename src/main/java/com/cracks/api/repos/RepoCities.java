package com.cracks.api.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cracks.api.modelos.Cities;

public interface RepoCities extends JpaRepository<Cities,Long>{
    
}
