package com.cracks.api.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cracks.api.modelos.User;

public interface RepoUser extends JpaRepository<User,Long>{
    boolean existsByName(String name);
    User findByName(String name);
    Optional<User> findById(Long id);

}
