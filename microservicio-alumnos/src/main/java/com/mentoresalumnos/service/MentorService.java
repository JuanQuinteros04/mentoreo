package com.mentoresalumnos.service;

import com.mentoresalumnos.model.dtos.MentorDTO;
import com.mentoresalumnos.model.dtos.MentorResponse;

import java.util.List;

public interface MentorService {

    List<MentorResponse> findAll();

    MentorResponse findById(Long id);

    List<MentorResponse> findByLocationContaining(String location);

    MentorResponse createMentor(MentorDTO mentorDTO);

    void update(Long id, MentorDTO mentorDTO);

    void deleteMentor(Long id);

    void addStudent(Long mentorId,Long studentId);

}
