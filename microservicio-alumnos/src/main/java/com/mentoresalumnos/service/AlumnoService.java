package com.mentoresalumnos.service;

import com.mentoresalumnos.model.dtos.AlumnoDTO;
import com.mentoresalumnos.model.dtos.AlumnoResponse;

import java.util.List;

public interface AlumnoService {

    List<AlumnoResponse> findAll();

    AlumnoResponse findById(Long id);

    AlumnoResponse createAlumno(AlumnoDTO alumnoDTO);

    void update(Long id, AlumnoDTO alumnoDTO);

    void deleteAlumno(Long id);

    void addMentor(Long alumnoId, Long mentorId);



}
