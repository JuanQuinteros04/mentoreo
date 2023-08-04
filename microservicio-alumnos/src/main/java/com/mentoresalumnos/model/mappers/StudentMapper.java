package com.mentoresalumnos.model.mappers;

import com.mentoresalumnos.model.Student;
import com.mentoresalumnos.model.dtos.StudentDTO;
import com.mentoresalumnos.model.dtos.StudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(target = "id", ignore = true)
    Student studentDTOToStudent(StudentDTO studentDTO);


    StudentResponse studentToStudentResponse(Student student);

}
