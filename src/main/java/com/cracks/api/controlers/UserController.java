package com.cracks.api.controlers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

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

import com.cracks.api.dtos.RegistroDto;
import com.cracks.api.dtos.pushUserInterestDto;
import com.cracks.api.modelos.Cities;
import com.cracks.api.modelos.Interest;
import com.cracks.api.modelos.Session;
import com.cracks.api.modelos.User;
import com.cracks.api.modelos.aux.Coordenadas;
import com.cracks.api.repos.RepoCities;
import com.cracks.api.repos.RepoGoals;
import com.cracks.api.repos.RepoInterest;
import com.cracks.api.repos.RepoSession;
import com.cracks.api.repos.RepoSports;
import com.cracks.api.repos.RepoUser;
import com.cracks.api.repos.aux.RepoCoordenadas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuarios", description = "Todo lo referente a los Usuarios de la Aplicación")
@RestController
public class UserController {
    @Autowired
    private RepoUser repoUser;

    @Autowired
    private RepoSession repoSession;

    @Autowired
    private RepoCities repoCities;

    @Autowired
    private RepoCoordenadas repoCoordenadas;

    @Autowired
    private RepoGoals repoGoals;

    @Autowired
    private RepoSports repoSports;

    @Autowired
    private RepoInterest repoInterest;

    @Operation(summary = "Usuario Por Id", description = "Obtener un usuario puntual con su número de identificación", responses = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/pullUserInformation/{id}")
    public ResponseEntity<Optional<User>> user(@PathVariable Long id) {
        Optional<User> u = repoUser.findById(id);

        return new ResponseEntity<Optional<User>>(u, HttpStatus.OK);

    }

    @PostMapping(value = "/userPicture", consumes = "multipart/form-data")
    public void userPicture(@RequestParam("id") Long id, @RequestParam("picture") MultipartFile picture)
            throws IOException {
        Path path = Paths.get("bd/userPictures/" + id + ".jpg");
        Files.createDirectories(path.getParent());
        byte[] pictureB = picture.getBytes();
        Files.write(path, pictureB);

    }

    @GetMapping("/userPicture/{id}")
    public ResponseEntity<Resource> userPicture(@PathVariable Long id) throws MalformedURLException {
        Path path = Paths.get("bd/userPictures/").resolve(id + ".jpg");
        Resource resource = new UrlResource(path.toUri());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);

    }

    @Operation(summary="Completar datos de Registro")
    @PostMapping("/datosRegistro")
    public ResponseEntity<String> datosRegistros(@RequestBody RegistroDto dto){
        // System.out.println(dto.titl);
        // System.out.println(dto.about);
        User u=repoUser.findById(dto.id).get();
        u.setAbout(dto.about);
        u.setBirthDate(dto.birthDate);
        u.setGender(dto.gender);
        u.setEmail(dto.email);
        u.setPhone(dto.phone);
        try {
            
            Cities c=repoCities.findById(dto.city).get();
            u.setCity(c);
            Coordenadas coord=repoCoordenadas.findById(dto.cordenadas).get();
            u.setCordenadas(coord);
            repoUser.save(u);
            return new ResponseEntity<String>("Registro exitoso para "+u.getName(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error al intentar registrar "+u.getName(),HttpStatus.CONFLICT);

        }

    }
    @Operation(summary = "Agregar los intereses de Objeticos para el Usuario")
    @PostMapping("/setGoals")
    public ResponseEntity<String> setGoals(@RequestBody pushUserInterestDto dto){
        User u=repoUser.findById(dto.userId).get();
        for (Long g : dto.interest) {
            Interest i=new Interest();
            i.setUsuario(u);;
            i.setGoals(repoGoals.findById(g).get());
            i.setRegistro(LocalDateTime.now());
            repoInterest.save(i);
        }
        return new ResponseEntity<String>("Objetivos agregados con exito",HttpStatus.OK);
    }
    @Operation(summary = "Agregar los intereses de Deporte para el Usuario")
    @PostMapping("/setSports")
    public ResponseEntity<String> setSports(@RequestBody pushUserInterestDto dto){
        User u=repoUser.findById(dto.userId).get();
        for (Long g : dto.interest) {
            Interest i=new Interest();
            i.setUsuario(u);;
            i.setSports(repoSports.findById(g).get());
            i.setRegistro(LocalDateTime.now());
            repoInterest.save(i);
        }
        return new ResponseEntity<String>("Objetivos agregados con exito",HttpStatus.OK);
    }

    @Operation(summary = "Session", description = "Trae la session correspondiente inidandole u id de Usuario")
    @GetMapping("/pullSession/{userId}")
    public ResponseEntity<Session> pullSession(@PathVariable Long userId) {
        Session s = repoSession.findByUserId(userId);
        s.setPasswrd(null);
        s.getUsers().setGoals(null);
        s.getUsers().setSports(null);
        return new ResponseEntity<Session>(s, HttpStatus.OK);
    }

}
