package com.cracks.api.controlers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cracks.api.dtos.PullGoalsDto;
import com.cracks.api.dtos.SportPostDto;
import com.cracks.api.dtos.UserActivitiesDto;
import com.cracks.api.modelos.Goals;
import com.cracks.api.modelos.GoalsSports;
import com.cracks.api.modelos.Interest;
import com.cracks.api.modelos.Sports;
// import com.cracks.api.modelos.Interest;
import com.cracks.api.modelos.aux.CategoryGoals;
import com.cracks.api.repos.RepoGoals;
import com.cracks.api.repos.RepoInterest;
import com.cracks.api.repos.RepoSports;
import com.cracks.api.repos.RepoUser;
import com.cracks.api.repos.aux.RepoCategoryGoals;
import com.cracks.api.repos.aux.RepoClimateSports;
import com.cracks.api.repos.aux.RepoCommunityGoals;
import com.cracks.api.repos.aux.RepoDifficultySports;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.cracks.api.dtos.CoindicenciasGoalsDto;
import com.cracks.api.dtos.EventActivitiesDto;
import com.cracks.api.dtos.GoalPostDto;

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

    @Autowired
    private RepoCategoryGoals repoCategory;

    @Autowired
    private RepoCommunityGoals repoCommunity;

    @Autowired
    private RepoDifficultySports repoDificulty;

    @Autowired
    private RepoClimateSports repoClimate;

    @Operation(summary = "Intereses del usuario", description = "Trae una lista sencilla de todos los Intereses del Usuario")
    @GetMapping("/pullUserActivities/{id}")
    public ResponseEntity<List<UserActivitiesDto>> pullUserActivities(@PathVariable Long id) {
        List<UserActivitiesDto> activities = repoInterest.getGoalsDtoFromUser(id);
        activities.addAll(repoInterest.getSportsDtoFromUser(id));
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @Operation(summary = "Intereses del Evento", description = "Trae una lista sencilla de todos los Intereses del Evento")
    @GetMapping("/pullEventActivities/{id}")
    public ResponseEntity<List<UserActivitiesDto>> pullEventActivities(@PathVariable Long id) {
        List<UserActivitiesDto> activities = repoInterest.getGoalsDtoFromEvent(id);
        activities.addAll(repoInterest.getSportsDtoFromEvent(id));
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @Operation(summary = "Coincidencias de Categoria para Objetivo", description = "Trae una lista sencilla de todos los Objetivos cuya Categoría coincida con la categoria de cualquier interes que el usuario tenga")
    @GetMapping("/pullGoals/{userId}")
    public ResponseEntity<Set<CoindicenciasGoalsDto>> goals(@PathVariable Long userId) {
        Set<CoindicenciasGoalsDto> coincidencias = new HashSet<CoindicenciasGoalsDto>();
        List<Goals> goals = repoInterest.getGoalsFromUser(userId);
        for (Goals g : goals) {
            CategoryGoals cat = g.getCategory();
            coincidencias.addAll(repoGoals.findByCat(cat));
        }
        for (Goals g : goals) {
            CoindicenciasGoalsDto borrarMe = new CoindicenciasGoalsDto(g.getId(), g.getTitle());
            coincidencias.remove(borrarMe);
        }
        return new ResponseEntity<>(coincidencias, HttpStatus.OK);
    }

    @Operation(summary = "Coincidencias de Categoria para Deportes", description = "Trae una lista sencilla de todos los Deportes cuya Categoría coincida con la categorias de cualquier interes que el usuario tenga")
    @GetMapping("/pullSports/{userId}")
    public ResponseEntity<Set<CoindicenciasGoalsDto>> sports(@PathVariable Long userId) {
        Set<CoindicenciasGoalsDto> coincidencias = new HashSet<>();
        List<Sports> sports = repoInterest.getSportsFromUser(userId);
        for (Sports s : sports) {
            CategoryGoals cat = s.getCategory();
            coincidencias.addAll(repoSports.findByCat(cat));
        }
        for (Sports s : sports) {
            CoindicenciasGoalsDto borrarMe = new CoindicenciasGoalsDto(s.getId(), s.getTitle());
            coincidencias.remove(borrarMe);
        }

        return new ResponseEntity<>(coincidencias, HttpStatus.OK);
    }

    @Operation(summary = "Agregar Goal(Objetivo) ")
    @PostMapping("/goal")
    public ResponseEntity<String> goal(@RequestBody GoalPostDto gDto) {
        Goals g = new Goals();
        g.setTitle(gDto.title);
        try {
            g.setCategory(repoCategory.findById(gDto.category).get());
            g.setCommunity(repoCommunity.findById(gDto.comunity).get());
            repoGoals.save(g);
            return new ResponseEntity<String>("ok", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("mal", HttpStatus.OK);
        }
    }

    @Operation(summary = "Agregar Deporte")
    @PostMapping("/sport")
    public ResponseEntity<String> sport(@RequestBody SportPostDto sDto) {
        Sports s = new Sports();
        s.setTitle(sDto.title);
        s.setStatics(sDto.statics);
        s.setEquipament(sDto.equipament);
        try {
            s.setCategory(repoCategory.findById(sDto.category).get());
            s.setCommunity(repoCommunity.findById(sDto.comunity).get());
            s.setDifficulty(repoDificulty.findById(sDto.dificulty).get());
            s.setClimate(repoClimate.findById(sDto.climate).get());

            repoSports.save(s);
            return new ResponseEntity<String>("ok", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("mal", HttpStatus.OK);
        }
    }

    @Operation(summary = "Lista paginada de todos los Goals (Objtivos) ")
    @GetMapping("/goal/{cantidad}/{pagina}")
    public List<PullGoalsDto> goal(@PathVariable int cantidad,@PathVariable int pagina){
        PageRequest page=PageRequest.of(pagina-1,cantidad);
        return repoGoals.getAllGoals(page);
    }
    @Operation(summary = "Lista paginada de todos los Deportes ")
    @GetMapping("/sport/{cantidad}/{pagina}")
    public List<PullGoalsDto> sport(@PathVariable int cantidad,@PathVariable int pagina){
        PageRequest page=PageRequest.of(pagina-1,cantidad);

        return repoSports.getAllSports(page);
    }



}
