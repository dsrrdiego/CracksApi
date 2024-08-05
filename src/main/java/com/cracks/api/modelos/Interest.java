package com.cracks.api.modelos;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class Interest {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = true)
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = true)
    private Events evento;

    @ManyToOne
    @JoinColumn(name = "goals", nullable = true) 
    private Goals goals;

    @ManyToOne
    @JoinColumn(name = "sports", nullable = true) 
    private Sports sports;

    @Column
    private LocalDateTime registro;












}
