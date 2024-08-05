package com.cracks.api.modelos;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import com.cracks.api.modelos.aux.Coordenadas;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
// @AllArgsConstructor
@Entity
public class Interest_users {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "goals", nullable = true) // Permitir que goal_id sea null
    private Goals goals;

    @ManyToOne
    @JoinColumn(name = "sports", nullable = true) // Permitir que goal_id sea null
    private Sports sports;

    public Interest_users(Long id, User usuario, Goals goals, Sports sports) {
        System.out.println("aqui");
        this.id = id;
        this.usuario = usuario;
        if (goals != null && sports != null) {
            throw new IllegalArgumentException("Both goal and sport cannot have values at the same time");
        }

        this.goals = goals;
        this.sports = sports;
    }

    public void setGoals(Goals goal) {
        if (goal != null && this.sports != null) {
            throw new IllegalArgumentException("Both goal and sport cannot have values at the same time");
        }
        this.goals = goal;
    }

    public void setSports(Sports sport) {
        if (sport != null && this.goals != null) {
            throw new IllegalArgumentException("Both goal and sport cannot have values at the same time");
        }
        this.sports = sport;
    }











}
