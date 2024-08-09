package com.cracks.api.modelos;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Interest {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = true)
    @JsonIgnore
    private User usuario;

    
    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = true)
    @JsonIgnore
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
