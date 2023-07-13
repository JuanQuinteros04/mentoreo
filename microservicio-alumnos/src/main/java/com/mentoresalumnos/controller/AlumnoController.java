package com.mentoresalumnos.controller;

import com.mentoresalumnos.model.dtos.AlumnoDTO;
import com.mentoresalumnos.model.dtos.AlumnoResponse;
import com.mentoresalumnos.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<AlumnoResponse> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(alumnoService.findById(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AlumnoResponse> createAlumno(@RequestBody AlumnoDTO alumnoDTO){

        AlumnoResponse alumnoResponse = alumnoService.createAlumno(alumnoDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(alumnoResponse.getId())
                .toUri();

        return ResponseEntity.created(location).body(alumnoResponse);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody AlumnoDTO alumnoDTO){
        alumnoService.update(id, alumnoDTO);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
         alumnoService.deleteAlumno(id);
         return ResponseEntity.status(204).build();
    }

    @PutMapping(value = "/add/{id}/mentor/{idMentor}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addMentor(@PathVariable("id") Long id, @PathVariable("idMentor") Long idMentor ){
        alumnoService.addMentor(id,idMentor);
        return ResponseEntity.status(204).build();
    }
}
