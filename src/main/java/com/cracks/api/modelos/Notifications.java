package com.cracks.api.modelos;

import java.time.LocalDateTime;

import com.cracks.api.interfaces.ActiocNotification;
import com.cracks.api.modelos.aux.StatusNotification;
import com.cracks.api.modelos.aux.TypeNotification;

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
@Table(name = "Notification")
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User userTrget;

    @ManyToOne
    private User userOrigin;

    @ManyToOne
    private Events event;

    @ManyToOne
    private StatusNotification status;

    @ManyToOne
    private TypeNotification type;

    @Column
    private String title;


    private int message;

    @Enumerated(EnumType.STRING)
    private ActiocNotification action;

    @Column
    private boolean automaticDelete=false;

    @Column
    private LocalDateTime visualizationDate;
    
    @Column
    private LocalDateTime registerDate;

}
