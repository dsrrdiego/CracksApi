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
@Table(name = "EstadisticUser")
public class EstadisticUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Column
    private String relacion;

    @Column
    private String achievementLevel;

    @ManyToOne
    private Goals goal;

    @ManyToOne
    private Sports sport;

    @Column
    private String CommitementLevel;

    @Column
    private String statics;

    @Column
    private LocalDateTime registerDate;

}