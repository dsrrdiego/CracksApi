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
@AllArgsConstructor
@Entity
public class Interest_events {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
}
