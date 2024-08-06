package com.cracks.api.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import com.cracks.api.modelos.Events;
import com.cracks.api.modelos.Goals;
import com.cracks.api.modelos.Interest;
import com.cracks.api.modelos.Sports;

import lombok.Data;

@Data
public class EventDto implements Comparable<EventDto> {
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
    List<String> goals = new ArrayList<>();
    List<String> sports = new ArrayList<>();
    Double prioridad;

    public EventDto(Events e, double prioridad) {
        user_id = e.getId();
        title = e.getTitle();
        body = e.getDescription();
        if (e.getStatus() != null)
            status_id = e.getStatus().getId();
        if (e.getLocation() != null) {
            localtionLat = e.getLocation().getLatitud();
            localtionLon = e.getLocation().getLongitud();
            location_description = e.getLocation().getDescription();
            location_address = e.getLocation().getAddress();
        }
        dateInit = e.getDateInit();
        dateEnd = e.getDateEnd();
        maxParticipants = e.getMaxParticipantes();
        if (e.getCategory() != null)
            category = e.getCategory().getId();
        urlShare = e.getUrlShare();
        
        for (Interest i : e.getGoals()) {
            if (i.getGoals() != null)
                goals.add(i.getGoals().getTitle());
        }
        for (Interest i : e.getSports()) {
            if (i.getSports() != null)
                sports.add(i.getSports().getTitle());
        }
        this.prioridad = prioridad;
    }

    @Override
    public int compareTo(EventDto e) {
        return (int) (e.getPrioridad() * 100 - this.prioridad * 100);
    }

}