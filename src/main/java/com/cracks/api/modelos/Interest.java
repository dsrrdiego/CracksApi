package com.cracks.api.modelos;



import java.time.LocalDateTime;

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
@Table(name="Interest")
public class Interest {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private OwnerInterest owner;

    @ManyToOne
    private GoalsSports goal_sport_interest;

    @Column
    private LocalDateTime creationData;
}
