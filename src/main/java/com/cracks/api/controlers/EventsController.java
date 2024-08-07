package com.cracks.api.controlers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cracks.api.dtos.EventDto;
import com.cracks.api.modelos.Events;
import com.cracks.api.modelos.Goals;
import com.cracks.api.modelos.Interest;
import com.cracks.api.modelos.Sports;
// import com.cracks.api.modelos.Interest;
import com.cracks.api.modelos.aux.Coordenadas;
import com.cracks.api.repos.RepoConfig;
import com.cracks.api.repos.RepoEvents;
import com.cracks.api.repos.RepoInterest;
// import com.cracks.api.repos.RepoInterest;
import com.cracks.api.repos.RepoUser;
import com.cracks.api.repos.aux.RepoCategoryEvents;
import com.cracks.api.repos.aux.RepoCoordenadas;
import com.cracks.api.repos.aux.RepoStatusEvents;
import com.cracks.api.servicios.GoalSportsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Tag(name = "Eventos", description = "Controladores relacionados a los Eventos de la aplicación")
@RestController
public class EventsController {
    @Autowired
    private RepoEvents repoEvents;

    // @PersistenceContext
    // private EntityManager entityManager;

    // @Autowired
    // private GoalSportsService goalSportsService;

    @Autowired
    private RepoUser repoUser;

    @Autowired
    private RepoStatusEvents repoStatus;

    @Autowired
    private RepoCategoryEvents repoCategory;

    @Autowired
    private RepoCoordenadas repoCoordenadas;

    @Autowired
    private RepoInterest repoInterest;

    @Autowired
    private RepoConfig repoConfig;
   
    @Operation(summary = "Eventos para un usuario (paginado) ", description = "Trae una lista de eventos acordes al usuario, paginado , comenzado por página 1")
    @GetMapping("/pullEvents/{userId}/{pagina}/{cantidad}")
    public ResponseEntity<List<EventDto>> pullEvents(@PathVariable Long userId, @PathVariable int cantidad,
            @PathVariable int pagina) {
        List<EventDto> resultado = new ArrayList<>();

        List<Goals> uGoals = repoInterest.getGoalsFromUser(userId);
        List<Sports> uSports = repoInterest.getSportsFromUser(userId);

        int userGoalsSize = uGoals.size();
        int userSportsSize = uSports.size();

        List<Events> eventos = repoEvents.findByDate(); 
        int index = 0;
        for (Events e : eventos) {
            index++;
            List<Goals> eGoals = repoInterest.getGoalsFromEvent(e.getId());
            List<Sports> eSports = repoInterest.getSportsFromEvent(e.getId());

            int coincidenciasGoals = 0;
            for (Goals eG : eGoals) {
                if (uGoals.contains(eG)) {
                    coincidenciasGoals++;
                }
            }
            int coincidenciasSports = 0;
            for (Sports eS : eSports) {

                if (uSports.contains(eS)) {
                    coincidenciasSports++;

                }
            }
            double prioridad = 0.4 * ((double) coincidenciasGoals / (double) userGoalsSize)
                    + 0.6 * ((double) coincidenciasSports / (double) userSportsSize);

            double ponderacion=Double.parseDouble(repoConfig.find("Ponderacion"));
            double prioridad2= (ponderacion*(double)coincidenciasSports+(double)coincidenciasGoals)/(ponderacion*(double)userSportsSize+(double)userGoalsSize);
            
            System.out.println("\n** Para evento: " + e.getId());
            System.out.println("Coincide Goals " + coincidenciasGoals + " de " + userGoalsSize);
            System.out.println("Coincide Sports " + coincidenciasSports + " de " + userSportsSize);
            System.out.println("Porcentaje de prioridad: " + prioridad);
            System.out.println("Porcentaje de prioridad2: " + prioridad2);
            EventDto eDto = new EventDto(e, prioridad,prioridad2);

            if (index < (cantidad + 1 * pagina) && index > cantidad * (pagina - 1))
                resultado.add(eDto);

            if (index+1 == (cantidad + 1 * pagina) ) break;


        }
        Collections.sort(resultado);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    // @Operation(summary = "Evento por Id")
    // @GetMapping("/pullEventById/{id}")
    // public ResponseEntity<Events> events(@PathVariable Long id) {
    // Events e = repoEvents.fiXIde(id);
    // e.setGoals(goalSportsService.getEventsGoals(id));
    // e.setSports(goalSportsService.getEventsSports(id));

    // return new ResponseEntity<Events>(e, HttpStatus.OK);
    // }

    @PostMapping("/postEvent")
    public ResponseEntity<String> postEvent(@RequestBody EventDto e) {
        // ObjectMapper om=new ObjectMapper();
        // EventDto e=om.readValue(event,EventDto.class);
        Events nuevo = new Events(e);
        Coordenadas coords = new Coordenadas(e.getLocaltionLat(), e.getLocaltionLon(), e.getLocation_description(),
                e.getLocation_address());
        repoCoordenadas.save(coords);
        nuevo.setLocation(coords);
        nuevo.setUser(repoUser.findById(e.getUser_id()).get());
        nuevo.setStatus(repoStatus.findById(e.getStatus_id()).get());
        nuevo.setCategory(repoCategory.findById(e.getCategory()).get());
        nuevo.setRegisterDate(LocalDateTime.now());
        repoEvents.save(nuevo);
        return new ResponseEntity<String>("{'id':" + nuevo.getId() + "}", HttpStatus.OK);

    }

    @PostMapping(value = "/eventPicture", consumes = "multipart/form-data")
    public void eventPicture(@RequestParam("id") Long id, @RequestParam("picture") MultipartFile picture)
            throws IOException {
        Path path = Paths.get("bd/eventPictures/" + id + ".jpg");
        Files.createDirectories(path.getParent());
        byte[] pictureB = picture.getBytes();
        Files.write(path, pictureB);

        Events e = repoEvents.findById(id).get();
        e.setPicture(path.getFileName().toString());
        System.out.println("\n\n" + path.toString() + "\n\n");
        repoEvents.save(e);

    }

    @GetMapping("/eventPicture/{id}")
    public ResponseEntity<Resource> eventPicture(@PathVariable Long id) throws MalformedURLException {
        Path path = Paths.get("bd/eventPictures/").resolve(id + ".jpg");
        Resource resource = new UrlResource(path.toUri());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG); // Cambia el tipo de contenido

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);

    }

}
