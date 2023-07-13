package com.mentoresalumnos.service;

import com.mentoresalumnos.model.dtos.MentorDTO;
import com.mentoresalumnos.model.dtos.MentorResponse;

import java.util.List;

public interface MentorService {

    List<MentorResponse> findAll();

    MentorResponse findById(Long id);

    MentorResponse createMentor(MentorDTO mentorDTO);

    void update(Long id, MentorDTO mentorDTO);

    void deleteMentor(Long id);

    void addAlumno(Long mentorId,Long alumnoId);

}
