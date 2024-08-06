package com.cracks.api.controlers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cracks.api.modelos.Session;
import com.cracks.api.repos.RepoSession;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Sesión",description = "Todo lo referente a cada sesión de usuario")
@RestController
public class SessionController {

    @Autowired
    private RepoSession repoSession;

    @Operation(summary="Session",description="Trae la session correspondiente inidandole u id de Usuario")
    @GetMapping("/pullSession/{userId}")
    public ResponseEntity<Session> pullSession(@PathVariable Long userId){
        Session s = repoSession.findByUserId(userId);
        s.setPasswrd(null);
        s.getUsers().setGoals(null);
        s.getUsers().setSports(null);
        return new ResponseEntity<Session>(s,HttpStatus.OK)  ;
    }
    
}
