package com.mentoresalumnos.service;

import com.mentoresalumnos.model.dtos.StudentDTO;
import com.mentoresalumnos.model.dtos.StudentResponse;

import java.util.List;

public interface StudentService {

    List<StudentResponse> findAll();

    StudentResponse findById(Long id);

    StudentResponse createAlumno(StudentDTO studentDTO);

    void update(Long id, StudentDTO studentDTO);

    void deleteAlumno(Long id);

    void addMentor(Long alumnoId, Long mentorId);



}
