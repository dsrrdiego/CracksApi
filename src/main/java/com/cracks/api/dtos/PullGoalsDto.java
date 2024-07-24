package com.cracks.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PullGoalsDto {
    Long id;
    String title;

    public PullGoalsDto(Long id, String title){
        this.id=id;
        this.title=title;
    }
}
