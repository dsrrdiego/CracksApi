package com.cracks.api.modelos;



import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="OwnerInterest")
public class OwnerInterest {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

}