package com.cracks.api.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;



@AllArgsConstructor
@Data
public class PushParticipantsDto {
    Long eventId;
    Long userId;
    Long status;
    Long role;
    LocalDateTime dateCreation;
 
}
