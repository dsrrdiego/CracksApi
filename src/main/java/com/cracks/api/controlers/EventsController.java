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
import com.cracks.api.dtos.EventPostDto;
import com.cracks.api.modelos.Events;
import com.cracks.api.modelos.Goals;
import com.cracks.api.modelos.Interest;
import com.cracks.api.modelos.Sports;
import com.cracks.api.modelos.User;
import com.cracks.api.modelos.aux.CategoryEvents;
// import com.cracks.api.modelos.Interest;
import com.cracks.api.modelos.aux.Coordenadas;
import com.cracks.api.repos.RepoConfig;
import com.cracks.api.repos.RepoEvents;
import com.cracks.api.repos.RepoGoals;
import com.cracks.api.repos.RepoInterest;
import com.cracks.api.repos.RepoParticipants;
import com.cracks.api.repos.RepoSports;
import com.cracks.api.repos.RepoUser;
import com.cracks.api.repos.aux.RepoCategoryEvents;
import com.cracks.api.repos.aux.RepoCoordenadas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Eventos", description = "Controladores relacionados a los Eventos de la aplicación")
@RestController
public class EventsController {
    @Autowired
    private RepoEvents repoEvents;

    @Autowired
    private RepoUser repoUser;

    @Autowired
    private RepoCategoryEvents repoCategory;

    @Autowired
    private RepoCoordenadas repoCoordenadas;

    @Autowired
    private RepoInterest repoInterest;

    @Autowired
    private RepoConfig repoConfig;

    @Autowired
    private RepoParticipants repoParticipants;

    @Autowired
    private RepoGoals repoGoals;

    @Autowired
    private RepoSports repoSports;

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
            float prioridad = (float) (0.4 * ((float) coincidenciasGoals / (float) userGoalsSize)
                    + 0.6 * ((float) coincidenciasSports / (float) userSportsSize));

            float ponderacion = (float) Double.parseDouble(repoConfig.find("Ponderacion"));
            float prioridad2 = (ponderacion * (float) coincidenciasSports + (float) coincidenciasGoals)
                    / (ponderacion * (float) userSportsSize + (float) userGoalsSize);

            System.out.println("\n** Para evento: " + e.getId());
            System.out.println("Coincide Goals " + coincidenciasGoals + " de " + userGoalsSize);
            System.out.println("Coincide Sports " + coincidenciasSports + " de " + userSportsSize);
            System.out.println("Porcentaje de prioridad: " + prioridad);
            System.out.println("Porcentaje de prioridad2: " + prioridad2);
            EventDto eDto = new EventDto(e, prioridad, prioridad2);

            if (index < (cantidad + 1 * pagina) && index > cantidad * (pagina - 1))
                resultado.add(eDto);

            if (index + 1 == (cantidad + 1 * pagina))
                break;

        }
        Collections.sort(resultado);
        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }

    @Operation(summary = "Evento por Id")
    @GetMapping("/pullEventById/{id}")
    public ResponseEntity<EventDto> events(@PathVariable Long id) {
        Events e = repoEvents.findById(id).get();
        EventDto eDto = new EventDto(e, 1, 1);
        return new ResponseEntity<>(eDto, HttpStatus.OK);
    }

    @Operation(summary = "Evento pasados", description = "Eventos finalizados a los que se vinculó éste usuario.")
    @GetMapping("/pullPassedEventsByUser/{userId}")
    public ResponseEntity<List<EventDto>> pullPassedEventsByUser(@PathVariable Long userId) {
        List<EventDto> respuesta = new ArrayList<>();
        List<Events> eventos = repoParticipants.findPasadosParaParticipant(userId);
        for (Events e : eventos) {
            EventDto eDto = new EventDto(e, 0, 0);
            respuesta.add(eDto);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.OK);

    }

    @PostMapping("/postEvent")
    public ResponseEntity<String> postEvent(@RequestBody EventPostDto e) {
        Events nuevo = new Events(e);
        try {

            User user = repoUser.findById(e.user_id).get();
            nuevo.setUser(user);

            Coordenadas coord = new Coordenadas(e.locationLat, e.locationLong, e.location_description,
                    e.location_address);
            repoCoordenadas.save(coord);
            nuevo.setLocation(coord);

            CategoryEvents cat = repoCategory.findById(e.category).get();
            nuevo.setCategory(cat);

            Events grabado = repoEvents.save(nuevo);

            for (Long gId : e.goals) {
                Goals g = repoGoals.findById(gId).get();
                Interest i = new Interest();
                i.setEvento(grabado);
                i.setGoals(g);
                i.setRegistro(LocalDateTime.now());
                repoInterest.save(i);
            }
            for (Long sId : e.sports) {
                Sports s = repoSports.findById(sId).get();
                Interest i = new Interest();
                i.setEvento(grabado);
                i.setSports(s);
                i.setRegistro(LocalDateTime.now());
                repoInterest.save(i);
            }

            return new ResponseEntity<String>("{'id':" + grabado.getId() + "}", HttpStatus.OK);
        } catch (Exception err) {
            return new ResponseEntity<String>("Error, Existe el usuario? o Nombre de Evento repetido", HttpStatus.OK);

        }

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
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);

    }

}
