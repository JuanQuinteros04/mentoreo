package com.mentoresalumnos.service;

import com.mentoresalumnos.exceptions.NotFoundException;
import com.mentoresalumnos.model.Alumno;
import com.mentoresalumnos.model.Mentor;
import com.mentoresalumnos.model.dtos.AlumnoDTO;
import com.mentoresalumnos.model.dtos.AlumnoResponse;
import com.mentoresalumnos.model.mappers.AlumnoMapper;
import com.mentoresalumnos.persistence.AlumnoRepository;
import com.mentoresalumnos.persistence.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlumnoServiceImpl implements AlumnoService{

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private MentorRepository mentorRepository;

    AlumnoMapper alumnoMapper = AlumnoMapper.INSTANCE;

    @Override
    public List<AlumnoResponse> findAll() {
        return alumnoRepository.findAll().stream().map(alumno -> alumnoMapper.alumnoToAlumnoResponse(alumno))
                .collect(Collectors.toList());
    }

    @Override
    public AlumnoResponse findById(Long id) {
        return alumnoMapper.alumnoToAlumnoResponse(alumnoRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public AlumnoResponse createAlumno(AlumnoDTO alumnoDTO) {
        Alumno alumno = alumnoMapper.alumnoDTOToAlumno(alumnoDTO);
        alumno.setCantidadMentores(alumno.getMentores().size());
        alumnoRepository.save(alumno);
        return alumnoMapper.alumnoToAlumnoResponse(alumno);
    }

    @Override
    public void update(Long id, AlumnoDTO alumnoDTO) {

        Alumno alumno = alumnoRepository.findById(id).orElseThrow(NotFoundException::new);


        if (alumnoDTO.getNombre() != null) {
            alumno.setNombre(alumnoDTO.getNombre());
        }
        if (alumnoDTO.getApellido() != null) {
            alumno.setApellido(alumnoDTO.getApellido());
        }
        if (alumnoDTO.getEdad() != null) {
            alumno.setEdad(alumnoDTO.getEdad());
        }
        if (alumnoDTO.getAlumnoNivel() != null) {
            alumno.setAlumnoNivel(alumnoDTO.getAlumnoNivel());
        }
        alumnoRepository.save(alumno);
    }

    @Override
    public void deleteAlumno(Long id) {
        Alumno alumno = alumnoRepository.findById(id).orElseThrow(NotFoundException::new);
        alumnoRepository.delete(alumno);
    }

    @Override
    public void addMentor(Long alumnoId ,Long mentorId) {
        Mentor mentor = mentorRepository.findById(mentorId).orElseThrow(NotFoundException::new);
        Alumno alumno = alumnoRepository.findById(alumnoId).orElseThrow(NotFoundException::new);
        alumno.getMentores().add(mentor);
        alumnoRepository.save(alumno);
    }
}
