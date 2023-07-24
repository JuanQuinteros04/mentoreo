package com.mentoresalumnos.controller;

import com.mentoresalumnos.model.dtos.MentorDTO;
import com.mentoresalumnos.model.dtos.MentorResponse;
import com.mentoresalumnos.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RefreshScope
@RequestMapping("/mentors")
@RestController
public class MentorController {

    @Autowired
    private MentorService mentorService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MentorResponse>> findAll(){
        return ResponseEntity.ok(mentorService.findAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MentorResponse> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(mentorService.findById(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MentorResponse> createMentor(@RequestBody MentorDTO mentorDTO){

        MentorResponse mentorResponse = mentorService.createMentor(mentorDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(mentorResponse.getId())
                .toUri();

        return ResponseEntity.created(location).body(mentorResponse);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody MentorDTO mentorDTO){
        mentorService.update(id, mentorDTO);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        mentorService.deleteMentor(id);
        return ResponseEntity.status(204).build();
    }

    @PutMapping(value = "/add/{id}/student/{idStudent}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addStudent(@PathVariable("id") Long id, @PathVariable("idStudent") Long idStudent ){
        mentorService.addStudent(id,idStudent);
        return ResponseEntity.status(204).build();
    }

}
