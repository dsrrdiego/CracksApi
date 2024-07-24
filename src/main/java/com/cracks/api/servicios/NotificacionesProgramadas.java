package com.cracks.api.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.cracks.api.repos.RepoEvents;

@Service
public class NotificacionesProgramadas {

    @Autowired
    // private RepoEvents repoEvents;

    @Scheduled(fixedRate = 60000)
    public void eventoVencido() {
        // Consultar la base de datos
        // List<MyEntity> entities = myRepository.findEntitiesToProcess();
        // boolean x=repoEvents.vencido();
        boolean x=true;
        if (x){
            System.out.println("Se vencio un evento");
        }
        
        // Ejecutar el método si se cumple la condición
        // for (MyEntity entity : entities) {
        //     if (entity.getExpiryDate().isBefore(LocalDateTime.now())) {
        //         executeMethod(entity);
        //     }
        // }
    }

    // private void executeMethod(MyEntity entity) {
    //     // Lógica para ejecutar el método
    //     System.out.println("Ejecutando método para entidad: " + entity.getId());
    // }
}
