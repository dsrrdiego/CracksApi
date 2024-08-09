package com.cracks.api.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.cracks.api.servicios.ConsultaGeneral;

@Tag(name = "Servicios", description = "")
@RestController
public class Servicios {
    
    @Autowired
    private ConsultaGeneral consultaGeneral;



    @Operation(hidden = true)
    @PostMapping("/consulta")
    public List<Object> consulta(@RequestBody String a) {
        return consultaGeneral.consultar(a);
    }

    @Operation(summary = "Exportar Bd", description = "Desacargar mediante este endpoint la base de datos a un script SQL")
    @GetMapping("/exportar")
    public ResponseEntity<Resource> exportar(){
        String q="SCRIPT TO 'bd.sql'";
        consultaGeneral.consultar(q);

        Resource resource = new FileSystemResource("bd.sql");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=bd.sql");

     
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);

        
    }

    private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";
    private static final String SERVER_KEY = "TU_SERVER_KEY_DE_FIREBASE";
    @GetMapping("/notificar/{titulo}/{token}")
    public void sendNotification(@PathVariable String deviceToken, @PathVariable String title) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "key=" + SERVER_KEY);
        headers.set("Content-Type", "application/json");
        String message="Hola que tal";
        String body = "{"
            + "\"to\":\"" + deviceToken + "\","
            + "\"notification\":{"
            + "\"title\":\"" + title + "\","
            + "\"body\":\"" + message + "\""
            + "}"
            + "}";

        HttpEntity<String> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(FIREBASE_API_URL, HttpMethod.POST, request, String.class);

        System.out.println("Response: " + response.getBody());
    }
}
