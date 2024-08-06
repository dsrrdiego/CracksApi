package com.cracks.api.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
// @AllArgsConstructor
public class UserActivitiesDto {
    Long id;
    String title;

    public UserActivitiesDto(Long id, String title){
        this.id=id;
        this.title=title;
    }
}
