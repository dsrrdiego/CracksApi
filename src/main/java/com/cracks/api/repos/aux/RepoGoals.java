package com.cracks.api.repos.aux;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cracks.api.dtos.CoindicenciasGoalsDto;
import com.cracks.api.modelos.Goals;
import com.cracks.api.modelos.aux.CategoryGoals;

public interface RepoGoals extends JpaRepository<Goals, Long> {
    @Query("SELECT new com.cracks.api.dtos.CoindicenciasGoalsDto(g.id, g.title) FROM Goals g WHERE g.category=:cat")
    List<CoindicenciasGoalsDto> findByCat(CategoryGoals cat);
}
