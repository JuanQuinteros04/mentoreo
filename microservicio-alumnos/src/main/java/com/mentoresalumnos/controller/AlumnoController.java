package com.mentoresalumnos.controller;

import com.mentoresalumnos.model.dtos.AlumnoResponse;
import com.mentoresalumnos.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/alumnos")
@RestController
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AlumnoResponse>> findAll(){
        return ResponseEntity.ok(alumnoService.findAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AlumnoResponse> findById(Long id){
        return ResponseEntity.ok(alumnoService.findById(id));
    }

//    @PostMapping(value = "/crear")
}
