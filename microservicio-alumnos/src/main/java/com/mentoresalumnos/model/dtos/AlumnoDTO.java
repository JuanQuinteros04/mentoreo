package com.mentoresalumnos.model.dtos;

import com.mentoresalumnos.model.AlumnoNivel;
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

    private AlumnoNivel alumnoNivel;


}
