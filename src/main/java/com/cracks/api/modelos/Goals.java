package com.cracks.api.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity

@Inheritance(strategy = InheritanceType.JOINED)


public class Goals extends GoalsSports{
   
}
