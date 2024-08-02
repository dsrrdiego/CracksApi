package com.cracks.api.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cracks.api.modelos.Events;
import com.cracks.api.modelos.User;
import com.cracks.api.repos.RepoEvents;
import com.cracks.api.repos.RepoParticipants;


@Service
public class NotificacionesProgramadas {

    @Autowired
    private RepoEvents repoEvents;

    @Autowired
    private RepoParticipants repoParticipants;

    @Scheduled(fixedRate = 6000)
    public void eventoVencido() {
        // List<Events> eventoVencidos=repoEvents.vencidos();

        // for (Events e : eventoVencidos) {
        //     e.setNotificado(true);
        //     repoEvents.save(e);

        //     System.out.println("\n\n***************"+e.getTitle()+"\n\n");
        //     List<User> users=repoParticipants.usersByEventId(e.getId());
        //     for (User u : users) {
                
        //         System.out.println(u.getName());
                
        //     }
            
        // }
    }

}
