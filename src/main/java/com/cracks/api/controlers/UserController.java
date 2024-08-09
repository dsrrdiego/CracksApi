package com.cracks.api.controlers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cracks.api.modelos.Session;
import com.cracks.api.modelos.User;
import com.cracks.api.repos.RepoSession;
import com.cracks.api.repos.RepoUser;

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
