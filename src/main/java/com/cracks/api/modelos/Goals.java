package com.cracks.api.modelos;




import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity

@Inheritance(strategy = InheritanceType.JOINED)

// @Table(name = "Goals")

public class Goals extends GoalsSports{
   
}
