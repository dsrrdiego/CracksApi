package com.cracks.api.modelos;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import com.cracks.api.modelos.aux.Coordenadas;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Users")
@Schema(description = "Usuario del sistema")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name;
    
    @Column
    private String about;

    @Schema(description = "Fecha de Nacimiento del usuario")
    @Column
    private LocalDate birthDate;

    @Column
    private String gender;

    @Column
    private String email;

    @Column
    private String phone;

    @ManyToOne
    private Cities city;

    @ManyToOne
    private Coordenadas cordenadas;

    @Column
    private String picture;

    @ManyToMany
    @JoinTable(name="Interest",foreignKey = @ForeignKey(name = "FK_goals_de_User"))
    private List<Goals> goals;

    @ManyToMany
    @JoinTable(name="Interest",foreignKey = @ForeignKey(name = "FK_sports_de_User"))
    private List<Sports> sports;
    
    public User orElseThrow(Object object) {
        throw new UnsupportedOperationException("Unimplemented method 'orElseThrow'");
    }












}
