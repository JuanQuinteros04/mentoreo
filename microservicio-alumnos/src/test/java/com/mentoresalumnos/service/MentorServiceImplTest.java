package com.mentoresalumnos.service;

import com.mentoresalumnos.model.Mentor;
import com.mentoresalumnos.model.Student;
import com.mentoresalumnos.model.dtos.MentorDTO;
import com.mentoresalumnos.persistence.MentorRepository;
import com.mentoresalumnos.persistence.StudentRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MentorServiceImplTest {
    @Mock
    MentorRepository mentorRepository;

    @Mock
    StudentRepository studentRepository;

    private static final Long MENTOR_ID = 1L;
    private static final String MENTOR_NAME = "Francisco";
    private static final String MENTOR_LASTNAME = "Busleiman";
    private static final int MENTOR_AGE = 23;
    private static final int MENTOR_EXPERIENCIE_TIME = 10;
    private static final String MENTOR_LOCATION  = "Cordoba, Argentina";
    private static final List<Student> STUDENTS = new ArrayList<>();

    private static final Long MENTOR_ID2 = 1L;
    private static final String MENTOR_NAME2 = "Juan";
    private static final String MENTOR_LASTNAME2 = "Quinteros";
    private static final int MENTOR_AGE2 = 23;
    private static final int MENTOR_EXPERIENCIE_TIME2 = 4;
    private static final String MENTOR_LOCATION2  = "Medellin, Colombia";
    private static final List<Student> STUDENTS2 = new ArrayList<>();

    private static final Long STUDENT_ID2 = 2L;
    private static final String STUDENT_NAME2 = "Ramiro";
    private static final String STUDENT_LASTNAME2 = "Marra";
    private static final int STUDENT_AGE2 = 19;
    private static final String STUDENT_LEVEL2 = "principiante";
    private static final int NUMBER_MENTORS2  = 3;

    @InjectMocks
    MentorServiceImpl mentorService;

    private Mentor getMentor(Long id, String name, String lastName, int age, int experienceTime, String location, List<Student> students){
        return Mentor.builder()
                .id(id)
                .name(name)
                .lastName(lastName)
                .age(age)
                .experienceTime(experienceTime)
                .location(location)
                .students(students)
                .build();
    }

    private MentorDTO getMentorDTO(String name, String lastName, int age, int experienceTime, String location){
        return MentorDTO.builder()
                .name(name)
                .lastName(lastName)
                .age(age)
                .experienceTime(experienceTime)
                .location(location)
                .build();
    }

    private List<Mentor> getMentors(){
        List<Mentor> mentors = new ArrayList<>();

        Mentor mentor1 = getMentor(MENTOR_ID, MENTOR_NAME, MENTOR_LASTNAME, MENTOR_AGE, MENTOR_EXPERIENCIE_TIME, MENTOR_LOCATION, STUDENTS);
        Mentor mentor2 = getMentor(MENTOR_ID2, MENTOR_NAME2, MENTOR_LASTNAME2, MENTOR_AGE2, MENTOR_EXPERIENCIE_TIME2, MENTOR_LOCATION2, STUDENTS2);

        mentors.add(mentor1);
        mentors.add(mentor2);

        return mentors;
    }

    private Student getStudent(Long id, String name, String lastName, int age, String studentLevel, int numberMentors, List<Mentor> mentor){
        return Student.builder()
                .id(id)
                .name(name)
                .lastName(lastName)
                .age(age)
                .studentLevel(studentLevel)
                .numberMentors(numberMentors)
                .mentors(mentor)
                .build();

    }

}
