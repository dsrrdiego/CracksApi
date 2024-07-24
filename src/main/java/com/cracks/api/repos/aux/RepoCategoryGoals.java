package com.cracks.api.repos.aux;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cracks.api.modelos.aux.CategoryGoals;

public interface RepoCategoryGoals extends JpaRepository<CategoryGoals,Long> {
    
}
