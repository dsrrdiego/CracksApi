package com.cracks.api.modelos.estadisticas;

import lombok.NoArgsConstructor;


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