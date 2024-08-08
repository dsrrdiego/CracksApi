package com.cracks.api.modelos;


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
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Data
@Inheritance(strategy = InheritanceType.JOINED)

// @Table(name = "Goals")

public class GoalsSports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @ManyToOne
    private CategoryGoals category;

    @ManyToOne
    private CommunityGoals community;
}
