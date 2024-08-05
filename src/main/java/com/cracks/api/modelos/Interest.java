package com.cracks.api.modelos;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.cracks.api.dtos.EventDto;
import com.cracks.api.modelos.aux.CategoryEvents;
import com.cracks.api.modelos.aux.Coordenadas;
import com.cracks.api.modelos.aux.StatusEvents;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDateTime date;

    

}
