package com.mentoresalumnos.service;

import com.google.common.base.Verify;
import com.mentoresalumnos.exceptions.NotFoundException;
import com.mentoresalumnos.model.Mentor;
import com.mentoresalumnos.model.Student;
import com.mentoresalumnos.model.dtos.StudentDTO;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {
    @Mock
    StudentRepository studentRepository;

    @Mock
    MentorRepository mentorRepository;
    private static final Long STUDENT_ID = 1L;

    private static final String STUDENT_NAME = "Juan";
    private static final String STUDENT_LASTNAME = "Quinteros";
    private static final int STUDENT_AGE = 23;
    private static final String STUDENT_LEVEL = "Intermedio";
    private static final int NUMBER_MENTORS  = 2;
    private static final List<Mentor> MENTORS = new ArrayList<>();


    private static final Long STUDENT_ID2 = 2L;
    private static final String STUDENT_NAME2 = "Ramiro";
    private static final String STUDENT_LASTNAME2 = "Marra";
    private static final int STUDENT_AGE2 = 19;
    private static final String STUDENT_LEVEL2 = "principiante";
    private static final int NUMBER_MENTORS2  = 3;
    private static final List<Mentor> MENTORS2 = new ArrayList<>();

    private static final Long MENTOR_ID = 1L;

    private static final String MENTOR_NAME = "Francisco";
    private static final String MENTOR_LASTNAME = "Busleiman";
    private static final int MENTOR_AGE = 34;
    private static final int MENTOR_EXPERIENCIE_TIME  = 2;
    private static final String MENTOR_LOCATION  = "Cordoba, Argentina";


    @InjectMocks
    StudentServiceImpl studentService;

    @InjectMocks
    MentorServiceImpl mentorService;


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
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(getStudent(STUDENT_ID, STUDENT_NAME,STUDENT_LASTNAME, STUDENT_AGE, STUDENT_LEVEL, NUMBER_MENTORS, MENTORS)));
        StudentResponse studentResponse = studentService.findById(1L);

        verify(studentRepository, times(1)).findById(anyLong());
        assertEquals(STUDENT_ID, studentResponse.getId());
        assertEquals(STUDENT_NAME, studentResponse.getName());
        assertEquals(STUDENT_LASTNAME, studentResponse.getLastName());
        assertEquals(STUDENT_AGE, studentResponse.getAge());
        assertEquals(STUDENT_LEVEL, studentResponse.getStudentLevel());
    }

    @Test
    public void findByIdNotFound(){
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> studentService.findById(STUDENT_ID));
    }
    @Test
    public void createStudent(){
        when(studentRepository.save(any(Student.class))).thenReturn(getStudent(STUDENT_ID, STUDENT_NAME,STUDENT_LASTNAME, STUDENT_AGE, STUDENT_LEVEL, NUMBER_MENTORS, MENTORS));
        StudentResponse studentResponse = studentService.createAlumno(getStudentDTO(STUDENT_NAME2,STUDENT_LASTNAME2, STUDENT_AGE2, STUDENT_LEVEL2));

        ArgumentCaptor<Student> argumentCaptorStudent = ArgumentCaptor.forClass(Student.class);

        verify(studentRepository, times(1)).save(argumentCaptorStudent.capture());

        Student student = argumentCaptorStudent.getValue();

        assertNull(student.getId());
        assertEquals(STUDENT_NAME2, student.getName());
        assertEquals(STUDENT_LASTNAME2, student.getLastName());
        assertEquals(STUDENT_AGE2, student.getAge());
        assertEquals(STUDENT_LEVEL2, student.getStudentLevel());
    }

    @Test
    void updateStudent(){
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(getStudent(STUDENT_ID, STUDENT_NAME,STUDENT_LASTNAME, STUDENT_AGE, STUDENT_LEVEL, NUMBER_MENTORS, MENTORS)));
        studentService.update(anyLong(), getStudentDTO(STUDENT_NAME2,STUDENT_LASTNAME2, STUDENT_AGE2, STUDENT_LEVEL2));

        ArgumentCaptor<Student> argumentCaptorStudent = ArgumentCaptor.forClass(Student.class);

        verify(studentRepository, times(1)).findById(anyLong());
        verify(studentRepository, times(1)).save(argumentCaptorStudent.capture());

        Student student = argumentCaptorStudent.getValue();

        assertEquals(STUDENT_NAME2, student.getName());
        assertEquals(STUDENT_LASTNAME2, student.getLastName());
        assertEquals(STUDENT_AGE2, student.getAge());
        assertEquals(STUDENT_LEVEL2, student.getStudentLevel());
    }

    @Test
    public void deleteStudent(){
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(getStudent(STUDENT_ID, STUDENT_NAME,STUDENT_LASTNAME, STUDENT_AGE, STUDENT_LEVEL, NUMBER_MENTORS, MENTORS)));
        studentService.deleteAlumno(STUDENT_ID);

        verify(studentRepository, times(1)).findById(anyLong());
        verify(studentRepository, times(1)).delete(any(Student.class));
    }

    @Test
    public void deleteNotFound(){
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, ()-> studentService.deleteAlumno(STUDENT_ID));
    }

    @Test
    public void addMentor(){
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(getStudent(STUDENT_ID, STUDENT_NAME,STUDENT_LASTNAME, STUDENT_AGE, STUDENT_LEVEL, NUMBER_MENTORS, MENTORS)));
        when(mentorRepository.findById(anyLong())).thenReturn(Optional.of(getMentor(MENTOR_ID, MENTOR_NAME, MENTOR_LASTNAME, MENTOR_AGE, MENTOR_EXPERIENCIE_TIME, MENTOR_LOCATION)));
        studentService.addMentor(STUDENT_ID, MENTOR_ID);

        verify(studentRepository, times(1)).findById(anyLong());
        verify(mentorRepository, times(1)).findById(anyLong());

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

        Student student1 = getStudent(STUDENT_ID, STUDENT_NAME, STUDENT_LASTNAME, STUDENT_AGE, STUDENT_LEVEL, NUMBER_MENTORS, MENTORS);
        Student student2 = getStudent(STUDENT_ID2, STUDENT_NAME2, STUDENT_LASTNAME2, STUDENT_AGE2, STUDENT_LEVEL2, NUMBER_MENTORS2, MENTORS2);

        students.add(student1);
        students.add(student2);
        return students;
    }

    private Mentor getMentor(Long id, String name, String lastName, int age, int experienceTime, String location){
        return Mentor.builder()
                .id(id)
                .name(name)
                .lastName(lastName)
                .age(age)
                .experienceTime(experienceTime)
                .location(location)
                .build();
    }

}
