package com.cracks.api.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cracks.api.dtos.PullParticipantsDto;
import com.cracks.api.dtos.PushParticipantsDto;
import com.cracks.api.modelos.Participants;
import com.cracks.api.repos.RepoEvents;
import com.cracks.api.repos.RepoParticipants;
import com.cracks.api.repos.RepoUser;
import com.cracks.api.repos.aux.RepoRoleParticipants;
import com.cracks.api.repos.aux.RepoStatusParticipants;
import com.cracks.api.servicios.GoalSportsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Participantes", description = "Todo lo relacionado a inscripciones de eventos")
@RestController
public class ParticipantsController {
    @Autowired
    private RepoParticipants repoParticipants;
    @Autowired
    private GoalSportsService goalSportsService;
    @Autowired
    RepoUser repoUser;
    @Autowired
    RepoEvents repoEvents;
    @Autowired
    RepoStatusParticipants repoStatus;
    @Autowired
    RepoRoleParticipants repoRole;

    @Operation(summary = "Listar Participantes de un evento")
    @GetMapping("/pullParticipantsEventById/{eventId}")
    public ResponseEntity<List<PullParticipantsDto>> pullParticipantsById(@PathVariable Long eventId) {
        return new ResponseEntity<List<PullParticipantsDto>>(repoParticipants.getByEventId(eventId), HttpStatus.OK);
    }

    @PostMapping("/pushParticipant")
    public ResponseEntity<String> pushParticipant(@RequestBody PushParticipantsDto dto) {
        try {

            Participants p = new Participants();
            p.setUser(repoUser.findById(dto.getUserId()).get());
            p.setEvent(repoEvents.findById(dto.getEventId()).get());
            p.setStatus(repoStatus.findById(dto.getStatus()).get());
            p.setRole(repoRole.findById(dto.getRole()).get());
            repoParticipants.save(p);
            return new ResponseEntity<String>("Participante registrado exitosamente",
                    HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<String>("El usuario ya está registrado para este evento.", HttpStatus.CONFLICT);

        } catch (Exception e) {
            return new ResponseEntity<String>("Error inesperado. " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
