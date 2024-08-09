package com.cracks.api.modelos;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.cracks.api.modelos.aux.RoleParticipants;
import com.cracks.api.modelos.aux.StatusParticipants;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Participants", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_id", "event_id"})
})
public class Participants {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Events event;

    @ManyToOne
    private StatusParticipants status;

    @ManyToOne
    private RoleParticipants role;

    @Column
    LocalDateTime dateCreation;



}
