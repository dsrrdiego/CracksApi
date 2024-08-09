package com.cracks.api.controlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cracks.api.auth.JwtTokenProvider;
import com.cracks.api.dtos.LoginDto;
import com.cracks.api.interfaces.TypeLogin;
import com.cracks.api.modelos.User;
import com.cracks.api.modelos.Cities;
import com.cracks.api.modelos.Session;
import com.cracks.api.repos.RepoCities;
import com.cracks.api.repos.RepoSession;
import com.cracks.api.repos.RepoUser;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Autenticación", description = "Controladores encargados de todo lo referente a Registro y Login de usuarios")
public class AuthController {
    @Autowired
    private RepoUser repoUser;/// <repodel user User>

    @Autowired
    private RepoSession repoSession;

    @Autowired
    private RepoCities repoCities;

  

    public String desEncriptar(MultipartFile clave) {
        try {
            Path path = Paths.get("password.enc");
            byte[] claveB = clave.getBytes();
            Files.write(path, claveB);
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "openssl", "pkeyutl", "-decrypt", "-inkey", "private.pem", "-in", "password.enc", "-passin",
                    "pass:diego");
            Process process = processBuilder.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String result = reader.lines().collect(Collectors.joining("\n"));
                return ("Contraseña descifrada: " + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;// ResponseEntity.status(500).body("Error al procesar el archivo");
        }
    }

    public String desEncriptar(String  clave) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("sh","-c","echo -n \""+clave+ "\"|base64 -d |openssl pkeyutl -decrypt -inkey private.pem -passin pass:diego");
            Process process = processBuilder.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String result = reader.lines().collect(Collectors.joining("\n"));
                return ("Contraseña descifrada: " + result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;// ResponseEntity.status(500).body("Error al procesar el archivo");
        }
    }

    @Operation(summary = "Registro de Usuario", description = "Punto de entrada para Registrar un Usuario Nuevo")
    @PostMapping(value = "/registro", consumes = "multipart/form-data")
    public ResponseEntity<HashMap<String,Long>> registro(@RequestParam("name") String name,
            @RequestParam("psw") MultipartFile clave) {

        // if (repoUser.existsByName(name)) {
        //     return new ResponseEntity<String>("El usuario ya existe", HttpStatus.CONFLICT);
        // }

        Cities c = new Cities();
        c.setCode("7500");
        c.setName("3arr");
        repoCities.save(c);

        User u = new User();
        u.setName(name);
        u.setCity(c);
        repoUser.save(u);

        String psw = desEncriptar(clave);
        System.out.println("desencriptado" + psw);

        BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
        psw = crypt.encode(psw);
        Session session = new Session(u, psw);
        session.setTypeLogin(TypeLogin.MANUAL);
        repoSession.save(session);
            HashMap<String,Long> h=new HashMap<String,Long>();
            h.put("id",u.getId());
        return new ResponseEntity<>(h, HttpStatus.OK);
    }

    @Autowired
    private JwtTokenProvider generarToken;

    @Autowired
    private AuthenticationManager authManager;

    @Operation(summary = "Logueo de Usuario", description = "Punto de entrada para el Usuario, aqui recibirá un Token para tener acceso a la API")
    @PostMapping(value = "/login", consumes = "multipart/form-data")
    public ResponseEntity<String> login(@RequestParam("name") String name, @RequestParam("psw") MultipartFile clave) {
        String psw = desEncriptar(clave);
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(name, psw));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = generarToken.generateToken(auth);
        return new ResponseEntity<String>(token, HttpStatus.OK);

    }

    @Operation(summary = "Logueo de Usuario via String", description = "Punto de entrada para el Usuario, aqui recibirá un Token para tener acceso a la API",hidden = true)
    @PostMapping(value = "/login2",consumes = "multipart/form-data")
    public ResponseEntity<String> login2(@RequestBody LoginDto dto) {
        String psw=dto.getPwd().replace(" ","\n");
        System.out.println("vino:\n"+psw);
        psw = desEncriptar(psw);
        System.out.println("desenc: "+psw);
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getName(), psw));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = generarToken.generateToken(auth);
        return new ResponseEntity<String>(token, HttpStatus.OK);

    }

    // @Autowired
    // JwtAuthenticationFilter jwtFiltro;

    // @Operation(summary = "Punto de prueba de acceso ", description = "Ingresar
    // con Token")
    // @GetMapping("/hola")
    // public String hola(HttpServletRequest request) {

    // String token = jwtFiltro.getTokenFromRequest(request);

    // String a = generarToken.getUsername(token);

    // return "Hola " + a;
    // }
    @Operation(hidden=true)
    @GetMapping("/aa")
    public String aa(){
        return "hola";
    }

    @Operation(hidden = true)
    @PostMapping("/encriptar")
    public String encriptar(@RequestParam String clave) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("sh","-c",
                "echo "+ clave + " | openssl pkeyutl -encrypt -inkey public.pem -pubin | base64");
        Process process = processBuilder.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String result = reader.lines().collect(Collectors.joining("\n"));
            System.out.println("hash: \n"+result+"\nDesenc: "+desEncriptar(result)+"\n");
            return result; 
        }
    }
}
