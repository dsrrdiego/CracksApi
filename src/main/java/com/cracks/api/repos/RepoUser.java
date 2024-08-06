package com.cracks.api.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cracks.api.dtos.PullGoalsDto;
import com.cracks.api.modelos.GoalsSports;
import com.cracks.api.modelos.Interest;
import com.cracks.api.modelos.User;

public interface RepoUser extends JpaRepository<User,Long>{
    // List<User> findByName(String name);
    boolean existsByName(String name);
    User findByName(String name);
    Optional<User> findById(Long id);

}
