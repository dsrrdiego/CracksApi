package com.cracks.api.repos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cracks.api.modelos.Config;

public interface RepoConfig extends JpaRepository<Config, Long> {
    @Query("SELECT c.valor FROM Config c WHERE c.variable=:variable")
    String find(String variable);


}
