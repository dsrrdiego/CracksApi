package com.cracks.api.dtos;

import java.time.LocalDateTime;


import lombok.Data;
@Data
public class EventDto {
    Long user_id;
    String title;
    String body;
    Long status_id;
    Float localtionLat;
    Float localtionLon;
    String location_description;
    String location_address;
    LocalDateTime dateInit;
    LocalDateTime dateEnd;
    int maxParticipants;
    // boolean visibility=false;
    // boolean enabled=false;
    // boolean approvalRequired=false;
    Long category;
    String urlShare;
    

}
