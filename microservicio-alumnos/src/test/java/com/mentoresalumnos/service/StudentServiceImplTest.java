package com.mentoresalumnos.service;

import com.mentoresalumnos.model.Student;
import com.mentoresalumnos.model.dtos.StudentDTO;
import com.mentoresalumnos.model.dtos.StudentResponse;
import com.mentoresalumnos.persistence.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {
    @Mock
    StudentRepository studentRepository;
    private static final Long STUDENT_ID = 1L;

    private static final String STUDENT_NAME = "Juan";
    private static final String STUDENT_LASTNAME = "Quinteros";
    private static final int STUDENT_AGE = 23;
    private static final String STUDENT_LEVEL = "Intermedio";
    private static final int NUMBER_MENTORS  = 2;
//    private static final List<Mentor> MENTORS = new ArrayList<>();


    private static final Long STUDENT_ID2 = 2L;
    private static final String STUDENT_NAME2 = "Ramiro";
    private static final String STUDENT_LASTNAME2 = "Marra";
    private static final int STUDENT_AGE2 = 19;
    private static final String STUDENT_LEVEL2 = "principiante";
    private static final int NUMBER_MENTORS2  = 3;
//    private static final List<Mentor> MENTORS2 = new ArrayList<>();

    @InjectMocks
    StudentServiceImpl studentService;


    @Test
    public void findAll(){
        when(studentRepository.findAll()).thenReturn(getStudents());

        List<StudentResponse> studentsResponseList = studentService.findAll();

        verify(studentRepository, times(1)).findAll();
        assertEquals(STUDENT_ID, studentsResponseList.get(0).getId());
        assertEquals(STUDENT_NAME, studentsResponseList.get(0).getName());
        assertEquals(STUDENT_LASTNAME, studentsResponseList.get(0).getLastName());
        assertEquals(STUDENT_AGE, studentsResponseList.get(0).getAge());
        assertEquals(STUDENT_LEVEL, studentsResponseList.get(0).getStudentLevel());
        assertEquals(STUDENT_ID2, studentsResponseList.get(1).getId());
        assertEquals(STUDENT_NAME2, studentsResponseList.get(1).getName());
        assertEquals(STUDENT_LASTNAME2, studentsResponseList.get(1).getLastName());
        assertEquals(STUDENT_AGE2, studentsResponseList.get(1).getAge());
        assertEquals(STUDENT_LEVEL2, studentsResponseList.get(1).getStudentLevel());


    }

    @Test
    public void findById(){
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(getStudent(STUDENT_ID, STUDENT_NAME,STUDENT_LASTNAME, STUDENT_AGE, STUDENT_LEVEL, NUMBER_MENTORS)));
        StudentResponse studentResponse = studentService.findById(1L);

        verify(studentRepository, times(1)).findById(anyLong());
        assertEquals(STUDENT_ID, studentResponse.getId());
        assertEquals(STUDENT_NAME, studentResponse.getName());
        assertEquals(STUDENT_LASTNAME, studentResponse.getLastName());
        assertEquals(STUDENT_AGE, studentResponse.getAge());
        assertEquals(STUDENT_LEVEL, studentResponse.getStudentLevel());
    }

    private Student getStudent(Long id, String name, String lastName, int age, String studentLevel, int numberMentors){
        return Student.builder()
                .id(id)
                .name(name)
                .lastName(lastName)
                .age(age)
                .studentLevel(studentLevel)
                .numberMentors(numberMentors)
                .build();

    }

    private StudentDTO getStudentDTO(String name, String lastName, int age, String studentLevel){
        return StudentDTO.builder()
                .name(name)
                .lastName(lastName)
                .age(age)
                .studentLevel(studentLevel)
                .build();
    }

    private List<Student> getStudents(){

        List<Student> students = new ArrayList<>();

        Student student1 = getStudent(STUDENT_ID, STUDENT_NAME, STUDENT_LASTNAME, STUDENT_AGE, STUDENT_LEVEL, NUMBER_MENTORS);
        Student student2 = getStudent(STUDENT_ID2, STUDENT_NAME2, STUDENT_LASTNAME2, STUDENT_AGE2, STUDENT_LEVEL2, NUMBER_MENTORS2);

        students.add(student1);
        students.add(student2);
        return students;
    }

}
