package com.mentoresalumnos.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoDTO {

    private String nombre;

    private String apellido;

    private Integer edad;

    private String alumnoNivel;


}
