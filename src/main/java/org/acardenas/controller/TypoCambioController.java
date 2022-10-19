package org.acardenas.controller;

import org.acardenas.dto.CambioRequestDTO;
import org.acardenas.model.TipoCambioModel;
import org.acardenas.pojo.ObjectResponse;
import org.acardenas.service.TipoCambioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/exchanges-rate")
@Controller
public class TypoCambioController {

    private final TipoCambioService tipoCambioService;

    @Autowired
    public TypoCambioController(TipoCambioService tipoCambioService){
        this.tipoCambioService = tipoCambioService;
    }

    @PostMapping(path = "/crear",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crear(@RequestBody TipoCambioModel tipoCambioModel){
        return ResponseEntity
                .ok()
                .body(ObjectResponse.builder().data(tipoCambioService.crear(tipoCambioModel)).build());
    }

    @PutMapping(path = "/modificar",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modificar(@RequestBody TipoCambioModel tipoCambioModel){
        return ResponseEntity
                .ok()
                .body(ObjectResponse.builder().data(tipoCambioService.modificar(tipoCambioModel)).build());
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cambioMoneda(@RequestBody CambioRequestDTO cambioRequestDTO){
        return ResponseEntity
                .ok()
                .body(ObjectResponse.builder().data(tipoCambioService.cambio(cambioRequestDTO)).build());
    }

}
