package com.cracks.api.modelos;




import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity

@Inheritance(strategy = InheritanceType.JOINED)

// @Table(name = "Goals")

public class Goals extends GoalsSports{
    // @OneToMany(mappedBy = "goals")
    // private List<Interest_users> usuario;
   
}
