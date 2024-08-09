package com.cracks.api.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;



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
 
}
