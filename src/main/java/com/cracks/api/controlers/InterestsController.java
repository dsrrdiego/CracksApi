package com.cracks.api.controlers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cracks.api.dtos.PullGoalsDto;
import com.cracks.api.dtos.UserActivitiesDto;
import com.cracks.api.modelos.Goals;
import com.cracks.api.modelos.GoalsSports;
import com.cracks.api.modelos.Interest;
// import com.cracks.api.modelos.Interest;
import com.cracks.api.modelos.aux.CategoryGoals;
import com.cracks.api.repos.RepoInterest;
import com.cracks.api.repos.RepoSports;
import com.cracks.api.repos.RepoUser;
import com.cracks.api.repos.aux.RepoGoals;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.cracks.api.dtos.CoindicenciasGoalsDto;
import com.cracks.api.dtos.EventActivitiesDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Tag(name = "Instereses", description = "Todas las operaciones vinculadas a los Intereses, tanto de Usuarios como de Eventos, tanto de Objetivos como Deportes")
@RestController
public class InterestsController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private RepoGoals repoGoals;
    
    @Autowired
    private RepoSports repoSports;

    @Autowired
    private RepoInterest repoInterest;

    @Operation(summary = "Intereses del usuario", description = "Trae una lista sencilla de todos los Intereses del Usuario")
    @GetMapping("/pullUserActivities/{id}")
    public ResponseEntity<List<UserActivitiesDto>> pullUserActivities(@PathVariable Long id) {
        List<UserActivitiesDto> activities =repoInterest.getGoalsFromUser(id);
        activities.addAll(repoInterest.getSportsFromUser(id));
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @Operation(summary = "Intereses del Evento", description = "Trae una lista sencilla de todos los Intereses del Evento")
    @GetMapping("/pullEventActivities/{id}")
    public ResponseEntity<List<UserActivitiesDto>> pullEventActivities(@PathVariable Long id) {
        List<UserActivitiesDto> activities =repoInterest.getGoalsFromEvent(id);
        activities.addAll(repoInterest.getSportsFromEvent(id));
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    // @Operation(summary = "Coincidencias de Categoria para Objetivo", description = "Trae una lista sencilla de todos los Objetivos cuya Categoría coincida con la categoria de cualquier interes que el usuario tenga")
    // @GetMapping("/pullGoals/{userId}")
    // public ResponseEntity<List<CoindicenciasGoalsDto>> goals(@PathVariable Long id) {
    //     List<UserActivitiesDto> goals =repoInterest.getGoalsFromUser(id);
    //     // List<CoindicenciasGoalsDto> respuesta = new ArrayList<>();
        // String jpql = "SELECT i FROM Interest i WHERE TYPE(i.owner) = OwnerInterestUser AND TYPE(i.goal_sport_interest)=Goals AND i.owner.user.id=:id";
    //     TypedQuery<Interest> query = entityManager.createQuery(jpql, Interest.class);
    //     query.setParameter("id", id);
    //     List<Interest> intereses = query.getResultList();
        // for (Go i : intereses) {
    //         CategoryGoals cat = i.getGoal_sport_interest().getCategory();
    //         List<CoindicenciasGoalsDto> conicidencias = repoGoals.findByCat(cat);
    //         respuesta.addAll(conicidencias);

    //     }
    //     return new ResponseEntity<>(respuesta, HttpStatus.OK);
    // }

    // @Operation(summary = "Coincidencias de Categoria para Deportes", description = "Trae una lista sencilla de todos los Deportes cuya Categoría coincida con la categorias de cualquier interes que el usuario tenga")
    // @GetMapping("/pullSports/{id}")
    // public ResponseEntity<List<CoindicenciasGoalsDto>> sports(@PathVariable Long id) {
    //     List<CoindicenciasGoalsDto> respuesta = new ArrayList<>();
    //     String jpql = "SELECT i FROM Interest i WHERE TYPE(i.owner) = OwnerInterestUser AND TYPE(i.goal_sport_interest)=Sports AND i.owner.user.id=:id";
    //     TypedQuery<Interest> query = entityManager.createQuery(jpql, Interest.class);
    //     query.setParameter("id", id);
    //     List<Interest> intereses = query.getResultList();
    //     for (Interest i : intereses) {
    //         CategoryGoals cat = i.getGoal_sport_interest().getCategory();
    //         List<CoindicenciasGoalsDto> conicidencias = repoSports.findByCat(cat);
    //         respuesta.addAll(conicidencias);

    //     }
    //     return new ResponseEntity<>(respuesta, HttpStatus.OK);
    // }

    // @GetMapping("/eventActivities/{id}")
    // public ResponseEntity<List<String>> eventgoals(@PathVariable Long id) {
    // String jpql = "SELECT i.goal_sport_interest.title FROM Interest i WHERE
    // TYPE(i.owner) = OwnerInterestEvent AND TYPE(i.goal_sport_interest)=Goals AND
    // i.owner.event.id=:id";
    // TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
    // query.setParameter("id", id);
    // return new ResponseEntity<>(query.getResultList(), HttpStatus.OK);
    // }

    // @GetMapping("/eventSports/{id}")
    // public ResponseEntity<List<String>> eventSports(@PathVariable Long id) {
    // String jpql = "SELECT i.goal_sport_interest.title FROM Interest i WHERE
    // TYPE(i.owner) = OwnerInterestEvent AND TYPE(i.goal_sport_interest)=Sports AND
    // i.owner.event.id=:id";
    // TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
    // query.setParameter("id", id);
    // return new ResponseEntity<>(query.getResultList(), HttpStatus.OK);
    // }

}
