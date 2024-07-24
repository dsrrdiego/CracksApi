package com.cracks.api.modelos.estadisticas;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.cracks.api.modelos.Goals;
import com.cracks.api.modelos.Sports;
import com.cracks.api.modelos.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EstadisticScreen")
public class   EstadisticScreen{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   

}