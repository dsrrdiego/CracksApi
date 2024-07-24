package com.cracks.api.repos.aux;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cracks.api.modelos.aux.RoleParticipants;

public interface RepoRoleParticipants extends JpaRepository<RoleParticipants,Long>{
    
}
