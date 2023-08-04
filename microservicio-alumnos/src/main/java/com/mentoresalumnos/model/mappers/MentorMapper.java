package com.mentoresalumnos.model.mappers;

import com.mentoresalumnos.model.Mentor;
import com.mentoresalumnos.model.dtos.MentorDTO;
import com.mentoresalumnos.model.dtos.MentorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MentorMapper {

    MentorMapper INSTANCE = Mappers.getMapper(MentorMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "students", ignore = true)
    Mentor mentorDTOToMentor(MentorDTO mentorDTO);


    MentorResponse mentorToMentorResponse(Mentor mentor);
}
