package com.mentoresalumnos.service;

import com.mentoresalumnos.exceptions.NotFoundException;
import com.mentoresalumnos.model.Student;
import com.mentoresalumnos.model.Mentor;
import com.mentoresalumnos.model.dtos.MentorDTO;
import com.mentoresalumnos.model.dtos.MentorResponse;
import com.mentoresalumnos.model.mappers.MentorMapper;
import com.mentoresalumnos.persistence.StudentRepository;
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
    private StudentRepository studentRepository;

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
    public MentorResponse createMentor(MentorDTO mentorDTO) {
        Mentor mentor = mentorMapper.mentorDTOToMentor(mentorDTO);
        mentorRepository.save(mentor);
        return mentorMapper.mentorToMentorResponse(mentor);
    }

    @Override
    public void update(Long id, MentorDTO mentorDTO) {
        Mentor mentor = mentorRepository.findById(id).orElseThrow(NotFoundException::new);


        if (mentorDTO.getAge() != null) {
            mentor.setName(mentorDTO.getName());
        }
        if (mentorDTO.getLastName() != null) {
            mentor.setLastName(mentorDTO.getLastName());
        }
        if (mentorDTO.getAge() != null) {
            mentor.setAge(mentorDTO.getAge());
        }
        if (mentorDTO.getExperienceTime() != null) {
            mentor.setExperienceTime(mentorDTO.getExperienceTime());
        }
        if (mentorDTO.getLocation() != null) {
            mentor.setLocation(mentorDTO.getLocation());
        }

        mentorRepository.save(mentor);
    }

    @Override
    public void deleteMentor(Long id) {
        mentorRepository.delete(mentorRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public void addStudent(Long mentorId, Long studentId) {
        Mentor mentor = mentorRepository.findById(mentorId).orElseThrow(NotFoundException::new);
        Student student = studentRepository.findById(studentId).orElseThrow(NotFoundException::new);

        if(mentor.getStudents().size() < 3){
            mentor.getStudents().add(student);

            mentorRepository.save(mentor);
        }else throw new RuntimeException("El limite de alumnos es 3. Lo sentimos.");


    }
}
