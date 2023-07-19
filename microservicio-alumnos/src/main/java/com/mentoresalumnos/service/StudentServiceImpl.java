package com.mentoresalumnos.service;

import com.mentoresalumnos.exceptions.NotFoundException;
import com.mentoresalumnos.model.Student;
import com.mentoresalumnos.model.Mentor;
import com.mentoresalumnos.model.dtos.StudentDTO;
import com.mentoresalumnos.model.dtos.StudentResponse;
import com.mentorsstudents.model.mappers.StudentMapper;
import com.mentoresalumnos.persistence.StudentRepository;
import com.mentoresalumnos.persistence.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MentorRepository mentorRepository;

    StudentMapper studentMapper = StudentMapper.INSTANCE;

    @Override
    public List<StudentResponse> findAll() {
        return studentRepository.findAll().stream().map(alumno -> studentMapper.studentToStudentResponse(alumno))
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponse findById(Long id) {
        return studentMapper.studentToStudentResponse(studentRepository.findById(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public StudentResponse createAlumno(StudentDTO studentDTO) {
        Student student = studentMapper.studentDTOToStudent(studentDTO);
        studentRepository.save(student);
        return studentMapper.studentToStudentResponse(student);
    }

    @Override
    public void update(Long id, StudentDTO studentDTO) {

        Student student = studentRepository.findById(id).orElseThrow(NotFoundException::new);


        if (studentDTO.getName() != null) {
            student.setName(studentDTO.getName());
        }
        if (studentDTO.getLastName() != null) {
            student.setLastName(studentDTO.getLastName());
        }
        if (studentDTO.getAge() != null) {
            student.setAge(studentDTO.getAge());
        }
        if (studentDTO.getStudentLevel() != null) {
            student.setStudentLevel(studentDTO.getStudentLevel());
        }
        studentRepository.save(student);
    }

    @Override
    public void deleteAlumno(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(NotFoundException::new);
        studentRepository.delete(student);
    }

    @Override
    public void addMentor(Long alumnoId ,Long mentorId) {
        Mentor mentor = mentorRepository.findById(mentorId).orElseThrow(NotFoundException::new);
        Student student = studentRepository.findById(alumnoId).orElseThrow(NotFoundException::new);
        if(student.getMentors().size() < 3){
            student.getMentors().add(mentor);
            student.setNumberMentors(student.getMentors().size());
            studentRepository.save(student);
        }else throw new RuntimeException("El maximo de mentores permitidos es 3. Lo sentimos");
    }
}
