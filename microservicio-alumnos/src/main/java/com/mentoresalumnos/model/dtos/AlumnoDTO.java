package com.mentoresalumnos.model.dtos;

import com.mentoresalumnos.model.AlumnoNivel;
import com.mentoresalumnos.model.Mentor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoDTO {

    private String nombre;

    private String apellido;

    private Long edad;

    private AlumnoNivel alumnoNivel;

    private Long cantidadMentores;

    private List<Mentor> mentores;


}
