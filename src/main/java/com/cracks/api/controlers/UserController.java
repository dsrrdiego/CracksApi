package com.cracks.api.controlers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cracks.api.modelos.User;
import com.cracks.api.repos.RepoUser;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Controlador de Usuarios", description = "Todo lo referente a los Usuarios de la Aplicación")
@RestController
public class UserController {
    @Autowired
    private RepoUser repoUser;

    @Operation(summary = "Usuario Por Id", description = "Obtener un usuario puntual con us número de identificación",responses = {
    @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
})
    @GetMapping("/pullUserInformation/{id}")
    public ResponseEntity<Optional<User>> user(@PathVariable Long id){
        Optional<User> u= repoUser.findById(id);

        return new ResponseEntity<Optional<User>>(u,HttpStatus.OK);
        
    }
    
}
