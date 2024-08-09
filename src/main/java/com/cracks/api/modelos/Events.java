package com.cracks.api.modelos;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.cracks.api.dtos.EventDto;
import com.cracks.api.dtos.EventPostDto;
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
@Table(name = "Events")
public class Events {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey=@ForeignKey(name="FK_id_Events"))
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
    private boolean notificado=false;

    @Column
    private String picture;

    @Column
    private Integer maxParticipants;

    @Column
    private Boolean enabled=true;

    @Column
    private Boolean visibility=true;

    @Column
    private Boolean approvalRequired;

    @ManyToOne
    private CategoryEvents category;

    @Column
    private String urlShare;


    @Column
    private LocalDateTime registerDate;


    @OneToMany(mappedBy="evento", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<Interest> goals;
   
    @OneToMany(mappedBy="evento", cascade=CascadeType.ALL, orphanRemoval = true)
    private List<Interest> sports;

    // @ManyToMany
    // @JoinTable(name="Interest_events")
    // private List<Goals> goals;

    // @ManyToMany
    // @JoinTable(name="Interest_events")
    // private List<Sports> sports;


    // @Transient
    // private ArrayList<String> goals;

    // @Transient
    // private ArrayList<String> sports;

    public Events(EventPostDto dto){
        title=dto.title;
        this.dateInit           =dto.dateInit;
        this.dateEnd =dto.dateEnd;
        this.maxParticipants =dto.maxParticipants;
        this.visibility =dto.visibility;
        this.enabled =dto.enabled;
        this.approvalRequired =dto.approvalRequired;
        this.urlShare =dto.urlShare;
    }

   
    
  

}
