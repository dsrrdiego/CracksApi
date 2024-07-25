package com.cracks.api.controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.*;

import com.cracks.api.servicios.ConsultaGeneral;

@RestController
public class Servicios {
    
    @Autowired
    private ConsultaGeneral consultaGeneral;

    @Operation(hidden = true)
    @PostMapping("/consulta")
    public List<Object> consulta(@RequestBody String a) {
        return consultaGeneral.consultar(a);
    }

  
}
