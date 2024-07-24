package com.cracks.api.modelos;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.cracks.api.dtos.EventDto;
import com.cracks.api.modelos.aux.CategoryEvents;
import com.cracks.api.modelos.aux.Coordenadas;
import com.cracks.api.modelos.aux.StatusEvents;
import com.cracks.api.repos.RepoUser;
import com.cracks.api.repos.aux.RepoCategoryEvents;
import com.cracks.api.repos.aux.RepoStatusEvents;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Events")
public class Events {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Column
    String title;

    @Column
    String description;

    @ManyToOne
    private StatusEvents status;

    @ManyToOne
    private Coordenadas location;

    @Column
    private LocalDateTime dateInit;

    @Column
    private LocalDateTime dateEnd;

    @Column
    private String picture;

    @Column
    private int maxParticipantes;

    @Column
    private boolean enabled;

    @Column
    private boolean approvalRequired;

    @ManyToOne
    private CategoryEvents category;

    @Column
    private String urlShare;


    @Column
    private LocalDateTime registerDate;


    @Transient
    private ArrayList<String> goals;

    @Transient
    private ArrayList<String> sports;

    public Events(EventDto dto){
        title=dto.getTitle();
        description=dto.getBody();
        dateInit=dto.getDateInit();
        dateEnd=dto.getDateEnd();
        maxParticipantes=dto.getMaxParticipants();
        enabled=true;
        approvalRequired=false;
        urlShare=dto.getUrlShare();
    }
    
  

}
