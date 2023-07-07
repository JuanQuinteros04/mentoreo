package com.mentoresalumnos.model.mappers;

import com.mentoresalumnos.model.Alumno;
import com.mentoresalumnos.model.dtos.AlumnoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AlumnoMapper {

    AlumnoMapper INSTANCE = Mappers.getMapper(AlumnoMapper.class);

    @Mapping(target = "id", ignore = true)
    Alumno alumnoDTOToAlumno(AlumnoDTO alumnoDTO);


    AlumnoDTO alumnoToAlumnoDTO(Alumno alumno);

}
