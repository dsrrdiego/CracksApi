package com.cracks.api.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
// @AllArgsConstructor
public class EventActivitiesDto {
    Long id;
    Long eventId;
    String title;

    public EventActivitiesDto(Long id, Long userId, String title){
        this.id=id;
        this.eventId=userId;
        this.title=title;
    }
}
