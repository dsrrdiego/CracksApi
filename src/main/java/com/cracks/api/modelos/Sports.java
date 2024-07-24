package com.cracks.api.modelos;

import java.time.LocalDateTime;

import com.cracks.api.modelos.aux.ClimateSports;
import com.cracks.api.modelos.aux.DifficultySports;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true)

public class Sports extends GoalsSports{
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private Long id;

   
    @ManyToOne
    private DifficultySports difficulty;

    @ManyToOne
    private ClimateSports climate;

    @Column
    private String statics;

    @Column
    private String equipament;


}
