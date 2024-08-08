package com.cracks.api.dtos;

import java.time.LocalDateTime;
import java.util.List;

public class EventPostDto {
    public Long user_id;
    public String title;
    public float locationLat;
    public float locationLong; 
    public String location_description;
    public String location_address; 
    public LocalDateTime dateInit;
    public LocalDateTime dateEnd;
    public int maxParticipants;
    public boolean visibility;
    public boolean enabled;
    public boolean approvalRequired;
    public Long category;
    public String urlShare;
    public List<Long> goals;
    public List<Long> sports;
}
