package com.cracks.api.controlers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cracks.api.dtos.PullGoalsDto;
import com.cracks.api.dtos.UserActivitiesDto;
import com.cracks.api.modelos.Interest;
import com.cracks.api.repos.RepoInterest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@RestController
public class InterestsController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/pullUserActivities/{id}")
    public ResponseEntity<List<UserActivitiesDto>> pullUserActivities(@PathVariable Long id) {
         
        String jpql = "SELECT new com.cracks.api.dtos.UserActivitiesDto(i.id, :id,i.goal_sport_interest.title) FROM Interest i WHERE TYPE(i.owner) = InterestUser AND i.owner.user.id=:id";
        TypedQuery<UserActivitiesDto> query = entityManager.createQuery(jpql, UserActivitiesDto.class);
        query.setParameter("id", id);
        return new ResponseEntity<>(query.getResultList(), HttpStatus.OK);
    }

    @GetMapping("/pullEventActivities/{id}")
    public ResponseEntity<List<UserActivitiesDto>> pullEventActivities(@PathVariable Long id) {
         
        String jpql = "SELECT new com.cracks.api.dtos.UserActivitiesDto(i.id, :id,i.goal_sport_interest.title) FROM Interest i WHERE TYPE(i.owner) = InterestEvent AND i.owner.event.id=:id";
        TypedQuery<UserActivitiesDto> query = entityManager.createQuery(jpql, UserActivitiesDto.class);
        query.setParameter("id", id);
        return new ResponseEntity<>(query.getResultList(), HttpStatus.OK);
    }


    @GetMapping("/pullGoals/{id}")
    public ResponseEntity<List<PullGoalsDto>> goals(@PathVariable Long id) {
        String jpql = "SELECT new com.cracks.api.dtos.PullGoalsDto(i.id,i.goal_sport_interest.title) FROM Interest i WHERE TYPE(i.owner) = InterestUser AND TYPE(i.goal_sport_interest)=Goals AND i.owner.user.id=:id";
        TypedQuery<PullGoalsDto> query = entityManager.createQuery(jpql, PullGoalsDto.class);
        query.setParameter("id", id);
        return new ResponseEntity<>(query.getResultList(), HttpStatus.OK);
    }
    @GetMapping("/pullSports/{id}")
    public ResponseEntity<List<PullGoalsDto>> sports(@PathVariable Long id) {
        String jpql = "SELECT new com.cracks.api.dtos.PullGoalsDto(i.id,i.goal_sport_interest.title) FROM Interest i WHERE TYPE(i.owner) = InterestUser AND TYPE(i.goal_sport_interest)=Sports AND i.owner.user.id=:id";
        TypedQuery<PullGoalsDto> query = entityManager.createQuery(jpql, PullGoalsDto.class);
        query.setParameter("id", id);
        return new ResponseEntity<>(query.getResultList(), HttpStatus.OK);
    }

    // @GetMapping("/eventActivities/{id}")
    // public ResponseEntity<List<String>> eventgoals(@PathVariable Long id) {
    //     String jpql = "SELECT i.goal_sport_interest.title FROM Interest i WHERE TYPE(i.owner) = InterestEvent AND TYPE(i.goal_sport_interest)=Goals AND i.owner.event.id=:id";
    //     TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
    //     query.setParameter("id", id);
    //     return new ResponseEntity<>(query.getResultList(), HttpStatus.OK);
    // }

    // @GetMapping("/eventSports/{id}")
    // public ResponseEntity<List<String>> eventSports(@PathVariable Long id) {
    //     String jpql = "SELECT i.goal_sport_interest.title FROM Interest i WHERE TYPE(i.owner) = InterestEvent AND TYPE(i.goal_sport_interest)=Sports AND i.owner.event.id=:id";
    //     TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
    //     query.setParameter("id", id);
    //     return new ResponseEntity<>(query.getResultList(), HttpStatus.OK);
    // }

}
