package com.mentoresalumnos.service;

import com.mentoresalumnos.exceptions.NotFoundException;
import com.mentoresalumnos.model.Alumno;
import com.mentoresalumnos.model.Mentor;
import com.mentoresalumnos.model.dtos.MentorDTO;
import com.mentoresalumnos.model.dtos.MentorResponse;
import com.mentoresalumnos.model.mappers.MentorMapper;
import com.mentoresalumnos.persistence.AlumnoRepository;
import com.mentoresalumnos.persistence.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MentorServiceImpl implements MentorService{

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    MentorMapper mentorMapper = MentorMapper.INSTANCE;

    @Override
    public List<MentorResponse> findAll() {

        return mentorRepository.findAll().stream().map(mentor -> mentorMapper.mentorToMentorResponse(mentor))
                .collect(Collectors.toList());
    }

    @Override
    public MentorResponse findById(Long id) {

        return mentorMapper.mentorToMentorResponse(mentorRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public MentorResponse createMentor(MentorDTO mentorDTO, Long alumnoId) {

        Mentor mentor = mentorMapper.mentorDTOToMentor(mentorDTO);
        mentor.setAlumno(alumnoRepository.findById(alumnoId).orElseThrow(NotFoundException::new));

        mentorRepository.save(mentor);

        return mentorMapper.mentorToMentorResponse(mentor);    }

    @Override
    public void update(Long id, MentorDTO mentorDTO) {
        Mentor mentor = mentorRepository.findById(id).orElseThrow(NotFoundException::new);


        if (mentorDTO.getNombre() != null) {
            mentor.setNombre(mentorDTO.getNombre());
        }
        if (mentorDTO.getApellido() != null) {
            mentor.setApellido(mentorDTO.getApellido());
        }
        if (mentorDTO.getEdad() != null) {
            mentor.setEdad(mentorDTO.getEdad());
        }
        if (mentorDTO.getAlumnoId() != null) {
            Alumno alumno = alumnoRepository.findById(mentorDTO.getAlumnoId()).orElseThrow(NotFoundException::new);
            mentor.setAlumno(alumno);
        }
        mentorRepository.save(mentor);
    }

    @Override
    public void deleteMentor(Long id) {
        mentorRepository.delete(mentorRepository.findById(id).orElseThrow(NotFoundException::new));
    }
}
