package com.mentoresalumnos.controller;

import com.mentoresalumnos.persistence.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/mentores")
@RestController
public class MentorController {

    @Autowired
    private MentorRepository mentorRepository;

}
