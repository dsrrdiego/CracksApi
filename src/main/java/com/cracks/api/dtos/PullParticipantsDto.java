package com.cracks.api.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


// @Setter
// @Getter
// @NoArgsConstructor
@AllArgsConstructor
@Data
public class PullParticipantsDto {
    Long id;
    Long eventId;
    Long userId;
    String userName;
    String status;
    String role;
    LocalDateTime dateCreation;
    // public PullParticipantsDto(Long id, Long userId, Long eventId, Long status, Long role, LocalDateTime dateCreation) {
    //     this.id = id;
    //     this.userId = userId;
    //     this.eventId = eventId;
    //     this.status = status;
    //     this.role = role;
    //     this.dateCreation = dateCreation;
    // }
}
