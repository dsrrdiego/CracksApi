package com.cracks.api.modelos;
    

import java.time.LocalDateTime;

import com.cracks.api.modelos.aux.NameUserScore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "UserScore")
public class UserScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Events event;

    @ManyToOne
    private NameUserScore name;
    
    @Column
    private float score;

    @Column
    private float rangeInit;

    @Column
    private float rangeEnd;

    @Column
    private LocalDateTime registerDate;
}