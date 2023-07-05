package com.mentoresalumnos.service;

import com.mentoresalumnos.model.Alumno;

import java.util.List;

public interface AlumnoService {

    List<Alumno> findAll();

    Alumno findById(Long id);


}
