package com.mentoresalumnos.service;

import com.mentoresalumnos.model.Mentor;
import com.mentoresalumnos.model.Student;
import com.mentoresalumnos.model.dtos.MentorDTO;
import com.mentoresalumnos.model.dtos.MentorResponse;
import com.mentoresalumnos.model.dtos.StudentResponse;
import com.mentoresalumnos.persistence.MentorRepository;
import com.mentoresalumnos.persistence.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MentorServiceImplTest {
    @Mock
    private MentorRepository mentorRepository;

    @Mock
    private StudentRepository studentRepository;

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
    private MentorServiceImpl mentorService;


    @Test
    public void findAll(){
        when(mentorRepository.findAll()).thenReturn(getMentors());
        List<MentorResponse> studentsResponse = mentorService.findAll();

        verify(mentorRepository, times(1)).findAll();
        assertEquals(MENTOR_ID, studentsResponse.get(0).getId());
        assertEquals(MENTOR_NAME, studentsResponse.get(0).getName());
        assertEquals(MENTOR_LASTNAME, studentsResponse.get(0).getLastName());
        assertEquals(MENTOR_AGE, studentsResponse.get(0).getAge());
        assertEquals(MENTOR_EXPERIENCIE_TIME, studentsResponse.get(0).getExperienceTime());
        assertEquals(MENTOR_LOCATION, studentsResponse.get(0).getLocation());

        assertEquals(MENTOR_ID2, studentsResponse.get(1).getId());
        assertEquals(MENTOR_NAME2, studentsResponse.get(1).getName());
        assertEquals(MENTOR_LASTNAME2, studentsResponse.get(1).getLastName());
        assertEquals(MENTOR_AGE2, studentsResponse.get(1).getAge());
        assertEquals(MENTOR_EXPERIENCIE_TIME2, studentsResponse.get(1).getExperienceTime());
        assertEquals(MENTOR_LOCATION2, studentsResponse.get(1).getLocation());
    }

    @Test
    public void findById(){
        when(mentorRepository.findById(anyLong())).thenReturn(Optional.of(getMentor(MENTOR_ID, MENTOR_NAME, MENTOR_LASTNAME, MENTOR_AGE, MENTOR_EXPERIENCIE_TIME, MENTOR_LOCATION, STUDENTS)));
        MentorResponse mentorResponse = mentorService.findById(1L);

        verify(mentorRepository, times(1)).findById(MENTOR_ID);
        assertEquals(MENTOR_ID, mentorResponse.getId());
        assertEquals(MENTOR_NAME, mentorResponse.getName());
        assertEquals(MENTOR_LASTNAME, mentorResponse.getLastName());
        assertEquals(MENTOR_AGE, mentorResponse.getAge());
        assertEquals(MENTOR_EXPERIENCIE_TIME, mentorResponse.getExperienceTime());
        assertEquals(MENTOR_LOCATION, mentorResponse.getLocation());
    }

    @Test
    public void createMentor(){
        when(mentorRepository.save(any(Mentor.class))).thenReturn(getMentor(MENTOR_ID, MENTOR_NAME, MENTOR_LASTNAME, MENTOR_AGE, MENTOR_EXPERIENCIE_TIME, MENTOR_LOCATION, STUDENTS));
        MentorResponse mentorResponse = mentorService.createMentor(getMentorDTO(MENTOR_NAME, MENTOR_LASTNAME, MENTOR_AGE, MENTOR_EXPERIENCIE_TIME, MENTOR_LOCATION));

        ArgumentCaptor<Mentor> mentorArgumentCaptor = ArgumentCaptor.forClass(Mentor.class);

        verify(mentorRepository, times(1)).save(mentorArgumentCaptor.capture());

        Mentor mentor = mentorArgumentCaptor.getValue();

        assertEquals(MENTOR_NAME, mentor.getName());
        assertEquals(MENTOR_LASTNAME, mentor.getLastName());
        assertEquals(MENTOR_AGE, mentor.getAge());
        assertEquals(MENTOR_EXPERIENCIE_TIME, mentor.getExperienceTime());
        assertEquals(MENTOR_LOCATION, mentor.getLocation());
    }

    @Test
    public void updateMentor(){
        when(mentorRepository.findById(anyLong())).thenReturn(Optional.of(getMentor(MENTOR_ID, MENTOR_NAME, MENTOR_LASTNAME, MENTOR_AGE, MENTOR_EXPERIENCIE_TIME, MENTOR_LOCATION, STUDENTS)));
        mentorService.update(2L, getMentorDTO(MENTOR_NAME2, MENTOR_LASTNAME2, MENTOR_AGE2, MENTOR_EXPERIENCIE_TIME2, MENTOR_LOCATION2));

        ArgumentCaptor<Mentor> mentorArgumentCaptor = ArgumentCaptor.forClass(Mentor.class);

        verify(mentorRepository, times(1)).findById(anyLong());
        verify(mentorRepository, times(1)).save(mentorArgumentCaptor.capture());

        Mentor mentor = mentorArgumentCaptor.getValue();

        assertEquals(MENTOR_NAME2, mentor.getName());
        assertEquals(MENTOR_LASTNAME2, mentor.getLastName());
        assertEquals(MENTOR_AGE2, mentor.getAge());
        assertEquals(MENTOR_EXPERIENCIE_TIME2, mentor.getExperienceTime());
        assertEquals(MENTOR_LOCATION2, mentor.getLocation());
    }


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
