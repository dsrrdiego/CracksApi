package com.cracks.api.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cracks.api.modelos.Notifications;

public interface RepoNotifications extends JpaRepository<Notifications,Long>{
    
}
