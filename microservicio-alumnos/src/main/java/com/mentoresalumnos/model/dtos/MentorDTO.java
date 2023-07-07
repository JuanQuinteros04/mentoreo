package com.mentoresalumnos.model.dtos;

import com.mentoresalumnos.model.Alumno;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MentorDTO {

    private String nombre;

    private String apellido;

    private Long edad;

    private Alumno alumno;


}
