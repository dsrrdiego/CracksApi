package com.cracks.api.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cracks.api.dtos.CoindicenciasGoalsDto;
import com.cracks.api.modelos.Sports;
import com.cracks.api.modelos.aux.CategoryGoals;

public interface RepoSports extends JpaRepository<Sports, Long> {
    @Query("SELECT new com.cracks.api.dtos.CoindicenciasGoalsDto(s.id, s.title) FROM Sports s WHERE s.category=:cat")
    List<CoindicenciasGoalsDto> findByCat(CategoryGoals cat);

}
