package com.cracks.api.modelos;


import java.time.LocalDateTime;

import com.cracks.api.modelos.aux.CategoryGoals;
import com.cracks.api.modelos.aux.CommunityGoals;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Setter
@Getter
@Entity

@Inheritance(strategy = InheritanceType.JOINED)

// @Table(name = "Goals")

public class Goals extends GoalsSports{
   
}
