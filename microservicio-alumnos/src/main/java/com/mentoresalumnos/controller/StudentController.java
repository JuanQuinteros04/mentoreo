package com.mentoresalumnos.controller;

import com.mentoresalumnos.model.dtos.StudentDTO;
import com.mentoresalumnos.model.dtos.StudentResponse;
import com.mentoresalumnos.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RefreshScope
@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentResponse>> findAll(){
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentResponse> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(studentService.findById(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentResponse> createAlumno(@RequestBody StudentDTO studentDTO){

        StudentResponse alumnoResponse = studentService.createAlumno(studentDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(alumnoResponse.getId())
                .toUri();

        return ResponseEntity.created(location).body(alumnoResponse);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody StudentDTO studentDTO){
        studentService.update(id, studentDTO);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
         studentService.deleteAlumno(id);
         return ResponseEntity.status(204).build();
    }

    @PutMapping(value = "/add/{id}/mentor/{idMentor}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addMentor(@PathVariable("id") Long id, @PathVariable("idMentor") Long idMentor ){
        studentService.addMentor(id,idMentor);
        return ResponseEntity.status(204).build();
    }
}
