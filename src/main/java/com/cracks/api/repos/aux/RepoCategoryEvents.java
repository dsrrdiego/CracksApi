package com.cracks.api.repos.aux;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cracks.api.modelos.aux.CategoryEvents;

public interface RepoCategoryEvents extends JpaRepository<CategoryEvents,Long>{
    
}
