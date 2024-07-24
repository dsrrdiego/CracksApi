package com.cracks.api.modelos.aux;





import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Coordenadas")
public class Coordenadas {
@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private float latitud;

    @Column
    private float longitud;

    @Column
    private String description;

    @Column
    private String address;

    public Coordenadas(float latitud,float longitud,String description, String address){
        this.latitud=latitud;
        this.longitud=longitud;
        this.description=description;
        this.address=address;
    }
}